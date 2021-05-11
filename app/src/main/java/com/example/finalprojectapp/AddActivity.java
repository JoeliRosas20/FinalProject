package com.example.finalprojectapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
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
    private final String NAME = "AddActivity";
    public static final String ADDED = "itemAdded";
    String item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(NAME, "Inside onCreate");
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
                Log.d(NAME, "Inside this method");
                itemAdded = itemAdd.getText().toString();
                Log.d(NAME, "Leaving this method with:"+itemAdded);
            }
        });

        addButt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(NAME, "Inside onClick");
                //Intent inty = new Intent(v.getContext(), GotActivity.class);
                //item = itemAdd.getText().toString();
                Intent inty = new Intent();
                //inty.putExtra(ADDED, itemAdded);
                inty.putExtra(ADDED, itemAdded);
                setResult(RESULT_OK, inty);
                //startActivity(inty);
                Log.d(NAME, "Leaving onClick with: " + itemAdded);
                finish();
            }
        });
        Log.d(NAME, "Leaving onCreate");
    }
    private void whichColor(SharedPreferences sharedPreferences){


    }

    private void changeButtonColor(String color) {
        addButt.setBackgroundColor(Integer.parseInt(color));

    }
}