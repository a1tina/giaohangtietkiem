package com.example.ghtk;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.ghtk.QCModel.QC1Activity;
import com.example.ghtk.QCModel.QC2Activity;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FirstFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
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

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FirstFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FirstFragment newInstance(String param1, String param2) {
        FirstFragment fragment = new FirstFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
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
        ImageButton iBTaoDon = view.findViewById(R.id.imageButton);
        ImageButton iBLienHe = view.findViewById((R.id.imageButton7));
        ImageButton iBKhoHang = view.findViewById(R.id.imageButton5);
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