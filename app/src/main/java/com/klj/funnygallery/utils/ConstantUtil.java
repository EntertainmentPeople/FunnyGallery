package com.klj.funnygallery.utils;

/**
 * 常量工具类
 * 爆笑：今日最新：GET: ic.snssdk.com/2/image/recent/?tag=heavy&count=20&min_behot_time=1478072942
 * <p>
 * 今日热门：GET: ic.snssdk.com/2/image/top/?tag=heavy&days=1&count=300
 * <p>
 * 本周热门：GET：ic.snssdk.com/2/image/top/?tag=heavy&days=7&count=300
 * <p>
 * 漫画：GET：ic.snssdk.com/2/image/recent/?tag=comic&count=20
 * <p>
 * 萌图：GET：ic.snssdk.com/2/image/recent/?tag=meng&count=20
 * <p>
 * 动图：GET: ic.snssdk.com/2/image/recent/?tag=gif&count=20&min_behot_time=1478069377
 * <p>
 * <p>
 * <p>
 * 获取的评论：GET：isub.snssdk.com/2/data/v4/get_comments/?group_id=51987667030&count=20&offset=0&sort=recent
 * <p>
 * 顶：POST：isub.snssdk.com/2/data/item_action/  参数：action  digg, group_id 51987770097, tag heavy
 * <p>
 * 踩：POST：isub.snssdk.com/2/data/item_action/  参数：action  bury, group_id 51973977526, tag heavy
 * <p>
 * 评论点赞：POST：isub.snssdk.com/2/data/comment_action/  参数：action digg, comment_id 5200090066921
 * <p>
 * 评论踩:POST:isub.snssdk.com/2/data/comment_action/  参数：action bury, comment_id 52004291745
 * <p>
 * 取消收藏：POST：isub.snssdk.com/2/data/item_action/  参数：action unrepin, group_id 51987770097,tag heavy
 * <p>
 * 收藏：POST：isub.snssdk.com/2/data/item_action/   参数：action=repin，group_id=51973977526，tag=heavy
 * <p>
 * 我的收藏：GET：isub.snssdk.com/2/data/get_favorites/?item_type=1&tag=heavy&count=200&order=asc&min_repin_time=0
 * /2/data/get_favorites/?item_type=1&tag=comic&count=200&order=asc&min_repin_time=0
 * <p>
 * 提出意见前：GET：ichannel.snssdk.com/feedback/2/list/?appkey=gallery-funny-android&count=50&app_name=funny_gallery&version_code=241&device_platform=android
 * 提出意见后：GET：ichannel.snssdk.com/feedback/2/list/?appkey=gallery-funny-android&count=50&app_name=funny_gallery&version_code=241&device_platform=android&channel=goapk&device_id=33893122889
 * <p>
 * 常见问题：Host: ib.snssdk.com/faq/?night_mode=0&app_name=funny_gallery&version_code=241&device_platform=android
 * <p>
 * <p>
 * 夜间模式：GET：ib.snssdk.com/faq/?night_mode=0
 * <p>
 * 发送意见：POST：ichannel.snssdk.com/feedback/1/post_message/  参数：content ，appkey gallery-funny-android，app_version  2.4.1
 * <p>
 * 检查新版本：GET：ichannel.snssdk.com/service/2/check_version/?&app_name=funny_gallery&version_code=241&device_platform=android
 * <p>
 * <p>
 * 授权登录：GET：isub.snssdk.com/2/auth/login/v2/?platform=qq_weibo
 * <p>
 * <p>
 * 转发到QQ空间：POST:isub.snssdk.com/2/data/post_message/   参数：action  comment, platform  qzone_sns,  tag  heavy ,group_id 51980715507
 * <p>
 * 发送评论：POST：isub.snssdk.com/2/data/post_message/   参数：action  comment, text ， tag  heavy ,group_id 51980715507，is_comment 1
 */

public class ConstantUtil {
    public static final String URL_MAIN_ROOT_PATH = "http://ic.snssdk.com/2/image";     //主根地址
    public static final String URL_CHILD_ROOT_PATH = "http://isub.snssdk.com/2/data";  //子地址
    public static final String URL_NEW_TODAY_PATH = "/recent/";  //最新   tag:heavy,comic,meng,gif    min_behot_time:时间
    public static final String URL_HOT_TODAY_PATH = "/top/";  //最热  tag:heavy,comic,meng,gif   days:1,7
    public static final String URL_GET_COMMENTS_PATH = "/v4/get_comments/";  //获取评论   group_id：故事ID
    public static final String URL_SEND_COMMENTS_PATH="/post_message/";        //发送评论
    public static final String URL_ITEM_ACTION_PATH = "/item_action/";  //故事顶，踩，收藏 参数：action  digg, group_id 51987770097, tag heavy
    public static final String URL_COMMENT_ACTION_PATH = "/comment_action/";  //评论顶，踩 参数：action  digg, group_id 51987770097, tag heavy
    public static final String URL_REPIN_PATH = "/get_favorites/";    //收藏
    public static final String URL_OPINION_PATH = "ichannel.snssdk.com";     //意见
    public static final String URL_BEFORE_OPINION_PATH = "/feedback/2/list/?appkey=gallery-funny-android&count=50&" +
            "app_name=funny_gallery&version_code=241&device_platform=android";          //提出意见之前
    public static final String URL_AFTER_OPINION_PATH = "&channel=goapk&device_id=33893122889";     //提出意见之后
    public static final String URL_CHECKED_UPDATE_PATH = "/service/2/check_version/?&app_name=funny_gallery&version_code=241&device_platform=android";//检车更新

    public static final String HEAVY="heavy";      //爆笑
    public static final String COMIC="comic";      //漫画
    public static final String MENG="meng";        //萌图
    public static final String GIF="gif";          //动图

    public static final String REPIN="repin";      //收藏
    public static final String UNREPIN="unrepin";  //取消收藏

    public static final String DIGG="digg";        //顶
    public static final String BURF="bury";        //踩

}
