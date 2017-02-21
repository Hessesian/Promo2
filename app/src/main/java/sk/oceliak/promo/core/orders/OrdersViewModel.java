package sk.oceliak.promo.core.orders;

import android.support.annotation.NonNull;

import javax.inject.Inject;

import it.cosenonjaviste.mv2m.ViewModel;
import sk.oceliak.promo.model.api.ApiService;
import sk.oceliak.promo.model.api.models.Item;

/**
 * TODO CLASS_DESCRIPTION
 */
public class OrdersViewModel extends ViewModel<Item, OrdersModel> {

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
        model.getPhone().set(getArgument().getOwner().getDisplayName());
        model.getText().set(getArgument().getBody());
        return model;
    }
}
