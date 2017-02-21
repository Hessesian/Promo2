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
import sk.oceliak.promo.model.api.models.Item;
import sk.oceliak.promo.model.api.models.Result;
import sk.oceliak.promo.model.api.responses.ContactsResponse;
import sk.oceliak.promo.model.realm.settings.RxRealmCache;
import timber.log.Timber;

/**
 * TODO CLASS_DESCRIPTION
 */
public class MenuViewModel extends ViewModel<Void, MenuModel> {

    ApiService mApiService;
    Navigator mNavigator;

    // WebApi Default query values
    public String filter = "withbody";
    public String fromDate = "1459468800";
    public String toDate = "1461974400";
    public String order = "desc";
    public String sort = "creation";
    public String site = "cooking";
    public int pageSize = 5;

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
      /*  Observable<ContactsResponse> response = mApiService.getContacts();
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
        }*/

        Observable<Result> results = mApiService.getResult(filter, fromDate, toDate, order, sort, site, pageSize, 1);
        results.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .compose(RxRealmCache.cache(Result.class))
                .map(Result::getItems)
                .subscribe(items -> {
                            if (model.getItems().isEmpty()) {
                                model.items.addAll(items);
                            }
                        },
                        throwable -> Timber.d(throwable, "Failed to retrieve data")
                );
    }

    @Override
    public void resume() {
        super.resume();
        loadData();
    }

    public void onClick(Integer position) {
        Item contact = model.getItems().get(position);
        mNavigator.openContact(activityHolder, contact);
    }
}
