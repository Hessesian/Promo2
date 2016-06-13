package sk.oceliak.promo.core.menu;

import android.os.Parcel;
import android.os.Parcelable;

import com.hannesdorfmann.parcelableplease.annotation.ParcelablePlease;

import sk.oceliak.promo.core.base.ListModel;
import sk.oceliak.promo.model.api.models.Contact;

/**
 * Java model of menu
 */

@ParcelablePlease
public class MenuModel extends ListModel<Contact> implements Parcelable {
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        MenuModelParcelablePlease.writeToParcel(this, dest, flags);
    }

    public static final Creator<MenuModel> CREATOR = new Creator<MenuModel>() {
        public MenuModel createFromParcel(Parcel source) {
            MenuModel target = new MenuModel();
            MenuModelParcelablePlease.readFromParcel(target, source);
            return target;
        }

        public MenuModel[] newArray(int size) {
            return new MenuModel[size];
        }
    };
}
