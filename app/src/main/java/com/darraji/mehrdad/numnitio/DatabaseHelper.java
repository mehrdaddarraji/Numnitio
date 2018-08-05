package com.darraji.mehrdad.numnitio;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String databaseName = "register.db";
    public static final String tableName = "register";
    public static final String colOne = "ID";
    public static final String colTwo = "FirstName";
    public static final String colThree = "LastName";
    public static final String colFour = "Email";
    public static final String colFive = "Password";

    public DatabaseHelper(Context context) {
        super(context, databaseName, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE " + databaseName + " (ID INTEGER PRIMARY KEY AUTO" +
                " INCREMENT, FirstName TEXT, LastName TEXT, Email TEXT, Password TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + databaseName);
        onCreate(sqLiteDatabase);
    }
}
