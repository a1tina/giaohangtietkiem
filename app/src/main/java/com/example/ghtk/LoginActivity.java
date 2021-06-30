package com.example.ghtk;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.example.ghtk.api.ApiClient;
import com.example.ghtk.fragment.FourthFragment;
import com.example.ghtk.storage.SharedPrefManager;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    FourthFragment fourthFragment;
    Button bLogin;
    SignInButton bGoogleSignIn;
    TextView tvSignup;
    TextInputEditText tietEmail, tietPassword;
    TextInputLayout textInputLayoutEmail, textInputLayoutPassword;


    private static final int RC_SIGN_IN = 100;
    private GoogleSignInClient googleSignInClient;

    private FirebaseAuth firebaseAuth;
    ProgressDialog progressDialog;

    private static final String TAG = "GOOGLE_SIGN_IN_TAG";
    private static final Pattern PASSWORD_PATTERN =
            Pattern.compile("^" +
                    //"(?=.*[0-9])" +         //at least 1 digit
                    //"(?=.*[a-z])" +         //at least 1 lower case letter
                    //"(?=.*[A-Z])" +         //at least 1 upper case letter
                    "(?=.*[a-zA-Z])" +      //any letter
                    "(?=.*[@#$%^&+=])" +    //at least 1 special character
                    "(?=\\S+$)" +           //no white spaces
                    ".{8,}" +               //at least 8 characters
                    "$");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        bGoogleSignIn = findViewById(R.id.bGoogleSignIn);

        bLogin = findViewById(R.id.b_login);
        tvSignup = findViewById(R.id.tv_signup);
        tietEmail = findViewById(R.id.tiet_email);
        tietPassword = findViewById(R.id.tiet_password);
        textInputLayoutEmail = findViewById(R.id.textInputLayoutEmail);
        textInputLayoutPassword = findViewById(R.id.textInputLayoutPassword);

        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Đang xử lý");
        progressDialog.setMessage("Vui lòng đợi");


        bLogin.setOnClickListener(v -> {
            if (!validateEmail() | !validatePassword()) {
                return;
            }
            normalLogin();
        });

        String text = "Chưa có tài khoản? Đăng ký tại đây";

        SpannableString ss = new SpannableString(text);
        ClickableSpan clickableSpan1 = new ClickableSpan() {
            @Override
            public void onClick(@NonNull View widget) {
                startActivity(new Intent(LoginActivity.this, SignUpActivity.class));
            }

            @Override
            public void updateDrawState(@NonNull TextPaint ds) {
                super.updateDrawState(ds);
                ds.setColor(Color.BLUE);
                ds.setUnderlineText(true);
            }
        };
        ss.setSpan(clickableSpan1, 26, 34, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        tvSignup.setText(ss);
        tvSignup.setMovementMethod(LinkMovementMethod.getInstance());
        tvSignup.setHighlightColor(ContextCompat.getColor(this, R.color.color9F5A7B));


        //Configure the Google SignIn
        GoogleSignInOptions googleSignInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        googleSignInClient = GoogleSignIn.getClient(this, googleSignInOptions);

        //init firebase auth
        firebaseAuth = FirebaseAuth.getInstance();
        checkUser();

        //Google SignInButton: Click to begin Google Sign In
        bGoogleSignIn.setOnClickListener(v -> {
            //begin Google Sign In
            Log.d(TAG, "onClick: begin Google Sign In");
            Intent intent = googleSignInClient.getSignInIntent();
            startActivityForResult(intent, RC_SIGN_IN);
        });
    }

    private void checkUser() {
        //if user is already signed in then go MainActivity
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
        if (firebaseUser != null || SharedPrefManager.getInstance(this).isLoggedIn()) {
            Log.d(TAG, "checkUser: Already logged in");
            Intent intent = new Intent(this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {
            Log.d(TAG, "onActivityResult: Google Sign In intent result");
            Task<GoogleSignInAccount> accountTask = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                GoogleSignInAccount account = accountTask.getResult(ApiException.class);
                firebaseAuthWithGoogleAccount(account);
                Log.d("Account", String.format("Display name: %s \n Email: %s \n IdToken: %s \n UrlPhoto: %s", account.getDisplayName(), account.getEmail(),
                        account.getIdToken(), account.getPhotoUrl().toString()));
            } catch (Exception e) {
                //Log.d(TAG, "onActivityResult: "+e.printStackTrace());
                e.printStackTrace();
            }

        }
    }


    private void firebaseAuthWithGoogleAccount(GoogleSignInAccount account) {
        Log.d(TAG, "firebaseAuthWithGoogleAccount: begin firebase auth with Google account");
        AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken(), null);
        firebaseAuth.signInWithCredential(credential)
                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        Log.d(TAG, "onSuccess: Logged In");

                        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
                        String uid = firebaseUser.getUid();
                        String email = firebaseUser.getEmail();

                        Log.d(TAG, "onSuccess: Email: " + email);
                        Log.d(TAG, "onSuccess: UID: " + uid);

                        if (authResult.getAdditionalUserInfo().isNewUser()) {
                            Log.d(TAG, "onSuccess: Account Created...\n" + email);
                            Toast.makeText(LoginActivity.this, "Tài khoản tạo mới thành công", Toast.LENGTH_SHORT).show();
                        } else {
                            Log.d(TAG, "onSuccess: Existing user...\n" + email);
                            Toast.makeText(LoginActivity.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                        }
                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
                        finish();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d(TAG, "onFailure: Login Failed " + e.getMessage());
                    }
                });
    }

    private void normalLogin() {
        String email = tietEmail.getText().toString().trim();
        String password = tietPassword.getText().toString().trim();
        progressDialog.show();
        Call<LoginResult> call = ApiClient
                .getInstance()
                .getApi()
                .postLogin(email, password);
        call.enqueue(new Callback<LoginResult>() {
            @Override
            public void onResponse(Call<LoginResult> call, Response<LoginResult> response) {
                LoginResult loginResult = response.body();
                if (loginResult.getMsg().equals("Đăng nhập thành công")) {
                    Toast.makeText(LoginActivity.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                    SharedPrefManager.getInstance(LoginActivity.this)
                            .saveUser(loginResult.getUser());
                    Customer customer = SharedPrefManager.getInstance(LoginActivity.this).getProfile();
                    Call<Customer> call2 = ApiClient
                            .getInstance()
                            .getApi()
                            .updateProfile(loginResult.getAccessToken(), customer.getTenKH(), customer.getSDT(), customer.getDiaChi());
                    call2.enqueue(new Callback<Customer>() {
                        @Override
                        public void onResponse(Call<Customer> call, Response<Customer> response) {
                            Customer customer = response.body();
                            SharedPrefManager.getInstance(LoginActivity.this)
                                    .saveProfile(customer.getProfile());
                        }

                        @Override
                        public void onFailure(Call<Customer> call, Throwable t) {

                        }
                    });

                    progressDialog.hide();
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                } else {
                    Toast.makeText(LoginActivity.this, "Email hoặc mật khẩu không đúng!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<LoginResult> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "Đăng nhập thất bại", Toast.LENGTH_SHORT).show();
                progressDialog.hide();
            }
        });


    }

    private boolean validateEmail() {
        String emailInput = textInputLayoutEmail.getEditText().getText().toString().trim();
        if (emailInput.isEmpty()) {
            textInputLayoutEmail.setError("Email không được để trống");
            return false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()) {
            textInputLayoutEmail.setError("Vui lòng điền đúng định dạng email");
            return false;
        } else {
            textInputLayoutEmail.setError(null);
            return true;
        }
    }

    private boolean validatePassword() {
        String passwordInput = textInputLayoutPassword.getEditText().getText().toString().trim();
        if (passwordInput.isEmpty()) {
            textInputLayoutPassword.setError("Không được để trống mật khẩu!");
            return false;
        } else if (!PASSWORD_PATTERN.matcher(passwordInput).matches()) {
            textInputLayoutPassword.setError("Mật khẩu phải có ít nhất 8 kí tự, bao gồm ít nhất 1 kí tự đặc biệt và không có khoảng trắng");
            return false;
        } else {
            textInputLayoutPassword.setError(null);
            return true;
        }
    }

}



