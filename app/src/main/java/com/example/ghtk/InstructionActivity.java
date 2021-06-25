package com.example.ghtk;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.ghtk.tools.NoLimitScreen;

public class InstructionActivity extends AppCompatActivity {
    ImageButton ibBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instruction);
        NoLimitScreen.apply(this);
        ibBack = findViewById(R.id.ibBack);

        ibBack.setOnClickListener(v -> finish());
    }
}