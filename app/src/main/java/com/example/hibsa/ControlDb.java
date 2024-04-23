package com.example.hibsa;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class ControlDb extends SQLiteOpenHelper {

    SQLiteDatabase db ;
    private final String sqlCreateUsers = "CREATE TABLE Users ("
            + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
            + "name TEXT,"
            + "email TEXT,"
            + "password TEXT,"
            + "role TEXT"
            + ");";
    private final String sqlCreateCars =  "CREATE TABLE Cars ("
            + "id INTEGER," /*INTEGER PRIMARY KEY AUTOINCREMENT,*/
            + "brand TEXT,"
            + "name TEXT,"
            + "model INTEGER,"
            + "description TEXT,"
            + "price INTEGER,"
            + "cylinders INTEGER,"
            + "image TEXT"
            + ")";

    public ControlDb(@Nullable Context context, @Nullable String name, @Nullable CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        // db.execSQL(sqlCreateUsers);
        sqLiteDatabase.execSQL(sqlCreateCars);
        System.out.println("Base de datos creada");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS Cars");
        sqLiteDatabase.execSQL(sqlCreateCars);
        System.out.println("Base de datos actualizada");
    }

    public SQLiteDatabase connect(){
        db = this.getWritableDatabase();
        return db;
    }

    public void insertUser(int code, String name) {
        db.execSQL("INSERT INTO Users (code, name) " + "VALUES (" + code + "," + name + ")");
        System.out.println("Usuario insertado");
    }

    public void close() {
        if (db != null) {
            db.close();
        }
    }
}
