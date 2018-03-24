package com.matrixxun.starry.badgetextview;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.Toolbar;
import android.view.Display;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import static android.util.TypedValue.COMPLEX_UNIT_DIP;
import static android.util.TypedValue.applyDimension;

/**
 * @author donghua.xdh
 */
public class MaterialBadgeMenuItem {

    private MaterialBadgeTextView badge;
    private ImageView icon;

    public static class Builder {
        Activity activity;
        int textBackgroundColor; // TRANSPARENT = 0;
        int textColor; // TRANSPARENT = 0;
        int iconTintColor; // TRANSPARENT = 0;
        Drawable iconDrawable;
        Toolbar.OnMenuItemClickListener onMenuItemClickListener;

        public Builder(Activity activity) {
            this.activity = activity;
        }

        public Builder textBackgroundColor(int textBackgroundColor) {
            this.textBackgroundColor = textBackgroundColor;
            return this;
        }

        public Builder textColor(int textColor) {
            this.textColor = textColor;
            return this;
        }

        public Builder iconTintColor(int iconTintColor) {
            this.iconTintColor = iconTintColor;
            return this;
        }

        public Builder iconDrawable(Drawable iconDrawable) {
            this.iconDrawable = iconDrawable;
            return this;
        }

        public Builder setOnMenuItemClickListener(Toolbar.OnMenuItemClickListener onMenuItemClickListener) {
            this.onMenuItemClickListener = onMenuItemClickListener;
            return this;
        }

        public MaterialBadgeMenuItem create(MenuItem item) {
            return new MaterialBadgeMenuItem(item, this);
        }
    }

    private MaterialBadgeMenuItem(final MenuItem item, @NonNull final MaterialBadgeMenuItem.Builder builder) {
        if (item == null) return;
        item.setActionView(R.layout.menu_action_item_badge);
        FrameLayout view;

        view = (FrameLayout) item.getActionView();
        TextView title = (TextView) view.findViewById(R.id.menu_title);
        badge = (MaterialBadgeTextView) view.findViewById(R.id.menu_badge);
        icon = (ImageView) view.findViewById(R.id.menu_badge_icon);

        //Display icon in ImageView
        if (icon != null) {
            if (builder.iconDrawable != null) {
                title.setVisibility(View.GONE);
                icon.setImageDrawable(builder.iconDrawable);
                if (builder.iconTintColor != Color.TRANSPARENT) {
                    icon.setColorFilter(builder.iconTintColor);
                }
            } else {
                icon.setVisibility(View.GONE);
                title.setText(item.getTitle());
            }
        }

        if (badge != null && builder.textBackgroundColor != Color.TRANSPARENT) {
            badge.setBackgroundColor(builder.textBackgroundColor);
        }

        if (badge != null && builder.textColor != Color.TRANSPARENT) {
            badge.setTextColor(builder.textColor);
        }


        //Bind onOptionsItemSelected to the activity
        if (builder.activity != null) {
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    boolean consumed = false;
                    if (builder.onMenuItemClickListener != null) {
                        consumed = builder.onMenuItemClickListener.onMenuItemClick(item);
                    }
                    if (!consumed) {
                        builder.activity.onMenuItemSelected(Window.FEATURE_OPTIONS_PANEL, item);
                    }
                }
            });

            view.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    Display display = builder.activity.getWindowManager().getDefaultDisplay();
                    Point size = new Point();
                    display.getSize(size);
                    int width = size.x;
                    Toast toast = Toast.makeText(builder.activity, item.getTitle(), Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.TOP, width / 5, convertDpToPx(builder.activity, 48));
                    toast.show();
                    return true;
                }
            });
        }

        item.setVisible(true);
    }

    public MaterialBadgeTextView getBadge() {
        return badge;
    }

    public ImageView getIcon(){
        return icon;
    }

    public static MaterialBadgeTextView getBadge(MenuItem menu) {
        if (menu == null) {
            return null;
        }
        FrameLayout badge = (FrameLayout) menu.getActionView();
        MaterialBadgeTextView badgeView = (MaterialBadgeTextView) badge.findViewById(R.id.menu_badge);
        return badgeView;
    }

    /**
     * hide the given menu item
     *
     * @param menu
     */
    public void hide(MenuItem menu) {
        menu.setVisible(false);
    }


    public int convertDpToPx(Context context, float dp) {
        return (int) applyDimension(COMPLEX_UNIT_DIP, dp, context.getResources().getDisplayMetrics());
    }

    public void setBackgroundCompat(View v, Drawable d) {
        if (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.JELLY_BEAN) {
            v.setBackgroundDrawable(d);
        } else {
            v.setBackground(d);
        }
    }

    public static String formatNumber(int value, boolean limitLength) {
        if (value < 0) {
            return "-" + formatNumber(-value, limitLength);
        } else if (value < 100) {
            return Long.toString(value);
        } else {
            return "99+";
        }

    }
}
