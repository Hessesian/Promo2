package sk.oceliak.promo.core.orders;

import android.support.annotation.NonNull;

import javax.inject.Inject;

import it.cosenonjaviste.mv2m.ViewModel;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import sk.oceliak.promo.model.api.ApiService;
import sk.oceliak.promo.model.api.models.Contact;
import sk.oceliak.promo.model.api.models.Order;
import sk.oceliak.promo.model.api.responses.OrdersRresponse;
import sk.oceliak.promo.model.realm.settings.RxRealmCache;
import timber.log.Timber;

/**
 * TODO CLASS_DESCRIPTION
 */
public class OrdersViewModel extends ViewModel<Contact, OrdersModel> {

    ApiService mApiService;

    @Inject
    public OrdersViewModel(ApiService service) {
        mApiService = service;
    }

    @Override
    public void resume() {
        super.resume();


    }

    @NonNull
    @Override
    protected OrdersModel createModel() {
        OrdersModel model = new OrdersModel();
        model.getPhone().set(getArgument().getPhone());
        Observable<OrdersRresponse> orders = mApiService.getOrders(getArgument().getRemoteId());
        if (model.getItems().isEmpty()) {
            orders.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .compose(RxRealmCache.cache(OrdersRresponse.class))
                    .subscribe(orders1 -> {
                                if (model.getItems().isEmpty()) {
                                    model.getItems().addAll(orders1.getOrders());
                                }
                            },
                            throwable -> Timber.d(throwable, "Orders error")
                    );
        }
        return model;
    }

    public void onClick(Integer position) {
        Order order = model.getItems().get(position);
    }
}
