package sk.oceliak.promo.ui.base;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

/**
 * Base activity from which other activties should extend
 * we can define global behaviour here
 */
public abstract class BaseFragmentActivity extends AppCompatActivity {

    private ViewDataBinding mBinding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, getViewId());
        setContentView(mBinding.getRoot());
        FragmentManager manager = getSupportFragmentManager();

        if(savedInstanceState == null){
            manager.beginTransaction().replace(getFragmentResId(), createFragment()).commit();
        }
    }

    protected abstract @LayoutRes int getViewId();

    protected abstract @IdRes int getFragmentResId();

    @NonNull
    protected abstract Fragment createFragment();
}
