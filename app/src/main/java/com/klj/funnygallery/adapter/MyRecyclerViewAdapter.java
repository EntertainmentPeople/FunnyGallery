package com.klj.funnygallery.adapter;

import android.content.Context;
import android.support.annotation.BoolRes;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.klj.funnygallery.R;
import com.klj.funnygallery.entity.StoryInfo;
import com.klj.funnygallery.exception.MyException;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 为RecyclerView设置适配器
 */
public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.MyViewHolder> implements View.OnClickListener {

    private List<StoryInfo> lists = new ArrayList<>();      //数据
    private Context context;    //上下文
    private Map<Integer, Boolean> map = new HashMap<>();       //存放CheckBox的状态

    /**
     * 点击的回调
     *
     * @param v
     */
    @Override
    public void onClick(View v) {
        if (myClickCallBack != null) {
            myClickCallBack.click(v, (Integer) v.getTag());
        } else {
            throw new RuntimeException(new MyException("对象为空"));
        }
    }

    public interface MyClickCallBack {
        void click(View v, int position);
    }

    MyClickCallBack myClickCallBack = null;

    /**
     * 注册监听的事件
     */
    public void setOnMyClickListener(MyClickCallBack myClickCallBack) {
        this.myClickCallBack = myClickCallBack;
    }

    public MyRecyclerViewAdapter(Context context, List<StoryInfo> lists) {
        this.lists = lists;
        this.context = context;
        if (context instanceof MyClickCallBack) {
            myClickCallBack = (MyClickCallBack) context;
        }
        initMap();
    }

    /**
     * 初始化Map
     */
    private void initMap() {
        for (int i = 0; i < lists.size(); i++) {
            map.put(i, false);
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_recycler_item, null);
        return new MyViewHolder(view);
    }

    /**
     * 返回map
     * @return
     */
    public Map<Integer, Boolean> getMap() {
        return map;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        /*View view = holder.itemView;
        view.setOnClickListener(this);
        view.setTag(position);*/
        StoryInfo storyInfo = lists.get(position);
        Picasso.with(context).load(storyInfo.getMiddleUrlList().get(0)).into(holder.getIvShow());
        holder.getTvDescribe().setText(storyInfo.getDescription());
        holder.getCbFavor().setText(storyInfo.getRepinCount() + "");
        if (storyInfo.getUserRepin() != 0) {
            map.put(position,true);
        }
        setClickListener(holder.getIvShow(),position);
        //setClickListener(holder.getRlFavor(),position);
        setClickListener(holder.getCbFavor(),position);
        // 设置CheckBox的状态
        if (map.get(position) == null) {
            map.put(position, false);
        }
        holder.getCbFavor().setChecked(map.get(position));
        setClickListener(holder.getIvShar(),position);
    }

    /**
     * 设置tag和点击监听
     * @param view
     * @param position
     */
    private void setClickListener(View view, int position) {
        view.setOnClickListener(this);
        view.setTag(position);
    }

    @Override
    public int getItemCount() {
        return lists.size();
    }

    //点击item选中CheckBox
    public void setSelectItem(CheckBox cbFavor,int position) {
        //对当前状态取反
        if (map.get(position)) {
            map.put(position, false);
        } else {
            map.put(position, true);
        }
        //this.notifyDataSetChanged();

        cbFavor.setChecked(map.get(position));
        this.notifyItemChanged(position);
    }

    /**
     * 找控件
     */
    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView ivShow;   //展示图片
        TextView tvDescribe;    //描述
        RelativeLayout rlFavor;   //收藏
        CheckBox cbFavor;       //收藏的图片
        //TextView tvFavorNum;    //收藏的数量
        ImageView ivShar;       //转发

        public MyViewHolder(View itemView) {
            super(itemView);
            ivShow = (ImageView) itemView.findViewById(R.id.iv_recycler_show);
            tvDescribe = (TextView) itemView.findViewById(R.id.tv_recycler_describe);
            //rlFavor = (RelativeLayout) itemView.findViewById(R.id.rl_recycler_favor);
            cbFavor = (CheckBox) itemView.findViewById(R.id.cb_recycler_favor);
            //tvFavorNum = (TextView) itemView.findViewById(R.id.tv_recycler_favornum);
            ivShar = (ImageView) itemView.findViewById(R.id.iv_recycler_share);
        }

        public ImageView getIvShow() {
            return ivShow;
        }

        public TextView getTvDescribe() {
            return tvDescribe;
        }

        public RelativeLayout getRlFavor() {
            return rlFavor;
        }

        public CheckBox getCbFavor() {
            return cbFavor;
        }

       /* public TextView getTvFavorNum() {
            return tvFavorNum;
        }*/

        public ImageView getIvShar() {
            return ivShar;
        }
    }
}
