package com.example.ghtk;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class InfoAccountActivity extends AppCompatActivity {
    private TextView tvProfileName, tvProfilePhoneNumber, tvProfileMail;
    private FirebaseAuth firebaseAuth;
    ImageButton ibBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_account);

        tvProfileName = findViewById(R.id.tv_personal_name);
        tvProfilePhoneNumber = findViewById(R.id.tv_personal_phone_number);
        tvProfileMail = findViewById(R.id.tv_personal_mail);
        ibBack = findViewById(R.id.ibBack);

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