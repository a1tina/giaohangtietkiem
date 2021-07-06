package com.example.ghtk.fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ghtk.LoginResult;
import com.example.ghtk.R;
import com.example.ghtk.adapter.OrderRecyclerAdapter;
import com.example.ghtk.api.ApiClient;
import com.example.ghtk.databinding.ActivityBillBinding;
import com.example.ghtk.listener.CustomEventListener;
import com.example.ghtk.models.Order;
import com.example.ghtk.storage.SharedPrefManager;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BillActivityFragment_2 extends Fragment {
    private View.OnClickListener listener;
    private RecyclerView recyclerView;
    private CheckBox cbx;
    private OrderRecyclerAdapter orderRecyclerAdapter;
    private ArrayList<Order> arrayList;
    private ArrayList<Order> selectedArrayList;
    private View view;
    private ActivityBillBinding activityBillBinding;
    private ProgressDialog progressDialog;
    private CustomEventListener customEventListener;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public BillActivityFragment_2(ActivityBillBinding binding) {
        // Required empty public constructor
        activityBillBinding = binding;
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
        view = inflater.inflate(R.layout.fragment_bill_activity_2, container, false);
        LinearLayout linear = view.findViewById(R.id.linearlayout_receiver);
        cbx = view.findViewById(R.id.cbx_number_order);
        //Set color for button all
        view.findViewById(R.id.btn_all_receive).setBackgroundResource(R.drawable.button_activated);
        ((AppCompatButton)view.findViewById(R.id.btn_all_receive)).setTextColor(getResources().getColor(android.R.color.white));
        //Initial recyclerview
        recyclerView = view.findViewById(R.id.recyclerview_receiver);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        //Init progress
        progressDialog = new ProgressDialog(getContext());
        progressDialog.setTitle("Đang tải dữ liệu");
        progressDialog.setMessage("Vui lòng đợi...");
        listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickEventHandler(v, linear);
            }
        };
        customEventListener = list -> Toast.makeText(getContext(), list.get(0), Toast.LENGTH_SHORT).show();
        customEventListener = list -> {
            //Toast.makeText(getContext(), list.get(0), Toast.LENGTH_SHORT).show();
            String accessToken = SharedPrefManager.getInstance(getContext()).getUser().getAccessToken();
            for(String s : list){
                arrayList.removeIf(item -> item.getMadonhang() == s);
                ApiClient.getInstance().getOrderApi().DeleteOrderDetailById(accessToken, Integer.parseInt(s))
                        .enqueue(new Callback<String>() {
                            @Override
                            public void onResponse(Call<String> call, Response<String> response) { }
                            @Override
                            public void onFailure(Call<String> call, Throwable t) { }
                        });
            }
        };
        setListener(linear);
        return view;
    }


    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initData();
    }

    private void initData(){
        progressDialog.show();
        arrayList = new ArrayList<>();
        selectedArrayList = new ArrayList<>();
        //Get access Token
        LoginResult loginResult = SharedPrefManager.getInstance(getContext()).getUser();
        String accessToken = loginResult.getAccessToken();
        ApiClient.getInstance().getOrderApi().GetOrderByIdReceiver(accessToken).enqueue(new Callback<List<Order>>() {
            @Override
            public void onResponse(Call<List<Order>> call, Response<List<Order>> response) {
                progressDialog.dismiss();
                List<Order> list = response.body();
                if(list != null && response.isSuccessful()) {
                    for(Order item : list){
                        if(item.getTrangthai() > 1 && item.getTrangthai() < 6)
                            arrayList.add(item);
                    }
                    setAdapter();
                }
                cbx.setText(String.format("%d đơn hàng", arrayList.size()));
                checkNumberOrder(view, arrayList);
            }

            @Override
            public void onFailure(Call<List<Order>> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(getContext(), "Có lỗi xảy ra", Toast.LENGTH_SHORT).show();
            }
        });

    }
    private void setAdapter(){
        orderRecyclerAdapter = new OrderRecyclerAdapter(arrayList, getContext(), activityBillBinding, view, customEventListener);
        recyclerView.setAdapter(orderRecyclerAdapter);
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
        if(btn.getId() == R.id.btn_all_receive){
            selectedArrayList = (ArrayList<Order>) arrayList.clone();
        }
        else{
            int flag = 3;
            selectedArrayList.clear();
            switch (btn.getId()){
                case R.id.btn_success: flag = 4;
                    break;
                case R.id.btn_wait_return: flag = 5;
                    break;
                case R.id.btn_returned: flag = 6;
                    break;
                default:
                    break;
            }
            for(Order item : arrayList){
                if(item.getTrangthai() == flag)
                    selectedArrayList.add(item);
            }
        }
        orderRecyclerAdapter.updateList(selectedArrayList);
        orderRecyclerAdapter.notifyDataSetChanged();
        checkNumberOrder(view, selectedArrayList);
        cbx.setText(String.format("%d đơn hàng", selectedArrayList.size()));
    }

    private void checkNumberOrder(View v, ArrayList<Order> list){
        if(list.size() > 0){
            view.findViewById(R.id.nodata_layout).setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
        }
        else{
            recyclerView.setVisibility(View.GONE);
            view.findViewById(R.id.nodata_layout).setVisibility(View.VISIBLE);
        }
    }
}