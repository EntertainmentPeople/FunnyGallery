package com.klj.funnygallery.adapter;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.klj.funnygallery.R;
import com.klj.funnygallery.entity.StoryInfo;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * 自定义一个ListView适配器
 */
public class MyListViewAdapter extends BaseAdapter {
    List<StoryInfo> list;   //数据源
    Context context;        //上下文
    public MyListViewAdapter(Context context, List<StoryInfo> list) {
        this.list=list;
        this.context=context;
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
        ViewHolder vh;
        if (convertView==null){
            vh=new ViewHolder();
            convertView= LayoutInflater.from(context).inflate(R.layout.layout_gif_list_item,parent,false);
            vh.ivShow= (ImageView) convertView.findViewById(R.id.iv_gif_list_show);
            vh.tvDescribe= (TextView) convertView.findViewById(R.id.tv_gif_list_describe);
            vh.rbFavor= (RadioButton) convertView.findViewById(R.id.rb_gif_list_favor);
            convertView.setTag(vh);
        }else {
            vh= (ViewHolder) convertView.getTag();
        }
        StoryInfo storyInfo = list.get(position);
        Picasso.with(context).load(storyInfo.getMiddleUrlList().get(0)).into(vh.ivShow);
        vh.tvDescribe.setText(storyInfo.getDescription());
        vh.rbFavor.setText(storyInfo.getRepinCount()+"");
        return convertView;
    }

    /**
     * 模板
     */
    private class ViewHolder{
        public ImageView ivShow;
        public TextView tvDescribe;
        public RadioButton rbFavor;
    }
}
