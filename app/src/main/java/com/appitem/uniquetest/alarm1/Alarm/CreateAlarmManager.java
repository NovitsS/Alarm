package com.appitem.uniquetest.alarm1.Alarm;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import com.appitem.uniquetest.alarm1.activity.AddAlarmActivity;
import com.appitem.uniquetest.alarm1.activity.MainActivity;
import com.appitem.uniquetest.alarm1.database.MyDataBase;

import java.security.PublicKey;
import java.util.Calendar;

/**
 * Created by HUSTy on 2016/11/10.
 */
public class CreateAlarmManager {
    private static AlarmManager mAlarmManager;
    private Calendar mCalendar;
    private PendingIntent mPendingIntent;
    private Intent mServiceintent;
    private static Context context;

    public CreateAlarmManager(Context context,String rouseTimeHour, String rouseTimeMinute,String createTime,String musicUri,String label){
        int mRouseTimeHour,mRouseTimeMinute;
        this.context=context;
        mRouseTimeHour=Integer.parseInt(rouseTimeHour);
        mRouseTimeMinute=Integer.parseInt(rouseTimeMinute);
        mCalendar=Calendar.getInstance();
        mCalendar.setTimeInMillis(System.currentTimeMillis());
        mCalendar.set(Calendar.HOUR_OF_DAY,mRouseTimeHour);
        mCalendar.set(Calendar.MINUTE,mRouseTimeMinute);
        mCalendar.set(Calendar.SECOND,0);
        mServiceintent=new Intent("com.appitem.uniquetest.alarm1.broadcast.Music");
        mServiceintent.putExtra(MyDataBase.MUSICURL,musicUri);
        mServiceintent.putExtra(MyDataBase.LABEL,label);
        mPendingIntent = PendingIntent.getBroadcast(MainActivity.context, Integer.parseInt(createTime), mServiceintent, PendingIntent.FLAG_CANCEL_CURRENT);
        mAlarmManager=(AlarmManager) MainActivity.context.getSystemService(Context.ALARM_SERVICE);
        mAlarmManager.setExact(AlarmManager.RTC_WAKEUP,mCalendar.getTimeInMillis(),mPendingIntent);
        //TODO:设置多重循环闹钟
        //mAlarmManager.setRepeating(AlarmManager.RTC_WAKEUP,mCalendar.getTimeInMillis(),5000,mPendingIntent);
    }

    public static void deleteAlarm(String createTime){
        mAlarmManager=(AlarmManager) MainActivity.context.getSystemService(Context.ALARM_SERVICE);
        Intent intent=new Intent("com.appitem.uniquetest.alarm1.broadcast.Music");
        PendingIntent  pendingIntent=PendingIntent.getBroadcast(MainActivity.context,Integer.parseInt(createTime),intent,PendingIntent.FLAG_CANCEL_CURRENT);
        mAlarmManager.cancel(pendingIntent);
    }

    /*
    private static CreateAlarmManager mAlarmManager;
    private CreateAlarmManager(){}

    public static CreateAlarmManager getInstance(){
        if (mAlarmManager==null){
            mAlarmManager = new CreateAlarmManager();
        }
        return mAlarmManager;
    }
    */

}
