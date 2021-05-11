package com.example.finalprojectapp;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import java.util.LinkedList;

public class MainActivity extends AppCompatActivity {

    private ListDBHelper listDBHelper;
    LinkedList<ListItem> mList;
    RecyclerView recyclerView;
    ListAdapter listAdapter;
    private static final String NAME = "MAIN";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(NAME, "Inside onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(NAME, "Inside onCreate onClick");
                //Add something to list
                addItemDialog();
            }
        });
        listDBHelper = new ListDBHelper(this);
        mList = listDBHelper.list();
        recyclerView = findViewById(R.id.recycler_view);
        listAdapter = new ListAdapter(this, mList);
        recyclerView.setVisibility(View.VISIBLE);
        recyclerView.setAdapter(listAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        PreferenceManager.setDefaultValues(this, R.xml.settings, false);
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        String storecolor = sharedPreferences.getString(getString(R.string.color_choices),"#0000FF");



        Log.d(NAME, "Leaving the onCreate");
    }

    private void addItemDialog(){
        Log.d(NAME, "Inside this helper");
        final AlertDialog.Builder addDialog = new AlertDialog.Builder(MainActivity.this);
        addDialog.setTitle("Add a list");
        final EditText input = new EditText(MainActivity.this);
        addDialog.setView(input);
        addDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Log.d(NAME, "Helper Adding it");
                String item = input.getText().toString();
                Log.d(NAME, item);
                if (TextUtils.isEmpty(item)){
                    Toast.makeText(MainActivity.this, "Please put something", Toast.LENGTH_LONG).show();
                }
                else{
                    Log.d(NAME, "There is something here. Add it Helper");
                    ListItem nList = new ListItem(item);
                    listDBHelper.addList(nList);
                    finish();
                    startActivity(getIntent());
                    Log.d(NAME, "I'm out of here");
                }
            }
        }).create();
        addDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        }).create();
        addDialog.show();
        Log.d(NAME, "Leaving this helper");
    }

    /*
    End of relevancy territory for me
    */

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent intent = new Intent(this, SettingsActivity.class);
            startActivity(intent);
            return true;
        }
        if (id == R.id.info){

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (listDBHelper != null) {
            listDBHelper.close();
        }
    }


    public void showInfo(MenuItem item) {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Info");
        builder.setMessage("Press the Button on the bottom right to create a new list.\n" +
                "If you want to delete a list, press the remove button on the top right of each list.");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) { }
        });
        builder.show();
    }
}