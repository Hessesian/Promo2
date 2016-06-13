package sk.oceliak.promo.model.realm;

import io.realm.RealmConfiguration;

/**
 * interface for enum to get config file
 */
public interface Config {
    RealmConfiguration getConfiguration();
}
