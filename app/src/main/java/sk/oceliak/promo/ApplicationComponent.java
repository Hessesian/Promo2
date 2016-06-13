package sk.oceliak.promo;

import javax.inject.Singleton;

import dagger.Component;
import sk.oceliak.promo.core.menu.MenuViewModel;
import sk.oceliak.promo.core.newcontact.NewContactViewModel;
import sk.oceliak.promo.core.orders.OrdersViewModel;

/**
 * TODO CLASS_DESCRIPTION
 */


@Singleton
@Component(modules = {AppModule.class})
public interface ApplicationComponent {

    MenuViewModel getMenuViewModel();
    NewContactViewModel getNewContactViewModel();
    OrdersViewModel getOrdersViewModel();

}
