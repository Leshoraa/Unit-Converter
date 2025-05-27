package com.leshoraa.unitconverter.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.leshoraa.unitconverter.databinding.ItemGridBinding;
import com.leshoraa.unitconverter.databinding.ItemTitleBinding;
import com.leshoraa.unitconverter.model.UnitItem;

import java.util.List;

public class GridAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final List<UnitItem> itemList;

    private final OnUnitClickListener listener;

    public GridAdapter(List<UnitItem> itemList, OnUnitClickListener listener) {
        this.itemList = itemList;
        this.listener = listener;
    }

    @Override
    public int getItemViewType(int position) {
        return itemList.get(position).getType();
    }

    public UnitItem getItem(int position) {
        return itemList.get(position);
    }

    public interface OnUnitClickListener {
        void onUnitClick(UnitItem item);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        if (viewType == UnitItem.TYPE_TITLE) {
            ItemTitleBinding binding = ItemTitleBinding.inflate(inflater, parent, false);
            return new TitleViewHolder(binding);
        } else {
            ItemGridBinding binding = ItemGridBinding.inflate(inflater, parent, false);
            return new UnitViewHolder(binding);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        UnitItem item = itemList.get(position);

        if (holder instanceof TitleViewHolder) {
            ((TitleViewHolder) holder).binding.textTitle.setText(item.getUnitName());
        } else if (holder instanceof UnitViewHolder) {
            UnitViewHolder unitHolder = (UnitViewHolder) holder;
            unitHolder.binding.textItem.setText(item.getUnitName());
            unitHolder.binding.textSymbol.setText(item.getUnitSymbol());
            unitHolder.binding.getRoot().setOnClickListener(v -> listener.onUnitClick(item));
        }
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public static class TitleViewHolder extends RecyclerView.ViewHolder {
        final ItemTitleBinding binding;

        public TitleViewHolder(ItemTitleBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    public static class UnitViewHolder extends RecyclerView.ViewHolder {
        final ItemGridBinding binding;

        public UnitViewHolder(ItemGridBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}