package com.klj.funnygallery.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.klj.funnygallery.R;

import java.util.List;

/**
 * Created by 娱乐人物 on 2016/11/9.
 */
public class MySpinnerAdapter extends BaseAdapter {
    private Context context;    //上下文
    private List<String> list;  //数据集合
    ViewHolder vh;

    public MySpinnerAdapter(Context context, List<String> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
       if(convertView==null){
           vh=new ViewHolder();
           convertView= LayoutInflater.from(context).inflate(R.layout.layout_spinner_item,parent,false);
           vh.tvChoose= (TextView) convertView.findViewById(R.id.tv_spinner_choose);
           convertView.setTag(vh);
       }else {
           vh= (ViewHolder) convertView.getTag();
       }
        vh.tvChoose.setText(list.get(position));
        return convertView;
    }

    private class ViewHolder {
        public TextView tvChoose;
    }
}
