package sk.oceliak.promo.core.menu;

import android.support.annotation.NonNull;

import javax.inject.Inject;

import it.cosenonjaviste.mv2m.ViewModel;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import sk.oceliak.promo.Flags;
import sk.oceliak.promo.core.navigation.Navigator;
import sk.oceliak.promo.model.api.ApiService;
import sk.oceliak.promo.model.api.models.Contact;
import sk.oceliak.promo.model.api.responses.ContactsResponse;
import sk.oceliak.promo.model.realm.settings.RxRealmCache;
import timber.log.Timber;

/**
 * TODO CLASS_DESCRIPTION
 */
public class MenuViewModel extends ViewModel<Void, MenuModel> {

    ApiService mApiService;
    Navigator mNavigator;

    @Inject
    public MenuViewModel(ApiService service, Navigator navigator) {
        mApiService = service;
        mNavigator = navigator;
    }

    @NonNull
    @Override
    protected MenuModel createModel() {
        MenuModel model = new MenuModel();
        return model;
    }

    private void loadData(){
        if(Flags.FLAG_CONTACT_ADDED){
            model.getItems().clear();
            Flags.FLAG_CONTACT_ADDED = false;
        }
        Observable<ContactsResponse> response = mApiService.getContacts();
        if (model.getItems().isEmpty()) {
            response.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .compose(RxRealmCache.cache(ContactsResponse.class))
                    .map(ContactsResponse::getContacts)
                    .subscribe(contacts -> {
                                if (model.getItems().isEmpty()) {
                                    model.items.addAll(contacts);
                                }
                            },
                            throwable -> Timber.d(throwable, "Failed to retrieve data")
                    );
        }
    }

    @Override
    public void resume() {
        super.resume();
        loadData();
    }

    public void onClick(Integer position) {
        Contact contact = model.getItems().get(position);
        mNavigator.openContact(activityHolder, contact);
    }
}
