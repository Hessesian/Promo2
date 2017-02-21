package sk.oceliak.promo.ui.utils;

/**
 * Created by Juraj Oceliak{juraj.oceliak@eman.cz} on 2/21/17.
 */

import android.os.Parcel;

import com.hannesdorfmann.parcelableplease.ParcelBagger;

import io.realm.RealmList;

import android.os.Parcel;

import com.hannesdorfmann.parcelableplease.ParcelBagger;

import io.realm.RealmList;

public class RealmListBagger implements ParcelBagger<RealmList> {
    @Override
    public void write(RealmList value, Parcel out, int flags) {
        out.writeInt(value == null ? -1 : 0);
        if (value != null) {
            out.writeList(value);
        }
    }

    @Override
    public RealmList read(Parcel in) {
        RealmList realmList = new RealmList();
        int n = in.readInt();
        if (n == -1) {
            return null;
        } else {
            in.readList(realmList, realmList.getClass().getClassLoader());
            return realmList;
        }
    }
}