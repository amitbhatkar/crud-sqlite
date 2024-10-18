package com.example.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.view.View;

import androidx.annotation.Nullable;

public class DB_Helper extends SQLiteOpenHelper {

    private static final String database_name= "DEMO.db";

    public DB_Helper(@Nullable Context context) {
        super(context, database_name, null, 1);

        //SQLiteDatabase db=this.getReadableDatabase();         // last
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String var_query = "CREATE TABLE register (NAME TEXT, ROLL_NO INTEGER PRIMARY KEY, DOB INTEGER,CONTACT INTEGER)";
        db.execSQL(var_query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS register");
        onCreate(db);
    }


    // ---------------------------- INSERT VALUES COLUM --------------------------
    public void getInsertTable(String name, String roll, String DOB1, String contact){

        SQLiteDatabase sq=this.getWritableDatabase();

        ContentValues content= new ContentValues();
        content.put("NAME", name);
        content.put("ROLL_NO", roll);
        content.put("DOB", DOB1);
        content.put("CONTACT", contact);

        sq.insert("register", null, content);
        sq.close();

    }


    // ---------------------------- VIEW ALL VALUES --------------------------------

    public Cursor getData()
    {
        SQLiteDatabase db= this.getWritableDatabase();
        Cursor cur;
        cur = db.rawQuery("select * from register", null);
        return cur;
    }


    //------------------------------UPDATE DATA-----------------------------------------
    public Boolean getUpdateData(String name, String roll, String DOB1, String contact){

        SQLiteDatabase sq=this.getWritableDatabase();

        ContentValues content= new ContentValues();

        content.put("name", name);
        content.put("roll", roll);
        content.put("DOB", DOB1);
        content.put("CONTACT", contact);

        Cursor cr= sq.rawQuery("select * from register where roll=?",new String[]{roll});

        if (cr.getCount()>0)
        {
            int result= sq.update("register", content, "roll=?", new String[]{roll});
            if (result==-1)
                return false;
            else
                return true;
        }
        else
            return false;

    }


    //------------------------------DELETE DATA-----------------------------------------
    public Boolean getdeleteData(String name) {

        SQLiteDatabase sq = this.getWritableDatabase();

        Cursor cr = sq.rawQuery("select * from register where name=?", new String[]{name});

        if (cr.getCount() > 0) {
            long result = sq.delete("register", "name=?", new String[]{name});
            if (result == -1)
                return false;
            else
                return true;
        } else
            return false;
    }

}
