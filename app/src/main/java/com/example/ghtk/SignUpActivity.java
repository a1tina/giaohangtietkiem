package com.example.ghtk;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.TextView;

public class SignUpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        TextView tvSignIn = findViewById(R.id.tv_signin);

        String text = "Đã có tài khoản? Đăng nhập tại đây";

        SpannableString ss = new SpannableString(text);
        ClickableSpan clickableSpan1 = new ClickableSpan() {
            @Override
            public void onClick(@NonNull View widget) {
                startActivity(new Intent(SignUpActivity.this, LoginActivity.class));
            }

            @Override
            public void updateDrawState(@NonNull TextPaint ds) {
                super.updateDrawState(ds);
                ds.setColor(Color.BLUE);
                ds.setUnderlineText(true);
            }
        };
        ss.setSpan(clickableSpan1, 26, 34, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        tvSignIn.setText(ss);
        tvSignIn.setMovementMethod(LinkMovementMethod.getInstance());
        tvSignIn.setHighlightColor(ContextCompat.getColor(this, R.color.color9F5A7B));

    }
}