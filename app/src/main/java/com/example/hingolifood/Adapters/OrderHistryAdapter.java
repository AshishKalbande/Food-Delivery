package com.example.hingolifood.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hingolifood.FoodModel;
import com.example.hingolifood.databinding.ItemsBinding;
import com.example.hingolifood.databinding.OrderHistroyItemsBinding;
import com.example.hingolifood.models.OrderHistryModel;
import com.google.firebase.firestore.auth.User;

import java.util.ArrayList;
import java.util.List;

public class OrderHistryAdapter extends RecyclerView.Adapter<OrderHistryAdapter.OrderHistryHolder> {

    private Context context;
    private List<OrderHistryModel> orderlist = new ArrayList<>();

    public OrderHistryAdapter(Context context, List<OrderHistryModel> orderlist) {
        this.context = context;
        this.orderlist = orderlist;
    }

    @NonNull
    @Override
    public OrderHistryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        OrderHistroyItemsBinding orderHistroyItemsBinding = OrderHistroyItemsBinding.inflate(layoutInflater, parent, false);
        return new OrderHistryAdapter.OrderHistryHolder(orderHistroyItemsBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderHistryHolder holder, int position) {
        OrderHistryModel model = orderlist.get(position);
        holder.binding.setOrderHistryData(model);

    }

    @Override
    public int getItemCount() {
        return orderlist.size();
    }

    public static class OrderHistryHolder extends RecyclerView.ViewHolder{

        OrderHistroyItemsBinding binding;
        public OrderHistryHolder(@NonNull OrderHistroyItemsBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

        }
    }
}
