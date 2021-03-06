package com.example.ghtk.fragment;

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
import com.example.ghtk.Customer;
import com.example.ghtk.InfoAccountActivity;
import com.example.ghtk.InstructionActivity;
import com.example.ghtk.LoginActivity;
import com.example.ghtk.LoginResult;
import com.example.ghtk.MainActivity;
import com.example.ghtk.R;
import com.example.ghtk.RegulationActivity;
import com.example.ghtk.api.ApiClient;
import com.example.ghtk.storage.SharedPrefManager;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.vishnusivadas.advanced_httpurlconnection.FetchData;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class FourthFragment extends Fragment {

    public static TextView profileName;
    public TextView profileEmail;
    public ImageView profileImage;
    private AppCompatButton logoutBtn, acbProfileInfo, acbInstruction;
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
        View view = inflater.inflate(R.layout.fragment_fourth, container, false);
        profileName = (TextView) view.findViewById(R.id.tv_profilename);
        profileEmail = (TextView) view.findViewById((R.id.tv_email));
        profileImage = (ImageView) view.findViewById(R.id.iv_avatar);
        logoutBtn = (AppCompatButton) view.findViewById(R.id.acb_logout);
        acbTeamOfUse = view.findViewById(R.id.acb_term_of_use);
        acbProfileInfo = view.findViewById(R.id.acb_profile_in4);
        acbInstruction = view.findViewById(R.id.acb_instruction);




        firebaseAuth = FirebaseAuth.getInstance();
        checkUser();

        acbTeamOfUse.setOnClickListener(v -> startActivity(new Intent(getActivity(), RegulationActivity.class)));
        acbProfileInfo.setOnClickListener(v -> startActivity(new Intent(getActivity(), InfoAccountActivity.class)));
        acbInstruction.setOnClickListener(v -> startActivity(new Intent(getActivity(), InstructionActivity.class)));

        logoutBtn.setOnClickListener(v -> {
            firebaseAuth.signOut();
            SharedPrefManager.getInstance(getActivity()).clear();
            Intent intent = new Intent(getActivity(), LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        });
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
        if (firebaseUser != null) {
            String name = firebaseUser.getDisplayName();
            String email = firebaseUser.getEmail();
            String imgurl = firebaseUser.getPhotoUrl().toString();
            Glide.with(getActivity()).load(imgurl).into(profileImage);
            profileName.setText(name);
            profileEmail.setText(email);
        } else if (SharedPrefManager.getInstance(getActivity()).isLoggedIn()) {

            LoginResult loginResult = SharedPrefManager.getInstance(getActivity()).getUser();
            String accessToken = loginResult.getAccessToken();
            Call<Customer> call = ApiClient
                    .getInstance().getApi().getProfile(accessToken);
            call.enqueue(new Callback<Customer>() {
                @Override
                public void onResponse(Call<Customer> call, Response<Customer> response) {
                    Customer customer = response.body();
                    SharedPrefManager.getInstance(getActivity())
                            .saveProfile(customer.getProfile());
                    customer = SharedPrefManager.getInstance(getActivity()).getProfile();
                    profileName.setText(customer.getTenKH());
                    profileEmail.setText(customer.getUsername());

                }

                @Override
                public void onFailure(Call<Customer> call, Throwable t) {

                }
            });
            /* LoginResult loginResult = SharedPrefManager.getInstance(getActivity()).getUser();
            ReceiveCustomer customer = SharedPrefManager.getInstance(getActivity()).getProfile();
            profileName.setText(customer.getTenKH());
            profileEmail.setText(customer.getUsername());

             */
        } else {
            Intent intent = new Intent(getActivity(), LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            getActivity().finish();
        }

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
        } else if (SharedPrefManager.getInstance(getActivity()).isLoggedIn()) {
            /* LoginResult loginResult = SharedPrefManager.getInstance(getActivity()).getUser();*/
            Customer customer = SharedPrefManager.getInstance(getActivity()).getProfile();
            profileName.setText(customer.getTenKH());
            profileEmail.setText(customer.getUsername());
        } else {
            Intent intent = new Intent(getActivity(), LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            getActivity().finish();
        }

    }
}