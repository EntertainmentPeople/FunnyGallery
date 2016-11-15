package com.klj.funnygallery.entity;

import java.io.Serializable;
import java.util.List;

/**
 * 搞笑囧图实体类
 */
public class StoryInfo implements Serializable{
    private int largeHeight;
    private int userBury;                      //用户踩
    private int userRepinTime;                 //用户收藏
    private String format;                   //图片格式
    private List<String> largeUrlList;
    private String tagId;                 //
    private int middleWidth;
    private String tag;                     //类型标记
    private String shareUrl;
    private List<String> middleUrlList;
    private int userDigg;                   //用户顶
    private List<String> thumbnailUrlList;
    private boolean isShorten;
    private boolean isGif;          //是否是动图
    private String description;         //描述
    private String largeUrl;                //大图URL
    private int buryCount;              //踩总数
    private int repinCount;                 //收藏总数
    private int level;                       //评论数
    private int diggCount;                   //顶总数
    private int behotTime;              //最新时间
    private String middleUrl;         //中图URL
    private int commentCount;      //评论数
    private String thumbnailUrl;        //缩略图URL
    private int userRepin;                  //用户收藏
    private int middleHeight;
    private String groupId;                     //ID
    private int largeWidth;

    public StoryInfo() {
    }

    public StoryInfo(int largeHeight, int userBury, int userRepinTime, String format, List<String> largeUrlList,
                     String tagId, int middleWidth, String tag, String shareUrl, List<String> middleUrlList, int userDigg,
                     List<String> thumbnailUrlList, boolean isShorten, boolean isGif, String description, String largeUrl,
                     int buryCount, int repinCount, int level, int diggCount, int behotTime, String middleUrl, int commentCount,
                     String thumbnailUrl, int userRepin, int middleHeight, String groupId, int largeWidth) {
        this.largeHeight = largeHeight;
        this.userBury = userBury;
        this.userRepinTime = userRepinTime;
        this.format = format;
        this.largeUrlList = largeUrlList;
        this.tagId = tagId;
        this.middleWidth = middleWidth;
        this.tag = tag;
        this.shareUrl = shareUrl;
        this.middleUrlList = middleUrlList;
        this.userDigg = userDigg;
        this.thumbnailUrlList = thumbnailUrlList;
        this.isShorten = isShorten;
        this.isGif = isGif;
        this.description = description;
        this.largeUrl = largeUrl;
        this.buryCount = buryCount;
        this.repinCount = repinCount;
        this.level = level;
        this.diggCount = diggCount;
        this.behotTime = behotTime;
        this.middleUrl = middleUrl;
        this.commentCount = commentCount;
        this.thumbnailUrl = thumbnailUrl;
        this.userRepin = userRepin;
        this.middleHeight = middleHeight;
        this.groupId = groupId;
        this.largeWidth = largeWidth;
    }

    public int getLargeHeight() {
        return largeHeight;
    }

    public void setLargeHeight(int largeHeight) {
        this.largeHeight = largeHeight;
    }

    public int getUserBury() {
        return userBury;
    }

    public void setUserBury(int userBury) {
        this.userBury = userBury;
    }

    public int getUserRepinTime() {
        return userRepinTime;
    }

    public void setUserRepinTime(int userRepinTime) {
        this.userRepinTime = userRepinTime;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public List<String> getLargeUrlList() {
        return largeUrlList;
    }

    public void setLargeUrlList(List<String> largeUrlList) {
        this.largeUrlList = largeUrlList;
    }

    public String getTagId() {
        return tagId;
    }

    public void setTagId(String tagId) {
        this.tagId = tagId;
    }

    public int getMiddleWidth() {
        return middleWidth;
    }

    public void setMiddleWidth(int middleWidth) {
        this.middleWidth = middleWidth;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getShareUrl() {
        return shareUrl;
    }

    public void setShareUrl(String shareUrl) {
        this.shareUrl = shareUrl;
    }

    public List<String> getMiddleUrlList() {
        return middleUrlList;
    }

    public void setMiddleUrlList(List<String> middleUrlList) {
        this.middleUrlList = middleUrlList;
    }

    public int getUserDigg() {
        return userDigg;
    }

    public void setUserDigg(int userDigg) {
        this.userDigg = userDigg;
    }

    public List<String> getThumbnailUrlList() {
        return thumbnailUrlList;
    }

    public void setThumbnailUrlList(List<String> thumbnailUrlList) {
        this.thumbnailUrlList = thumbnailUrlList;
    }

    public boolean isShorten() {
        return isShorten;
    }

    public void setShorten(boolean shorten) {
        isShorten = shorten;
    }

    public boolean isGif() {
        return isGif;
    }

    public void setGif(boolean gif) {
        isGif = gif;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLargeUrl() {
        return largeUrl;
    }

    public void setLargeUrl(String largeUrl) {
        this.largeUrl = largeUrl;
    }

    public int getBuryCount() {
        return buryCount;
    }

    public void setBuryCount(int buryCount) {
        this.buryCount = buryCount;
    }

    public int getRepinCount() {
        return repinCount;
    }

    public void setRepinCount(int repinCount) {
        this.repinCount = repinCount;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getDiggCount() {
        return diggCount;
    }

    public void setDiggCount(int diggCount) {
        this.diggCount = diggCount;
    }

    public int getBehotTime() {
        return behotTime;
    }

    public void setBehotTime(int behotTime) {
        this.behotTime = behotTime;
    }

    public String getMiddleUrl() {
        return middleUrl;
    }

    public void setMiddleUrl(String middleUrl) {
        this.middleUrl = middleUrl;
    }

    public int getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(int commentCount) {
        this.commentCount = commentCount;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    public int getUserRepin() {
        return userRepin;
    }

    public void setUserRepin(int userRepin) {
        this.userRepin = userRepin;
    }

    public int getMiddleHeight() {
        return middleHeight;
    }

    public void setMiddleHeight(int middleHeight) {
        this.middleHeight = middleHeight;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public int getLargeWidth() {
        return largeWidth;
    }

    public void setLargeWidth(int largeWidth) {
        this.largeWidth = largeWidth;
    }

    @Override
    public String toString() {
        return "StoryInfo{" +
                "largeHeight=" + largeHeight +
                ", userBury=" + userBury +
                ", userRepinTime=" + userRepinTime +
                ", format='" + format + '\'' +
                ", largeUrlList=" + largeUrlList +
                ", tagId='" + tagId + '\'' +
                ", middleWidth=" + middleWidth +
                ", tag='" + tag + '\'' +
                ", shareUrl='" + shareUrl + '\'' +
                ", middleUrlList=" + middleUrlList +
                ", userDigg=" + userDigg +
                ", thumbnailUrlList=" + thumbnailUrlList +
                ", isShorten=" + isShorten +
                ", isGif=" + isGif +
                ", description='" + description + '\'' +
                ", largeUrl='" + largeUrl + '\'' +
                ", buryCount=" + buryCount +
                ", repinCount=" + repinCount +
                ", level=" + level +
                ", diggCount=" + diggCount +
                ", behotTime=" + behotTime +
                ", middleUrl='" + middleUrl + '\'' +
                ", commentCount=" + commentCount +
                ", thumbnailUrl='" + thumbnailUrl + '\'' +
                ", userRepin=" + userRepin +
                ", middleHeight=" + middleHeight +
                ", groupId='" + groupId + '\'' +
                ", largeWidth=" + largeWidth +
                '}';
    }
}
