package com.klj.funnygallery.utils;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.klj.funnygallery.R;
import com.klj.funnygallery.cn.sharesdk.onekeyshare.OnekeyShare;
import com.klj.funnygallery.entity.StoryInfo;
import com.lzy.okhttputils.OkHttpUtils;
import com.lzy.okhttputils.callback.StringCallback;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.tencent.qq.QQ;
import okhttp3.Call;
import okhttp3.Response;

/**
 * 常用方法和变量的工具类
 */
public class Utils {
    public static boolean isShowComments = true;      //用于标志是否显示评论
    public static String url;           //网络请求地址
    private static Map<String, String> urlMap = new HashMap<>();      //用于存储网络请求的参数

    public static Map<String, String> getUrlMap() {
        return urlMap;
    }
    public static void setUrlMap(Map<String, String> map) {
        Utils.urlMap = map;
    }

    private static Map<String,Object> loginMap=loginMap=new HashMap<String, Object>();

    public static Map<String, Object> getLoginMap() {
        return loginMap;
    }

    /**
     * 显示信息
     *
     * @param context 上下文
     * @param msg     要显示数据
     */
    public static void showMessage(Context context, String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }

    /**
     * 时间格式化
     *
     * @param timestampString
     * @return
     */
    public static String toTransferTime(String timestampString) {
        Long timestamp = Long.parseLong(timestampString) * 1000;
        String date = new java.text.SimpleDateFormat("MM/dd HH:mm").format(new java.util.Date(timestamp));
        return date;
    }

    /**
     * 第三方登陆
     */
    public static void login() {
        Platform qq = ShareSDK.getPlatform(QQ.NAME);
        //回调信息，可以在这里获取基本的授权返回的信息，但是注意如果做提示和UI操作要传到主线程handler里去执行
        qq.setPlatformActionListener(new PlatformActionListener() {
            @Override
            public void onError(Platform arg0, int arg1, Throwable arg2) {
                // TODO Auto-generated method stub
                arg2.printStackTrace();
            }

            @Override
            public void onComplete(Platform arg0, int arg1, HashMap<String, Object> arg2) {
                // TODO Auto-generated method stub
                //输出所有授权信息
                /*String userName = arg0.getDb().getUserName();
                String userId = arg0.getDb().getUserId();
                String userPhoto = arg0.getDb().getUserIcon();   //获取的是用户的头像
                String userGender = arg0.getDb().getUserGender();*/     //获取用户的性别
                loginMap=arg2;
                Log.e("---arg2----",loginMap.toString());
                Log.e("--------------", arg2.get("screen_name") + "");
            }

            @Override
            public void onCancel(Platform arg0, int arg1) {
                // TODO Auto-generated method stub

            }
        });
        //authorize与showUser单独调用一个即可
        //weibo.authorize();//单独授权,OnComplete返回的hashmap是空的
        qq.showUser(null);//授权并获取用户信息
        //移除授权
        //weibo.removeAccount(true);
    }

    /**
     * 分享
     *
     * @param context
     */
    public static void showShare(Context context) {
        ShareSDK.initSDK(context);
        OnekeyShare oks = new OnekeyShare();
        //关闭sso授权
        oks.disableSSOWhenAuthorize();
        // title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间等使用
        oks.setTitle("标题");
        // titleUrl是标题的网络链接，QQ和QQ空间等使用
        oks.setTitleUrl("http://sharesdk.cn");
        // text是分享文本，所有平台都需要这个字段
        oks.setText("我是分享文本");
        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
        //oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
        // url仅在微信（包括好友和朋友圈）中使用
        oks.setUrl("http://sharesdk.cn");
        // comment是我对这条分享的评论，仅在人人网和QQ空间使用
        oks.setComment("我是测试评论文本");
        // site是分享此内容的网站名称，仅在QQ空间使用
        oks.setSite(context.getString(R.string.app_name));
        // siteUrl是分享此内容的网站地址，仅在QQ空间使用
        oks.setSiteUrl("http://sharesdk.cn");
        // 启动分享GUI
        oks.show(context);
    }


    public static List<StoryInfo> loadDataGet(String url, Map<String, String> map) {
        final List<StoryInfo> list = new ArrayList<>();
        if (null != map) {
            OkHttpUtils.get(url)
                    .params(map)
                    .execute(new StringCallback() {
                        @Override
                        public void onSuccess(String s, Call call, Response response) {
                            try {
                                JSONObject jsonObject = new JSONObject(s);
                                if (jsonObject.optString("message").equals("success")) {
                                    list.addAll(parseJsonData(s, jsonObject));
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    });
        }
        return list;
    }

    /**
     * 解析json数据
     *
     * @param s
     * @param jsonObject
     */
    public static List<StoryInfo> parseJsonData(String s, JSONObject jsonObject) {
        List<StoryInfo> list = new ArrayList<>();
        JSONArray jsonArray = jsonObject.optJSONArray("data");
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject object = jsonArray.optJSONObject(i);
            int largeHeight = object.optInt("large_height");
            int userBury = object.optInt("user_bury");
            int userRepinTime = object.optInt("user_repin_time");
            String format = object.optString("format");
            List<String> largeUrlList = getList(object.optJSONArray("large_url_list"));
            String tagId = object.optString("tag_id");
            int middleWidth = object.optInt("middle_width");
            String tag = object.optString("tag");
            String shareUrl = object.optString("share_url");
            List<String> middleUrlList = getList(object.optJSONArray("middle_url_list"));
            int userDigg = object.optInt("user_digg");
            List<String> thumbnailUrlList = getList(object.optJSONArray("thumbnail_url_list"));
            boolean isShorten = object.optBoolean("is_shorten");
            boolean isGif = object.optBoolean("is_gif");
            String description = object.optString("description");
            String largeUrl = object.optString("large_url");
            int buryCount = object.optInt("bury_count");
            int repinCount = object.optInt("repin_count");
            int level = object.optInt("level");
            int diggCount = object.optInt("digg_count");
            int behotTime = object.optInt("behot_time");
            String middleUrl = object.optString("middle_url");
            int commentCount = object.optInt("comment_count");
            String thumbnailUrl = object.optString("thumbnail_url");
            int userRepin = object.optInt("user_repin");
            int middleHeight = object.optInt("middle_height");
            String groupId = object.optString("group_id");
            int largeWidth = object.optInt("large_width");
            StoryInfo storyInfo = new StoryInfo(largeHeight, userBury, userRepinTime, format, largeUrlList, tagId, middleWidth, tag, shareUrl,
                    middleUrlList, userDigg, thumbnailUrlList, isShorten, isGif, description, largeUrl, buryCount, repinCount, level, diggCount, behotTime, middleUrl,
                    commentCount, thumbnailUrl, userRepin, middleHeight, groupId, largeWidth);
            list.add(storyInfo);
        }
        StoryUtil.setStoryInfos(list);
        return list;
    }

    /**
     * 获取URL数组
     *
     * @param jsonArray
     * @return
     */
    private static List<String> getList(JSONArray jsonArray) {
        List<String> urlList = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            urlList.add(jsonArray.optJSONObject(i).optString("url"));
        }
        return urlList;
    }
}
