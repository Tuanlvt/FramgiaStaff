package com.framgia.framgiastaff.screen.main;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.framgia.framgiastaff.R;
import com.framgia.framgiastaff.data.model.Employee;
import com.framgia.framgiastaff.databinding.ItemListEmployeeBinding;
import com.framgia.framgiastaff.screen.BaseRecyclerViewAdapter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by levutantuan on 7/2/17.
 */

public class MainAdapter extends BaseRecyclerViewAdapter<MainAdapter.ItemViewHolder> {

    private final List<Employee> mEmployees;
    private BaseRecyclerViewAdapter.OnRecyclerViewItemClickListener<Employee> mListener;

    MainAdapter(@NonNull Context context, List<Employee> employees) {
        super(context);
        mEmployees = new ArrayList<>();
        if (employees == null) {
            return;
        }
        mEmployees.addAll(employees);
    }

    @Override
    public MainAdapter.ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemListEmployeeBinding binding =
                DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                        R.layout.item_list_employee, parent, false);
        return new MainAdapter.ItemViewHolder(binding, mListener);
    }

    @Override
    public void onBindViewHolder(MainAdapter.ItemViewHolder holder, int position) {
        holder.bind(mEmployees.get(position));
    }

    @Override
    public int getItemCount() {
        return mEmployees.size();
    }

    public void updateData(List<Employee> employees) {
        if (employees == null) {
            return;
        }
        mEmployees.clear();
        mEmployees.addAll(employees);
        notifyDataSetChanged();
    }

    public void setListener(OnRecyclerViewItemClickListener<Employee> listener) {
        mListener = listener;
    }

    public void removeItem(int position) {
        mEmployees.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, mEmployees.size());
    }

    static class ItemViewHolder extends RecyclerView.ViewHolder {

        private final ItemListEmployeeBinding mBinding;
        private final BaseRecyclerViewAdapter.OnRecyclerViewItemClickListener<Employee> mListener;

        ItemViewHolder(ItemListEmployeeBinding binding,
                BaseRecyclerViewAdapter.OnRecyclerViewItemClickListener<Employee> listener) {
            super(binding.getRoot());
            mBinding = binding;
            mListener = listener;
        }

        void bind(Employee employee) {
            mBinding.setViewModel(new ItemEmployeeViewModel(employee, mListener));
            mBinding.executePendingBindings();
        }
    }
}
