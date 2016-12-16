package com.appitem.uniquetest.alarm1.Alarm;

import android.app.Notification;
import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.appitem.uniquetest.alarm1.activity.PlayMusicActivity;
import com.appitem.uniquetest.alarm1.database.MyDataBase;

/**
 * Created by HUSTy on 2016/11/13.
 */
public class MusicPlayService extends Service {
    String mMusicuri,mAlarmLabel;
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    public void onCreate(){
        super.onCreate();
    }

    public int onStartCommand(Intent intent,int flags,int startId){
        Bundle mbundle=intent.getExtras();
        mAlarmLabel=mbundle.getString(MyDataBase.LABEL);
        mMusicuri=mbundle.getString(MyDataBase.MUSICURL);
        PlayMusicActivity.playMusic(mMusicuri);
        return super.onStartCommand(intent,flags,startId);
    }
}
