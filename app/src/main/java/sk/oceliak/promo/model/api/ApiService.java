package sk.oceliak.promo.model.api;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;
import sk.oceliak.promo.model.api.models.Contact;
import sk.oceliak.promo.model.api.models.Result;
import sk.oceliak.promo.model.api.responses.ContactsResponse;
import sk.oceliak.promo.model.api.responses.OrdersRresponse;

/**
 * Api interface defining calls
 */
public interface ApiService {
    String ENDPOINT_URL = "https://api.stackexchange.com/2.2/";

    @GET("contacts")
    Observable<ContactsResponse> getContacts();

    @GET("contacts/{id}/order")
    Observable<OrdersRresponse> getOrders(@Path("id") long id);

    @POST("contacts")
    Observable<Response<ResponseBody>> postContact(@Body Contact body);

    @GET("questions")
    Observable<Result> getResult(@Query("filter") String filter, @Query("fromdate") String fromDate, @Query("todate") String toDate,
                           @Query("order") String order, @Query("sort") String sort, @Query("site") String site, @Query("pagesize") int pageSize, @Query("page") int page );
 }
