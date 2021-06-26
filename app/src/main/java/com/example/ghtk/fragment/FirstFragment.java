package com.example.ghtk.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.ghtk.BillActivity;
import com.example.ghtk.ContactActivity;
import com.example.ghtk.CreateOrderActivity;
import com.example.ghtk.QC1Activity;
import com.example.ghtk.QC2Activity;
import com.example.ghtk.R;
import com.example.ghtk.WarehouseActivity;


public class FirstFragment extends Fragment {


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FirstFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_first, container, false);

        ImageButton iBTaoDon = (ImageButton) view.findViewById(R.id.imageButton);
        ImageButton iBLienHe = (ImageButton) view.findViewById((R.id.imageButton7));
        ImageButton iBKhoHang = (ImageButton) view.findViewById(R.id.imageButton5);
        ImageButton iBDonHang = (ImageButton) view.findViewById(R.id.imageButton1);
        iBTaoDon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), CreateOrderActivity.class));
            }
        });

        iBKhoHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), WarehouseActivity.class));
            }
        });

        iBLienHe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), ContactActivity.class));
            }
        });

        iBDonHang.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), BillActivity.class));
            }
        });

        ImageButton ibQC1 = view.findViewById(R.id.imageButton8);
        TextView tvQC1 = view.findViewById(R.id.textView9);
        ImageButton ibQC2 = view.findViewById(R.id.imageButton9);
        TextView tvQC2 = view.findViewById(R.id.textView10);

        ibQC1.setOnClickListener(v -> startActivity(new Intent(getActivity(), QC1Activity.class)));
        tvQC1.setOnClickListener(v -> startActivity(new Intent(getActivity(), QC1Activity.class)));

        ibQC2.setOnClickListener(v -> startActivity(new Intent(getActivity(), QC2Activity.class)));
        tvQC2.setOnClickListener(v -> startActivity(new Intent(getActivity(), QC2Activity.class)));

        iBTaoDon.setOnClickListener(v -> startActivity(new Intent(getActivity(), CreateOrderActivity.class)));

        iBKhoHang.setOnClickListener(v -> startActivity(new Intent(getActivity(), WarehouseActivity.class)));

        iBLienHe.setOnClickListener(v -> startActivity(new Intent(getActivity(), ContactActivity.class)));

        return view;


    }
}