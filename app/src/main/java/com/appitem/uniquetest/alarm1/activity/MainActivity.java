package com.appitem.uniquetest.alarm1.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import com.appitem.uniquetest.alarm1.R;
import com.appitem.uniquetest.alarm1.database.ItemArrayList;
import com.appitem.uniquetest.alarm1.database.OperateDateBase;
import com.appitem.uniquetest.alarm1.recyclerviewadapter.AlarmRecyclerViewAdapter;

public class MainActivity extends AppCompatActivity implements View.OnClickListener,AlarmRecyclerViewAdapter.OnItemDeleteClickListener{
    private RecyclerView mRecyclerView;
    private Toolbar toolbar;
    private FloatingActionButton fab;
    private AlarmRecyclerViewAdapter mAdapter;
    private ItemArrayList mItemArrayList;
    private OperateDateBase mOperateDateBase;
    public static Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context=getApplicationContext();
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mOperateDateBase=new OperateDateBase(this);
        mItemArrayList=new ItemArrayList();
        fab=(FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(this);
        mRecyclerView=(RecyclerView)findViewById(R.id.main_recyclerview);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter=new AlarmRecyclerViewAdapter(ItemArrayList.itemArrayList,this);
        mAdapter.setOnItemDeleteClickListener(this);
        mRecyclerView.setAdapter(mAdapter);

        ItemTouchHelper.SimpleCallback mCallback=new ItemTouchHelper.SimpleCallback(ItemTouchHelper.DOWN|ItemTouchHelper.UP,ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT){
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }
            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                int position=viewHolder.getAdapterPosition();
                if(direction==ItemTouchHelper.LEFT){
                    ItemArrayList.closeAlarm(position);
                    mAdapter.notifyDataSetChanged();
                }
                if(direction==ItemTouchHelper.RIGHT){
                    ItemArrayList.openAlarm(MainActivity.this,position);
                    mAdapter.notifyDataSetChanged();
                }
            }
        };

        ItemTouchHelper mItemTouchHelper=new ItemTouchHelper(mCallback);
        mItemTouchHelper.attachToRecyclerView(mRecyclerView);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.fab:
                Intent intent=new Intent(MainActivity.this,AddAlarmActivity.class);
                startActivity(intent);
                break;
        }
    }


    protected void onResume(){
        super.onResume();
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onItemDeleteClick(View v, int position) {
        ItemArrayList.deleteAlarm(position);
        mAdapter.notifyDataSetChanged();
    }
}
