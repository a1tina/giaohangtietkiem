package com.example.ghtk;

import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.braintreepayments.cardform.view.CardForm;
import com.example.ghtk.api.ApiClient;
import com.example.ghtk.storage.SharedPrefManager;
import com.google.android.material.textfield.TextInputEditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ChangeInfoActivity extends AppCompatActivity {
    EditText etChangeName, etChangeAddress, etChangePhoneNumber;
    TextInputEditText tietChangePassword;
    TextView tvChangeMail2;
    CardForm cardform;
    ImageButton ibBack;
    Button bSave;
    AutoCompleteTextView actvChangePaymentMethod;
    LinearLayout linearLayoutCardForm;
    Customer customer;
    @Override
    protected void onResume() {
        super.onResume();
        String[] paymentMethod = getResources().getStringArray(R.array.payment_method);
        ArrayAdapter  arrayAdapter = new ArrayAdapter(getApplicationContext(),R.layout.dropdown_item, paymentMethod);
        actvChangePaymentMethod.setAdapter(arrayAdapter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_info);
        actvChangePaymentMethod = findViewById(R.id.actv_change_payment_method);
        etChangeAddress = findViewById(R.id.et_change_address);
        etChangeName = findViewById(R.id.et_change_name);
        etChangePhoneNumber = findViewById(R.id.et_change_phone_number);
        cardform = findViewById(R.id.cardform);
        ibBack = findViewById(R.id.ibBack);
        tvChangeMail2 = findViewById(R.id.tv_change_mail2);
        tietChangePassword = findViewById(R.id.tiet_change_password);
        bSave = findViewById(R.id.b_save);
        linearLayoutCardForm = findViewById(R.id.linearlayout_cardform);


        LoginResult loginResult = SharedPrefManager.getInstance(this).getUser();
        etChangeName.setText(loginResult.getCustomerName());
        tvChangeMail2.setText(loginResult.getUsername());
        tietChangePassword.setText(loginResult.getPassword());


        cardform.cardRequired(true)
                .expirationRequired(true)
                .cvvRequired(true)
                .postalCodeRequired(true)
                .mobileNumberRequired(true)
                .mobileNumberExplanation("Tin nhắn xác nhận sẽ được gửi tới SĐT này")
                .setup(ChangeInfoActivity.this);
        cardform.getCvvEditText().setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_VARIATION_PASSWORD);

        ibBack.setOnClickListener(v -> finish());

        bSave.setOnClickListener(v -> {
            saveInfo();
            Toast.makeText(ChangeInfoActivity.this, "Đã lưu", Toast.LENGTH_SHORT).show();
            finish();
        });

        actvChangePaymentMethod.setOnItemClickListener((parent, view, position, id) -> {
            if(actvChangePaymentMethod.getText().toString().equals("Thẻ VISA")){
                linearLayoutCardForm.setVisibility(View.VISIBLE);
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        //Customer customer = SharedPrefManager.getInstance(this).getProfile();
        LoginResult loginResult = SharedPrefManager.getInstance(ChangeInfoActivity.this).getUser();
        String accessToken = loginResult.getAccessToken();
        ApiClient.getInstance().getApi().getProfile(accessToken).enqueue(new Callback<Customer>() {
            @Override
            public void onResponse(Call<Customer> call, Response<Customer> response) {
                Customer customer = response.body();
                etChangeName.setText(customer.getTenKH());
                tvChangeMail2.setText(customer.getUsername());
                tietChangePassword.setText(customer.getPassword());
                etChangeAddress.setText(customer.getDiaChi());
                etChangePhoneNumber.setText(customer.getSDT());
            }
            @Override
            public void onFailure(Call<Customer> call, Throwable t) { }
        });
    }

    private void saveInfo() {
        String email = tvChangeMail2.getText().toString().trim();
        String password = tietChangePassword.getText().toString().trim();
        String name = etChangeName.getText().toString().trim();
        String address = etChangeAddress.getText().toString().trim();
        String phone = etChangePhoneNumber.getText().toString().trim();
        LoginResult loginResult = SharedPrefManager.getInstance(this).getUser();
        String accessToken = loginResult.getAccessToken();

        Call<Customer> call = ApiClient
                .getInstance().getApi().updateProfile(accessToken, name, phone, address);
        call.enqueue(new Callback<Customer>() {
            @Override
            public void onResponse(Call<Customer> call, Response<Customer> response) {
                customer = response.body();
                SharedPrefManager.getInstance(ChangeInfoActivity.this)
                        .saveProfile(customer.getProfile());
            }

            @Override
            public void onFailure(Call<Customer> call, Throwable t) {
            }
        });

    }
}