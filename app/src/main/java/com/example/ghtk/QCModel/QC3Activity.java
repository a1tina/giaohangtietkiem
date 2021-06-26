package com.example.ghtk.QCModel;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ghtk.R;
import com.example.ghtk.SignUpActivity;

public class QC3Activity extends AppCompatActivity {
    WebView webViewContent;
    Button bSignUp;
    ImageButton ibBack;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qc3);

        webViewContent = findViewById(R.id.wv_content);
        ibBack = findViewById(R.id.ibBack);
        bSignUp = findViewById(R.id.b_signup);

        webViewContent.getSettings().setJavaScriptEnabled(true);
        webViewContent.loadUrl("file:///android_asset/qc3.html");

        ibBack.setOnClickListener(v -> finish());
        bSignUp.setOnClickListener(v -> {
            startActivity(new Intent(QC3Activity.this, SignUpActivity.class));
            finish();
        });

    }
}
