package com.example.crudsqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(Context context) {
        super(context, "Userdata.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("create Table Userdetails( admissionNo TEXT primary key,fullName TEXT, contact TEXT, email TEXT, dob TEXT, course TEXT, department TEXT )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int i1) {
        DB.execSQL("drop Table if exists Userdetails");
    }

    //Methode to insert data to the db
    public Boolean insertUserData(String admissionNo,String fullName,String contact, String email, String dob, String course, String department){
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("AdmissionNo",admissionNo);
        contentValues.put("FullName",fullName);
        contentValues.put("Contact",contact);
        contentValues.put("Email",email);
        contentValues.put("DOB",dob);
        contentValues.put("Course",course);
        contentValues.put("Department",department);

        long result = DB.insert("Userdetails", null, contentValues);
        if (result == -1){
            return false;
        }else {
            return true;
        }
    }

    //Methode to update data
    public Boolean updateUserData(String admissionNo,String fullName,String contact, String email, String dob, String course, String department){
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("FullName",fullName);
        contentValues.put("Contact",contact);
        contentValues.put("Email",email);
        contentValues.put("DOB",dob);
        contentValues.put("Course",course);
        contentValues.put("Department",department);

        //Select a row
        Cursor cursor = DB.rawQuery("Select * from USerdetails where admissionNo = ?",new String[] {admissionNo});

        if (cursor.getCount() > 0){
            long result = DB.update("Userdetails", contentValues,"admissionNo = ?",new String[] {admissionNo});
            if (result == -1){
                return false;
            }else {
                return true;
            }
        }else {
            return false;
        }
    }

    //Methode to delete data from the DB
    public Boolean deleteData(String admissionNo){
        SQLiteDatabase DB = this.getWritableDatabase();


        //Select a row
        Cursor cursor = DB.rawQuery("Select * from USerdetails where admissionNo = ?",new String[] {admissionNo});

        if (cursor.getCount() > 0){
            long result = DB.delete("Userdetails","admissionNo = ?",new String[] {admissionNo});
            if (result == -1){
                return false;
            }else {
                return true;
            }
        }else {
            return false;
        }
    }

    //Method view data
    public Cursor getData(){
        SQLiteDatabase DB = this.getWritableDatabase();

        //Select a row
        Cursor cursor = DB.rawQuery("Select * from USerdetails",null);

        return cursor;
    }
}
