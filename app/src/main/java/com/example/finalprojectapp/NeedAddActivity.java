package com.example.finalprojectapp;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class NeedAddActivity extends AppCompatActivity {
    private EditText itemAdd;
    private Button addButt;
    private String itemAdded;
    private final String NAME = "NeedAddActivity";
    public static final String ADDED = "itemAdded";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        Log.d(NAME, "Inside onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.need_add_activity);
        itemAdd = findViewById(R.id.need_adds);
        addButt = findViewById(R.id.need_addButt);
        itemAdd.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                Log.d(NAME, "Inside this method");
                itemAdded = itemAdd.getText().toString();
                Log.d(NAME, "Leaving this method with:"+itemAdded);
            }
        });
        addButt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(NAME, "Inside onClick");
                Intent inty = new Intent();
                inty.putExtra(ADDED, itemAdded);
                setResult(RESULT_OK, inty);
                Log.d(NAME, "Leaving onClick with: " + itemAdded);
                finish();
            }
        });
        Log.d(NAME, "Leave onCreate");
    }
}
