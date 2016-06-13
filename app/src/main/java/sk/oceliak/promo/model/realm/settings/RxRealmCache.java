package sk.oceliak.promo.model.realm.settings;

import io.realm.Realm;
import io.realm.RealmObject;
import rx.Observable;
import rx.Subscriber;
import sk.oceliak.promo.model.realm.RealmConfig;

public class RxRealmCache {

    private RxRealmCache() {
        throw new AssertionError("No instances");
    }

    public static <T extends RealmObject> Observable.Transformer<T, T> cache(final Class<T> clazz) {
        return source -> Observable
                .create(new Observable.OnSubscribe<T>() {
                    @Override
                    public void call(Subscriber<? super T> subscriber) {
                        Realm realm = Realm.getInstance(RealmConfig.CACHE.getConfiguration());
                        T cache = realm.where(clazz).findFirst();
                        if (cache != null) {
                            subscriber.onNext(cache);
                        }
                        subscriber.onCompleted();
                    }
                })
                .concatWith(source)
                .map(t -> {
                    Realm realm = Realm.getInstance(RealmConfig.CACHE.getConfiguration());
                    realm.beginTransaction();
                    T managed = realm.copyToRealmOrUpdate(t);
                    realm.commitTransaction();
                    return managed;
                });
    }
}
