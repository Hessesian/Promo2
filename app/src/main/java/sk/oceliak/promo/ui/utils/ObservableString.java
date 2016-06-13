package sk.oceliak.promo.ui.utils;

import android.databinding.BaseObservable;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * Observable string holding values, replacing {@link android.databinding.ObservableField<String>}
 */
public class ObservableString extends BaseObservable implements Parcelable {

    private String value = "";

    public ObservableString(String value) {
        this.value = value;
    }

    public ObservableString() { }

    protected ObservableString(Parcel in) {
        value = in.readString();
    }

    public static final Creator<ObservableString> CREATOR = new Creator<ObservableString>() {
        @Override
        public ObservableString createFromParcel(Parcel in) {
            return new ObservableString(in);
        }

        @Override
        public ObservableString[] newArray(int size) {
            return new ObservableString[size];
        }
    };

    public String get() {
        return value != null ? value : "";
    }

    public void set(String value) {
        if (value == null) value = "";
        if (!this.value.contentEquals(value)) {
            this.value = value;
            notifyChange();
        }
    }

    public boolean isEmpty() {
        return value == null || value.isEmpty();
    }

    public void clear() { set(null); }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(value);
    }
}