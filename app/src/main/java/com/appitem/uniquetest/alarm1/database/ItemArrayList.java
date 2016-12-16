package com.appitem.uniquetest.alarm1.database;

import android.content.ContentValues;
import android.content.Context;

import com.appitem.uniquetest.alarm1.Alarm.CreateAlarmManager;

import java.util.ArrayList;

/**
 * Created by HUSTy on 2016/11/9.
 */
public class ItemArrayList {
        public static ArrayList<Item> itemArrayList;
        //TODO:创建alarmManager的list

        public ItemArrayList(){
            itemArrayList=new ArrayList<Item>();
            OperateDateBase.addItemToItemArrayList();
        }

        public static void addItemToList(Item item){
            itemArrayList.add(item);
            OperateDateBase.writeItemToDateBase(item);
        }

        public static void closeAlarm(int position){
            ContentValues values=new ContentValues();
            String mCreateTime=itemArrayList.get(position).getmCreateTime();
            itemArrayList.get(position).setmState("未开启");
            values.put(MyDataBase.STATE,"未开启");
            OperateDateBase.upDateItem(mCreateTime,values);
            CreateAlarmManager.deleteAlarm(mCreateTime);
        }

        public static void openAlarm(Context context,int position){
            ContentValues values=new ContentValues();
            String mCreateTime=itemArrayList.get(position).getmCreateTime();
            String mRouseTimeHour=itemArrayList.get(position).getmRouseTimeHour();
            String mRouseTimeMinute=itemArrayList.get(position).getmRouseTimeMinute();
            String mItemMusicUri=itemArrayList.get(position).getmMusicUrl();
            String mItemLabel=itemArrayList.get(position).getmLabel();
            itemArrayList.get(position).setmState("已开启");
            values.put(MyDataBase.STATE,"已开启");
            CreateAlarmManager createAlarmManager=new CreateAlarmManager(context,mRouseTimeHour,mRouseTimeMinute,mCreateTime,mItemMusicUri,mItemLabel);
        }

        public static void deleteAlarm(int position){
            String mCreateTime=itemArrayList.get(position).getmCreateTime();
            CreateAlarmManager.deleteAlarm(mCreateTime);
            itemArrayList.remove(position);
            OperateDateBase.deleteItem(mCreateTime);
        }
}
