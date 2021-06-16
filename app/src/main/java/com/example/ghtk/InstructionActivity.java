package com.example.ghtk;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class InstructionActivity extends AppCompatActivity {
    ImageButton ibBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instruction);

        ibBack = findViewById(R.id.ibBack);

        ibBack.setOnClickListener(v -> finish());
    }
}