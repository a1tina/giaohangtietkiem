package com.example.ghtk;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

public class CreateOrderActivity extends AppCompatActivity {

    private static final String TAG = "IN_CREATE_ORDER_ACTIVITY_TAG";

    RelativeLayout rlSenderEdit, rlMainLayout, rlGrayBg;

    private String selectedImagePath;
    int SELECT_IMAGE_CODE = 1;

    Uri uri;

    LinearLayout linearLayoutPhoto, linearLayoutAddGoods, p;
    ImageButton iBBack, iBPhotoChoose, iBDeleteGoods, iBEdit;
    CheckBox checkBox4;
    Button bCreate;
    TextView bGoods, bCOD, bServiceChoose, bPhotoDelAll, bAddGoods;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_order);

        rlSenderEdit = findViewById(R.id.rl_sender_edit);
        rlMainLayout = findViewById(R.id.rl_mainCreateOrderLayout);
        rlGrayBg = findViewById(R.id.rl_graybg);

        checkBox4 = findViewById(R.id.checkBox4);
        bCreate = findViewById(R.id.b_create);
        iBEdit = findViewById(R.id.ib_edit);
        bGoods = findViewById(R.id.b_goods);
        bCOD = findViewById(R.id.b_cod);
        bServiceChoose = findViewById(R.id.b_service_choose);
        bPhotoDelAll = findViewById(R.id.b_photo_delete_all);
        bAddGoods = findViewById(R.id.add_goods);
        iBPhotoChoose = findViewById(R.id.ib_photo_choose);
        linearLayoutPhoto = findViewById(R.id.linearlayout_photo);
        linearLayoutAddGoods = findViewById(R.id.linearlayout_add_goods);
        p = findViewById(R.id.p);
        iBBack = findViewById(R.id.ibBack);

        rlSenderEdit.setVisibility(RelativeLayout.GONE);
        rlGrayBg.setVisibility(RelativeLayout.GONE);

        iBBack.setOnClickListener(v -> finish());

        //Tạo đơn
        bCreate.setOnClickListener(v -> {
            startActivity(new Intent(CreateOrderActivity.this, MainActivity.class));
            Toast.makeText(getBaseContext(), "Tạo đơn thành công" , Toast.LENGTH_SHORT ).show();
            finish();
        });

        iBEdit.setOnClickListener(v -> {
            rlSenderEdit.setVisibility(RelativeLayout.VISIBLE);
            rlGrayBg.setVisibility(RelativeLayout.VISIBLE);
        });

        rlGrayBg.setOnClickListener(v -> {
            rlSenderEdit.setVisibility(RelativeLayout.GONE);
            rlGrayBg.setVisibility(RelativeLayout.GONE);
        });


        //Tạo clickable cho TextView
        String text = "Tôi đã đọc và đồng ý với Điều khoản và quy định";
        String text2 = "Xem thêm quy định";
        String text3 = "Quy định thanh toán COD";
        String text4 = "Chọn dịch vụ";
        String text5 = "Xoá tất cả";
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

        ss = new SpannableString(text3);
        ClickableSpan clickableSpan3 = new ClickableSpan() {
            @Override
            public void onClick(@NonNull View widget) {
                startActivity(new Intent(CreateOrderActivity.this, CODActivity.class));
            }

            @Override
            public void updateDrawState(@NonNull TextPaint ds) {
                super.updateDrawState(ds);
                ds.setColor(Color.rgb(159, 90, 123));
                ds.setUnderlineText(false);
            }
        };
        ss.setSpan(clickableSpan3, 0,23, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        bCOD.setText(ss);
        bCOD.setMovementMethod(LinkMovementMethod.getInstance());

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
                startActivity(new Intent(CreateOrderActivity.this, RegulationActivity.class));
            }

            @Override
            public void updateDrawState(@NonNull TextPaint ds) {
                super.updateDrawState(ds);
                ds.setColor(Color.rgb(70, 168, 167));
                ds.setUnderlineText(false);
            }
        };
        ss.setSpan(clickableSpan5, 0,10, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        bPhotoDelAll.setText(ss);
        bPhotoDelAll.setMovementMethod(LinkMovementMethod.getInstance());

        ss = new SpannableString(text6);
        ClickableSpan clickableSpan6 = new ClickableSpan() {
            @Override
            public void onClick(@NonNull View widget) {
                LayoutInflater inflater = (LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View view = inflater.inflate(R.layout.mcvaddgoods, null);
                p.addView(view);
            }

            @Override
            public void updateDrawState(@NonNull TextPaint ds) {
                super.updateDrawState(ds);
                ds.setColor(Color.rgb(159, 90, 123));
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
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(Intent.createChooser(intent, "Title"), SELECT_IMAGE_CODE);
            ImageView imageView = new ImageView(CreateOrderActivity.this);
            imageView.setImageURI(uri);
            addView(imageView, 300, LinearLayout.LayoutParams.MATCH_PARENT);
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 1){
            uri = data.getData();
        }
    }

    public void addView(ImageView imageView, int width, int height){
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(width, height);
        imageView.setLayoutParams(layoutParams);
        linearLayoutPhoto.addView(imageView);
    }


}