package com.example.ghtk;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class QC4Activity extends AppCompatActivity {
    WebView webViewContent;
    Button bCreateOrder;
    ImageButton ibBack;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qc4);

        webViewContent = findViewById(R.id.wv_content);
        ibBack = findViewById(R.id.ibBack);
        bCreateOrder = findViewById(R.id.b_create_order);

        webViewContent.getSettings().setJavaScriptEnabled(true);
        webViewContent.loadUrl("file:///android_asset/qc3.html");

        ibBack.setOnClickListener(v -> finish());
        bCreateOrder.setOnClickListener(v -> {
            startActivity(new Intent(QC4Activity.this, CreateOrderActivity.class));
            finish();
        });

    }
}