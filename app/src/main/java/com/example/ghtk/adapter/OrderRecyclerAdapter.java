package com.example.ghtk.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ghtk.R;
import com.example.ghtk.databinding.ActivityBillBinding;
import com.example.ghtk.models.OrderItem;
import com.example.ghtk.viewmodels.OrderViewModel;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class OrderRecyclerAdapter extends RecyclerView.Adapter<OrderRecyclerAdapter.ViewHolder>{
    private ArrayList<OrderItem> arrayList;
    private Context context;
    private OrderViewModel orderViewModel;
    private boolean isEnable = false;
    private boolean isSelectAll = false;
    private ArrayList<String> selectList;
    private ActivityBillBinding activityBillBinding;
    private View fragment_view1;

    public OrderRecyclerAdapter(ArrayList<OrderItem> arrayList, Context context, ActivityBillBinding binding, View v) {
        this.arrayList = arrayList;
        this.context = context;
        selectList = new ArrayList<>();
        activityBillBinding = binding;
        fragment_view1 = v;
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
        orderViewModel = new ViewModelProvider((FragmentActivity)context).get(OrderViewModel.class);
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

        activityBillBinding.imgBtnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activityBillBinding.selectLayout.setVisibility(View.GONE);
                activityBillBinding.normalLayout.setVisibility(View.VISIBLE);
                ((CheckBox)fragment_view1.findViewById(R.id.cbx_number_order)).setChecked(false);
                isEnable = false;
                isSelectAll = false;
                selectList.clear();
                notifyDataSetChanged();
            }
        });
        activityBillBinding.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for(String s : selectList){
                    arrayList.removeIf(i -> (i.getOrderId() == s));
                }
                if(arrayList.size() == 0)
                    fragment_view1.findViewById(R.id.nodata_layout).setVisibility(View.VISIBLE);
                activityBillBinding.imgBtnClose.callOnClick();
                ((CheckBox)fragment_view1.findViewById(R.id.cbx_number_order)).setText(String.format("%s đơn hàng", arrayList.size()));
                notifyDataSetChanged();
            }
        });
        fragment_view1.findViewById(R.id.cbx_number_order).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(selectList.size() == arrayList.size()){
                    isSelectAll = false;
                    selectList.clear();
                }
                else{
                    isSelectAll = true;
                    selectList.clear();
                    for(OrderItem item : arrayList){
                        selectList.add(item.getOrderId());
                    }
                }
                orderViewModel.setText(String.valueOf(selectList.size()));
                if(!isEnable) {
                    isEnable = true;
                    activityBillBinding.normalLayout.setVisibility(View.GONE);
                    activityBillBinding.selectLayout.setVisibility(View.VISIBLE);
                    orderViewModel.getText().observe((FragmentActivity)context, new Observer<String>() {
                        @Override
                        public void onChanged(String s) {
                            activityBillBinding.selectTitle.setText(String.format("Chọn %s đơn hàng", s));
                        }
                    });
                }
                notifyDataSetChanged();
            }
        });
        holder.cbx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isEnable) {
                    isEnable = true;
                    activityBillBinding.normalLayout.setVisibility(View.GONE);
                    activityBillBinding.selectLayout.setVisibility(View.VISIBLE);
                    clickItem(holder);
                    orderViewModel.getText().observe((FragmentActivity)context, new Observer<String>() {
                        @Override
                        public void onChanged(String s) {
                            activityBillBinding.selectTitle.setText(String.format("Chọn %s đơn hàng", s));
                        }
                    });
                }else {
                    clickItem(holder);
                }
            }
        });
        if(!isSelectAll)
            holder.cbx.setChecked(false);
        else
            holder.cbx.setChecked(true);
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

    private void clickItem(ViewHolder holder){
        String s = arrayList.get(holder.getAdapterPosition()).getOrderId();
        if(holder.cbx.isChecked() == false){
            selectList.remove(s);
        }else {
            selectList.add(s);
        }
        if(selectList.size() == arrayList.size())
            ((CheckBox)fragment_view1.findViewById(R.id.cbx_number_order)).setChecked(true);
        orderViewModel.setText(String.valueOf(selectList.size()));
    }
}
