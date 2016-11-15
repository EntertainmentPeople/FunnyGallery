package com.klj.funnygallery.fragments;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import com.klj.funnygallery.DetailActivity;
import com.klj.funnygallery.MainActivity;
import com.klj.funnygallery.R;
import com.klj.funnygallery.adapter.MyRecyclerViewAdapter;
import com.klj.funnygallery.entity.StoryInfo;
import com.klj.funnygallery.utils.ConstantUtil;
import com.klj.funnygallery.utils.PhoneUtils;
import com.klj.funnygallery.utils.Utils;
import com.lzy.okhttputils.OkHttpUtils;
import com.lzy.okhttputils.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Response;

/**
 * 爆笑页面的fragment
 */
public class HeavyFragment extends Fragment {

    private RecyclerView rvShow;       //展示
    private MyRecyclerViewAdapter myRecyclerViewAdapter;
    private List<StoryInfo> list = new ArrayList<>();
    private MyBroadcast myBroadcast;
    private String title;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_heavy, container, false);
        findView(view,savedInstanceState);
        return view;
    }

    /**
     * 找到控件
     *
     * @param view
     * @param savedInstanceState
     */
    private void findView(View view, Bundle savedInstanceState) {
        rvShow = (RecyclerView) view.findViewById(R.id.rv_heavy_show);
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
        rvShow.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        initData();
        setAdapter();
        setListener();
        registerBroadcast();
    }

    /**
     * 设置监听
     */
    private void setListener() {
        myRecyclerViewAdapter.setOnMyClickListener(new MyClickListener());
    }

    /**
     * 设置设配器
     */
    private void setAdapter() {
        myRecyclerViewAdapter = new MyRecyclerViewAdapter(getActivity(), list);
        rvShow.setAdapter(myRecyclerViewAdapter);
    }

    /**
     * 更新适配器
     */
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            myRecyclerViewAdapter.notifyDataSetChanged();
        }
    };

    /**
     * 初始化数据
     */
    private void initData() {
        Map<String, String> map =new HashMap<>();
        map=Utils.getUrlMap();
        list.clear();
        OkHttpUtils.get(Utils.url)
                .params(map)
                .params(PhoneUtils.getMap())
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        try {
                            JSONObject jsonObject = new JSONObject(s);
                            if (jsonObject.optString("message").equals("success")) {
                                list.addAll(Utils.parseJsonData(s, jsonObject));
                                handler.sendEmptyMessage(100);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }

    /**
     * 设置点击事件
     */
    private class MyClickListener implements MyRecyclerViewAdapter.MyClickCallBack {
        @Override
        public void click(View v,int position) {
            switch (v.getId()){
                case R.id.iv_recycler_show:
                    Intent intent=new Intent(getActivity(), DetailActivity.class);
                    intent.putExtra("storyInfos", (Serializable) list);
                    intent.putExtra("position",position);
                    intent.putExtra("title", title);
                    startActivity(intent);
                    break;
                case R.id.cb_recycler_favor:
                    repin(v,position);
                    break;
                case R.id.iv_recycler_share:
                    Utils.showShare(getActivity());
                    break;
            }
        }
    }

    /**
     * 收藏
     * @param v
     * @param position
     */
    private void repin(View v, final int position) {
        final String action;
        if(list.get(position).getUserRepin()==0) {
            action=ConstantUtil.REPIN;
        }else {
            action=ConstantUtil.UNREPIN;
        }
        final CheckBox cbFavor = (CheckBox) v;
        OkHttpUtils.post(ConstantUtil.URL_CHILD_ROOT_PATH + ConstantUtil.URL_ITEM_ACTION_PATH)
                    .params("action", action)
                    .params("group_id", list.get(position).getGroupId())
                    .params("tag", ConstantUtil.HEAVY)
                    .params(PhoneUtils.getMap())
                    .execute(new StringCallback() {
                        @Override
                        public void onSuccess(String s, Call call, Response response) {
                            try {
                                JSONObject jsonObject =new JSONObject(s);
                                if(jsonObject.optString("message").equals("success")) {
                                    int repinCount = jsonObject.optInt("repin_count");
                                    list.get(position).setRepinCount(repinCount);
                                    setTvNum(action,position, cbFavor);
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    });
        }

    /**
     * 设置值
     * @param action
     * @param position
     * @param cbFavor
     */
    private void setTvNum(String action, int position, CheckBox cbFavor) {
        Map<Integer,Boolean> map=myRecyclerViewAdapter.getMap();
        int repinCount=list.get(position).getRepinCount();
        int userRepin=0;
        if(action.equals(ConstantUtil.REPIN)){
            userRepin=1;
        }
        cbFavor.setText(repinCount+"");
        list.get(position).setUserRepin(userRepin);
        setSelectItem(cbFavor,position,map);
    }

    /**
     *设置收藏
     * @param cbFavor
     * @param position
     * @param map
     */
    public void setSelectItem(CheckBox cbFavor,int position,Map<Integer,Boolean> map) {
        //对当前状态取反
        if (map.get(position)) {
            map.put(position, false);
        } else {
            map.put(position, true);
        }
        cbFavor.setChecked(map.get(position));
    }

    /**
     * 注册广播
     */
    private void registerBroadcast() {
        myBroadcast = new MyBroadcast();
        IntentFilter intentFilter = new IntentFilter(); // 过滤广播
        intentFilter.addAction("com.klj.funnygallery.fragment");
        MainActivity activity= (MainActivity) getActivity();
        activity.registerReceiver(myBroadcast, intentFilter);
    }

    /**
     * 广播接收器
     */
    public class MyBroadcast extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            Bundle bundle = intent.getExtras();
            String refresh = bundle.getString("refresh");
            if(null!=refresh&&refresh.equals(ConstantUtil.HEAVY)){
                initData();
            }
            if(null!=bundle.getString("title")) {
                title = bundle.getString("title");
            }
        }
    }

}
