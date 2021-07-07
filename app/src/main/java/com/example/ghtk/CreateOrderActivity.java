package com.example.ghtk;

import android.Manifest;
import android.app.NotificationManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.TextWatcher;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.ghtk.R;
import com.example.ghtk.api.ApiClient;
import com.example.ghtk.models.PackageInfo;
import com.example.ghtk.models.ReceiveCustomer;
import com.example.ghtk.storage.SharedPrefManager;
import com.example.ghtk.tools.NoLimitScreen;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import info.androidhive.fontawesome.FontDrawable;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreateOrderActivity extends AppCompatActivity {

    private static final String TAG = "IN_CREATE_ORDER_ACTIVITY_TAG";
    private static final int MY_PERMISSIONS_REQUEST_READ_CONTACTS = 3;
    private RelativeLayout rlSenderEdit, rlGrayBg;
    public  static final int REQUEST_PERMISSION_CODE  = 1 ;
    private static final int CAPTURE_IMAGE_CODE = 1;
    private Bitmap imageBitmap;
    private List<PackageInfo> packageInfoList;

    private LinearLayout p;
    private ImageButton iBBack, iBPhotoChoose, iBDeleteGoods, iBEdit;
    private CheckBox checkBox4;
    private Button bCreate;
    private TextView bGoods, bServiceChoose, bPhotoDelAll, bAddGoods, tv_total_postage2, bCOD;
    private EditText et_sender_address, editWeightOrigin, editQuantityOrigin;
    private ProgressDialog progressDialog_send, progressDialog_update_phone;
    private BottomSheetDialog bottomSheetDialog;
    private View dialogView;
    private float totalWeight = 0, totalMoney = 0;

    NotificationManager notificationManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_order);
        NoLimitScreen.apply(this);
        rlSenderEdit = findViewById(R.id.rl_sender_edit);
        rlGrayBg = findViewById(R.id.rl_graybg);
        checkBox4 = findViewById(R.id.checkBox4);
        bCreate = findViewById(R.id.b_create);
        iBEdit = findViewById(R.id.ib_edit);
        bGoods = findViewById(R.id.b_goods);
        bServiceChoose = findViewById(R.id.b_service_choose);
        bPhotoDelAll = findViewById(R.id.b_photo_delete_all);
        bAddGoods = findViewById(R.id.add_goods);
        iBPhotoChoose = findViewById(R.id.ib_photo_choose);
        p = findViewById(R.id.p);
        iBBack = findViewById(R.id.ibBack);
        et_sender_address = findViewById(R.id.et_sender_address);
        rlSenderEdit.setVisibility(RelativeLayout.GONE);
        rlGrayBg.setVisibility(RelativeLayout.GONE);

        tv_total_postage2 = findViewById(R.id.tv_total_postage2);
        editWeightOrigin = findViewById(R.id.goods_weight);
        editQuantityOrigin = findViewById(R.id.goods_quantity);

        //Init progressDialog
        progressDialog_send = new ProgressDialog(this);
        progressDialog_update_phone = new ProgressDialog(this);
        progressDialog_send.setTitle("Đang gửi đi");
        progressDialog_send.setMessage("Vui lòng đợi...");
        progressDialog_update_phone.setMessage("Vui lòng đợi...");
        //Create Update Dialog Bottomsheet
        createUpdateDialog();

        //Set address of sender

        //set Event

        //Delete noti when receive intent from NotificationReceiver
        notificationManager = (NotificationManager) getApplicationContext().getSystemService(NOTIFICATION_SERVICE);
        notificationManager.cancelAll();

        //Request permission
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_CONTACTS)
                != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.READ_CONTACTS)) {

                // Show an expanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.

            } else {

                // No explanation needed, we can request the permission.

                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.READ_CONTACTS},
                        MY_PERMISSIONS_REQUEST_READ_CONTACTS);

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        }

        iBBack.setOnClickListener(v -> finish());

        bCreate.setOnClickListener(v -> {
            callApi();
        });

        iBEdit.setOnClickListener(v -> {
            rlSenderEdit.setVisibility(RelativeLayout.VISIBLE);
            rlGrayBg.setVisibility(RelativeLayout.VISIBLE);
        });

        rlGrayBg.setOnClickListener(v -> {
            rlSenderEdit.setVisibility(RelativeLayout.GONE);
            rlGrayBg.setVisibility(RelativeLayout.GONE);
        });

        //Set onChange weight and quantity
        TextWatcher textWatcher = getMyTextWatcher(editWeightOrigin, editQuantityOrigin);
        editWeightOrigin.addTextChangedListener(textWatcher);
        editQuantityOrigin.addTextChangedListener(textWatcher);
        //Set FontDrawable
        EditText e = findViewById(R.id.goods_name);
        setPackageImageDrawable(e);

        //Enable permission
        EnableRuntimePermissionToAccessCamera();

        //Tạo clickable cho TextView
        String text = "Tôi đã đọc và đồng ý với Điều khoản và quy định";
        String text2 = "Xem thêm quy định";
        String text3 = "Quy định thanh toán COD";
        String text4 = "Chọn dịch vụ";
        String text5 = "Xoá";
        String text6 = "Thêm hàng hoá";

        SpannableString ss = new SpannableString(text);
        ClickableSpan clickableSpan1 = new ClickableSpan() {
            @Override
            public void onClick(@NonNull View widget) {
                startActivity(new Intent(CreateOrderActivity.this, ContactActivity.class));
            }

            @Override
            public void updateDrawState(@NonNull TextPaint ds) {
                super.updateDrawState(ds);
                ds.setColor(Color.BLUE);
                ds.setUnderlineText(false);
            }
        };
        ss.setSpan(clickableSpan1, 25,47, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        checkBox4.setText(ss);
        checkBox4.setMovementMethod(LinkMovementMethod.getInstance());
        checkBox4.setHighlightColor(ContextCompat.getColor(this, R.color.color9F5A7B));

        ss = new SpannableString(text2);
        ClickableSpan clickableSpan2 = new ClickableSpan() {
            @Override
            public void onClick(@NonNull View widget) {
                startActivity(new Intent(CreateOrderActivity.this, RegulationActivity.class));
            }

            @Override
            public void updateDrawState(@NonNull TextPaint ds) {
                super.updateDrawState(ds);
                ds.setColor(Color.rgb(70, 168, 167));
                ds.setUnderlineText(false);
            }
        };
        ss.setSpan(clickableSpan2, 0,17, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        bGoods.setText(ss);
        bGoods.setMovementMethod(LinkMovementMethod.getInstance());

       

        ss = new SpannableString(text4);
        ClickableSpan clickableSpan4 = new ClickableSpan() {
            @Override
            public void onClick(@NonNull View widget) {
                startActivity(new Intent(CreateOrderActivity.this, RegulationActivity.class));
            }

            @Override
            public void updateDrawState(@NonNull TextPaint ds) {
                super.updateDrawState(ds);
                ds.setColor(Color.rgb(70, 168, 167));
                ds.setUnderlineText(false);
            }
        };
        ss.setSpan(clickableSpan4, 0,12, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        bServiceChoose.setText(ss);
        bServiceChoose.setMovementMethod(LinkMovementMethod.getInstance());

        ss = new SpannableString(text5);
        ClickableSpan clickableSpan5 = new ClickableSpan() {
            @Override
            public void onClick(@NonNull View widget) {
                ((ImageView)findViewById(R.id.iv_image_package)).setImageBitmap(null);
                imageBitmap = null;
            }

            @Override
            public void updateDrawState(@NonNull TextPaint ds) {
                super.updateDrawState(ds);
                ds.setColor(Color.rgb(70, 168, 167));
                ds.setUnderlineText(false);
            }
        };
        ss.setSpan(clickableSpan5, 0,3, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        bPhotoDelAll.setText(ss);
        bPhotoDelAll.setMovementMethod(LinkMovementMethod.getInstance());

        ss = new SpannableString(text6);
        ClickableSpan clickableSpan6 = new ClickableSpan() {
            @Override
            public void onClick(@NonNull View widget) {
                LayoutInflater inflater = (LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View view = inflater.inflate(R.layout.mcvaddgoods, null);
                ImageButton b = view.findViewById(R.id.b_delete_goods);
                //Declare and Initialize view
                EditText e = view.findViewById(R.id.goods_name),
                         weightView = view.findViewById(R.id.goods_weight),
                         quantityView = view.findViewById(R.id.goods_quantity);
                setPackageImageDrawable(e);

                TextWatcher textWatcher1 = getMyTextWatcher(weightView, quantityView);
                weightView.addTextChangedListener(textWatcher1);
                quantityView.addTextChangedListener(textWatcher1);
                b.setOnClickListener(v -> {
                    p.removeView(view);

                });
                p.addView(view);
            }

            @Override
            public void updateDrawState(@NonNull TextPaint ds) {
                super.updateDrawState(ds);
                ds.setColor(getResources().getColor(R.color.light_red));
                ds.setUnderlineText(false);
            }
        };
        ss.setSpan(clickableSpan6, 0,13, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        bAddGoods.setText(ss);
        bAddGoods.setMovementMethod(LinkMovementMethod.getInstance());


        if(findViewById(R.id.mcvaddgoods) != null){
            Log.d(TAG, "There is a delete goods button!");
            iBDeleteGoods = findViewById(R.id.b_delete_goods);
            iBDeleteGoods.setOnClickListener(v -> {
                startActivity(new Intent(CreateOrderActivity.this, ContactActivity.class));
            });
        }


        //Thêm hình ảnh
        iBPhotoChoose.setOnClickListener(v -> {
              Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
              if(intent.resolveActivity(getPackageManager()) != null){
                  startActivityForResult(intent, CAPTURE_IMAGE_CODE);
              }
        });
    }
    private TextWatcher getMyTextWatcher(EditText weight_editText, EditText quantity_editText){
        return new TextWatcher() {
            private Float localTotalWeight = 0F, localTotalMoney = 0F;
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) { }
            @Override
            public void afterTextChanged(Editable s) {
                totalWeight -= localTotalWeight;
                totalMoney = totalWeight * 5000;

                if(!s.toString().equals("")){
                    String weight = weight_editText.getText().toString().trim();
                    String quantity = quantity_editText.getText().toString().trim();
                    localTotalWeight = Integer.parseInt(quantity) * Float.parseFloat(weight);
                    totalWeight += localTotalWeight;
                    totalMoney = totalWeight * 5000;
                }else{
                    localTotalWeight = 0F;
                }
                tv_total_postage2.setText(String.valueOf(totalMoney));
            }

            @Override
            protected void finalize() throws Throwable {
                super.finalize();
                totalWeight -= localTotalWeight;
                totalMoney = totalWeight * 5000;
                tv_total_postage2.setText(String.valueOf(totalMoney));
            }
        };
    }
    private void createUpdateDialog() {
        dialogView = LayoutInflater.from(this).inflate(R.layout.update_dialog_bottomsheet, null);
        bottomSheetDialog = new BottomSheetDialog(this);
        bottomSheetDialog.setContentView(dialogView);
        AppCompatButton updateBtn = dialogView.findViewById(R.id.btn_update_phone);
        AppCompatButton exitBtn = dialogView.findViewById(R.id.btn_exit);
        updateBtn.setOnClickListener(v -> {
            //startActivityForResult(new Intent(this, ChangeInfoActivity.class), 100);
            startActivity(new Intent(this, ChangeInfoActivity.class));
        });
        exitBtn.setOnClickListener(v -> {
            finish();
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        progressDialog_update_phone.show();
        LoginResult loginResult = SharedPrefManager.getInstance(this).getUser();
        String accessToken = loginResult.getAccessToken();
        ApiClient.getInstance().getApi().getProfile(accessToken).enqueue(new Callback<Customer>() {
            @Override
            public void onResponse(Call<Customer> call, Response<Customer> response) {
                progressDialog_update_phone.dismiss();
                Customer customer = response.body();
                checkUpdatePhone(customer.getSDT(), customer.getDiaChi());
                et_sender_address.setText(customer.getDiaChi());
            }
            @Override
            public void onFailure(Call<Customer> call, Throwable t) { progressDialog_update_phone.dismiss();}
        });
    }

    private void checkUpdatePhone(String phone, String address) {
        String s = "Bạn chưa cập nhật ";
        if(phone == null || phone.equals("")){
            ((TextView)dialogView.findViewById(R.id.notify_update)).setText(s + "số điện thoại");
            bottomSheetDialog.show();
            bottomSheetDialog.setCancelable(false);
        }else if(address == null || address.equals("")){
            ((TextView)dialogView.findViewById(R.id.notify_update)).setText(s + "địa chỉ");
            bottomSheetDialog.show();
            bottomSheetDialog.setCancelable(false);
        }
        else{
            bottomSheetDialog.dismiss();
        }

    }

    private void setPackageImageDrawable(EditText v) {
        FontDrawable drawable = new FontDrawable(CreateOrderActivity.this, R.string.fa_cube_solid, true, false);
        drawable.setTextColor(0xFFB8B8B8);
        drawable.setTextSize(18);
        v.setCompoundDrawablesRelativeWithIntrinsicBounds(drawable, null, null, null);
        v.setCompoundDrawablePadding(25);
    }

    private void EnableRuntimePermissionToAccessCamera() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(CreateOrderActivity.this, Manifest.permission.CAMERA))
        {
            // Printing toast message after enabling runtime permission.
            Toast.makeText(CreateOrderActivity.this,"Cho phép truy cập camera", Toast.LENGTH_LONG).show();

        } else {
            ActivityCompat.requestPermissions(CreateOrderActivity.this,new String[]{Manifest.permission.CAMERA}, REQUEST_PERMISSION_CODE);
        }
    }

    private void callApi() {
        progressDialog_send.show();
        if(imageBitmap == null){
            Toast.makeText(CreateOrderActivity.this, "Bạn chưa chụp ảnh", Toast.LENGTH_SHORT).show();
            return;
        }
        //
        getDataDSHH();
        String diachinhan = ((EditText)findViewById(R.id.et_receiver_address)).getText().toString().trim();
        String diachigui = ((EditText)findViewById(R.id.et_sender_address)).getText().toString().trim();
        String chiphi = tv_total_postage2.getText().toString().trim();
        String tennguoinhan = ((EditText)findViewById(R.id.et_receiver_name)).getText().toString().trim();
        String sdtnhan = ((EditText)findViewById(R.id.et_receiver_phone)).getText().toString().trim();
        //Convert bitmap to file
        File filesDir = getFilesDir();
        File imageFile = new File(filesDir,  packageInfoList.get(0).getTensp() + ".jpg");
        OutputStream os;
        try {
            os = new FileOutputStream(imageFile);
            imageBitmap.compress(Bitmap.CompressFormat.JPEG, 100, os);
            os.flush();
            os.close();
        } catch (Exception e) {
            Log.e(getClass().getSimpleName(), "Error writing bitmap", e);
        }
        //Create multipartBody
        ReceiveCustomer receiveCustomer = new ReceiveCustomer(tennguoinhan, sdtnhan);
        Gson gson = new GsonBuilder().setLenient().create();
        String json_package_list = gson.toJson(packageInfoList);
        String json_customer = gson.toJson(receiveCustomer);
        MultipartBody.Part requestBodyDSHH = MultipartBody.Part.createFormData("dshanghoa", json_package_list);
        MultipartBody.Part requestBodyKHN =MultipartBody.Part.createFormData("khnhan", json_customer);
        MultipartBody.Part requestBodyDCN = MultipartBody.Part.createFormData("diachinhan", diachinhan);
        MultipartBody.Part requestBodyCP = MultipartBody.Part.createFormData("chiphi", chiphi);
        MultipartBody.Part requestBodyDCG = MultipartBody.Part.createFormData("diachidi", diachigui);
        RequestBody requestBodyImage = RequestBody.create(MediaType.parse("multipart/form-data"), imageFile);
        MultipartBody.Part image = MultipartBody.Part.createFormData("image", imageFile.getName(), requestBodyImage);
        //Get accessToken
        LoginResult loginResult = SharedPrefManager.getInstance(CreateOrderActivity.this).getUser();
        String accessToken = loginResult.getAccessToken();
        //Call API
        ApiClient.getInstance().getOrderApi().PostCreateOrder(accessToken, requestBodyDSHH, requestBodyKHN, requestBodyDCN, requestBodyCP, requestBodyDCG,
            image).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                progressDialog_send.dismiss();
                if(response.isSuccessful()) {
                    Toast.makeText(CreateOrderActivity.this, "Tạo đơn thành công", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
            @Override
            public void onFailure(Call<String> call, Throwable t) {
                progressDialog_send.dismiss();
                Toast.makeText(CreateOrderActivity.this, "Có lỗi xảy ra", Toast.LENGTH_SHORT).show();
                Log.d("LOI LOI LOI", t.getMessage());
            }
        });
    }

    private void getDataDSHH() {
            packageInfoList = new ArrayList<>();
            for(int i = 0; i < p.getChildCount(); i++){
                EditText editName = (EditText) getEditText((ViewGroup) p.getChildAt(i), R.id.goods_name);
                EditText editWeight = (EditText) getEditText((ViewGroup) p.getChildAt(i), R.id.goods_weight);
                EditText editQuantity = (EditText) getEditText((ViewGroup) p.getChildAt(i), R.id.goods_quantity);
                String name = editName.getText().toString().trim();
                String weight = editWeight.getText().toString().trim();
                String quantity = editQuantity.getText().toString().trim();
                PackageInfo packageInfo = new PackageInfo(name, Float.parseFloat(weight), Integer.parseInt(quantity));
                packageInfoList.add(packageInfo);
            }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == CAPTURE_IMAGE_CODE && resultCode == RESULT_OK){
            Bundle extras = data.getExtras();
            imageBitmap = (Bitmap) extras.get("data");
            ((ImageView)findViewById(R.id.iv_image_package)).setImageBitmap(imageBitmap);
        }

    }

    private View getEditText(ViewGroup group, int idEditText){
        for(int i = 0; i < group.getChildCount(); i++){
            if(group.getChildAt(i).getId() == idEditText)
                return group.getChildAt(i);
        }
        return null;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull @NotNull String[] permissions, @NonNull @NotNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case REQUEST_PERMISSION_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    //Toast.makeText(CreateOrderActivity.this,"Ứng dụng có thể truy cập camera", Toast.LENGTH_SHORT).show();
                } else {
                    //Toast.makeText(CreateOrderActivity.this,"Ứng dụng không thể truy cập camera", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}