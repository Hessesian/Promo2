package sk.oceliak.promo.core.orders;

import android.databinding.ObservableField;
import android.os.Parcel;
import android.os.Parcelable;

import com.hannesdorfmann.parcelableplease.annotation.ParcelablePlease;

import sk.oceliak.promo.core.base.ListModel;
import sk.oceliak.promo.model.api.models.Item;

/**
 * TODO CLASS_DESCRIPTION
 */
@ParcelablePlease
public class OrdersModel extends ListModel<Item> implements Parcelable {

    ObservableField<String> mPhone = new ObservableField<>();
    ObservableField<String> mText = new ObservableField<>();

    public ObservableField<String> getPhone() {
        return mPhone;
    }

    public ObservableField<String> getText() {
        return mText;
    }

    public void setPhone(ObservableField<String> phone) {
        this.mPhone = phone;
    }

    public void setText(String mText) {
        this.mText.set(mText);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        OrdersModelParcelablePlease.writeToParcel(this, dest, flags);
    }

    public static final Creator<OrdersModel> CREATOR = new Creator<OrdersModel>() {
        public OrdersModel createFromParcel(Parcel source) {
            OrdersModel target = new OrdersModel();
            OrdersModelParcelablePlease.readFromParcel(target, source);
            return target;
        }

        public OrdersModel[] newArray(int size) {
            return new OrdersModel[size];
        }
    };
}
