package sk.oceliak.promo;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import sk.oceliak.promo.core.navigation.AppNavigator;
import sk.oceliak.promo.core.navigation.Navigator;
import sk.oceliak.promo.model.api.ApiService;
import sk.oceliak.promo.model.realm.settings.RealmExclusionStrategy;

/**
 * TODO CLASS_DESCRIPTION
 */

@Module
public class AppModule  {


    @Provides
    public Navigator provideNavigator() {
        return new AppNavigator();
    }

    @Provides @Singleton public ApiService provideApiService(){
        return createService(ApiService.class);
    }

    /**
     * This class should provide creation of API service and provide it to ViewModels
     * Using retrofit 2 as a standard, with logging level set to display body
     *
     * @param serviceClass Retrofit or similar
     * @param <T>          Generic type so you can use any type of API implementation
     *
     * @return Retrofit service
     */
    private static <T> T createService(Class<T> serviceClass) {

        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                .setExclusionStrategies(new RealmExclusionStrategy())
                .create();

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient httpClient = new OkHttpClient.Builder().addInterceptor(logging).build();

        String baseUrl = ApiService.ENDPOINT_URL;
        Retrofit mClient = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl(baseUrl)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(httpClient)
                .build();

        return mClient.create(serviceClass);
    }
}
