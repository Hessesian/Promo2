package sk.oceliak.promo.ui.utils;

import android.os.Parcel;
import android.os.Parcelable;

import com.hannesdorfmann.parcelableplease.annotation.ParcelablePlease;

import io.realm.RealmObject;

public class StringObject extends RealmObject implements Parcelable {

    public String mValue;

    public StringObject() {
    }

    public StringObject(String value) {
        mValue = value;
    }

    protected StringObject(Parcel in) {
        mValue = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mValue);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<StringObject> CREATOR = new Creator<StringObject>() {
        @Override
        public StringObject createFromParcel(Parcel in) {
            return new StringObject(in);
        }

        @Override
        public StringObject[] newArray(int size) {
            return new StringObject[size];
        }
    };

    public String getValue() {
        return mValue;
    }

    public void setValue(String value) {
        mValue = value;
    }

}
