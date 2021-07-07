package com.example.ghtk.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.ghtk.R;
import com.example.ghtk.Uudai1;
import com.google.android.material.card.MaterialCardView;


public class ThirdFragment_1 extends Fragment {



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_third_1, container, false);
        MaterialCardView mcvAds1 = view.findViewById(R.id.mcv_ads1);
        mcvAds1.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), Uudai1.class);
            startActivity(intent);
        });



        return view;
    }
}