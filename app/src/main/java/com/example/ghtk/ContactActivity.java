package com.example.ghtk;

import android.os.Bundle;
import android.webkit.WebView;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class ContactActivity extends AppCompatActivity {

    ImageButton ibBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);


        WebView webView = (WebView) findViewById(R.id.wv);
        ibBack = findViewById(R.id.ibBack);

        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl("file:///android_asset/contact.html");

        ibBack.setOnClickListener(v -> finish());

    }

}