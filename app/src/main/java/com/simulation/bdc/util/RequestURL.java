package com.simulation.bdc.util;

/**
 * 访问的网址
 */
public class RequestURL {

    public static String URL = "http://39.105.87.113:8080/lab.bdc/";

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
     * 返回通过 单词Id或者单词拼写查找单词的网址
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

}
