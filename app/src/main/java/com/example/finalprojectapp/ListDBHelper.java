package com.example.finalprojectapp;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.LinkedList;

public class ListDBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "test";
    public static final int DATABASE_VERSION = 1;
    public static final String TABLE_NAME = "List";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_NAME = "name";
    private static final String NAME = "LISTDBHELPER";

    public ListDBHelper(Context context) { super(context, DATABASE_NAME, null, DATABASE_VERSION); }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d(NAME, "Inside onCreate");
        String SQL_CREATE_LIST_TABLE = "CREATE TABLE " +
                TABLE_NAME + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY," +
                COLUMN_NAME + " TEXT" + ") ";
        db.execSQL(SQL_CREATE_LIST_TABLE);
        Log.d(NAME, "Leaving onCreate");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d(NAME, "Inside onUpgrade");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
        Log.d(NAME, "Leaving onCreate");
    }

    LinkedList<ListItem> list(){
        Log.d(NAME, "Inside list");
        String sql = "select * from " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        LinkedList<ListItem> mList = new LinkedList<>();
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor.moveToFirst()){
            Log.d(NAME, "Inside this if loop");
            do {
                Log.d(NAME, "Inside this do loop");
                int id = Integer.parseInt(cursor.getString(0));
                Log.d(NAME, "ID:"+Integer.toString(id));
                String name = cursor.getString(1);
                Log.d(NAME, "Name:"+name);
                mList.add(new ListItem(id, name));
            }
            while (cursor.moveToNext());
        }
        cursor.close();
        Log.d(NAME, "Leaving list");
        return mList;
    }


    public void addList(ListItem list){
        Log.d(NAME, "Inside addList");
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, list.getName());
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(TABLE_NAME, null, values);
        Log.d(NAME, "Leaving addList");
    }

    public void updateList(String list){
        Log.d(NAME, "Inside updateList");
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, list);
        SQLiteDatabase db = this.getWritableDatabase();
        db.update(TABLE_NAME, values,COLUMN_ID + " =?", new String[]{list});
        Log.d(NAME, "Inside updateList");
    }

    public void deleteList(int id){
        Log.d(NAME, "Inside deleteList");
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, COLUMN_ID + " = ?", new String[]{String.valueOf(id)});
        Log.d(NAME, "Leaving deleteList");
    }

}
