package com.example.raghav_dell.sqlalert;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.view.View;

/**
 * Created by raghav_dell on 30-Jul-16.
 */
public class Database_Helper extends SQLiteOpenHelper
{
    public static final String DATABASE_NAME="student";
    public static final String TABLE_NAME="student_table";
    public static final String COL_1="NAME";
    public static final String COL_2="SURNAME";
    public static final String COL_3="MARKS";
    public static final String COL_4="ID";
    public Database_Helper(Context context){
        super(context,DATABASE_NAME,null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(" create table " + TABLE_NAME + " ( ID INTEGER PRIMARY KEY AUTOINCREMENT,NAME TEXT,SURNAME TEXT,MARKS INTEGER ) ");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(" DROP TABLE IF EXISTS "+ TABLE_NAME);
        onCreate(db);
    }
    public boolean add(String NAME,String SURNAME,String MARKS)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1,NAME);
        contentValues.put(COL_2,SURNAME);
        contentValues.put(COL_3, MARKS);
       long result = db.insert(TABLE_NAME,null,contentValues);
        if (result==-1)
            return false;
        else
            return true;
    }
    public Cursor getdata()
    {
        SQLiteDatabase db = this .getWritableDatabase();
        Cursor res= db.rawQuery(" SELECT * FROM "+  TABLE_NAME,null );
        return res;
    }
    public boolean updatedata(String NAME,String SURNAME,String ID,String MARKS)
    {
        SQLiteDatabase db= this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1,NAME);
        contentValues.put(COL_2,SURNAME);
        contentValues.put(COL_3, MARKS);
        contentValues.put(COL_4, ID);
        db.update(TABLE_NAME,contentValues,"ID=?",new String[]{ID} );
        return true;
    }
    public Integer deletedata(String ID){
        SQLiteDatabase db=this.getWritableDatabase();
        return db.delete(TABLE_NAME,"ID=?",new String[]{ID});
    }
}
