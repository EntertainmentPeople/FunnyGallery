package com.klj.funnygallery.utils;

import com.klj.funnygallery.entity.StoryInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * 故事工具类
 */
public class StoryUtil {
    private static StoryInfo storyInfo;
    private static List<StoryInfo> storyInfos=new ArrayList<>();

    public static List<StoryInfo> getStoryInfos() {
        return storyInfos;
    }

    public static void setStoryInfos(List<StoryInfo> storyInfos) {
        StoryUtil.storyInfos = storyInfos;
    }

    /**
     * 收藏后修改用户的收藏状态
     * @param index
     * @param repin
     */
    public static void changeUserRepin(int index,int repin){
        storyInfos.get(index).setUserRepin(repin);
    }

    /**
     * 收藏后修改总的收藏数
     * @param index
     * @param repinCount
     */
    public static void changeRepinCount(int index,int repinCount){
        storyInfos.get(index).setUserRepin(repinCount);
    }
}
