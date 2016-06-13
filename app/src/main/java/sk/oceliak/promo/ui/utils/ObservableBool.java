/**
 * ObservableBool.java
 *
 * @project ceen_an
 * @package cz.eman.ceen.utils.ObservableBool
 * @author eMan s.r.o.
 * @since 3/18/16 5:49 PM
 */

package sk.oceliak.promo.ui.utils;

import android.databinding.BaseObservable;
import android.os.Parcel;
import android.os.Parcelable;

import com.annimon.stream.Objects;

/**
 * Custom class needed because {@link android.databinding.ObservableBoolean} doesn't provide two way
 * databinding, used in {@link sk.oceliak.promo.ui.binding.DataBindingConverters}
 */

public class ObservableBool extends BaseObservable implements Parcelable{
    private boolean mValue = false;

    public ObservableBool(boolean value) {
        this.mValue = value;
    }

    public ObservableBool() {
    }

    protected ObservableBool(Parcel in) {
        mValue = in.readByte() != 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte((byte) (mValue ? 1 : 0));
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<ObservableBool> CREATOR = new Creator<ObservableBool>() {
        @Override
        public ObservableBool createFromParcel(Parcel in) {
            return new ObservableBool(in);
        }

        @Override
        public ObservableBool[] newArray(int size) {
            return new ObservableBool[size];
        }
    };

    public boolean get() {
        return this.mValue;
    }

    public void set(boolean value) {
        if (!Objects.equals(value, this.mValue)) {
            this.mValue = value;
            this.notifyChange();
        }

    }
}