package sk.oceliak.promo.model.realm;

import io.realm.RealmConfiguration;
import sk.oceliak.promo.App;

/**
 */
public enum RealmConfig implements Config {

    CACHE("cache");

    private String name;
    private RealmConfiguration configuration;

    RealmConfig(String name) {
        this.name = name;
    }

    @Override
    public RealmConfiguration getConfiguration() {
        switch (this) {
            case CACHE:
                if (configuration == null) {
                    configuration = new RealmConfiguration.Builder(App.getInstance())
                            .name(name)
                            .deleteRealmIfMigrationNeeded()
                            .build();
                }
                return configuration;
        }
        return null;
    }
}
