package com.example.ghtk;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageButton;

public class CODActivity extends AppCompatActivity {
    Button button1, button2, button3;
    ImageButton ibBack;
    WebView wv1, wv2, wv3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cod);

        button1 = findViewById(R.id.b_question1);
        button2 = findViewById(R.id.b_question2);
        button3 = findViewById(R.id.b_question3);
        ibBack = findViewById(R.id.ibBack);
        wv1 = findViewById(R.id.wv_1);
        wv2 = findViewById(R.id.wv_2);
        wv3 = findViewById(R.id.wv_3);

        wv1.getSettings().setJavaScriptEnabled(true);
        wv1.loadUrl("file:///android_asset/cod_q1.html");
        wv1.setVisibility(WebView.GONE);

        wv2.getSettings().setJavaScriptEnabled(true);
        wv2.loadUrl("file:///android_asset/cod_q2.html");
        wv2.setVisibility(WebView.GONE);

        wv3.getSettings().setJavaScriptEnabled(true);
        wv3.loadUrl("file:///android_asset/cod_q3.html");
        wv3.setVisibility(WebView.GONE);

        ibBack.setOnClickListener(v -> finish());

        button1.setOnClickListener(v -> {
            if(wv1.getTag() != "noclick") {
                wv1.setVisibility(WebView.VISIBLE);
                wv1.setTag("noclick");
            }
            else {
                wv1.setVisibility(WebView.GONE);
                wv1.setTag("clicked");
            }
        });

        button2.setOnClickListener(v -> {
            if(wv2.getTag() != "noclick") {
                wv2.setVisibility(WebView.VISIBLE);
                wv2.setTag("noclick");
            }
            else {
                wv2.setVisibility(WebView.GONE);
                wv2.setTag("clicked");
            }
        });

        button3.setOnClickListener(v -> {
            if(wv3.getTag() != "noclick") {
                wv3.setVisibility(WebView.VISIBLE);
                wv3.setTag("noclick");
            }
            else {
                wv3.setVisibility(WebView.GONE);
                wv3.setTag("clicked");
            }
        });

    }
}