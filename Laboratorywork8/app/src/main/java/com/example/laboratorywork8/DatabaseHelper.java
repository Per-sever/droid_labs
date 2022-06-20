package com.example.laboratorywork8;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

public class DatabaseHelper extends SQLiteOpenHelper implements BaseColumns {
    public static final String DB_CONTACTS = "contacts.db";
    public static final int DATABASE_VERSION = 1;

    public DatabaseHelper(Context context) {
        super(context, DB_CONTACTS, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + DBContract.DBEntry.TABLE_NAME
                + " (_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + DBContract.DBEntry.COLUMN_NAME_NAME + " TEXT, " +
                DBContract.DBEntry.COLUMN_NAME_PHONE + " TEXT, " + DBContract.DBEntry.COLUMN_NAME_ADDRESS + " TEXT, " + DBContract.DBEntry.COLUMN_NAME_BIRTHDAY + " TEXT);" );
        ContentValues values = new ContentValues();
        values.put(DBContract.DBEntry.COLUMN_NAME_NAME, "Иван Иванов");
        values.put(DBContract.DBEntry.COLUMN_NAME_PHONE, "8-999-111-11-11");
        values.put(DBContract.DBEntry.COLUMN_NAME_ADDRESS, "улица Y, дом 11, кв 35");
        values.put(DBContract.DBEntry.COLUMN_NAME_BIRTHDAY, "03.11.1993");
        db.insert(DBContract.DBEntry.TABLE_NAME,
                DBContract.DBEntry.COLUMN_NAME_NAME, values);
        values.put(DBContract.DBEntry.COLUMN_NAME_NAME, "Пётр Петров");
        values.put(DBContract.DBEntry.COLUMN_NAME_PHONE, "8-999-222-22-22");
        values.put(DBContract.DBEntry.COLUMN_NAME_ADDRESS, "улица X, дом 20, кв 74");
        values.put(DBContract.DBEntry.COLUMN_NAME_BIRTHDAY, "03.11.1993");
        db.insert(DBContract.DBEntry.TABLE_NAME,
                DBContract.DBEntry.COLUMN_NAME_NAME, values);
        values.put(DBContract.DBEntry.COLUMN_NAME_NAME, "Иван Петров");
        values.put(DBContract.DBEntry.COLUMN_NAME_PHONE, "8-999-333-33-33");
        values.put(DBContract.DBEntry.COLUMN_NAME_ADDRESS, "улица Z, дом 1, кв 23");
        values.put(DBContract.DBEntry.COLUMN_NAME_BIRTHDAY, "03.11.1993");
        db.insert(DBContract.DBEntry.TABLE_NAME,
                DBContract.DBEntry.COLUMN_NAME_NAME, values);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int
            newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " +
                DBContract.DBEntry.TABLE_NAME);
        onCreate(db);
    }

}
