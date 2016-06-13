package sk.oceliak.promo.core.newcontact;

import android.support.annotation.NonNull;
import android.telephony.PhoneNumberUtils;
import android.view.View;

import javax.inject.Inject;

import it.cosenonjaviste.mv2m.ViewModel;
import retrofit2.Response;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import sk.oceliak.promo.R;
import sk.oceliak.promo.core.navigation.Navigator;
import sk.oceliak.promo.model.api.ApiService;
import sk.oceliak.promo.model.api.models.Contact;

/**
 * TODO CLASS_DESCRIPTION
 */
public class NewContactViewModel extends ViewModel<Void, NewContactModel> {

    ApiService mService;
    Navigator mNavigator;

    @Inject
    public NewContactViewModel(ApiService service, Navigator navigator) {
        mService = service;
        mNavigator = navigator;
    }

    @NonNull
    @Override
    protected NewContactModel createModel() {
        return new NewContactModel();
    }

    public void send(View v) {
        if (validate()) {
            Contact contact = new Contact();
            contact.setName(model.name.text.get());
            contact.setPhone(model.phone.text.get());

            Observable<Response> response = mService.postContact(contact);

            response.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(((responseBody) -> mNavigator.sendSuccess(activityHolder, responseBody)),
                            throwable -> mNavigator.sendFail(activityHolder, throwable));
        }

    }

    private boolean validate() {
        boolean isValid = true;
        model.getPhone().error.set(0);
        if (!model.getPhone().text.get().isEmpty()) {
            if (!PhoneNumberUtils.isGlobalPhoneNumber(model.getPhone().text.get())) {
                model.getPhone().error.set(R.string.error_bad_format);
                isValid = false;
            }
        } else {
            model.getPhone().error.set(R.string.error_bad_format);
            isValid = false;
        }

        model.getName().error.set(0);
        if(!model.getName().text.get().isEmpty()){
            if(model.getName().text.get().length()<5){
                model.getName().error.set(R.string.error_at_least_5_chars);
                isValid = false;
            }
        } else {
            model.getName().error.set(R.string.error_bad_format);
            isValid = false;
        }

        model.getPhone().notifyChange();
        model.getName().notifyChange();
        return isValid;
    }
}
