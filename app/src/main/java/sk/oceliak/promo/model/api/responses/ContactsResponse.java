package sk.oceliak.promo.model.api.responses;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import sk.oceliak.promo.model.api.models.Contact;

/**
 * TODO CLASS_DESCRIPTION
 */
public class ContactsResponse extends RealmObject {

    @PrimaryKey int id = 1337;
    @SerializedName("items")
    RealmList<Contact> contacts;

    public List<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(RealmList<Contact> contacts) {
        this.contacts = contacts;
    }
}
