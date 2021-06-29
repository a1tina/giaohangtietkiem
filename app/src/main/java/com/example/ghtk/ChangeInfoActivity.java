package com.example.ghtk;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.braintreepayments.cardform.view.CardForm;
import com.example.ghtk.api.ApiClient;
import com.example.ghtk.databinding.ActivityChangeInfoBinding;
import com.example.ghtk.storage.SharedPrefManager;
import com.google.android.gms.common.api.Api;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ChangeInfoActivity extends AppCompatActivity {
    ActivityChangeInfoBinding binding;
    @Override
    protected void onResume() {
        super.onResume();
        String[] paymentMethod = getResources().getStringArray(R.array.payment_method);
        ArrayAdapter  arrayAdapter = new ArrayAdapter(getApplicationContext(),R.layout.dropdown_item, paymentMethod);
        binding.actvChangePaymentMethod.setAdapter(arrayAdapter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityChangeInfoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        LoginResult loginResult = SharedPrefManager.getInstance(this).getUser();
        binding.etChangeName.setText(loginResult.getCustomerName());
        binding.tvChangeMail2.setText(loginResult.getUsername());
        binding.tietChangePassword.setText(loginResult.getPassword());


        binding.cardform.cardRequired(true)
                .expirationRequired(true)
                .cvvRequired(true)
                .postalCodeRequired(true)
                .mobileNumberRequired(true)
                .mobileNumberExplanation("Tin nhắn xác nhận sẽ được gửi tới SĐT này")
                .setup(ChangeInfoActivity.this);
        binding.cardform.getCvvEditText().setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_VARIATION_PASSWORD);

        binding.ibBack.setOnClickListener(v -> finish());

        binding.bSave.setOnClickListener(v -> {
            saveInfo();
            Toast.makeText(ChangeInfoActivity.this, "Đã lưu", Toast.LENGTH_SHORT).show();
            finish();
        });

        binding.actvChangePaymentMethod.setOnItemClickListener((parent, view, position, id) -> {
            if(binding.actvChangePaymentMethod.getText().toString().equals("Thẻ VISA")){
                binding.linearlayoutCardform.setVisibility(View.VISIBLE);
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        Customer customer = SharedPrefManager.getInstance(this).getProfile();
        binding.etChangeName.setText(customer.getTenKH());
        binding.tvChangeMail2.setText(customer.getUsername());
        binding.tietChangePassword.setText(customer.getPassword());
        binding.etChangeAddress.setText(customer.getDiaChi());
        binding.etChangePhoneNumber.setText(customer.getSDT());

    }

    private void saveInfo() {
        String email = binding.tvChangeMail2.getText().toString().trim();
        String password = binding.tietChangePassword.getText().toString().trim();
        String name = binding.etChangeName.getText().toString().trim();
        String address = binding.etChangeAddress.getText().toString().trim();
        String phone = binding.etChangePhoneNumber.getText().toString().trim();
        LoginResult loginResult = SharedPrefManager.getInstance(this).getUser();
        String accessToken = loginResult.getAccessToken();

        Call<LoginResult> call = ApiClient
                .getInstance().getApi().updateProfile(accessToken, name, phone, address);
        call.enqueue(new Callback<LoginResult>() {
            @Override
            public void onResponse(Call<LoginResult> call, Response<LoginResult> response) {
                LoginResult loginResult1 = response.body();
                SharedPrefManager.getInstance(ChangeInfoActivity.this)
                        .saveUser(loginResult.getUser());
            }

            @Override
            public void onFailure(Call<LoginResult> call, Throwable t) {
                Toast.makeText(ChangeInfoActivity.this, "Cập nhật thông tin thất bại", Toast.LENGTH_SHORT).show();

            }
        });



    }
}