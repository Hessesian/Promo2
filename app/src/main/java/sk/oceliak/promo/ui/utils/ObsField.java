package sk.oceliak.promo.ui.utils;

import android.databinding.BaseObservable;
import android.databinding.Observable;
import android.databinding.ObservableInt;
import android.os.Parcel;
import android.os.Parcelable;

import com.hannesdorfmann.parcelableplease.annotation.ParcelablePlease;


/**
 * Structure for edit text form observables, holding focus for validation triggering,
 * error for setting error string resources, and text containing user input
 */

@ParcelablePlease( ignorePrivateFields = true)
public class ObsField extends BaseObservable implements Parcelable {

    public ObservableString text = new ObservableString();
    public ObservableInt error = new ObservableInt();
    public ObservableBool focus = new ObservableBool();
    public ObservableBool mandatory = new ObservableBool(false);

    private boolean fieldVisited = false;

    public ObsField() {
        OnPropertyChangedCallback callback = new OnPropertyChangedCallback() {
            @Override
            public void onPropertyChanged(Observable sender, int propertyId) {
                if (focus.get()) {
                    error.set(0);
                    fieldVisited = true;
                }
            }
        };
        focus.addOnPropertyChangedCallback(callback);
    }

    public boolean isFieldVisited() {
        return fieldVisited;
    }

    public void setFieldVisited(boolean fieldVisited) {
        this.fieldVisited = fieldVisited;
    }

    protected ObsField(Parcel in) {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        ObsFieldParcelablePlease.writeToParcel(this, dest, flags);
    }

    public static final Creator<ObsField> CREATOR = new Creator<ObsField>() {
        public ObsField createFromParcel(Parcel source) {
            ObsField target = new ObsField();
            ObsFieldParcelablePlease.readFromParcel(target, source);
            return target;
        }

        public ObsField[] newArray(int size) {
            return new ObsField[size];
        }
    };
}
