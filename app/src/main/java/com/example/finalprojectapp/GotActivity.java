package com.example.finalprojectapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

import com.example.finalprojectapp.AddActivity;
import com.example.finalprojectapp.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class GotActivity extends AppCompatActivity {
    private FloatingActionButton addFloaty;
    private Button removeButt;
    private TextView listNames;
    private static final String NAME = "GotActivity";

    protected void onCreate(Bundle savedInstanceState) {
        Log.d(NAME, "Inside onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.got_activity);

        addFloaty = findViewById(R.id.floaty);
        removeButt = findViewById(R.id.removeButt);
        listNames = findViewById(R.id.list);
        //Include the following 2 in NeedActivity and GotActivity
        PreferenceManager.setDefaultValues(this, R.xml.settings, false);
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        //Store SharedPreference value here so that it goes into the helper
        loadBCFromPref(sharedPreferences);
        //This FAB sends us to the add Activity
        addFloaty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent tent = new Intent(v.getContext(), AddActivity.class);
                //startActivity(tent);
                startActivityForResult(tent, 1);

            }
        });


        removeButt.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

            }

        });
        Log.d(NAME, "Leaving onCreate");
        listNames.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                AlertDialog.Builder buildy = new AlertDialog.Builder(GotActivity.this);

                buildy.setMessage("Are you sure you want to remove this?")
                        .setTitle("Remove")
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                listNames.setVisibility(View.GONE);


                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                listNames.setVisibility(View.VISIBLE);
                            }

                        });




                return true;
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        Log.d(NAME, "Inside the onActivityResult");
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1){
            if (resultCode == RESULT_OK){
                String reply = data.getStringExtra(AddActivity.ADDED);
                Log.d("Got", "Reply:"+reply);
                listNames.setText(reply);
                listNames.setVisibility(View.VISIBLE);
            }
        }
        Log.d(NAME, "Leaving the onActivityResult");
    }
    private void loadBCFromPref(SharedPreferences sharedPreferences) {
        changeBC(sharedPreferences.getString(getString(R.string.color_choices), "#0000FF"));
    }

    private void changeBC(String color) {
        switch (color) {
            case "#0000FF":
                removeButt.setBackgroundColor(Color.BLUE);
                break;
            case "#FFFF0059":
                removeButt.setBackgroundColor(Color.RED);
                break;
            case "#FF00FF5D":
                removeButt.setBackgroundColor(Color.GREEN);
                break;
            default:
                break;
        }
    }
}
