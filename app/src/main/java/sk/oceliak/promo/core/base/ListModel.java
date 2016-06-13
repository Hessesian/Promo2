package sk.oceliak.promo.core.base;

import android.databinding.ObservableArrayList;

import com.hannesdorfmann.parcelableplease.annotation.Bagger;

import sk.oceliak.promo.core.utils.ObservableArrayListBagger;

public abstract class ListModel<T> {

    @Bagger(ObservableArrayListBagger.class)
    public ObservableArrayList<T> items = new ObservableArrayList<>();

    public final ObservableArrayList<T> getItems() {
        return items;
    }
}