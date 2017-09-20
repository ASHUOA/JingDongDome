package com.example.fanyishuo.jingdongdome.model.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by 北城 on 2017/9/13.
 */

public class SQLite extends SQLiteOpenHelper {
    public SQLite(Context context) {
        super(context, "JingDong.dp", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table jingdong(id Integer primary key autoincrement,username varchar(20),password varchar(20))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
