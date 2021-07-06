package com.example.ghtk;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ghtk.adapter.PackageItemAdapter;
import com.example.ghtk.api.ApiClient;
import com.example.ghtk.models.OrderDetail;
import com.example.ghtk.models.PackageInfo;
import com.example.ghtk.storage.SharedPrefManager;
import com.example.ghtk.tools.NoLimitScreen;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailsOrderActivity extends AppCompatActivity {
    private OrderDetail orderDetail;
    private ListView lst_package;
    private ImageButton btn_back;
    private List<PackageInfo> list;
    private String orderId;
    private TextView txt_name_receiver, txt_phone_receiver, txt_address_receiver, txt_address_sender, txt_name_sender,
            txt_phone_sender, txt_total_weight_data;
    private ImageView iv_image_package;
    private ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_order);
        NoLimitScreen.apply(this);
        btn_back = findViewById(R.id.imgBtn_back);
        lst_package = findViewById(R.id.lst_package);
        txt_name_receiver = findViewById(R.id.txt_name_receiver);
        txt_phone_receiver = findViewById(R.id.txt_phone_receiver);
        iv_image_package = findViewById(R.id.iv_image_package);
        txt_address_receiver = findViewById(R.id.txt_address_receiver);
        txt_address_sender = findViewById(R.id.txt_address);
        txt_name_sender = findViewById(R.id.txt_name);
        txt_phone_sender = findViewById(R.id.txt_phone);
        txt_total_weight_data = findViewById(R.id.txt_total_weight_data);
        //Init progress
        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Đang tải dữ liệu");
        progressDialog.setMessage("Vui lòng đợi...");
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        getIntentData();
        lst_package.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int action = event.getAction();
                switch (action) {
                    case MotionEvent.ACTION_DOWN:
                        // Disallow ScrollView to intercept touch events.
                        v.getParent().requestDisallowInterceptTouchEvent(true);
                        break;

                    case MotionEvent.ACTION_UP:
                        // Allow ScrollView to intercept touch events.
                        v.getParent().requestDisallowInterceptTouchEvent(false);
                        break;
                }

                // Handle ListView touch events.
                v.onTouchEvent(event);
                return true;
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        initData();
    }

    private void getIntentData() {
        Intent intent = getIntent();
        orderId = intent.getStringExtra("orderId");
        ((TextView)findViewById(R.id.txt_orderid_data)).setText(orderId);
    }

    private void initData() {
        list = new ArrayList<>();
        progressDialog.show();
        //Get accessToken
        LoginResult loginResult = SharedPrefManager.getInstance(DetailsOrderActivity.this).getUser();
        String accessToken = loginResult.getAccessToken();
        ApiClient.getInstance().getOrderApi().GetOrderDetailById(accessToken,Integer.parseInt(orderId)).enqueue(new Callback<OrderDetail>() {
            @Override
            public void onResponse(Call<OrderDetail> call, Response<OrderDetail> response) {
                progressDialog.dismiss();
                if(response.isSuccessful()){
                    orderDetail = response.body();
                    list.addAll(orderDetail.getDshanghoa());
                    setListViewAdapter();
                    setContent();
                }
            }

            @Override
            public void onFailure(Call<OrderDetail> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(DetailsOrderActivity.this, "Co loi xay ra", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setContent() {
        //Get user info
        Customer customer = SharedPrefManager.getInstance(DetailsOrderActivity.this).getProfile();
        //Assign data to view
        txt_name_sender.setText(customer.getTenKH());
        txt_phone_sender.setText(customer.getSDT());
        txt_address_sender.setText(customer.getSDT());
        txt_name_receiver.setText(orderDetail.getKhnhan().getName());
        txt_phone_receiver.setText(orderDetail.getKhnhan().getSdt());
        Picasso.get().load(orderDetail.getUrlImage()).placeholder(R.drawable.border_rectangle).into(iv_image_package);
        txt_address_receiver.setText(orderDetail.getDiachinhan());
        txt_address_sender.setText(orderDetail.getDiachidi());
        float totalWeight = 0;
        for(PackageInfo p : list){
            totalWeight += p.getCannang() * p.getSoluong();
        }
        txt_total_weight_data.setText(String.valueOf(totalWeight) + " kg");
    }

    private void setListViewAdapter() {
        lst_package = findViewById(R.id.lst_package);
        PackageItemAdapter adapter = new PackageItemAdapter(list, this);
        lst_package.setAdapter(adapter);
    }
}