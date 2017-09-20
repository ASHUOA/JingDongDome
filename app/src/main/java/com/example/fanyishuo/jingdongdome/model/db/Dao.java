package com.example.fanyishuo.jingdongdome.model.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.fanyishuo.jingdongdome.model.bean.SQLiteBean;

import java.util.ArrayList;

/**
 * Created by 北城 on 2017/9/13.
 */

public class Dao {

    private final SQLiteDatabase db;

    public Dao(Context context){
        SQLite sqLite = new SQLite(context);
        db = sqLite.getWritableDatabase();
    }

    public void add(String username,String password){
        ContentValues values = new ContentValues();
        values.put("username",username);
        values.put("password",password);
        db.insert("jingdong",null,values);
    }

    public ArrayList<SQLiteBean> chaxun(){
        ArrayList<SQLiteBean> list = new ArrayList<>();
        Cursor cursor = db.query(false, "jingdong", null, null, null, null, null, null, null);
        while (cursor.moveToNext()){
            String username = cursor.getString(cursor.getColumnIndex("username"));
            String password = cursor.getString(cursor.getColumnIndex("password"));
            SQLiteBean sqLiteBean = new SQLiteBean();
            sqLiteBean.setUsername(username);
            sqLiteBean.setPassword(password);
            list.add(sqLiteBean);
        }
        return list;
    }
}
