package com.example.ghtk;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class QC1Activity extends AppCompatActivity {
    WebView webViewContent;
    ImageButton ibBack;
    Button bSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qc1);

        webViewContent = findViewById(R.id.wv_content);
        ibBack = findViewById(R.id.ibBack);
        bSignUp = findViewById(R.id.b_signup);

        webViewContent.getSettings().setJavaScriptEnabled(true);
        webViewContent.loadUrl("file:///android_asset/qc1.html");

        ibBack.setOnClickListener(v -> finish());
        bSignUp.setOnClickListener(v -> {
            startActivity(new Intent(QC1Activity.this, SignUpActivity.class));
            finish();
        });


    }
}