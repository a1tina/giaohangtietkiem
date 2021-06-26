package com.example.ghtk;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.google.android.material.textfield.TextInputEditText;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

public class SignUpActivity extends AppCompatActivity {

    TextInputEditText textInputEditTextFullname, textInputEditTextEmail, textInputEditTextPassword;
    Button bSignUp;
    ProgressBar progressBar;
    private static final String URL = "http://localhost:9999/auth/register";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        TextView tvSignIn = findViewById(R.id.tv_signin);
        textInputEditTextFullname = findViewById(R.id.tiet_fullname);
        textInputEditTextEmail = findViewById(R.id.tiet_email);
        textInputEditTextPassword = findViewById(R.id.tiet_password);
        bSignUp = findViewById(R.id.b_signup);
        progressBar = findViewById(R.id.progressbar);


        String text = "Đã có tài khoản? Đăng nhập tại đây";

        SpannableString ss = new SpannableString(text);
        ClickableSpan clickableSpan1 = new ClickableSpan() {
            @Override
            public void onClick(@NonNull View widget) {
                startActivity(new Intent(SignUpActivity.this, LoginActivity.class));
                finish();
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

        bSignUp.setOnClickListener(v -> {

            String fullname, email, password;
            fullname = String.valueOf(textInputEditTextFullname.getText());
            password = String.valueOf(textInputEditTextPassword.getText());
            email = String.valueOf(textInputEditTextEmail.getText());


            if (!fullname.equals("") && !email.equals("") && !password.equals("")) {
                progressBar.setVisibility(View.VISIBLE);
                Handler handler = new Handler(Looper.getMainLooper());
                handler.post(() -> {
                    String[] field = new String[3];
                    field[0] = "fullname";
                    field[1] = "password";
                    field[2] = "email";

                    String[] data = new String[3];
                    data[0] = fullname;
                    data[1] = password;
                    data[2] = email;
                    PutData putData = new PutData("http://172.20.1.173/LoginGHTK/signup.php", "POST", field, data);
                    if (putData.startPut()) {
                        if (putData.onComplete()) {
                            progressBar.setVisibility(View.GONE);
                            String result = putData.getResult();
                            if (result.equals("Sign up Success")) {
                                Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(SignUpActivity.this, LoginActivity.class));
                                finish();
                            } else {
                                Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });
            } else {
                Toast.makeText(getApplicationContext(), "Hãy điền vào tất cả các trường", Toast.LENGTH_SHORT).show();
            }




        });


    }


}