package com.example.ghtk;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Spanned;
import android.webkit.WebView;
import android.widget.ImageButton;

import com.example.ghtk.tools.NoLimitScreen;

import org.sufficientlysecure.htmltextview.HtmlFormatter;
import org.sufficientlysecure.htmltextview.HtmlFormatterBuilder;
import org.sufficientlysecure.htmltextview.HtmlTextView;

public class ContactActivity extends AppCompatActivity {

    ImageButton ibBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        NoLimitScreen.apply(this);

        WebView webView = (WebView) findViewById(R.id.wv);
        ibBack = findViewById(R.id.ibBack);

        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl("file:///android_asset/contact.html");

        ibBack.setOnClickListener(v -> finish());

    }

}