package com.example.ghtk.intro;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.viewpager.widget.ViewPager;

import com.example.ghtk.LoginActivity;
import com.example.ghtk.R;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class IntroActivity extends AppCompatActivity {
    private ViewPager screenPager;
    IntroViewPagerAdapter introViewPagerAdapter;
    TabLayout tabIndicator;
    Button bNext;
    int position = 0;
    AppCompatButton acbGetStarted;
    Animation bAnim;
    private Object Intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        if(restorePrefData()){
            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(intent);
            finish();
        }

        setContentView(R.layout.activity_intro);

        bNext = findViewById(R.id.b_next);
        bAnim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.button_animation);
        acbGetStarted =findViewById(R.id.b_get_started);
        tabIndicator = findViewById(R.id.tab_indicator);

        List<ScreenItem> mList = new ArrayList<>();
        mList.add(new ScreenItem("Đa dạng hàng hoá gửi", "Fast Delivery hỗ trợ hơn 10000 các loại hàng khác nhau, từ thực phẩm, đò gia dụng đến các vật liệu xây dựng có kích thước và trọng tải lớn", R.drawable.img1));
        mList.add(new ScreenItem("Giao hàng nhanh chóng", "Fast Delivery cung cấp nhiều dịch vụ giao hàng khác nhau phù hợp với mong muốn của từng khách hàng", R.drawable.img2));
        mList.add(new ScreenItem("Thanh toán dễ dàng", "Khách hàng tự do lựa chọn hình thức thanh toán mong muốn. Mọi thứ đảm bảo nhanh chóng và bảo mật tuyệt đối", R.drawable.img3));

        //setup ViewPager
        screenPager = findViewById(R.id.screen_viewpager);
        introViewPagerAdapter = new IntroViewPagerAdapter(this, mList);
        screenPager.setAdapter(introViewPagerAdapter);

        //setuo TabLayout with ViewPager
        tabIndicator.setupWithViewPager(screenPager);

        bNext.setOnClickListener(v -> {
            position = screenPager.getCurrentItem();
            if (position < mList.size()){
                position++;
                screenPager.setCurrentItem(position);
            }

            if(position == mList.size() - 1){
                loadLastScreen();
            }
        });

        tabIndicator.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if(tab.getPosition() == mList.size() - 1){
                    loadLastScreen();
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        acbGetStarted.setOnClickListener(v -> {
            startActivity(new Intent(getApplicationContext(), LoginActivity.class));
            savePrefData();
            finish();
        });

    }

    private boolean restorePrefData() {
        SharedPreferences pref = getApplicationContext().getSharedPreferences("myPrefs", MODE_PRIVATE);
        Boolean isIntrpActivityOpenedBefore = pref.getBoolean("isIntroOpened", false);
        return isIntrpActivityOpenedBefore;
    }

    private void savePrefData() {
        SharedPreferences pref = getApplicationContext().getSharedPreferences("myPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putBoolean("isIntroOpened", true);
        editor.commit();
    }

    private void loadLastScreen() {
        bNext.setVisibility(View.INVISIBLE);
        acbGetStarted.setVisibility(View.VISIBLE);
        tabIndicator.setVisibility(View.INVISIBLE);

        acbGetStarted.setAnimation(bAnim);

    }
}