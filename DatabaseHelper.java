package com.example.chandu.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String dname="student.db";
    public static final String tname="sudent";
    public static final String col1="id";
    public static final String col2="name";
    public static final String col3="surname";
    public static final String col4="mark";
    public DatabaseHelper(Context context)
    {
        super(context, dname, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL("create  TABLE "+tname+"(id INTEGER  PRIMARY KEY AUTOINCREMENT,name TEXT,surname TEXT,mark INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1)
    {
        db.execSQL("DROP TABLE IF EXISTS "+tname);
        onCreate(db);

    }
    public boolean insertData(String name,String surname,String mark)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(col2,name);
        contentValues.put(col3,surname);
        contentValues.put(col4,mark);
       long r= db.insert(tname,null,contentValues);
       if(r==-1)
           return  false;
       else
           return true;

    }
public Cursor getdata()
{
    SQLiteDatabase db=this.getWritableDatabase();
    Cursor res=db.rawQuery("select * from "+tname,null);
    return res;
}
}
