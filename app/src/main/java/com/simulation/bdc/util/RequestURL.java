package com.simulation.bdc.util;

import android.util.Log;

import com.simulation.bdc.enitity.User;
import com.simulation.bdc.enitity.UserPlan;

/**
 * 访问的网址
 */
public class RequestURL {

    public static String URL = "http://123.206.29.55:8080/lab.bdc/";

    public static String USER_LOGIN_URL;

    private static String CHANGE_USER_INFO_URL;

    /**
     * 获取用户登录地址
     *
     * @param phoneNo
     * @param account
     * @param password
     * @return
     */
    public String getUserLoginUrl(String phoneNo, String account, String password) {
        String url = URL + "user/login";
        if (password == null) {
            return null;
        } else {
            url += "?password=" + password;
        }
        if (phoneNo != null) {
            url += "&phoneNo=" + phoneNo;
        } else if (account != null) {
            url += "&account=" + account;
        } else {
            url = null;
        }
        return url;
    }

    /**
     * 获取用户注册地址
     *
     * @param phoneNo
     * @param password
     * @param verificationCode
     * @return
     */
    public String getUserRegisterUrl(String phoneNo, String password, String verificationCode) {
        String url = URL + "user/regist";
        if (phoneNo == null || password == null || verificationCode == null) {
            url = null;
        } else {
            url += "?phoneNo=" + phoneNo + "&password=" + password + "&verificationCode=" + verificationCode;
        }
        return url;
    }

    /**
     * 获取验证码
     *
     * @param phoneNo
     * @return
     */
    public boolean getVerificationCode(String phoneNo) {
        return false;
    }

    /**
     * 返回通过 单词Id或者单词拼写模糊查找单词的网址
     *
     * @param wordId
     * @param wordName
     * @return
     */
    public String queryWordByWordName(Integer wordId, String wordName) {
        String url = URL + "word/query_by_word_info";
        if (wordId != null) {
            url += "?wordId=" + wordId;
        } else if (wordName != null && !wordName.equals("")) {
            url += "?wordName=" + wordName;
        } else {
            url = null;
        }
        return url;
    }

    /**
     * 返回通过单词拼写精确查找单词的网址
     * @param wordName
     * @return
     */
    public String queryWordByWordName(String wordName){
        String url = URL + "word/query_by_word_info";
        if(url != null){
            url += "?wordName=" + wordName;
        }else{
            url = null;
        }
        return url;
    }

    /**
     * 返回通过单词释义查找单词的网址
     *
     * @param mean
     * @return
     */
    public String queryWordByMean(String mean) {
        String url = URL + "word/query_by_mean";
        if (mean != null && !mean.equals("")) {
            url += "?mean=" + mean;
        } else {
            url = null;
        }
        return url;
    }

    /**
     * 返回 通过单元Id查找单词的网址
     * @param unitId
     * @return
     */
    public String queryWordByUnitId(int unitId,int userId){
        String url = URL + "word/query_by_unitId?unitId=" + unitId + "&userId=" + userId;
        return url;
    }

    /**
     * 获取修改用户计划信息的网址
     * @param userPlan
     * @return
     */
    public String updateUserPlan(UserPlan userPlan){
        StringBuffer url = new StringBuffer(URL + "/user/plan/update?");
        url.append("planId=" + userPlan.getPlanId());
        url.append("&unitId=" + userPlan.getUnitId());
        url.append("&wordId=" + userPlan.getWordId());
        url.append("&hasDone=" + userPlan.getHasDone());
        url.append("&isDoing=" + userPlan.getIsDoing());
        return url.toString();
    }

    /**
     * 获取用户添加完成单词表的url
     * @param userId
     * @param wordId
     * @return
     */
    public  String  addUserCompleteWord(int userId, int wordId){
        String url = URL + "word/add_word_complete";
        url += "?userId=" + userId + "&wordId=" + wordId;
        return url;
    }

    /**
     * 获取添加用户生词表 的url
     * @param userId
     * @param wordId
     * @return
     */
    public String addNewWord(int userId, int wordId){
        String url = URL + "user/newWord/add";
        url += "?userId=" + userId + "&wordId=" + wordId;
        return url;
    }

    /**
     * 获取更新 用户复习表的url
     * @param reviewId
     * @param reviewTimes
     * @return
     */
    public String  updateReview(int reviewId, int reviewTimes){
        return URL + "user/review/update?reviewId=" + reviewId + "&reviewTimes=" + reviewTimes;
    }

    /**
     * 获取添加用户复习表的url
     * @param userId
     * @param wordId
     * @return
     */
    public String addReview(int userId,int wordId){
        return URL + "user/review/add?userId=" + userId +"&wordId=" + wordId;
    }

    /**
     * 查找用户复习表信息
     * @param userId
     * @return
     */
    public String queryReview(int userId){
        return URL + "user/review/query?userId=" + userId;
    }

    /**
     * 获取添加用户计划的网址
     * @param userId
     * @param bookId
     * @param wordNumber
     * @return
     */
    public String addUserPlan(int userId,int bookId,int wordNumber){
        return URL + "user/plan/add?userId=" + userId + "&bookId=" + bookId + "&wordNumber=" + wordNumber;
    }

    /**
     * 获取查询用户计划的网址
     * @param userId
     * @return
     */
    public String queryUserPlan(int userId){
        return URL + "user/plan/query?userId=" + userId;
    }
}
