package com.appitem.uniquetest.alarm1.activity;

import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.appitem.uniquetest.alarm1.R;
import com.appitem.uniquetest.alarm1.activity.MainActivity;
import com.appitem.uniquetest.alarm1.database.MyDataBase;

import java.io.IOException;

/**
 * Created by HUSTy on 2016/11/13.
 */
public class PlayMusicActivity extends AppCompatActivity {
    private static MediaPlayer mMedioPlay=new MediaPlayer();
    private Button mSureButton;
    private EditText mAnswer;
    private TextView mNum1,mNum2,mCharater;
    private String mNumStr1,mNumStr2,mCharaterStr;
    private int num1,num2,num3,n;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playmusic);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        num1=getRandomNum();
        num2=getRandomNum();
        n=num1+num2;
        num1=Math.max(num1,num2);
        num2=n-num1;
        mCharaterStr=getCharaterRandom();
        num3=getResult(mCharaterStr);
        mNum1=(TextView)findViewById(R.id.num1_playmusic);
        mNum1.setText(Integer.toString(num1));
        mNum2=(TextView)findViewById(R.id.num2_playmusic);
        mNum2.setText(Integer.toString(num2));
        mCharater=(TextView)findViewById(R.id.charater1_playmusic);
        mCharater.setText(mCharaterStr);
        mSureButton=(Button)findViewById(R.id.sure_playmusic);
        mAnswer=(EditText)findViewById(R.id.answer_playmusic);
        mSureButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                String str=mAnswer.getText().toString();
                String n=Integer.toString(num3);
                if(n.equals(str))
                {
                    stopAlarmMusic();
                    finish();
                }else{
                    Toast.makeText(PlayMusicActivity.this,"答案错误",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public static void playMusic(String mMusicuri){
        Uri ur=Uri.parse(mMusicuri);
        try {
            mMedioPlay.reset();
            mMedioPlay.setAudioStreamType(AudioManager.STREAM_ALARM);
            mMedioPlay.setDataSource(MyApp.context,ur);
            mMedioPlay.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }
        mMedioPlay.start();
    }

    public static void stopAlarmMusic(){
        mMedioPlay.stop();
        mMedioPlay.reset();
    }

    private int getRandomNum(){
        return (int)(Math.random()*100);
    }

    private String getCharaterRandom(){
        switch ((int)(Math.random()*3)){
            case 0:
                return "+";
            case 1:
                return "-";
            case 2:
                return "*";
        }
        return null;
    }

    private int getResult(String str){
        if(str.equals("+"))
            return num1+num2;
        if(str.equals("-"))
            return num1-num2;
        if(str.equals("*"))
            return num1*num2;
        return -1;
    }
}
