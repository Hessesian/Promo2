package sk.oceliak.promo.ui.screens.newcontact;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import it.cosenonjaviste.mv2m.ViewModelFragment;
import sk.oceliak.promo.App;
import sk.oceliak.promo.core.newcontact.NewContactViewModel;
import sk.oceliak.promo.databinding.FragmentNewcontactBinding;

/**
 * Class for new contact creationg
 */
public class NewContactFragment extends ViewModelFragment<NewContactViewModel> {

    FragmentNewcontactBinding mBinding;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = FragmentNewcontactBinding.inflate(inflater);
        mBinding.setViewModel(viewModel);
        return mBinding.getRoot();
    }

    @Override
    public NewContactViewModel createViewModel() {
        return App.getComponent(this).getNewContactViewModel();
    }
}
