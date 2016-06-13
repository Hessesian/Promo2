package sk.oceliak.promo.ui.screens.orders;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;

import sk.oceliak.promo.R;
import sk.oceliak.promo.ui.base.BaseFragmentActivity;

/**
 * Orders activity on screen
 */
public class OrdersFragmentActivity extends BaseFragmentActivity {


    @Override
    protected int getViewId() {
        return R.layout.activity_order;
    }

    @Override
    protected int getFragmentResId() {
        return R.id.activity_fragment;
    }

    @NonNull
    @Override
    protected Fragment createFragment() {
        Fragment fragment = Fragment.instantiate(this, OrdersFragment.class.getName());

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            Bundle b = new Bundle(extras);
            fragment.setArguments(b);
        }
        return fragment;
    }
}
