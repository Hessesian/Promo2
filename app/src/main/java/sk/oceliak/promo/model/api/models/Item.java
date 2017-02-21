package sk.oceliak.promo.model.api.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.hannesdorfmann.parcelableplease.annotation.Bagger;
import com.hannesdorfmann.parcelableplease.annotation.ParcelablePlease;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import sk.oceliak.promo.ui.utils.RealmListBagger;
import sk.oceliak.promo.ui.utils.StringObject;

/**
 * Created by jaroslav.malan on 20. 12. 2016.
 */


@ParcelablePlease
public class Item extends RealmObject implements Parcelable {

    @SerializedName("tags")
    @Expose
    @Bagger(RealmListBagger.class)
    RealmList<StringObject> tags = null;
    @SerializedName("owner")
    @Expose
    Owner owner;
    @SerializedName("is_answered")
    @Expose
    boolean isAnswered;
    @SerializedName("view_count")
    @Expose
    long viewCount;
    @SerializedName("answer_count")
    @Expose
    long answerCount;
    @SerializedName("score")
    @Expose
    long score;
    @SerializedName("last_activity_date")
    @Expose
    long lastActivityDate;
    @SerializedName("creation_date")
    @Expose
    long creationDate;
    @SerializedName("last_edit_date")
    @Expose
    long lastEditDate;
    @SerializedName("question_id")
    @Expose
    @PrimaryKey
    long questionId;
    @SerializedName("link")
    @Expose
    String link;
    @SerializedName("title")
    @Expose
    String title;
    @SerializedName("body")
    @Expose
    String body;
    @SerializedName("accepted_answer_id")
    @Expose
    long acceptedAnswerId;

    public RealmList<StringObject> getTags() {
        return tags;
    }

    public void setTags(RealmList<StringObject> tags) {
        this.tags = tags;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public boolean isIsAnswered() {
        return isAnswered;
    }

    public void setIsAnswered(boolean isAnswered) {
        this.isAnswered = isAnswered;
    }

    public long getViewCount() {
        return viewCount;
    }

    public void setViewCount(long viewCount) {
        this.viewCount = viewCount;
    }

    public long getAnswerCount() {
        return answerCount;
    }

    public void setAnswerCount(long answerCount) {
        this.answerCount = answerCount;
    }

    public long getScore() {
        return score;
    }

    public void setScore(long score) {
        this.score = score;
    }

    public long getLastActivityDate() {
        return lastActivityDate;
    }

    public void setLastActivityDate(long lastActivityDate) {
        this.lastActivityDate = lastActivityDate;
    }

    public long getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(long creationDate) {
        this.creationDate = creationDate;
    }

    public long getLastEditDate() {
        return lastEditDate;
    }

    public void setLastEditDate(long lastEditDate) {
        this.lastEditDate = lastEditDate;
    }

    public long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(long questionId) {
        this.questionId = questionId;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public long getAcceptedAnswerId() {
        return acceptedAnswerId;
    }

    public void setAcceptedAnswerId(long acceptedAnswerId) {
        this.acceptedAnswerId = acceptedAnswerId;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        ItemParcelablePlease.writeToParcel(this, dest, flags);
    }

    public static final Creator<Item> CREATOR = new Creator<Item>() {
        public Item createFromParcel(Parcel source) {
            Item target = new Item();
            ItemParcelablePlease.readFromParcel(target, source);
            return target;
        }

        public Item[] newArray(int size) {
            return new Item[size];
        }
    };
}
