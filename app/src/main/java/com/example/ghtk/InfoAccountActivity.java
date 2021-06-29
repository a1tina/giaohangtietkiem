package com.example.ghtk;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.ghtk.tools.NoLimitScreen;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class InfoAccountActivity extends AppCompatActivity {
    private TextView tvProfileName, tvProfilePhoneNumber, tvProfileMail, bChangeInfo;
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
        ibBack = findViewById(R.id.ibBack);
        bChangeInfo = findViewById(R.id.b_personal_info_change);

        bChangeInfo.setOnClickListener(v -> {
            startActivity(new Intent(this, ChangeInfoActivity.class));
        });

        firebaseAuth = FirebaseAuth.getInstance();
        checkUser();

        ibBack.setOnClickListener(v -> finish());


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
        else{
            startActivity(new Intent(this, LoginActivity.class));
        }

    }
}