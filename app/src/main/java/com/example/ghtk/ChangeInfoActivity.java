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
import com.example.ghtk.databinding.ActivityChangeInfoBinding;


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
            Toast.makeText(ChangeInfoActivity.this, "Đã lưu", Toast.LENGTH_SHORT).show();
            finish();
        });

        binding.actvChangePaymentMethod.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(binding.actvChangePaymentMethod.getText().toString().equals("Thẻ VISA")){
                    binding.linearlayoutCardform.setVisibility(View.VISIBLE);
                }
            }
        });

    }
}