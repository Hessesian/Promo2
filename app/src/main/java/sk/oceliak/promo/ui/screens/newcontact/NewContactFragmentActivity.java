package sk.oceliak.promo.ui.screens.newcontact;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.widget.Toast;

import retrofit2.Response;
import sk.oceliak.promo.Flags;
import sk.oceliak.promo.R;
import sk.oceliak.promo.ui.base.BaseFragmentActivity;

/**
 * TODO CLASS_DESCRIPTION
 */
public class NewContactFragmentActivity extends BaseFragmentActivity {
    @NonNull
    @Override
    protected int getViewId() {
        return R.layout.activity_newcontact;
    }

    @NonNull
    @Override
    protected int getFragmentResId() {
        return R.id.activity_fragment;
    }

    @NonNull
    @Override
    protected Fragment createFragment() {
        return Fragment.instantiate(this, NewContactFragment.class.getName());
    }

    public void onSuccess(Response body){
        if(body.isSuccessful()){
            Toast.makeText(this.getApplicationContext(),getString(R.string.ok),Toast.LENGTH_LONG).show();
            Flags.FLAG_CONTACT_ADDED = true;
            finish();
        } else {
            Toast.makeText(this.getApplicationContext(),body.message(),Toast.LENGTH_LONG).show();
            finish();
        }
    }

    public void onFail(Throwable body) {
            Toast.makeText(this.getApplicationContext(), body.getMessage(), Toast.LENGTH_LONG).show();
            finish();
    }
}
