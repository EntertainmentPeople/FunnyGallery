package com.klj.funnygallery;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.klj.funnygallery.adapter.MySpinnerAdapter;
import com.klj.funnygallery.utils.ConstantUtil;
import com.klj.funnygallery.utils.FragmentUtil;
import com.klj.funnygallery.utils.Utils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private long exitTime = 0;      //退出时间
    private Button btnOffline;      //离线按钮
    private Button btnRefresh;      //刷新按钮
    private Spinner spinnerMenu;    //下拉菜单
    private ImageView ivDown;        //底线
    private ImageView ivMenu;        //白线
    //    private TextView tvRefreshNum;  //新数据
    private RadioGroup rgMenu;       //菜单选项组
    private FrameLayout flShow;       //用于显示Fragment的布局
    private List<Fragment> fragments = FragmentUtil.getMainFragments(); //存储Fragment
    private TextView tvMore;
    private RelativeLayout rlTitle;
    private MySpinnerAdapter mySpinnerAdapter;
    List<String> spinnerList = new ArrayList<>();
    private int index;
    Map<String, String> map = new HashMap<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    /**
     * 初始化
     */
    private void init() {
        findView();
        initFragment();
        setAdapter();
        setListener();
    }

    /**
     * 初始化Fragment
     */
    private void initFragment() {
        initData();
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.add(R.id.fl_show, fragments.get(0));
        ft.commit();
    }

    /**
     * 初始化URL和参数
     */
    private void initData() {
        Utils.url = ConstantUtil.URL_MAIN_ROOT_PATH + ConstantUtil.URL_NEW_TODAY_PATH;
        map.put("tag", ConstantUtil.HEAVY);
        map.put("count", 20 + "");
        Utils.setUrlMap(map);
    }

    /**
     * 设置监听
     */
    private void setListener() {
        rgMenu.setOnCheckedChangeListener(new MyRadioGroupChangeListener());
        spinnerMenu.setOnItemSelectedListener(new MySpinnerItemSelectedListener());
        btnOffline.setOnClickListener(new MyOnClickListener());
        btnRefresh.setOnClickListener(new MyOnClickListener());
    }

    /**
     * 设置适配器
     */
    private void setAdapter() {
        initSpinner();
        mySpinnerAdapter = new MySpinnerAdapter(MainActivity.this, spinnerList);
        spinnerMenu.setAdapter(mySpinnerAdapter);
    }

    /**
     * 初始化Spinner
     */
    private void initSpinner() {
        spinnerList.add("最新爆笑");
        spinnerList.add("今日热门");
        spinnerList.add("本周热门");
    }

    /**
     * 找到控件
     */
    private void findView() {
        rlTitle = (RelativeLayout) findViewById(R.id.rl_spinner_title);
        tvMore = (TextView) findViewById(R.id.tv_more);
        btnOffline = (Button) findViewById(R.id.btn_offline);
        btnRefresh = (Button) findViewById(R.id.btn_refresh);
        spinnerMenu = (Spinner) findViewById(R.id.spinner_menu);
        spinnerMenu.setDropDownVerticalOffset(75);
        ivDown = (ImageView) findViewById(R.id.iv_down);
        ivMenu = (ImageView) findViewById(R.id.iv_menu);
//        tvRefreshNum = (TextView) findViewById(R.id.tv_refresh_num);
        rgMenu = (RadioGroup) findViewById(R.id.rg_menu);
        flShow = (FrameLayout) findViewById(R.id.fl_show);
        tvMore.setVisibility(View.GONE);
    }

    /**
     * 为RadioGroup创建一个内部类，监听RadioButton的选择
     */
    private class MyRadioGroupChangeListener implements RadioGroup.OnCheckedChangeListener {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            switch (checkedId) {
                case R.id.rb_heavy:
                    index = 0;
                    break;
                case R.id.rb_comic:
                    index = 1;
                    break;
                case R.id.rb_meng:
                    index = 2;
                    break;
                case R.id.rb_gif:
                    index = 3;
                    break;
                case R.id.rb_more:
                    index = 4;
                    break;
            }
            setParams(null, 20);
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
                    ft.add(R.id.fl_show, fragments.get(i));
                }
            } else {
                ft.hide(fragments.get(i));
            }
        }
        ft.commit();
        setTitle(index);
    }

    /**
     * 设置标题
     */
    public void setTitle(int index) {
        boolean tag = true;
        spinnerList.clear();
        switch (index) {
            case 0:
                tag = true;
                spinnerList.add("最新爆笑");
                break;
            case 1:
                tag = true;
                spinnerList.add("最新漫画");
                break;
            case 2:
                tag = true;
                spinnerList.add("最新萌图");
                break;
            case 3:
                tag = true;
                spinnerList.add("最新动图");
                break;
            case 4:
                tag = false;
                break;
        }
        if (tag) {
            spinnerList.add("今日热门");
            spinnerList.add("本周热门");
        }

        setVisibility(tag, spinnerList);
    }

    /**
     * 设置控件是否可见
     */
    private void setVisibility(boolean tag, List<String> list) {
        int visibility;
        if (tag) {
            visibility = View.VISIBLE;
            tvMore.setVisibility(View.GONE);
        } else {
            visibility = View.GONE;
            tvMore.setVisibility(View.VISIBLE);
        }
        btnOffline.setVisibility(visibility);
        btnRefresh.setVisibility(visibility);
        rlTitle.setVisibility(visibility);
//        ivDown.setVisibility(visibility);
////        tvRefreshNum.setVisibility(visibility);
//        spinnerMenu.setVisibility(visibility);
        spinnerMenu.setAdapter(new MySpinnerAdapter(MainActivity.this, list));
        spinnerMenu.setEnabled(tag);
//        ivMenu.setVisibility(visibility);
    }

    /**
     * 为Spinner设置item选择监听
     */
    private class MySpinnerItemSelectedListener implements android.widget.AdapterView.OnItemSelectedListener {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            setUrlPath(parent.getItemAtPosition(position).toString());
            sendBroadcast("title", parent.getItemAtPosition(position).toString(), "com.klj.funnygallery.fragment");
            refresh();
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    }

    /**
     * 获取网络请求的URL
     *
     * @param s
     */
    private void setUrlPath(String s) {
        String url = new String();
        url += ConstantUtil.URL_MAIN_ROOT_PATH;
        switch (s) {
            case "最新爆笑":
                url += ConstantUtil.URL_NEW_TODAY_PATH;
                setParams(ConstantUtil.HEAVY, 20);
                break;
            case "最新漫画":
                url += ConstantUtil.URL_NEW_TODAY_PATH;
                setParams(ConstantUtil.COMIC, 20);
                break;
            case "最新萌图":
                url += ConstantUtil.URL_NEW_TODAY_PATH;
                setParams(ConstantUtil.MENG, 20);
                break;
            case "最新动图":
                url += ConstantUtil.URL_NEW_TODAY_PATH;
                setParams(ConstantUtil.GIF, 20);
                break;
            case "今日热门":
                url += ConstantUtil.URL_HOT_TODAY_PATH;
                setParams(null, 300, 1 + "");
                break;
            case "本周热门":
                url += ConstantUtil.URL_HOT_TODAY_PATH;
                setParams(null, 300, 7 + "");
                break;
        }
        Utils.url = String.valueOf(url);
    }

    /**
     * 设置网络请求的参数
     */
    private void setParams(String tag, int count, String... days) {
        map.clear();
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
            default:
                return;
        }
        if (days.length > 0) {
            map.put("days", days[0]);
        }
        map.put("tag", tag);
        map.put("count", count + "");
        Utils.setUrlMap(map);
    }

    /**
     * 重写onBackPressed，实现在里两秒时间内双击退出程序
     */
    @Override
    public void onBackPressed() {
        long mNowTime = System.currentTimeMillis();//获取第一次按键时间
        if ((mNowTime - exitTime) > 2000) {     //比较两次按键时间差
            Utils.showMessage(MainActivity.this, "再点一次退出！");
            exitTime = mNowTime;
        } else {//退出程序
            this.finish();
        }
    }

    /**
     * 重写onDestroy方法，实现在MainActivity销毁时退出程序
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.exit(0);     //退出程序
    }

    /**
     * 设置点击事件
     */
    private class MyOnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btn_offline:
                    offline();
                    break;
                case R.id.btn_refresh:
                    refresh();
                    break;
            }
        }
    }

    /**
     * 刷新
     */
    private void refresh() {
        setSendBroadcast("refresh");
    }

    /**
     * 离线
     */
    private void offline() {
        setSendBroadcast("offline");
    }

    /**
     * 设置要发送的广播信息
     *
     * @param key
     */
    private void setSendBroadcast(String key) {
        String tag = null;
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
        sendBroadcast(key, tag, "com.klj.funnygallery.fragment");
    }

    /**
     * 发送广播的方法
     */
    private void sendBroadcast(String action, Map<String, Object> map) {
        Intent intent = new Intent();
        for (String key : map.keySet()) {
            intent.putExtra(key, (Serializable) map.get(key));
        }
        intent.setAction(action);
        intent.setPackage("com.klj.funnygallery");
        sendBroadcast(intent);
    }

    /**
     * 发送广播的方法
     */
    private void sendBroadcast(String key, String msg, String action) {
        Intent intent = new Intent();
        intent.putExtra(key, msg);
        intent.setAction(action);
        intent.setPackage("com.klj.funnygallery");
        sendBroadcast(intent);
    }


}
