package com.klj.funnygallery;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.klj.funnygallery.entity.StoryInfo;
import com.klj.funnygallery.fragments.DetailFragment;
import com.klj.funnygallery.utils.Utils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 详情页面
 */
public class DetailActivity extends AppCompatActivity {

    private Button btnBack;     //返回按钮
    private TextView tvTitle;   //标题文本
    private ViewPager vpShow;   //滑动展示
    private List<StoryInfo> storyInfos; //存储传过来的数据
    private int position;       //记录当前浏览的位置
    private List<Fragment> fragments = new ArrayList<>(); //存储Fragment
    private ImageView ivComment;  //评论
    private ImageView ivDownload;   //下载
    private CheckBox cbFavor;       //收藏
    private ImageView ivShare;      //分享
    private String title;           //标题

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        init();
    }

    /**
     * 初始化
     */
    private void init() {
        findView();
        initData();
        setAdapter();
        setListener();
    }

    /**
     * 设置监听
     */
    private void setListener() {
        btnBack.setOnClickListener(new MyOnClickListener());
        ivComment.setOnClickListener(new MyOnClickListener());
        ivDownload.setOnClickListener(new MyOnClickListener());
        cbFavor.setOnClickListener(new MyOnClickListener());
        ivShare.setOnClickListener(new MyOnClickListener());
    }

    /**
     * 设置设配器
     */
    private void setAdapter() {
        vpShow.setAdapter(new MyViewPagerAdapter(getSupportFragmentManager()));
        vpShow.setCurrentItem(position);
    }

    /**
     * 初始化数据
     */
    private void initData() {
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        storyInfos = (List<StoryInfo>) bundle.getSerializable("storyInfos");
        position = bundle.getInt("position");
        title = bundle.getString("title");
        for (int i = 0; i < storyInfos.size(); i++) {
            fragments.add(new DetailFragment(storyInfos.get(i)));
        }
        tvTitle.setText(title);
    }

    /**
     * 找到控件
     */
    private void findView() {
        btnBack = (Button) findViewById(R.id.btn_detial_back);
        tvTitle = (TextView) findViewById(R.id.tv_detial_title);
        vpShow = (ViewPager) findViewById(R.id.vp_detail_show);
        ivComment = (ImageView) findViewById(R.id.iv_detail_comment);
        ivDownload = (ImageView) findViewById(R.id.iv_detail_download);
        cbFavor = (CheckBox) findViewById(R.id.cb_detail_favor);
        ivShare = (ImageView) findViewById(R.id.iv_detail_share);
    }

    /**
     * 为ViewPager设置适配器
     */
    private class MyViewPagerAdapter extends FragmentPagerAdapter {

        public MyViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }
    }

    /**
     * 设置点击监听事件
     */
    private class MyOnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btn_detial_back:
                    finish();
                    break;
                case R.id.iv_detail_comment:
                    break;
                case R.id.iv_detail_download:
                    download();
                    break;
                case R.id.cb_detail_favor:

                    break;
                case R.id.iv_detail_share:
                    Utils.showShare(DetailActivity.this);
                    break;
            }
        }
    }

    /**
     * 下载图片
     */
    private void download() {

    }
}
