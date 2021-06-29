package com.example.ghtk;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ghtk.adapter.PackageItemAdapter;
import com.example.ghtk.models.PackageInfo;
import com.example.ghtk.tools.NoLimitScreen;

import java.util.ArrayList;
import java.util.List;

public class DetailsOrderActivity extends AppCompatActivity {
    private ListView lst_package;
    private ImageButton btn_back;
    private List<PackageInfo> list;
    private String orderId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_order);
        NoLimitScreen.apply(this);
        initData();
        setListViewAdapter();
        btn_back = findViewById(R.id.imgBtn_back);
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

    private void getIntentData() {
        Intent intent = getIntent();
        orderId = intent.getStringExtra("orderId");
        ((TextView)findViewById(R.id.txt_orderid_data)).setText(orderId);
    }

    private void initData() {
        list = new ArrayList<PackageInfo>();
        list.add(new PackageInfo("Xe đẩy em bé", "https://via.placeholder.com/150", 20F,1));
        list.add(new PackageInfo("Dầu ăn", "https://via.placeholder.com/150", 5F, 3));
        list.add(new PackageInfo("Dầu ăn", "https://via.placeholder.com/150", 5F, 3));
    }

    private void setListViewAdapter() {
        lst_package = findViewById(R.id.lst_package);
        PackageItemAdapter adapter = new PackageItemAdapter(list, this);
        lst_package.setAdapter(adapter);
    }
}