package com.example.ghtk.QCModel;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ghtk.CreateOrderActivity;
import com.example.ghtk.R;

public class QC2Activity extends AppCompatActivity {
    WebView webViewContent;
    Button bCreateOrder;
    ImageButton ibBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qc2);

        webViewContent = findViewById(R.id.wv_content);
        ibBack = findViewById(R.id.ibBack);
        bCreateOrder = findViewById(R.id.b_create_order);

        webViewContent.getSettings().setJavaScriptEnabled(true);
        webViewContent.loadUrl("file:///android_asset/qc2.html");

        ibBack.setOnClickListener(v -> finish());
        bCreateOrder.setOnClickListener(v -> {
            startActivity(new Intent(QC2Activity.this, CreateOrderActivity.class));
            finish();
        });

    }
}