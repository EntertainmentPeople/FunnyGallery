package com.klj.funnygallery.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.klj.funnygallery.MyFavorActivity;
import com.klj.funnygallery.R;
import com.klj.funnygallery.utils.Utils;

/**
 * 萌图页面的fragment
 */
public class MoreFragment extends Fragment {
    private RelativeLayout rlLogin;     //登录
    private TextView tvLogin;           //显示登录后的用户名
    private RelativeLayout rlFavor;     //我的收藏
    private TextView tvFavorNum;           //显示我收藏的图片数

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_more, container, false);
        findView(view);
        return view;
    }

    /**
     * 找到控件
     *
     * @param view
     */
    private void findView(View view) {
        rlLogin = (RelativeLayout) view.findViewById(R.id.rl_more_login);
        tvLogin = (TextView) view.findViewById(R.id.tv_more_login);
        rlFavor = (RelativeLayout) view.findViewById(R.id.rl_more_myfavor);
        tvFavorNum = (TextView) view.findViewById(R.id.tv_more_myfavor);

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
        setListener();
    }

    /**
     * 设置监听
     */
    private void setListener() {
        rlLogin.setOnClickListener(new MyOnClickListener());
        rlFavor.setOnClickListener(new MyOnClickListener());
    }

    /**
     * 设置点击监听
     */
    private class MyOnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.rl_more_login:
                    Utils.login();
                    tvLogin.setText(Utils.getLoginMap().get("nickname").toString());
                    break;
                case R.id.rl_more_myfavor:
                    Intent intent=new Intent(getActivity(), MyFavorActivity.class);
                    startActivity(intent);
                    break;
            }
        }
    }
}
