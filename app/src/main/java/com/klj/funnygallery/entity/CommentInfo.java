package com.klj.funnygallery.entity;

/**
 * Created by 娱乐人物 on 2016/11/9.
 */
public class CommentInfo {

    /**
     * create_time : 1478067957
     * user_name : 哥轮了布丶
     * user_digg : 0
     * platform : feifei
     * uid : 0
     * user_profile_image_url : http://p3.pstatp.com/thumb/2452/5563935234
     * user_id : 2909396348
     * bury_count : 0
     * digg_count : 22565
     * user_profile_url :
     * user_bury : 0
     * text : 这次是真正的有光它会亮，没光的时候它绝对不会亮
     * id : 52008739970
     * user_verified : false
     */
    private int uid;        //
    private String text;    //评论内容
    private int createTime; //评论时间
    private int userDigg;    //用户点赞
    private String id;         //id
    private int userBury;     //用户踩
    private String userProfileUrl;//
    private String userId;      //用户ID
    private int buryCount;      //踩总数
    private int diggCount;      //顶总数
    private boolean userVerified;//
    private String platform;    //
    private String userName;    //用户姓名
    private String userProfileImageUrl; //用户头像URL

    public CommentInfo() {
    }

    public CommentInfo(int uid, String text, int createTime, int userDigg, String id, int userBury,
                       String userProfileUrl, String userId, int buryCount, int diggCount,
                       boolean userVerified, String platform, String userName, String userProfileImageUrl) {
        this.uid = uid;
        this.text = text;
        this.createTime = createTime;
        this.userDigg = userDigg;
        this.id = id;
        this.userBury = userBury;
        this.userProfileUrl = userProfileUrl;
        this.userId = userId;
        this.buryCount = buryCount;
        this.diggCount = diggCount;
        this.userVerified = userVerified;
        this.platform = platform;
        this.userName = userName;
        this.userProfileImageUrl = userProfileImageUrl;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getCreateTime() {
        return createTime;
    }

    public void setCreateTime(int createTime) {
        this.createTime = createTime;
    }

    public int getUserDigg() {
        return userDigg;
    }

    public void setUserDigg(int userDigg) {
        this.userDigg = userDigg;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getUserBury() {
        return userBury;
    }

    public void setUserBury(int userBury) {
        this.userBury = userBury;
    }

    public String getUserProfileUrl() {
        return userProfileUrl;
    }

    public void setUserProfileUrl(String userProfileUrl) {
        this.userProfileUrl = userProfileUrl;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getBuryCount() {
        return buryCount;
    }

    public void setBuryCount(int buryCount) {
        this.buryCount = buryCount;
    }

    public int getDiggCount() {
        return diggCount;
    }

    public void setDiggCount(int diggCount) {
        this.diggCount = diggCount;
    }

    public boolean isUserVerified() {
        return userVerified;
    }

    public void setUserVerified(boolean userVerified) {
        this.userVerified = userVerified;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserProfileImageUrl() {
        return userProfileImageUrl;
    }

    public void setUserProfileImageUrl(String userProfileImageUrl) {
        this.userProfileImageUrl = userProfileImageUrl;
    }

    @Override
    public String toString() {
        return "CommentInfo{" +
                "uid=" + uid +
                ", text='" + text + '\'' +
                ", createTime=" + createTime +
                ", userDigg=" + userDigg +
                ", id='" + id + '\'' +
                ", userBury=" + userBury +
                ", userProfileUrl='" + userProfileUrl + '\'' +
                ", userId='" + userId + '\'' +
                ", buryCount=" + buryCount +
                ", diggCount=" + diggCount +
                ", userVerified=" + userVerified +
                ", platform='" + platform + '\'' +
                ", userName='" + userName + '\'' +
                ", userProfileImageUrl='" + userProfileImageUrl + '\'' +
                '}';
    }
}
