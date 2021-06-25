package com.example.ghtk.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ghtk.R;
import com.example.ghtk.models.OrderItem;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class OrderRecyclerAdapter extends RecyclerView.Adapter<OrderRecyclerAdapter.ViewHolder>{
    private ArrayList<OrderItem> arrayList;
    private Context context;

    public OrderRecyclerAdapter(ArrayList<OrderItem> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }
    public void updateList(ArrayList<OrderItem> filteredList){
        this.arrayList = filteredList;
    }
    @NonNull
    @NotNull
    @Override
    public OrderRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View layoutInflater = LayoutInflater.from(parent.getContext()).inflate(R.layout.order_item, null);
        TextView textView =  layoutInflater.findViewById(R.id.txt_state);
        Drawable stateDrawable = textView.getBackground();
        stateDrawable = DrawableCompat.wrap(stateDrawable);
        switch (viewType){
            case 1: DrawableCompat.setTint(stateDrawable, 0xFFCC0000);
                break;
            case 2: DrawableCompat.setTint(stateDrawable, 0xFFFFBB7F);
                break;
            case 3: DrawableCompat.setTint(stateDrawable, 0xFF00ad14);
                break;
            case 4: DrawableCompat.setTint(stateDrawable, 0xFFff7b00);
                break;
            case 5: DrawableCompat.setTint(stateDrawable, 0xFF4762ff);
                break;
            default:
                break;
        }
        textView.setBackground(stateDrawable);
        return new ViewHolder(layoutInflater);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull OrderRecyclerAdapter.ViewHolder holder, int position) {
        holder.cbx.setText(arrayList.get(position).getOrderId());
        String name_phone = arrayList.get(position).getName() + " - " + arrayList.get(position).getPhone();
        holder.name_phone.setText(name_phone);
        holder.address.setText(arrayList.get(position).getAddress());
        String state;
        switch (arrayList.get(position).getState()){
            case 0: state = "Chờ lấy";
                break;
            case 1:state = "Đã lấy";
                break;
            case 2:state = "Đang giao";
                break;
            case 3: state = "Giao thành công";
                break;
            case 4: state = "Đang duyệt hoàn";
                break;
            case 5: state = "Hoàn thành công";
                break;
            default: state = "";
                break;
        }
       holder.state.setText(state);
    }

    @Override
    public int getItemViewType(int position) {
        OrderItem orderItem = arrayList.get(position);
        return orderItem.getState();
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private CheckBox cbx;
        private TextView state, name_phone, address;
        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            cbx = itemView.findViewById(R.id.cbx_orderId);
            state = itemView.findViewById(R.id.txt_state);
            name_phone = itemView.findViewById(R.id.txt_name);
            address = itemView.findViewById(R.id.txt_address);
        }
    }
}
