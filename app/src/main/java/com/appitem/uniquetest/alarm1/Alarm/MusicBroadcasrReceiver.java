package com.appitem.uniquetest.alarm1.Alarm;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.PowerManager;

import com.appitem.uniquetest.alarm1.R;
import com.appitem.uniquetest.alarm1.activity.PlayMusicActivity;
import com.appitem.uniquetest.alarm1.database.MyDataBase;

/**
 * Created by HUSTy on 2016/11/13.
 */
public class MusicBroadcasrReceiver extends BroadcastReceiver {
    PowerManager.WakeLock wl;
    Notification.Builder mAlarmNotification;
    NotificationManager mAlarmNotificationManager;
    Notification mNotification;
    String mMusicuri,mAlarmLabel;
    Context context;

    @Override
    public void onReceive(Context context, Intent intent) {
        this.context=context;
        Bundle mbundle=intent.getExtras();
        mAlarmLabel=mbundle.getString(MyDataBase.LABEL);
        mMusicuri=mbundle.getString(MyDataBase.MUSICURL);
        lightenScreen();
        PlayMusicActivity.playMusic(mMusicuri);
        sendNotification(mAlarmLabel);
    }

    private void lightenScreen(){
        PowerManager pm=(PowerManager)context.getSystemService(context.POWER_SERVICE);
        wl=pm.newWakeLock(PowerManager.SCREEN_BRIGHT_WAKE_LOCK|PowerManager.ACQUIRE_CAUSES_WAKEUP, "TAG");
        wl.acquire();
        Handler mTimeDelayHandler=new Handler();
        mTimeDelayHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                wl.release();
            }
        },2*1000);
    }

    private void sendNotification(String mAlarmLabel){
        mAlarmNotificationManager=(NotificationManager) context.getSystemService(context.NOTIFICATION_SERVICE);
        long[] vibrate={0,1000,1000,1000};
        mAlarmNotification=new Notification.Builder(context)
                .setAutoCancel(true)
                .setTicker("闹铃响了")
                .setSmallIcon(R.drawable.ic_access_alarms_black_36dp)
                .setContentTitle("闹钟响了")
                .setContentText(mAlarmLabel)
                .setDefaults(Notification.DEFAULT_LIGHTS|Notification.DEFAULT_VIBRATE)
                .setVibrate(vibrate)
                .setContentIntent(getDefaultIntent(Notification.FLAG_AUTO_CANCEL));
        mNotification=mAlarmNotification.build();
        mAlarmNotificationManager.notify(Notification.FLAG_ONGOING_EVENT,mNotification);
        Handler mNotificationDeleteDelayHandler=new Handler();
        mNotificationDeleteDelayHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mAlarmNotificationManager.cancel(Notification.FLAG_ONGOING_EVENT);
            }
        },50*1000);
    }

    public PendingIntent getDefaultIntent(int flags){
        Intent intent=new Intent(context,PlayMusicActivity.class);
        intent.putExtra(MyDataBase.LABEL,mAlarmLabel);
        PendingIntent pendingIntent=PendingIntent.getActivity(context,1,intent,flags);
        return pendingIntent;
    }

}
