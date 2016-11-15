package com.klj.funnygallery.fragments;


import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.klj.funnygallery.R;
import com.klj.funnygallery.entity.CommentInfo;
import com.klj.funnygallery.entity.StoryInfo;
import com.klj.funnygallery.utils.CommentUtil;
import com.klj.funnygallery.utils.ConstantUtil;
import com.klj.funnygallery.utils.PhoneUtils;
import com.klj.funnygallery.utils.Utils;
import com.lzy.okhttputils.OkHttpUtils;
import com.lzy.okhttputils.callback.StringCallback;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Response;

/**
 * 用于展示评论的页面
 */
public class DetailFragment extends Fragment {
    private ImageView ivShow;       //展示图片
    private TextView tvDescribe;   //展示描述
    private Button btnDigg;         //点赞
    private Button btnBury;         //踩
    private Button btnRepin;        //展示收藏人数
    private LinearLayout llComment;//评论的布局
    private TextView tvComments;    //显示评论数
    private ListView lvShow;        //显示评论
    private StoryInfo storyInfo;    //信息
    private List<CommentInfo> commentInfos = new ArrayList<>();
    MyListViewAdapter myListViewAdapter;
    public DetailFragment(StoryInfo storyInfo) {
        this.storyInfo = storyInfo;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail, container, false);
        findView(view);
        return view;
    }

    /**
     * 找到控件
     * @param view
     */
    private void findView(View view) {
        ivShow = (ImageView) view.findViewById(R.id.iv_detailfragment_show);
        tvDescribe = (TextView) view.findViewById(R.id.tv_detailfragment_describe);
        btnDigg = (Button) view.findViewById(R.id.btn_detailfragment_digg);
        btnBury = (Button) view.findViewById(R.id.btn_detailfragment_bury);
        btnRepin = (Button) view.findViewById(R.id.btn_detailfragment_repin);
        llComment = (LinearLayout) view.findViewById(R.id.ll_detailfragment_comment);
        tvComments = (TextView) view.findViewById(R.id.tv_detailfragment_commentnum);
        lvShow = (ListView) view.findViewById(R.id.lv_detailfragment_comment);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        init();
    }

    /**
     * 初始化
     */
    private void init() {

        setValue();
//        if(!Utils.isShowComments){
//            lvShow.setVisibility(View.GONE);
//        }else {
        initData();
//        }
    }

    /**
     * 设置适配器
     */
    private void setAdapter() {
        myListViewAdapter=new MyListViewAdapter(getActivity());
        lvShow.setAdapter(myListViewAdapter);
    }


    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            //myListViewAdapter.notifyDataSetChanged();
            setAdapter();
            tvComments.setText("评论(" + CommentUtil.getTotalNumber() + ")");
        }
    };
    /**
     * 初始化数据
     */
    private void initData() {
        OkHttpUtils.get(ConstantUtil.URL_CHILD_ROOT_PATH + ConstantUtil.URL_GET_COMMENTS_PATH)
                .params(PhoneUtils.getMap())
                .params("group_id", storyInfo.getGroupId())
                .params("count", 20)
                .params("offset", 0)
                .params("sort", "recent")
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        try {
                            Log.e("--group_id--",storyInfo.getGroupId());
                            JSONObject jsonObject = new JSONObject(s);
                            Log.e("--s-",s);
                            if (jsonObject.optString("message").equals("success") && jsonObject.optString("group_id").equals(storyInfo.getGroupId())) {
                                Log.e("--s-",s);
                                parseJsonData(jsonObject, s);
                                handler.sendEmptyMessage(100);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });

    }

    /**
     * 解析数据
     * @param jsonObject
     * @param s
     */
    private void parseJsonData(JSONObject jsonObject, String s) {
        CommentUtil.setGroupId(storyInfo.getGroupId());
        CommentUtil.setTotalNumber(jsonObject.optInt("total_number"));
        CommentUtil.setHas_more(jsonObject.optBoolean("has_more"));
        CommentUtil.setCommentHtml(  jsonObject.optString("comment_html"));
        CommentUtil.setTagId( jsonObject.optInt("tag_id"));
        CommentUtil.setTag(jsonObject.optString("tag"));
        JSONArray jsonArray = jsonObject.optJSONArray("data");
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject object = jsonArray.optJSONObject(i);
            int uid = object.optInt("uid");
            String text = object.optString("text");
            int createTime = object.optInt("create_time");
            int userDigg = object.optInt("user_digg");
            String id = object.optString("id");
            int userBury = object.optInt("user_bury");
            String userProfileUrl = object.optString("user_profile_url");
            String userId = object.optString("user_id");
            int buryCount = object.optInt("bury_count");
            int diggCount = object.optInt("digg_count");
            boolean userVerified = object.optBoolean("user_verified");
            String platform = object.optString("platform");
            String userName = object.optString("user_name");
            String userProfileImageUrl = object.optString("user_profile_image_url");
            CommentInfo commentInfo = new CommentInfo(uid, text, createTime, userDigg, id, userBury,
                    userProfileUrl, userId, buryCount, diggCount,
                    userVerified, platform, userName, userProfileImageUrl);
            commentInfos.add(commentInfo);
        }
        CommentUtil.setCommentInfo(commentInfos);
    }

    /**
     * 设置值
     */
    private void setValue() {
        if (storyInfo.isGif()){
            Glide.with(this).load(storyInfo.getLargeUrl()).asGif().diskCacheStrategy(DiskCacheStrategy.SOURCE).into(ivShow);
        }else {
            Glide.with(this).load(storyInfo.getLargeUrl()).into(ivShow);
        }
        tvDescribe.setText(storyInfo.getDescription());
        btnDigg.setText("顶 " + storyInfo.getDiggCount());
        btnBury.setText("踩 " + storyInfo.getBuryCount());
        btnRepin.setText(storyInfo.getRepinCount() + "人收藏");
    }

    /**
     * 为ListView设置适配器
     */
    private class MyListViewAdapter extends BaseAdapter {
        Context context;
        ViewHolder vh;
        MyListViewAdapter(Context context){
            this.context=context;
        }

        @Override
        public int getCount() {
            return commentInfos.size();
        }

        @Override
        public Object getItem(int position) {
            return commentInfos.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if(convertView==null){
                vh=new ViewHolder();
                convertView=LayoutInflater.from(context).inflate(R.layout.layout_detail_list_item,parent,false);
                vh.ivPortrait = (ImageView) convertView.findViewById(R.id.iv_detailfragment_list_portrait);
                vh.tvName= (TextView) convertView.findViewById(R.id.tv_detailfragment_list_nickname);
                vh.tvTime= (TextView) convertView.findViewById(R.id.tv_detailfragment_list_time);
                vh.tvComment= (TextView) convertView.findViewById(R.id.tv_detailfragment_list_comment);
                vh.rbDigg= (RadioButton) convertView.findViewById(R.id.rb_detailfragment_list_digg);
                convertView.setTag(vh);
            }else {
                vh= (ViewHolder) convertView.getTag();
            }
            setListItem(context,vh,position);
            return convertView;
        }
    }

    /**
     * 为ListView中的item设置值
     * @param context
     * @param vh
     * @param position
     */
    private void setListItem(Context context, ViewHolder vh, int position) {
        CommentInfo commentInfo = commentInfos.get(position);
        Glide.with(this).load(commentInfo.getUserProfileImageUrl()).into(vh.ivPortrait);
        vh.tvName.setText(commentInfo.getUserName());
        vh.tvTime.setText(Utils.toTransferTime(commentInfo.getCreateTime()+""));
        vh.tvComment.setText(commentInfo.getText()+"");
        vh.rbDigg.setText(commentInfo.getDiggCount()+"");
    }

    /**
     * 模板
     */
    private class ViewHolder{
        public ImageView ivPortrait;
        public TextView tvName,tvTime,tvComment;
        public RadioButton rbDigg;
    }
}
