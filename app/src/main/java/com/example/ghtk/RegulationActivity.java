package com.example.ghtk;

import android.os.Bundle;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ghtk.tools.NoLimitScreen;

public class RegulationActivity extends AppCompatActivity {
    Button button1, button2, button3, button4;
    ImageButton ibBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regulation);
        NoLimitScreen.apply(this);
        WebView wv1 = (WebView) findViewById(R.id.wv_1);
        WebView wv4 = (WebView) findViewById(R.id.wv_4);
        WebView wv3 = (WebView) findViewById(R.id.wv_3);
        WebView wv2 = (WebView) findViewById(R.id.wv_2);
        button1 = findViewById(R.id.b_question1);
        button2 = findViewById(R.id.b_question2);
        button3 = findViewById(R.id.b_question3);
        button4 = findViewById(R.id.b_question4);
        ibBack = findViewById(R.id.ibBack);

        wv1.getSettings().setJavaScriptEnabled(true);
        wv1.loadUrl("file:///android_asset/question1.html");
        wv1.setVisibility(WebView.GONE);

        wv2.getSettings().setJavaScriptEnabled(true);
        wv2.loadUrl("file:///android_asset/question2.html");
        wv2.setVisibility(WebView.GONE);

        wv3.getSettings().setJavaScriptEnabled(true);
        wv3.loadUrl("file:///android_asset/question3.html");
        wv3.setVisibility(WebView.GONE);

        wv4.getSettings().setJavaScriptEnabled(true);
        wv4.loadUrl("file:///android_asset/question4.html");
        wv4.setVisibility(WebView.GONE);

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

        button4.setOnClickListener(v -> {
            if(wv4.getTag() != "noclick") {
                wv4.setVisibility(WebView.VISIBLE);
                wv4.setTag("noclick");
            }
            else {
                wv4.setVisibility(WebView.GONE);
                wv4.setTag("clicked");
            }
        });

    }
}