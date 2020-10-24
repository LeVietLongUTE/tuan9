package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class listviewbaseAdapter extends BaseAdapter {
private Context mContext;

    public ArrayList<listviewbean> arrayingListener;

    public listviewbaseAdapter(ArrayList<listviewbean> mListenerList, Context context) {
        mContext = context;
        this.arrayingListener = new ArrayList<listviewbean>();
        this.arrayingListener =mListenerList;}
   public class ViewHolder {
       ImageView mItemPic;
       TextView mLangName, moTa;
   }


    @Override
    public int getCount() {
        return arrayingListener.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public View getView(final int position, View view, ViewGroup parent) {
        final ViewHolder holder;
        if (view == null) {
            view = LayoutInflater.from(mContext).inflate(R.layout.listview_activity, null);
            holder = new ViewHolder();
            holder.mItemPic = (ImageView) view.findViewById(R.id.image);
            holder.mLangName=(TextView)view.findViewById(R.id.name);
            holder.moTa=(TextView)view.findViewById(R.id.money);

            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();

        }
        try {
            int image = arrayingListener.get(position).getImage();
            holder.mItemPic.setImageResource(image);
            holder.mLangName.setText(arrayingListener.get(position).getName());
            holder.moTa.setText(arrayingListener.get(position).getMoney());
        }catch (Exception ex){


        }

        return view;
    }

}
