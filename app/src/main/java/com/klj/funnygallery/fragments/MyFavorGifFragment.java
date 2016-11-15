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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.klj.funnygallery.DetailActivity;
import com.klj.funnygallery.MainActivity;
import com.klj.funnygallery.R;
import com.klj.funnygallery.adapter.MyListViewAdapter;
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
 * 动图页面的fragment
 */
public class MyFavorGifFragment extends Fragment {
    private ListView lvShow;    //显示数据
    private List<StoryInfo> list = new ArrayList<>();
    private String title;
    private MyListViewAdapter myListViewAdapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_gif, container, false);
        findView(view);
        return view;
    }

    /**
     * 找到控件
     * @param view
     */
    private void findView(View view) {
        lvShow= (ListView) view.findViewById(R.id.lv_gif_show);
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
        initData();
        setAdapter();
        setListener();
    }

    /**
     * 设置监听
     */
    private void setListener() {
        lvShow.setOnItemClickListener(new MyListViewItemClickListener());
    }

    /**
     * 设置适配器
     */
    private void setAdapter() {
        myListViewAdapter=new MyListViewAdapter(getActivity(),list);
        lvShow.setAdapter(myListViewAdapter);
    }


    /**
     * 更新适配器
     */
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            myListViewAdapter.notifyDataSetChanged();
        }
    };
    /**
     * 初始化数据
     */
    private void initData() {
        Map<String, String> map =new HashMap<>();
        map= Utils.getUrlMap();
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
     * 为ListView设置item点击事件
     */
    private class MyListViewItemClickListener implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Intent intent=new Intent(getActivity(), DetailActivity.class);
            intent.putExtra("storyInfos", (Serializable) list);
            intent.putExtra("position",position);
            intent.putExtra("title", title);
            startActivity(intent);
        }
    }
}
