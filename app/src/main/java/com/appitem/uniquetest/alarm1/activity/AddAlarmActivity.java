package com.appitem.uniquetest.alarm1.activity;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TimePicker;
import android.widget.Toast;

import com.appitem.uniquetest.alarm1.Alarm.CreateAlarmManager;
import com.appitem.uniquetest.alarm1.R;
import com.appitem.uniquetest.alarm1.database.Item;
import com.appitem.uniquetest.alarm1.database.ItemArrayList;

import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * Created by HUSTy on 2016/11/8.
 */
public class AddAlarmActivity extends AppCompatActivity implements View.OnClickListener,TimePicker.OnTimeChangedListener{

    private ImageButton mCancelButton,mSureButton,mMusicButton,mLoopButton;
    private EditText mLabelEditText;
    private TimePicker mTimePicker;
    private String mRouseTimeHour,mRouseTimeMinute,mMusicUri;
    private static final int CHOOSE_MUSIC=3;


    @TargetApi(Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addalarm);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mCancelButton = (ImageButton) findViewById(R.id.cancel_addalarm);
        mCancelButton.setOnClickListener(this);
        mSureButton = (ImageButton) findViewById(R.id.sure_addalarm);
        mSureButton.setOnClickListener(this);
        mMusicButton = (ImageButton) findViewById(R.id.music_addalarm);
        mMusicButton.setOnClickListener(this);
        mLoopButton = (ImageButton) findViewById(R.id.loop_addalarm);
        mLoopButton.setOnClickListener(this);
        mLabelEditText = (EditText) findViewById(R.id.edittext_addalarm);
        mTimePicker=(TimePicker)findViewById(R.id.timepicker_addalarm);
        mTimePicker.setIs24HourView(true);
        mTimePicker.setOnTimeChangedListener(this);
    }

    public Item getAlarmMessage(){
        //TODO:添加音乐url，添加循环次数
        Item item=new Item(mLabelEditText.getText().toString(),getCurrentTime(),
                mRouseTimeHour,mRouseTimeMinute,mMusicUri,null,"已开启");
        return item;
    }

    public String getCurrentTime(){
        SimpleDateFormat format=new SimpleDateFormat("MMddHHmmss");
        Date date=new Date();
        return format.format(date);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.cancel_addalarm:
                finish();
                break;
            case R.id.sure_addalarm:
                Item item=getAlarmMessage();
                ItemArrayList.addItemToList(item);
                CreateAlarmManager createAlarmManager=new CreateAlarmManager(AddAlarmActivity.this,mRouseTimeHour,mRouseTimeMinute,getCurrentTime(),mMusicUri,mLabelEditText.getText().toString());
                finish();
                break;
            case R.id.music_addalarm:
                Intent intent=new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("*/*");
                intent.addCategory(Intent.CATEGORY_OPENABLE);
                startActivityForResult(intent,CHOOSE_MUSIC);
                break;
            case R.id.loop_addalarm:
                break;
        }
    }

    @Override
    public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
        mRouseTimeHour=Integer.toString(hourOfDay);
        mRouseTimeMinute=Integer.toString(minute);
    }

    @Override
    protected void onActivityResult(int requestCode,int resultCode,Intent date){
        switch (requestCode){
            case CHOOSE_MUSIC:
                Uri uri=date.getData();
                mMusicUri=uri.toString();
        }
    }

}
