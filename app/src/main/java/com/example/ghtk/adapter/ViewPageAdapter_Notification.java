package com.example.ghtk.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.ghtk.fragment.ThirdFragment_1;
import com.example.ghtk.fragment.ThirdFragment_2;
import com.example.ghtk.fragment.ThirdFragment_3;

import org.jetbrains.annotations.NotNull;

public class ViewPageAdapter_Notification extends FragmentStatePagerAdapter {
    public ViewPageAdapter_Notification(@NonNull @NotNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @NotNull
    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position){
            case 0: fragment = new ThirdFragment_1();
                break;
            case 1: fragment = new ThirdFragment_2();
                break;
            case 2: fragment = new ThirdFragment_3();
                break;
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0: return "Tất cả";
            case 1: return "Đơn hàng";
            case 2: return "Khuyến mãi";
            default: return null;
        }
    }
}
