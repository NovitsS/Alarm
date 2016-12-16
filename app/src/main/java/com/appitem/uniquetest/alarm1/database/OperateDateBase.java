package com.appitem.uniquetest.alarm1.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by HUSTy on 2016/11/9.
 */
public class OperateDateBase {
    private static MyDataBase myDataBase;
    private static SQLiteDatabase mDataBaseReader,mDataBaseWriter;

    public OperateDateBase(Context context){
        myDataBase=new MyDataBase(context);
        mDataBaseReader=myDataBase.getReadableDatabase();
        mDataBaseWriter=myDataBase.getWritableDatabase();
    }


    public static void writeItemToDateBase(Item item){
        mDataBaseWriter.insert(MyDataBase.TABLENAME,null,convertItemToValues(item));
    }

    private static ContentValues convertItemToValues(Item item){
        ContentValues values=new ContentValues();
        values.put(MyDataBase.LABEL, item.getmLabel());
        values.put(MyDataBase.CREATETIME, item.getmCreateTime());
        values.put(MyDataBase.ROUSETIMEHOUR, item.getmRouseTimeHour());
        values.put(MyDataBase.ROUSETIMEMINUTE, item.getmRouseTimeMinute());
        values.put(MyDataBase.MUSICURL, item.getmMusicUrl());
        values.put(MyDataBase.STATE, item.getmState());
        values.put(MyDataBase.LOOP, item.getmLoop());
        return values;
    }

    public static void addItemToItemArrayList(){
        Cursor cursor = mDataBaseReader.query(MyDataBase.TABLENAME, null, null, null, null, null, null);
        int num=cursor.getCount();
        for(int i=0;i<num;i++){
            cursor.moveToPosition(i);
            Item item=new Item(cursor.getString(cursor.getColumnIndex(MyDataBase.LABEL)),
                    cursor.getString(cursor.getColumnIndex(MyDataBase.CREATETIME)),
                    cursor.getString(cursor.getColumnIndex(MyDataBase.ROUSETIMEHOUR)),
                    cursor.getString(cursor.getColumnIndex(MyDataBase.ROUSETIMEMINUTE)),
                    cursor.getString(cursor.getColumnIndex(MyDataBase.MUSICURL)),
                    cursor.getString(cursor.getColumnIndex(MyDataBase.LOOP)),
                    cursor.getString(cursor.getColumnIndex(MyDataBase.STATE)));
            ItemArrayList.itemArrayList.add(item);
        }
        cursor.close();
    }

    public static void upDateItem(String createTime,ContentValues values){
        mDataBaseWriter.update(MyDataBase.TABLENAME,values,MyDataBase.CREATETIME+"=?",new String[]{createTime});
    }

    public static void deleteItem(String createTime){
        mDataBaseWriter.delete(MyDataBase.TABLENAME,MyDataBase.CREATETIME+"=?",new String[]{createTime});
    }
}
