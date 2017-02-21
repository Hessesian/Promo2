package sk.oceliak.promo.ui.utils;

import android.os.Parcel;
import android.os.Parcelable;

import com.hannesdorfmann.parcelableplease.annotation.ParcelablePlease;

import io.realm.RealmObject;

/**
 * Object holding int for realm usage
 * Created by {juraj.oceliak@eman.cz} on 6/6/16.
 */


public class IntObject extends RealmObject implements Parcelable {

    public IntObject(int value) {
        mValue = value;
    }

    public IntObject() {
    }

    int mValue;

    protected IntObject(Parcel in) {
        mValue = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(mValue);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<IntObject> CREATOR = new Creator<IntObject>() {
        @Override
        public IntObject createFromParcel(Parcel in) {
            return new IntObject(in);
        }

        @Override
        public IntObject[] newArray(int size) {
            return new IntObject[size];
        }
    };

    public int getValue() {
        return mValue;
    }

    public void setValue(int value) {
        mValue = value;
    }

}
