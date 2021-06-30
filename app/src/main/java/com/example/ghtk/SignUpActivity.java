package com.example.ghtk;

import android.app.ProgressDialog;
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
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.example.ghtk.api.ApiClient;
import com.example.ghtk.api.ApiService;
import com.example.ghtk.storage.SharedPrefManager;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpActivity extends AppCompatActivity {
    TextInputLayout textInputLayoutFullname, textInputLayoutEmail, textInputLayoutPassword;
    TextInputEditText textInputEditTextFullname, textInputEditTextEmail, textInputEditTextPassword;
    Button bSignUp;
    ProgressBar progressBar;
    ProgressDialog progressDialog;
    private static final Pattern PASSWORD_PATTERN =
            Pattern.compile("^" +
                    //"(?=.*[0-9])" +         //at least 1 digit
                    //"(?=.*[a-z])" +         //at least 1 lower case letter
                    //"(?=.*[A-Z])" +         //at least 1 upper case letter
                    "(?=.*[a-zA-Z])" +      //any letter
                    "(?=.*[@#$%^&+=])" +    //at least 1 special character
                    "(?=\\S+$)" +           //no white spaces
                    ".{4,}" +               //at least 4 characters
                    "$");



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        TextView tvSignIn = findViewById(R.id.tv_signin);
        textInputLayoutEmail = findViewById(R.id.til_email);
        textInputLayoutFullname = findViewById(R.id.til_fullname);
        textInputLayoutPassword = findViewById(R.id.til_password);
        textInputEditTextFullname = findViewById(R.id.tiet_fullname);
        textInputEditTextEmail = findViewById(R.id.tiet_email);
        textInputEditTextPassword = findViewById(R.id.tiet_password);
        bSignUp = findViewById(R.id.b_signup);
        progressBar = findViewById(R.id.progressbar);

        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Đang xử lý");
        progressDialog.setMessage("Vui lòng đợi");





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
            if(!validateEmail() | !validatePassword() | !validateFullname()){
                return;
            }
            signUp();
        });


    }

    private void signUp() {
        String fullname = textInputEditTextFullname.getText().toString().trim();
        String email = textInputEditTextEmail.getText().toString().trim();
        String password = textInputEditTextPassword.getText().toString().trim();


        progressDialog.show();
        Call<LoginResult> call = ApiClient
                .getInstance()
                .getApi()
                .createUser(email, password);
        call.enqueue(new Callback<LoginResult>() {
            @Override
            public void onResponse(Call<LoginResult> call, Response<LoginResult> response) {
                LoginResult loginResult = response.body();
                Call<Customer> call2 = ApiClient
                        .getInstance()
                        .getApi()
                        .updateProfile(loginResult.getAccessToken(), fullname, "0123456789", "TPHCM");
                call2.enqueue(new Callback<Customer>() {
                    @Override
                    public void onResponse(Call<Customer> call, Response<Customer> response) {
                        Customer customer = response.body();
                        SharedPrefManager.getInstance(SignUpActivity.this)
                                .saveProfile(customer.getProfile());
                    }

                    @Override
                    public void onFailure(Call<Customer> call, Throwable t) {

                    }
                });

                Toast.makeText(SignUpActivity.this, "Đăng ký thành công", Toast.LENGTH_SHORT).show();
                progressDialog.hide();
                startActivity(new Intent(SignUpActivity.this, LoginActivity.class));
                finish();
            }

            @Override
            public void onFailure(Call<LoginResult> call, Throwable t) {
                Toast.makeText(SignUpActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private boolean validateFullname(){
        String emailInput = textInputLayoutFullname.getEditText().getText().toString().trim();
        if(emailInput.isEmpty()) {
            textInputLayoutFullname.setError("Họ và tên không được để trống");
            return false;
        }
        else {
            textInputEditTextFullname.setError(null);
            return true;
        }
    }

    private boolean validateEmail(){
        String emailInput = textInputLayoutEmail.getEditText().getText().toString().trim();
        if(emailInput.isEmpty()){
            textInputLayoutEmail.setError("Email không được để trống");
            return false;
        }else if(!Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()){
            textInputLayoutEmail.setError("Vui lòng điền đúng định dạng email");
            return false;
        }
        else {
            textInputLayoutEmail.setError(null);
            return true;
        }
    }

    private boolean validatePassword(){
        String passwordInput = textInputLayoutPassword.getEditText().getText().toString().trim();
        if(passwordInput.isEmpty()){
            textInputLayoutPassword.setError("Không được để trống mật khẩu!");
            return false;
        }else if(!PASSWORD_PATTERN.matcher(passwordInput).matches()){
            textInputLayoutPassword.setError("Mật khẩu quá yếu");
            return false;
        }
        else {
            textInputLayoutPassword.setError(null);
            return true;
        }
    }

    public void confirmInput(View v){
        if(!validateEmail() | !validatePassword()){
            return;
        }

    }



}