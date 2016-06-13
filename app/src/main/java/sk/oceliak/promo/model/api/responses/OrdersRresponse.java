package sk.oceliak.promo.model.api.responses;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import sk.oceliak.promo.model.api.models.Order;

/**
 * TODO CLASS_DESCRIPTION
 */
public class OrdersRresponse extends RealmObject{

    @PrimaryKey int id = 1337;
    @SerializedName("items")
    RealmList<Order> orders;

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(RealmList<Order> orders) {
        this.orders = orders;
    }
}
