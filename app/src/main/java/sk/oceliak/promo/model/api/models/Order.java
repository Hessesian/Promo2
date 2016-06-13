package sk.oceliak.promo.model.api.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;
import com.hannesdorfmann.parcelableplease.annotation.ParcelablePlease;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * TODO CLASS_DESCRIPTION
 */

@ParcelablePlease
public class Order extends RealmObject implements Parcelable {
    @SerializedName("name") @PrimaryKey String name;
    @SerializedName("count") int count;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        OrderParcelablePlease.writeToParcel(this, dest, flags);
    }

    public static final Creator<Order> CREATOR = new Creator<Order>() {
        public Order createFromParcel(Parcel source) {
            Order target = new Order();
            OrderParcelablePlease.readFromParcel(target, source);
            return target;
        }

        public Order[] newArray(int size) {
            return new Order[size];
        }
    };
}
