package com.example.root.databaseconnection;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by root on 23/1/17.
 */

public class CustomSqliteHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "school";
    private static final int VERSION = 2;

    private static final String TABLE_NAME = "STUDENT";
    private static final String NAME = "name";
    private static final String AGE = "age";
    private static final String EMAIL="email";

    private final String CREATE_TABLE_STUDENT = "CREATE TABLE "+TABLE_NAME+" ( "+ NAME + " TEXT PRIMARY KEY, " + AGE + " TEXT, "+EMAIL+" TEXT "+")";

    public CustomSqliteHelper(Context context,  SQLiteDatabase.CursorFactory factory) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_STUDENT);


    }

    public void insertStudent(String name,String age, String email){
        SQLiteDatabase database = getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(NAME , name);
        contentValues.put(AGE,""+age);
        contentValues.put(EMAIL,email);
        database.insert(TABLE_NAME,null,contentValues);
    }

    public Cursor getStudents(){
        return getReadableDatabase().query(TABLE_NAME,null,null,null,null,null,null);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
