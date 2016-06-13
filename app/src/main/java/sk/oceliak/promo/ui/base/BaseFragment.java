package sk.oceliak.promo.ui.base;

import it.cosenonjaviste.mv2m.ViewModel;
import it.cosenonjaviste.mv2m.ViewModelFragment;

/**
 * Base fragment from which other fragments in app should inherit
 */
public abstract class BaseFragment<VM extends ViewModel<?, ?>> extends ViewModelFragment<VM> {
}
