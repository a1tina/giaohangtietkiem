package com.example.ghtk;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ghtk.tools.NoLimitScreen;
import com.google.android.material.card.MaterialCardView;

public class InstructionActivity extends AppCompatActivity {
    ImageButton ibBack;
    MaterialCardView materialCardViewInstruction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instruction);
        NoLimitScreen.apply(this);
        ibBack = findViewById(R.id.ibBack);
        materialCardViewInstruction = findViewById(R.id.cardview_instruction);

        ibBack.setOnClickListener(v -> finish());

        materialCardViewInstruction.setOnClickListener(v -> {
            try{
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + "com.android.chrome")));
            } catch(ActivityNotFoundException e){
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://play.google.com/store/apps/details?id=" + getPackageName())));
            }
        });
    }
}