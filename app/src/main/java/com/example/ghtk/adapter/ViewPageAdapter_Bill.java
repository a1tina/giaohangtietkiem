package com.example.ghtk.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.ghtk.databinding.ActivityBillBinding;
import com.example.ghtk.fragment.BillActivityFragment_1;
import com.example.ghtk.fragment.BillActivityFragment_2;

import org.jetbrains.annotations.NotNull;

public class ViewPageAdapter_Bill extends FragmentStatePagerAdapter {
    private ActivityBillBinding activityBillBinding;
    public ViewPageAdapter_Bill(@NonNull @NotNull FragmentManager fm, int behavior, ActivityBillBinding binding) {
        super(fm, behavior);
        activityBillBinding = binding;
    }

    @NonNull
    @NotNull
    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position){
            case 0: fragment = new BillActivityFragment_1(activityBillBinding);
                break;
            case 1: fragment = new BillActivityFragment_2();
                break;
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0: return "Hàng gửi";
            case 1: return "Hàng nhận";
            default: return null;
        }
    }
}
