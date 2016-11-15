package com.klj.funnygallery;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.RadioGroup;

import com.klj.funnygallery.utils.ConstantUtil;
import com.klj.funnygallery.utils.FragmentUtil;
import com.klj.funnygallery.utils.Utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyFavorActivity extends AppCompatActivity {
    private Button btnBack;      //离线按钮
    private Button btnSync;      //刷新按钮
    private RadioGroup rgMenu;       //菜单选项组
    private FrameLayout flShow;       //用于显示Fragment的布局
    private List<Fragment> fragments = FragmentUtil.getMyFavorFragments(); //存储Fragment
    private int index;
    Map<String, String> map = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_favor);
        init();
    }

    /**
     * 初始化
     */
    private void init() {
        findView();
        initFragment();
        setListener();
    }

    /**
     * 初始化Fragment
     */
    private void initFragment() {
        initData();
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.add(R.id.fl_myfavor_show, fragments.get(0));
        ft.commit();
    }

    /**
     * 初始化URL和参数
     */
    private void initData() {
        Utils.url = ConstantUtil.URL_CHILD_ROOT_PATH + ConstantUtil.URL_REPIN_PATH;
        map.put("tag", ConstantUtil.HEAVY);
        map.put("count", 200 + "");
        map.put("item_type",1+"");
        map.put("order","asc");
        map.put("min_repin_time",0+"");
        Utils.setUrlMap(map);
    }

    /**
     * 设置监听
     */
    private void setListener() {
        rgMenu.setOnCheckedChangeListener(new MyRadioGroupChangeListener());
        btnBack.setOnClickListener(new MyOnClickListener());
        btnSync.setOnClickListener(new MyOnClickListener());
    }

    /**
     * 找到控件
     */
    private void findView() {
        btnBack = (Button) findViewById(R.id.btn_myfavor_back);
        btnSync = (Button) findViewById(R.id.btn_myfavor_sync);
        rgMenu = (RadioGroup) findViewById(R.id.rg_myfavor_menu);
        flShow = (FrameLayout) findViewById(R.id.fl_myfavor_show);
    }

    /**
     * 为RadioGroup创建一个内部类，监听RadioButton的选择
     */
    private class MyRadioGroupChangeListener implements RadioGroup.OnCheckedChangeListener {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            switch (checkedId) {
                case R.id.rb_myfavor_heavy:
                    index = 0;
                    break;
                case R.id.rb_myfavor_comic:
                    index = 1;
                    break;
                case R.id.rb_myfavor_meng:
                    index = 2;
                    break;
                case R.id.rb_myfavor_gif:
                    index = 3;
                    break;
            }
            setParams(index, 200);
            setShowFragment(index);
        }
    }

    /**
     * 设置要显示的Fragment
     */
    private void setShowFragment(int index) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        for (int i = 0; i < fragments.size(); i++) {
            if (i == index) {
                if (fragments.get(i).isAdded()) {
                    ft.show(fragments.get(i));
                } else {
                    ft.add(R.id.fl_myfavor_show, fragments.get(i));
                }
            } else {
                ft.hide(fragments.get(i));
            }
        }
        ft.commit();
    }

    /**
     * 设置网络请求的参数
     */
    private void setParams(int index, int count) {
        map.clear();
        String tag=null;
        switch (index) {
            case 0:
                tag = ConstantUtil.HEAVY;
                break;
            case 1:
                tag = ConstantUtil.COMIC;
                break;
            case 2:
                tag = ConstantUtil.MENG;
                break;
            case 3:
                tag = ConstantUtil.GIF;
                break;
        }
        map.put("item_type",1+"");
        map.put("order","asc");
        map.put("min_repin_time",0+"");
        map.put("tag", tag);
        map.put("count", count + "");
        Utils.setUrlMap(map);
    }


    /**
     * 设置点击事件
     */
    private class MyOnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btn_myfavor_back:
                    finish();
                    break;
                case R.id.btn_myfavor_sync:
                    break;
            }
        }
    }
}
