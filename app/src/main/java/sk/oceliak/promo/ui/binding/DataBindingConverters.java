package sk.oceliak.promo.ui.binding;

import android.databinding.BindingAdapter;
import android.databinding.ObservableInt;
import android.databinding.adapters.ListenerUtil;
import android.support.v4.util.Pair;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import sk.oceliak.promo.R;
import sk.oceliak.promo.ui.utils.ObsField;
import sk.oceliak.promo.ui.utils.ObservableBool;
import sk.oceliak.promo.ui.utils.ObservableString;
import sk.oceliak.promo.ui.utils.TextWatcherAdapter;

/**
 * Converters for databiding
 */
public class DataBindingConverters {

    @BindingAdapter({"app:imageUrl"})
    public static void loadImage(ImageView view, String url) {
        if (!TextUtils.isEmpty(url)) {
            Picasso.with(view.getContext()).load(url).placeholder(R.drawable.ic_mood_black_24dp).into(view);
        } else {
            view.setImageDrawable(null);
        }
    }

    @BindingAdapter("android:text")
    public static void bindEditText(EditText view,
                                    final ObservableString observableString) {

        Pair pair = (Pair) view.getTag(R.id.binded);

        if (pair == null || pair.first != observableString) {
            if (pair != null) {
                view.removeTextChangedListener((TextWatcher) pair.second);
            }
            TextWatcherAdapter watcher;
            watcher = new TextWatcherAdapter() {
                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    observableString.set(s.toString());
                }
            };
            view.setTag(R.id.binded, new Pair<>(observableString, watcher));
            view.addTextChangedListener(watcher);
        }

        String newValue = observableString.get();
        if (!view.getText().toString().equals(newValue)) {
            view.setText(newValue);
        }
    }


    /**
     * Translates string reference into text for view
     *
     * @param view
     * @param errorResId
     */
    @BindingAdapter({"error"})
    public static void bindValidationError(EditText view, ObservableInt errorResId) {
        if(errorResId.get() != 0){
            view.setError(view.getResources().getString(errorResId.get()));
        }
    }


    /**
     * Binds focus listener to edit text, which updates observable object that is monitored in viewModel
     *
     * @param view  Edit text that user interacts with
     * @param focus Custom class holding focus boolean
     */

    @BindingAdapter("focus")
    public static void bindFocusToEditText(EditText view,
                                           final ObservableBool focus) {
        final View.OnFocusChangeListener newListener = ((v, hasFocus) -> focus.set(hasFocus));

        //Tool to prevent double binding of listener
        final View.OnFocusChangeListener oldListener = ListenerUtil.trackListener(view,
                newListener, R.id.focusBinded);
        if (oldListener != null) {
            view.setOnFocusChangeListener(null);
        }
        if (newListener != null) {
            view.setOnFocusChangeListener(newListener);
        }
    }


    /**
     * Binds focus, text and error message from edit text to observable
     *
     * @param view  View to be binded to viewModel
     * @param field Observable binded to
     */

    @BindingAdapter("bind")
    public static void bindAllToEditText(EditText view, final ObsField field) {
        bindEditText(view, field.text);
        bindFocusToEditText(view, field.focus);
        bindValidationError(view, field.error);
    }
}
