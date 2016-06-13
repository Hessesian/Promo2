package sk.oceliak.promo.core.navigation;

import it.cosenonjaviste.mv2m.ActivityHolder;
import retrofit2.Response;
import sk.oceliak.promo.model.api.models.Contact;
import sk.oceliak.promo.ui.screens.menu.MenuFragmentActivity;
import sk.oceliak.promo.ui.screens.newcontact.NewContactFragmentActivity;

/**
 * Implementation of {@link Navigator}
 */
public class AppNavigator implements Navigator{

    @Override
    public void openContact(ActivityHolder activityHolder, Contact contact) {
        ((MenuFragmentActivity)activityHolder.getActivity()).openContact(contact);
    }

    @Override
    public void sendSuccess(ActivityHolder activityHolder, Response responseBody) {
        ((NewContactFragmentActivity) activityHolder.getActivity()).onSuccess(responseBody);
    }

    @Override
    public void sendFail(ActivityHolder activityHolder, Throwable throwable) {
        ((NewContactFragmentActivity) activityHolder.getActivity()).onFail(throwable);

    }
}
