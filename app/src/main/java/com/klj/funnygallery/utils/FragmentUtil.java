package com.klj.funnygallery.utils;

import android.support.v4.app.Fragment;

import com.klj.funnygallery.fragments.ComicFragment;
import com.klj.funnygallery.fragments.GifFragment;
import com.klj.funnygallery.fragments.HeavyFragment;
import com.klj.funnygallery.fragments.MengFragment;
import com.klj.funnygallery.fragments.MoreFragment;
import com.klj.funnygallery.fragments.MyFavorComicFragment;
import com.klj.funnygallery.fragments.MyFavorGifFragment;
import com.klj.funnygallery.fragments.MyFavorHeavyFragment;
import com.klj.funnygallery.fragments.MyFavorMengFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Fragment工具类
 */
public class FragmentUtil {
    private static List<Fragment> mainFragments =new ArrayList<>();     //用于存储Fragment的List集合
    private static List<Fragment> myFavorFragments =new ArrayList<>();     //用于存储我的收藏的Fragment的List集合
    /**
     * 初始化数据
     */
    static {
        mainFragments.add(new HeavyFragment());     //添加爆笑页面
        mainFragments.add(new ComicFragment());     //添加漫画页面
        mainFragments.add(new MengFragment());      //添加萌图页面
        mainFragments.add(new GifFragment());       //添加动图页面
        mainFragments.add(new MoreFragment());
        myFavorFragments.add(new MyFavorHeavyFragment());   //添加爆笑页面
        myFavorFragments.add(new MyFavorComicFragment());//添加漫画页面
        myFavorFragments.add(new MyFavorMengFragment());//添加萌图页面
        myFavorFragments.add(new MyFavorGifFragment());//添加动图页面

    }

    /**
     * 获取所有的Fragment
     * @return fragments
     */
    public static List<Fragment> getMainFragments(){
        return mainFragments;
    }

    /**
     * 获取fragment的容量
     * @return fragments.size()
     */
    public static int getFragmentSize(){
        return mainFragments.size();
    }

    /**
     * 获取所有的Fragment
     * @return fragments
     */
    public static List<Fragment> getMyFavorFragments(){
        return myFavorFragments;
    }

    /**
     * 获取fragment的容量
     * @return fragments.size()
     */
    public static int getMyFavorFragmentsSize(){
        return myFavorFragments.size();
    }


}
