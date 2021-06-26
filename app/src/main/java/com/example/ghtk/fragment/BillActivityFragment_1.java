package com.example.ghtk.fragment;

import android.os.Bundle;

import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.ghtk.R;
import com.example.ghtk.adapter.OrderRecyclerAdapter;
import com.example.ghtk.constant.OrderState;
import com.example.ghtk.models.OrderItem;

import java.util.ArrayList;
import java.util.stream.Collectors;


public class BillActivityFragment_1 extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private View.OnClickListener listener;
    private RecyclerView recyclerView;
    private CheckBox cbx;
    private OrderRecyclerAdapter orderRecyclerAdapter;
    private ArrayList<OrderItem> arrayList;
    private ArrayList<OrderItem> selectedArrayList;
    private View view;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public BillActivityFragment_1() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_bill_activity_1, container, false);
        LinearLayout linear = (LinearLayout) view.findViewById(R.id.linearlayout_sender);
        cbx = view.findViewById(R.id.cbx_number_order);
        view.findViewById(R.id.btn_all).setBackgroundResource(R.drawable.button_activated);
        ((AppCompatButton)view.findViewById(R.id.btn_all)).setTextColor(getResources().getColor(android.R.color.white));
        recyclerView = view.findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        initData();
        setAdapter();
        checkNumberOrder(view);
        listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickEventHandler(v, linear);
            }
        };
        setListener(linear);
        return view;
    }
    private void initData(){
        arrayList = new ArrayList<>();
        arrayList.add(new OrderItem("1672014177948", "Nguyễn Thị B", "0345678910", "P.Linh Trung - Q.Thủ Đức - TP.HCM", OrderState.WAITING));
        arrayList.add(new OrderItem("1672014177949", "Nguyễn Quốc C", "0345228350", "P.Lộc Phát - TP.Bảo Lộc - T.Lâm Đồng", OrderState.DELIVERING));
        arrayList.add(new OrderItem("1672014177950", "Lê Thị D", "0345678252", "TT.Ma Lâm- H.Hàm Thuận Bắc - T.Bình Thuận", OrderState.DELIVERY_SUCCESS));
        arrayList.add(new OrderItem("1672014177951", "Phạm Văn E", "0345226321", " X.La Nan - H.Đức Cơ - T.Gia Lai", OrderState.TAKEN));
        selectedArrayList = new ArrayList<>(arrayList);
        cbx.setText(String.format("%d đơn hàng", selectedArrayList.size()));
    }
    private void setAdapter(){
        orderRecyclerAdapter = new OrderRecyclerAdapter(selectedArrayList, getContext());
        recyclerView.setAdapter(orderRecyclerAdapter);
        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(itemDecoration);
    }
    private void setListener(LinearLayout linear){
        for(int i = 0; i < linear.getChildCount(); i++){
            linear.getChildAt(i).setOnClickListener(this.listener);
        }
    }

    private void onClickEventHandler(View v, LinearLayout linear){
        AppCompatButton btn = (AppCompatButton) v;
        for(int i = 0; i < linear.getChildCount(); i++){
            linear.getChildAt(i).setBackgroundResource(R.drawable.button_style);
            ((AppCompatButton)linear.getChildAt(i)).setTextColor(getResources().getColor(android.R.color.black));
        }
        btn.setBackgroundResource(R.drawable.button_activated);
        btn.setTextColor(getResources().getColor(android.R.color.white));
        if(btn.getId() == R.id.btn_all){
            selectedArrayList = (ArrayList<OrderItem>) arrayList.clone();
        }
        else{
            int flag = 0;
            selectedArrayList.clear();
            switch (btn.getId()){
                case R.id.btn_got: flag = 1;
                    break;
                case R.id.btn_delivering: flag = 2;
                    break;
                case R.id.btn_success: flag = 3;
                    break;
                case R.id.btn_wait_return: flag = 4;
                    break;
                case R.id.btn_returned: flag = 5;
                    break;
                default:
                    break;
            }
            for(OrderItem item : arrayList){
                if(item.getState() == flag)
                    selectedArrayList.add(item);
            }
        }
        orderRecyclerAdapter.updateList(selectedArrayList);
        orderRecyclerAdapter.notifyDataSetChanged();
        checkNumberOrder(view);
        cbx.setText(String.format("%d đơn hàng", selectedArrayList.size()));
    }

    private void checkNumberOrder(View v){
        if(selectedArrayList.size() > 0){
            view.findViewById(R.id.img_nothing).setVisibility(View.INVISIBLE);
            view.findViewById(R.id.txt_nothing).setVisibility(View.INVISIBLE);
            recyclerView.setVisibility(View.VISIBLE);
        }
        else{
            recyclerView.setVisibility(View.INVISIBLE);
            view.findViewById(R.id.img_nothing).setVisibility(View.VISIBLE);
            view.findViewById(R.id.txt_nothing).setVisibility(View.VISIBLE);
        }
    }
}