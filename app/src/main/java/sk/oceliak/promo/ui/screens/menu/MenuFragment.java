package sk.oceliak.promo.ui.screens.menu;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import it.cosenonjaviste.mv2m.ViewModelFragment;
import sk.oceliak.promo.App;
import sk.oceliak.promo.R;
import sk.oceliak.promo.core.menu.MenuViewModel;
import sk.oceliak.promo.databinding.FragmentMenuBinding;
import sk.oceliak.promo.databinding.ItemContactBinding;
import sk.oceliak.promo.model.api.models.Contact;
import sk.oceliak.promo.model.api.models.Item;
import sk.oceliak.promo.ui.utils.RecyclerBindingBuilder;

/**
 * TODO CLASS_DESCRIPTION
 */
public class MenuFragment extends ViewModelFragment<MenuViewModel> {

    FragmentMenuBinding mBinding;

    @Override
    public MenuViewModel createViewModel() {
        return App.getComponent(this).getMenuViewModel();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = FragmentMenuBinding.inflate(inflater);
        return new RecyclerBindingBuilder<Item>(inflater, null, viewModel, R.layout.fragment_menu)
                .viewHolder(ItemContactBinding::inflate,ItemContactBinding::setContact, (position) -> viewModel.onClick(position)).getRoot();

    }
}
