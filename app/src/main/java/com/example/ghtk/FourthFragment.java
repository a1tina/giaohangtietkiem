package com.example.ghtk;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class FourthFragment extends Fragment {

    public static TextView profileName;
    public TextView profileEmail;
    public ImageView profileImage;
    private AppCompatButton logoutBtn;
    AppCompatButton acbTeamOfUse;
    private FirebaseAuth firebaseAuth;



    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FourthFragment() {
        // Required empty public constructor
    }


    public static FourthFragment newInstance(String param1, String param2) {
        FourthFragment fragment = new FourthFragment();
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
        View view =  inflater.inflate(R.layout.fragment_fourth, container, false);
        profileName = (TextView) view.findViewById(R.id.tv_profilename);
        profileEmail = (TextView) view.findViewById((R.id.tv_email));
        profileImage = (ImageView) view.findViewById(R.id.iv_avatar);
        logoutBtn = (AppCompatButton) view.findViewById(R.id.acb_logout);
        acbTeamOfUse = view.findViewById(R.id.acb_term_of_use);

        firebaseAuth = FirebaseAuth.getInstance();
        checkUser();

        acbTeamOfUse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), RegulationActivity.class));
            }
        });

        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebaseAuth.signOut();
                checkUser();
            }
        });
        return view;
    }


    private void checkUser() {
        //get current user
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
        if (firebaseUser != null) {
            String name = firebaseUser.getDisplayName();
            String email = firebaseUser.getEmail();
            String imgurl = firebaseUser.getPhotoUrl().toString();
            Glide.with(getActivity()).load(imgurl).into(profileImage);
            profileName.setText(name);
            profileEmail.setText(email);
        }
        else{
            startActivity(new Intent(getActivity(), LoginActivity.class));
        }

    }
}