package com.example.finalprojectapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class NeedActivity extends AppCompatActivity {
    private FloatingActionButton addFloaty;
    private Button removeButt;
    private TextView listNames;
    private static final String NAME = "GotActivity";
    ListItem item;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        Log.d(NAME, "Inside onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.need_activity);
        addFloaty = findViewById(R.id.Need_floaty);
        removeButt = findViewById(R.id.Need_remove);
        listNames = findViewById(R.id.list);
        addFloaty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent tent = new Intent(v.getContext(), NeedAddActivity.class);
                startActivityForResult(tent, 1);
            }
        });
        removeButt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        Log.d(NAME, "Leaving onCreate");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        Log.d(NAME, "Inside the onActivityResult");
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1){
            if (resultCode == RESULT_OK){
                String reply = data.getStringExtra(NeedAddActivity.ADDED);
                Log.d("Got", "Reply:"+reply);
                listNames.setText(reply);
                listNames.setVisibility(View.VISIBLE);
            }
        }
        Log.d(NAME, "Leaving the onActivityResult");
    }

    public void SearchStore(View view) {
        Log.d(NAME, "Inside SearchStore method");
        Uri addressUri = Uri.parse("geo:0,0?q=+shoes+store");
        Intent intent = new Intent(Intent.ACTION_VIEW, addressUri);
        intent.setPackage("com.google.android.apps.maps");
        startActivity(intent);
        Log.d(NAME, "Leaving SearchStore method");
    }

    public void SearchSite(View view) {
        Log.d(NAME, "Inside SearchSite method");
        String url = "https://shopping.google.com/?nord=1";
        Uri webpage = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
        startActivity(intent);
        Log.d(NAME, "Inside SearchSite method");
    }
}
