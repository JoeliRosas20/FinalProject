package com.example.finalprojectapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.finalprojectapp.R;

public class AddActivity extends AppCompatActivity {
    private EditText itemAdd;
    private Button addButt;
    private String itemAdded;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("onCreate", "Inside this method");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        itemAdd = findViewById(R.id.adds);
        addButt = findViewById(R.id.addButt);

        itemAdd.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                itemAdded = itemAdd.getText().toString();

            }
        });

        addButt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("AddOnClick", "Inside this method");
                Intent inty = new Intent(v.getContext(), GotActivity.class);

                inty.putExtra("itemAdded", itemAdded);

                startActivity(inty);
                Log.d("AddOnClick", "Leaving this method with: " + itemAdded);
            }
        });
        Log.d("AddOnCreate", "Leaving this method");
    }
}