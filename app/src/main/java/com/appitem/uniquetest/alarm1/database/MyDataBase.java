package com.appitem.uniquetest.alarm1.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by HUSTy on 2016/11/8.
 */
public class MyDataBase extends SQLiteOpenHelper {
    public static final String TABLENAME="messageOfAlarm";
    public static final String ID="_id";
    public static final String LABEL="label";
    public static final String CREATETIME="createtime";
    public static final String ROUSETIMEHOUR="rousetimehour";
    public static final String ROUSETIMEMINUTE = "rousetimemunite";
    public static final String MUSICURL="musicurl";
    public static final String LOOP="loop";
    public static final String STATE="state";
    protected Context context;

    public MyDataBase(Context context) {
        super(context, "AlarmDataBase", null, 1);
        this.context=context;
    }

    //TODO:创建数据库的时间表
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE "+TABLENAME+"("+
                ID+" integer primary key autoincrement," +
                LABEL+" text,"+
                CREATETIME+" text,"+
                ROUSETIMEHOUR+" text,"+
                ROUSETIMEMINUTE+" text,"+
                MUSICURL+" text," +
                LOOP+" text,"+
                STATE+" text"+")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
