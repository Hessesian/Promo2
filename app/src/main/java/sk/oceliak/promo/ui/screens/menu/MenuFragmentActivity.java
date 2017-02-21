package sk.oceliak.promo.ui.screens.menu;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.Menu;
import android.view.MenuItem;

import it.cosenonjaviste.mv2m.ArgumentManager;
import sk.oceliak.promo.R;
import sk.oceliak.promo.model.api.models.Contact;
import sk.oceliak.promo.model.api.models.Item;
import sk.oceliak.promo.ui.base.BaseFragmentActivity;
import sk.oceliak.promo.ui.screens.newcontact.NewContactFragmentActivity;
import sk.oceliak.promo.ui.screens.orders.OrdersFragmentActivity;

public class MenuFragmentActivity extends BaseFragmentActivity {

    private static final String CONTACT = "key_contact";

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.action_add:
                Intent intent = new Intent(this, NewContactFragmentActivity.class);
                startActivity(intent);
                return true;
            case R.id.action_settings:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @NonNull
    @Override
    protected int getViewId() {
        return R.layout.activity_menu;
    }

    @NonNull
    @Override
    protected int getFragmentResId() {
        return R.id.activity_fragment;
    }

    @NonNull
    @Override
    protected Fragment createFragment() {
        return Fragment.instantiate(this, MenuFragment.class.getName());
    }

    public void openItem(Item item) {
        Intent intent = new Intent(this, OrdersFragmentActivity.class);
        ArgumentManager.writeArgument(intent, item);
        startActivity(intent);
    }
}
