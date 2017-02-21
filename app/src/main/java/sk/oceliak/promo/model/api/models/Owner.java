package sk.oceliak.promo.model.api.models;

/**
 * Created by jaroslav.malan on 20. 12. 2016.
 */


import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.hannesdorfmann.parcelableplease.annotation.ParcelablePlease;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

@ParcelablePlease
public class Owner extends RealmObject implements Parcelable {
    @SerializedName("reputation")
    @Expose
     long reputation;
    @SerializedName("user_id")
    @Expose
    @PrimaryKey
     long userId;
    @SerializedName("user_type")
    @Expose
     String userType;
    @SerializedName("profile_image")
    @Expose
     String profileImage;
    @SerializedName("display_name")
    @Expose
     String displayName;
    @SerializedName("link")
    @Expose
     String link;
    @SerializedName("accept_rate")
    @Expose
     long acceptRate;

    public long getReputation() {
        return reputation;
    }

    public void setReputation(long reputation) {
        this.reputation = reputation;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public long getAcceptRate() {
        return acceptRate;
    }

    public void setAcceptRate(long acceptRate) {
        this.acceptRate = acceptRate;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        OwnerParcelablePlease.writeToParcel(this, dest, flags);
    }

    public static final Creator<Owner> CREATOR = new Creator<Owner>() {
        public Owner createFromParcel(Parcel source) {
            Owner target = new Owner();
            OwnerParcelablePlease.readFromParcel(target, source);
            return target;
        }

        public Owner[] newArray(int size) {
            return new Owner[size];
        }
    };
}
