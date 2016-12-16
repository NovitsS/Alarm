package com.appitem.uniquetest.alarm1.database;

import java.util.ArrayList;

/**
 * Created by HUSTy on 2016/11/9.
 */
public class Item {
    protected String mLabel;
    protected String mCreateTime;
    protected String mRouseTimeHour;
    protected String mRouseTimeMinute;
    protected String mMusicUrl;
    protected String mLoop;
    protected String mState;

    public Item(String mLabel,String mCreateTime,String mRouseTimeHour,String mRouseTimeMinute,String mMusicUrl,String mLoop,String mState){
        this.mLabel=mLabel;
        this.mCreateTime=mCreateTime;
        this.mRouseTimeHour=mRouseTimeHour;
        this.mRouseTimeMinute=mRouseTimeMinute;
        this.mMusicUrl=mMusicUrl;
        this.mLoop=mLoop;
        this.mState=mState;
    }


    public String getmLabel() {
        return mLabel;
    }

    public String getmCreateTime() {
        return mCreateTime;
    }


    public String getmMusicUrl() {
        return mMusicUrl;
    }

    public String getmLoop() {
        return mLoop;
    }

    public String getmState() {
        return mState;
    }

    public String getmRouseTimeHour() {
        return mRouseTimeHour;
    }

    public String getmRouseTimeMinute() {
        return mRouseTimeMinute;
    }

    public void setmState(String mState) {
        this.mState = mState;
    }
}