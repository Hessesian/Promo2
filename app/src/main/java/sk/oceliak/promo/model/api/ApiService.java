package sk.oceliak.promo.model.api;

import okhttp3.ResponseBody;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import rx.Observable;
import sk.oceliak.promo.model.api.models.Contact;
import sk.oceliak.promo.model.api.responses.ContactsResponse;
import sk.oceliak.promo.model.api.responses.OrdersRresponse;

/**
 * Api interface defining calls
 */
public interface ApiService {
    String ENDPOINT_URL = "http://private-ae353-contactstest.apiary-mock.com/";

    @GET("contacts")
    Observable<ContactsResponse> getContacts();

    @GET("contacts/{id}/order")
    Observable<OrdersRresponse> getOrders(@Path("id") long id);

    @POST("contacts")
    Observable<Response<ResponseBody>> postContact(@Body Contact body);
 }
