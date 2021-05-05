package com.example.finalprojectapp;

import android.content.Intent;
import android.os.Bundle;
import android.provider.BaseColumns;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ListActivity extends AppCompatActivity {
    private Button gotBtn;
    private Button needBtn;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        Log.d("ListOnCreate", "Inside onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_activity);
        gotBtn = findViewById(R.id.got_button);
        needBtn = findViewById(R.id.need_button);
        gotBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("ListOnClick", "Inside this method");
                Intent intent = new Intent(v.getContext(), GotActivity.class);
                startActivity(intent);
                Log.d("ListOnClick", "Leaving this method");
            }
        });
        needBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), NeedActivity.class);
                startActivity(intent);
            }
        });
        Log.d("ListOnCreate", "Leaving onCreate");
    }
}
