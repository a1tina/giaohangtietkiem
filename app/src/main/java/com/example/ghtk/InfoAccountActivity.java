package com.example.ghtk;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ghtk.api.ApiClient;
import com.example.ghtk.storage.SharedPrefManager;
import com.example.ghtk.tools.NoLimitScreen;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InfoAccountActivity extends AppCompatActivity {
    private TextView tvProfileName, tvProfilePhoneNumber, tvProfileMail, tvPersonalAddress, bChangeInfo;
    private FirebaseAuth firebaseAuth;
    ImageButton ibBack;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_account);
        NoLimitScreen.apply(this);
        tvProfileName = findViewById(R.id.tv_personal_name);
        tvProfilePhoneNumber = findViewById(R.id.tv_personal_phone_number);
        tvProfileMail = findViewById(R.id.tv_personal_mail);
        tvPersonalAddress = findViewById(R.id.tv_personal_address);
        ibBack = findViewById(R.id.ibBack);
        bChangeInfo = findViewById(R.id.b_personal_info_change);

        bChangeInfo.setOnClickListener(v -> {
            startActivity(new Intent(this, ChangeInfoActivity.class));
        });


        firebaseAuth = FirebaseAuth.getInstance();



        ibBack.setOnClickListener(v -> finish());


    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
        if(firebaseUser == null) {
            LoginResult loginResult = SharedPrefManager.getInstance(this).getUser();
            String accessToken = loginResult.getAccessToken();
            Call<Customer> call = ApiClient
                    .getInstance().getApi().getProfile(accessToken);
            call.enqueue(new Callback<Customer>() {
                @Override
                public void onResponse(Call<Customer> call, Response<Customer> response) {
                    Customer customer = response.body();
                    SharedPrefManager.getInstance(InfoAccountActivity.this)
                            .saveProfile(customer.getProfile());
                    customer = SharedPrefManager.getInstance(InfoAccountActivity.this).getProfile();
                    tvProfileName.setText(customer.getTenKH());
                    tvProfileMail.setText(customer.getUsername());
                    tvProfilePhoneNumber.setText(customer.getSDT());
                    tvPersonalAddress.setText(customer.getDiaChi());
                }

                @Override
                public void onFailure(Call<Customer> call, Throwable t) {

                }
            });
        }
        else{
            String name = firebaseUser.getDisplayName();
            String email = firebaseUser.getEmail();
            String phone = firebaseUser.getPhoneNumber();
            tvProfileName.setText(name);
            tvProfilePhoneNumber.setText(phone);
            tvProfileMail.setText(email);
        }
    }

    private void checkUser() {
        //get current user
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
        if (firebaseUser != null) {
            String name = firebaseUser.getDisplayName();
            String email = firebaseUser.getEmail();
            String phone = firebaseUser.getPhoneNumber();
            tvProfileName.setText(name);
            tvProfilePhoneNumber.setText(phone);
            tvProfileMail.setText(email);
        }
        else if (SharedPrefManager.getInstance(this).isLoggedIn()) {
            LoginResult loginResult = SharedPrefManager.getInstance(this).getUser();
            tvProfileName.setText(loginResult.getCustomerName());
            tvProfileMail.setText(loginResult.getUsername());
        }
        else{
            startActivity(new Intent(this, LoginActivity.class));
        }

    }
}