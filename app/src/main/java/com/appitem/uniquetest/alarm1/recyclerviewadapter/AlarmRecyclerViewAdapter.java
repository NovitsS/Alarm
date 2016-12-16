package com.appitem.uniquetest.alarm1.recyclerviewadapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;

import com.appitem.uniquetest.alarm1.R;
import com.appitem.uniquetest.alarm1.database.Item;
import java.util.ArrayList;


/**
 * Created by HUSTy on 2016/11/9.
 */
public class AlarmRecyclerViewAdapter extends RecyclerView.Adapter<AlarmRecyclerViewAdapter.ItemViewHolder>
                                    implements View.OnClickListener{
    private ArrayList<Item> mItemArrayList;
    private Context mComtext;
    private OnItemDeleteClickListener mListener=null;

    public AlarmRecyclerViewAdapter(ArrayList<Item> itemArrayList, Context context){
        this.mComtext=context;
        this.mItemArrayList=itemArrayList;
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(mComtext).inflate(R.layout.content_item,parent,false);
        ItemViewHolder itemViewHolder=new ItemViewHolder(view);
        return itemViewHolder;
    }

    //TODO:绑定item数据
    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        Item item=mItemArrayList.get(position);
        holder.mItemLoop.setText(item.getmLoop());
        holder.mItemRouseTime.setText(item.getmRouseTimeHour()+":"+
                item.getmRouseTimeMinute());
        holder.mItemLabel.setText(item.getmLabel());
        if(!item.getmState().equals("未开启"))
            holder.mItemState.setImageResource(R.drawable.ic_notifications_black_36dp);
        else holder.mItemState.setImageResource(R.drawable.ic_notifications_off_black_36dp);
        holder.mDeleteItemButton.setOnClickListener(this);
        holder.mDeleteItemButton.setTag(position);
    }

    @Override
    public int getItemCount() {
        int num=mItemArrayList.size();
        return num;
    }

    @Override
    public void onClick(View v) {
        if(mListener!=null)
            mListener.onItemDeleteClick(v,(Integer) v.getTag());
    }


    static class ItemViewHolder extends RecyclerView.ViewHolder{
        TextView mItemLabel,mItemRouseTime,mItemLoop;
        ImageView mItemState;
        ImageButton mDeleteItemButton;
        public ItemViewHolder(View itemView) {
            super(itemView);
            mItemLabel=(TextView)itemView.findViewById(R.id.itemlabel_item);
            mItemRouseTime=(TextView)itemView.findViewById(R.id.rousetime_item);
            mItemState=(ImageView)itemView.findViewById(R.id.itemstate_item);
            mItemLoop=(TextView)itemView.findViewById(R.id.loop_item);
            mDeleteItemButton=(ImageButton)itemView.findViewById(R.id.delete_item);
        }
    }

    public static interface OnItemDeleteClickListener{
        public void onItemDeleteClick(View v,int position);
    }

    public void setOnItemDeleteClickListener(OnItemDeleteClickListener listener){
        this.mListener=listener;
    }
}
