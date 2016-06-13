package sk.oceliak.promo.ui.screens.orders;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import it.cosenonjaviste.mv2m.ArgumentManager;
import it.cosenonjaviste.mv2m.ViewModelFragment;
import sk.oceliak.promo.App;
import sk.oceliak.promo.R;
import sk.oceliak.promo.core.orders.OrdersViewModel;
import sk.oceliak.promo.databinding.FragmentOrderBinding;
import sk.oceliak.promo.databinding.ItemOrderBinding;
import sk.oceliak.promo.model.api.models.Contact;
import sk.oceliak.promo.model.api.models.Order;
import sk.oceliak.promo.ui.utils.RecyclerBindingBuilder;
import timber.log.Timber;

/**
 * Fragment on screen displaying orders
 */
public class OrdersFragment extends ViewModelFragment<OrdersViewModel> {

    FragmentOrderBinding mBinding;

    @Override
    public void onCreate(Bundle state) {
        super.onCreate(state);
        Timber.d("created");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = FragmentOrderBinding.inflate(inflater);
        return new RecyclerBindingBuilder<Order>(inflater, null, viewModel, R.layout.fragment_order)
                .viewHolder(ItemOrderBinding::inflate, ItemOrderBinding::setOrder, (position) -> viewModel.onClick(position)).getRoot();

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Contact contact = (Contact) ArgumentManager.readArgument(getArguments());
        Timber.d(contact.getName());
    }

    @Override
    public OrdersViewModel createViewModel() {
        return App.getComponent(this).getOrdersViewModel();
    }
}
