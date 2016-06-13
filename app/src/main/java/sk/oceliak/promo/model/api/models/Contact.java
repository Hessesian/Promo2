package sk.oceliak.promo.model.api.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;
import com.hannesdorfmann.parcelableplease.annotation.ParcelablePlease;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Model class for user contact
 */

@ParcelablePlease
public class Contact extends RealmObject implements Parcelable {

    @SerializedName("id") @PrimaryKey long remoteId;
    @SerializedName("name") String name;
    @SerializedName("phone") String phone;
    @SerializedName("pictureUrl") String pictureUrl;

    public long getRemoteId() {
        return remoteId;
    }

    public void setRemoteId(long remoteId) {
        this.remoteId = remoteId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        ContactParcelablePlease.writeToParcel(this, dest, flags);
    }

    public static final Creator<Contact> CREATOR = new Creator<Contact>() {
        public Contact createFromParcel(Parcel source) {
            Contact target = new Contact();
            ContactParcelablePlease.readFromParcel(target, source);
            return target;
        }

        public Contact[] newArray(int size) {
            return new Contact[size];
        }
    };
}
