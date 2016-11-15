package com.klj.funnygallery.utils;

import com.klj.funnygallery.entity.CommentInfo;

import java.util.List;

/**
 * 评论工具类
 */
public class CommentUtil {
    private static int totalNumber;      //评论总数
    private static boolean hasMore;     //更多
    private static String commentHtml;
    private static int tagId;           //
    private static String tag;          //
    private static String message;      //
    private static String groupId;        //图片ID
    private static List<CommentInfo> commentInfo;

    public CommentUtil() {
    }

    public CommentUtil(int totalNumber, boolean hasMore, String commentHtml, int tagId, String tag, String message, String groupId, List<CommentInfo> commentInfo) {
        CommentUtil.totalNumber = totalNumber;
        CommentUtil.hasMore = hasMore;
        CommentUtil.commentHtml = commentHtml;
        CommentUtil.tagId = tagId;
        CommentUtil.tag = tag;
        CommentUtil.message = message;
        CommentUtil.groupId = groupId;
        CommentUtil.commentInfo = commentInfo;
    }

    public static int getTotalNumber() {
        return totalNumber;
    }

    public static void setTotalNumber(int totalNumber) {
        CommentUtil.totalNumber = totalNumber;
    }

    public static boolean isHas_more() {
        return hasMore;
    }

    public static void setHas_more(boolean hasMore) {
        CommentUtil.hasMore = hasMore;
    }

    public static String getCommentHtml() {
        return commentHtml;
    }

    public static void setCommentHtml(String commentHtml) {
        CommentUtil.commentHtml = commentHtml;
    }

    public static int getTagId() {
        return tagId;
    }

    public static void setTagId(int tagId) {
        CommentUtil.tagId = tagId;
    }

    public static String getTag() {
        return tag;
    }

    public static void setTag(String tag) {
        CommentUtil.tag = tag;
    }

    public static String getMessage() {
        return message;
    }

    public static void setMessage(String message) {
        CommentUtil.message = message;
    }

    public static String getGroupId() {
        return groupId;
    }

    public static void setGroupId(String groupId) {
        CommentUtil.groupId = groupId;
    }

    public static List<CommentInfo> getCommentInfo() {
        return commentInfo;
    }

    public static void setCommentInfo(List<CommentInfo> commentInfo) {
        CommentUtil.commentInfo = commentInfo;
    }

}
