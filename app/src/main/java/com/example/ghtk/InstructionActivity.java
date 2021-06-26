package com.example.ghtk;

import android.os.Bundle;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

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