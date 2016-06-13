package sk.oceliak.promo.core.navigation;

import it.cosenonjaviste.mv2m.ActivityHolder;
import retrofit2.Response;
import sk.oceliak.promo.model.api.models.Contact;

/**
 * Class delegating android actions from
 */
public interface Navigator {
    void openContact(ActivityHolder activityHolder, Contact contact);

    void sendSuccess(ActivityHolder activityHolder, Response responseBody);

    void sendFail(ActivityHolder activityHolder, Throwable throwable);
}
