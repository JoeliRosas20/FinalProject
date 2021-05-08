package com.example.finalprojectapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class GotActivity extends AppCompatActivity {
    private FloatingActionButton addFloaty;
    private Button removeButt;
    private TextView listNames;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.got_activity);

        addFloaty = findViewById(R.id.floaty);
        removeButt = findViewById(R.id.removeButt);
        listNames = findViewById(R.id.list);

        addFloaty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent tent = new Intent(v.getContext(), AddActivity.class);
                //startActivity(tent);


            }
        });


        removeButt.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

            }

        });

    }
}
