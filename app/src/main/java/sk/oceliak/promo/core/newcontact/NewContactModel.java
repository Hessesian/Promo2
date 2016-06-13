package sk.oceliak.promo.core.newcontact;

import android.os.Parcel;
import android.os.Parcelable;

import com.hannesdorfmann.parcelableplease.annotation.ParcelablePlease;

import sk.oceliak.promo.ui.utils.ObsField;

/**
 * TODO CLASS_DESCRIPTION
 */
@ParcelablePlease
public class NewContactModel implements Parcelable {

    ObsField name = new ObsField();
    ObsField phone = new ObsField();

    public ObsField getName() {
        return name;
    }

    public void setName(ObsField name) {
        this.name = name;
    }

    public ObsField getPhone() {
        return phone;
    }

    public void setPhone(ObsField phone) {
        this.phone = phone;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        NewContactModelParcelablePlease.writeToParcel(this, dest, flags);
    }

    public static final Creator<NewContactModel> CREATOR = new Creator<NewContactModel>() {
        public NewContactModel createFromParcel(Parcel source) {
            NewContactModel target = new NewContactModel();
            NewContactModelParcelablePlease.readFromParcel(target, source);
            return target;
        }

        public NewContactModel[] newArray(int size) {
            return new NewContactModel[size];
        }
    };
}
