/**
 * RecyclerBindingBuilder.java
 *
 * @project ceen_an
 * @package cz.eman.ceen.utils.RecyclerBindingBuilder
 * @author eMan s.r.o.
 * @since 3/24/16 11:20 AM
 */

package sk.oceliak.promo.ui.utils;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import it.cosenonjaviste.mv2m.ViewModel;
import it.cosenonjaviste.mv2m.recycler.BindableAdapter;
import it.cosenonjaviste.mv2m.recycler.BindableViewHolder;
import rx.functions.Action1;
import rx.functions.Func3;
import sk.oceliak.promo.R;
import sk.oceliak.promo.BR;
import sk.oceliak.promo.core.base.ListModel;

/**
 * Class that should provide binding for recyclerView
 * Needs additional tweaking for use case of various types of items
 *
 * @param <T> Generic type for item class to be injected into view
 */

public class RecyclerBindingBuilder<T> {

    private final LayoutInflater inflater;

    private final ViewModel<?, ? extends ListModel<T>> viewModel;

    private ViewDataBinding binding;

    private BindableAdapter.ViewHolderFactory<T> factory;

    /**
     * Constructor providing classic inflater to inflate selected class
     * currently set specifically for main activity, needs to be generalized for any type of layout
     *
     * @param inflater  Inflated, it would be good idea to replace it by already inflated view
     * @param container optional container for recycler to be inflated in
     * @param viewModel ViewModel class according to MVVM architecture, in XML has to be defined as "viewModel" variable name
     */
    public RecyclerBindingBuilder(LayoutInflater inflater, @Nullable ViewGroup container, ViewModel viewModel, @LayoutRes int layoutId) {
        this.inflater = inflater;
        this.viewModel = viewModel;
        binding = DataBindingUtil.inflate(inflater, layoutId, container, false);
        binding.setVariable(BR.viewModel,viewModel);
    }

    /**
     * @return Generated class of layout by Databinding android library
     */

    public ViewDataBinding getBinding() {
        if (((RecyclerView) binding.getRoot().findViewById(R.id.list)).getLayoutManager() == null) {
            linearLayoutManager();
        }
        return binding;
    }

    /**
     * @return Parent class for #setContentView in fragment/activity
     */
    public View getRoot() {
        return getBinding().getRoot();
    }

    /**
     * @return layout manager, can be customized
     */
    public RecyclerBindingBuilder<T> linearLayoutManager() {
        ((RecyclerView) binding.getRoot().findViewById(R.id.list)).setLayoutManager(new LinearLayoutManager(binding.getRoot().getContext()));
        return this;
    }

    private <B extends ViewDataBinding> RecyclerBindingBuilder<T> viewHolderWithCustomizer(
            Func3<LayoutInflater, ViewGroup, Boolean, B> inflateFunction,
            BindableViewHolder.Binder<B, T> binder,
            Action1<BindableViewHolder<T>> customizer) {
        factory = v -> {
            B binding = inflateFunction.call(inflater, v, false);
            BindableViewHolder<T> viewHolder = BindableViewHolder.create(binding, binder);
            if (customizer != null) {
                customizer.call(viewHolder);
            }
            return viewHolder;
        };

        ((RecyclerView) binding.getRoot().findViewById(R.id.list)).setAdapter(getNewAdapter());
        return this;
    }

    @NonNull
    public BindableAdapter<T> getNewAdapter() {
        return new BindableAdapter<>(viewModel.getModel().getItems(), factory);
    }

    public <B extends ViewDataBinding> RecyclerBindingBuilder<T> viewHolder(Func3<LayoutInflater, ViewGroup, Boolean, B> inflateFunction, BindableViewHolder.Binder<B, T> binder) {
        return viewHolderWithCustomizer(inflateFunction, binder, null);
    }

    public <B extends ViewDataBinding> RecyclerBindingBuilder<T> viewHolder(
            Func3<LayoutInflater, ViewGroup, Boolean, B> inflateFunction,
            BindableViewHolder.Binder<B, T> binder,
            Action1<Integer> clickListener) {
        return viewHolderWithCustomizer(inflateFunction, binder, vh -> vh.itemView.setOnClickListener(v -> clickListener.call(vh.getAdapterPosition())));
    }
}
