package com.example.ghtk;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
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
import androidx.core.content.ContextCompat;

import com.example.ghtk.models.PackageInfo;
import com.example.ghtk.tools.NoLimitScreen;

import org.xmlpull.v1.XmlPullParser;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.List;

public class CreateOrderActivity extends AppCompatActivity {

    private static final String TAG = "IN_CREATE_ORDER_ACTIVITY_TAG";

    RelativeLayout rlSenderEdit, rlMainLayout, rlGrayBg;

    private String selectedImagePath;
    int SELECT_IMAGE_CODE = 1;

    private Bitmap imageBitmap;
    private List<PackageInfo> packageInfoList;
    LinearLayout linearLayoutPhoto, linearLayoutAddGoods, p;
    ImageButton iBBack, iBPhotoChoose, iBDeleteGoods, iBEdit;
    CheckBox checkBox4;
    Button bCreate;
    TextView bGoods, bCOD, bServiceChoose, bPhotoDelAll, bAddGoods;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_order);
        NoLimitScreen.apply(this);
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

        ss = new SpannableString(text3);
        ClickableSpan clickableSpan3 = new ClickableSpan() {
            @Override
            public void onClick(@NonNull View widget) {
                startActivity(new Intent(CreateOrderActivity.this, CODActivity.class));
            }

            @Override
            public void updateDrawState(@NonNull TextPaint ds) {
                super.updateDrawState(ds);
                ds.setColor(getResources().getColor(R.color.light_red));
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
                ((ImageView)findViewById(R.id.iv_image_package)).setImageBitmap(null);;
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
                b.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        p.removeView(view);
                    }
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
                  startActivityForResult(intent, SELECT_IMAGE_CODE);
              }
        });
    }

    private void callApi() {
//        if(imageBitmap == null){
//            Toast.makeText(CreateOrderActivity.this, "Bạn chưa chụp ảnh", Toast.LENGTH_SHORT).show();
//            return;
//        }
        File file = savebitmap(imageBitmap);
        getDataDSHH();
        Toast.makeText(CreateOrderActivity.this, packageInfoList.get(0).getTensp(), Toast.LENGTH_SHORT).show();
//        String diachinhan = ((EditText)findViewById(R.id.et_receiver_address)).getText().toString().trim();
//        String diachigui = ((TextView)findViewById(R.id.tv_sender_info)).getText().toString().trim();
//        String chiphi = ((TextView)findViewById(R.id.tv_total_postage2)).getText().toString().trim();
////        RequestBody requestBodyDSHH = RequestBody.create(MediaType.parse("multipart/form-data"), );
////        RequestBody requestBodyKHN = RequestBody.create(MediaType.parse("multipart/form-data"), );
//        RequestBody requestBodyDCN = RequestBody.create(MediaType.parse("multipart/form-data"), diachinhan);
//        RequestBody requestBodyCP = RequestBody.create(MediaType.parse("multipart/form-data"), chiphi);
//        RequestBody requestBodyDCG = RequestBody.create(MediaType.parse("multipart/form-data"), diachigui);
//        RequestBody requestBodyImage = RequestBody.create(MediaType.parse("multipart/form-data"), file);
//        MultipartBody.Part multipartImage = MultipartBody.Part.createFormData("image", file.getName(), requestBodyImage);
//        IServiceApi.apiService.PostCreateOrder();
    }

    private void getDataDSHH() {
        for(int i = 0; i < p.getChildCount(); i++){
            View v = getLayoutInflater().inflate((XmlPullParser) p.getChildAt(i), null);
            EditText vname = v.findViewById(R.id.goods_name);
            EditText vweight = v.findViewById(R.id.goods_weight);
            EditText vquantity = v.findViewById(R.id.goods_quantity);
            String name = vname.getText().toString().trim();
            String weight = vweight.getText().toString().trim();
            String quantity = vquantity.getText().toString().trim();
            PackageInfo packageInfo = new PackageInfo(name, Float.parseFloat(weight), Integer.parseInt(quantity));
            packageInfoList.add(packageInfo);
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == SELECT_IMAGE_CODE && resultCode == RESULT_OK){
            //uri = data.getData();
            Bundle extras = data.getExtras();
            imageBitmap = (Bitmap) extras.get("data");
            ((ImageView)findViewById(R.id.iv_image_package)).setImageBitmap(imageBitmap);
        }
    }

    public void addView(ImageView imageView, int width, int height){
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(width, height);
        imageView.setLayoutParams(layoutParams);
        linearLayoutPhoto.addView(imageView);
    }

    private File savebitmap(Bitmap bmp) {
        String extStorageDirectory = Environment.getExternalStorageDirectory().toString();
        OutputStream outStream = null;
        // String temp = null;
        File file = new File(extStorageDirectory, "temp.png");
        if (file.exists()) {
            file.delete();
            file = new File(extStorageDirectory, "temp.png");

        }

        try {
            outStream = new FileOutputStream(file);
            bmp.compress(Bitmap.CompressFormat.PNG, 100, outStream);
            outStream.flush();
            outStream.close();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return file;
    }

}