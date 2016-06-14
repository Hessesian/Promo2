package sk.oceliak.promo;

import android.app.Application;
import android.content.Context;
import android.support.v4.app.Fragment;

import timber.log.Timber;

/**
 * Main application controller
 */
public class App extends Application {

    private ApplicationComponent component;

    private static App sInstance;

    /**
     * Static access to Application instance
     *
     * @return App instance
     */
    public static App getInstance() {
        return sInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
        Timber.plant(new Timber.DebugTree());

        component = DaggerApplicationComponent.builder()
                .appModule(new AppModule())
                .build();
    }


    public ApplicationComponent getComponent() {
        return component;
    }

    public static ApplicationComponent getComponent(Fragment fragment) {
        return getComponent(fragment.getActivity());
    }

    public static ApplicationComponent getComponent(Context context) {
        return ((App) context.getApplicationContext()).getComponent();
    }

    public void setComponent(ApplicationComponent component) {
        this.component = component;
    }
}
