package com.example.ghtk;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.ghtk.adapter.ViewPageAdapter_Bill;
import com.example.ghtk.databinding.ActivityBillBinding;
import com.example.ghtk.tools.NoLimitScreen;


public class BillActivity extends AppCompatActivity {
    private ActivityBillBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityBillBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        NoLimitScreen.apply(this);
        binding.imgBtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        setAdapter();
    }
    private void setAdapter(){
        ViewPageAdapter_Bill adapter = new ViewPageAdapter_Bill(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT, binding);
        binding.viewpagerBill.setAdapter(adapter);
        binding.tablayoutBill.setupWithViewPager(binding.viewpagerBill);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

    }
}