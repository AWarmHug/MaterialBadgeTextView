package sample.badgeview.starry.matrixxun.com.materialbadgetextview;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.matrixxun.starry.badgetextview.MaterialBadgeMenuItem;

public class MainActivity extends AppCompatActivity {

    private boolean showRedIcon = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);

        MaterialBadgeMenuItem menuItemNewFeature = new MaterialBadgeMenuItem.Builder(this)
                .iconDrawable(ContextCompat.getDrawable(this, R.drawable.ic_account_my_order_md))
                .iconTintColor(Color.WHITE)
                .textBackgroundColor(Color.parseColor("#EF4738"))
                .textColor(Color.WHITE)
                .create(menu.findItem(R.id.menu_new_feature));


        MaterialBadgeMenuItem menuItemNotification = new MaterialBadgeMenuItem.Builder(this)
                .iconDrawable(ContextCompat.getDrawable(this, R.drawable.ic_notification_md))
                .iconTintColor(Color.WHITE)
                .textBackgroundColor(Color.parseColor("#36B100"))
                .textColor(Color.WHITE)
                .create(menu.findItem(R.id.menu_notification));
        menuItemNotification.getBadge().setBadgeCount("5");


        MaterialBadgeMenuItem menuItemMessage = new MaterialBadgeMenuItem.Builder(this)
                .iconDrawable(ContextCompat.getDrawable(this, R.drawable.ic_email_md))
                .iconTintColor(Color.WHITE)
                .textBackgroundColor(Color.parseColor("#EF4738"))
                .textColor(Color.WHITE)
                .create(menu.findItem(R.id.menu_message));
        menuItemMessage.getBadge().setBadgeCount(999);


        MaterialBadgeMenuItem menuItemShopCart = new MaterialBadgeMenuItem.Builder(this)
                .iconDrawable(ContextCompat.getDrawable(this, R.drawable.ic_shopping_cart_md))
                .iconTintColor(Color.WHITE)
                .textBackgroundColor(Color.parseColor("#FB8C00"))
                .textColor(Color.WHITE)
                .create(menu.findItem(R.id.menu_shopcart));
        menuItemShopCart.getBadge().setText("New");

        MaterialBadgeMenuItem menuSettings = new MaterialBadgeMenuItem.Builder(this)
                .iconDrawable(ContextCompat.getDrawable(this, R.drawable.ic_vec_setting_d))
                .iconTintColor(Color.WHITE)
                .textBackgroundColor(Color.parseColor("#FB8C00"))
                .textColor(Color.WHITE)
                .create(menu.findItem(R.id.action_settings));
        menuSettings.getBadge().setBadgeCount(10);

        return true;
    }

    private void toggleRedIconInNewFeatureMenu(MenuItem menuItemNewFeature) {
        showRedIcon = !showRedIcon;
        if (showRedIcon) {
            MaterialBadgeMenuItem.getBadge(menuItemNewFeature).setHighLightMode(true);
        } else {
            MaterialBadgeMenuItem.getBadge(menuItemNewFeature).clearHighLightMode();
        }
    }


    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        MenuItem menuItemNewFeature = menu.findItem(R.id.menu_new_feature);
        toggleRedIconInNewFeatureMenu(menuItemNewFeature);
        return super.onPrepareOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Toast.makeText(this, "Click Setting Menu!", Toast.LENGTH_LONG).show();
            return true;
        } else if (id == R.id.menu_message) {
            Toast.makeText(this, "Click Message Menu!", Toast.LENGTH_LONG).show();
            return true;
        } else if (id == R.id.menu_shopcart) {
            Toast.makeText(this, "Click ShopCart Menu!", Toast.LENGTH_LONG).show();
            return true;
        } else if (id == R.id.menu_new_feature) {
            Toast.makeText(this, "Toggle Red Icon in New Feature Menu!", Toast.LENGTH_LONG).show();
            //noinspection RestrictedApi
            invalidateOptionsMenu();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
