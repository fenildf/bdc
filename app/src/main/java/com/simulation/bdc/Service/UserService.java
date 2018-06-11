package com.simulation.bdc.Service;

import android.util.Log;

import com.simulation.bdc.enitity.Book;
import com.simulation.bdc.enitity.Unit;
import com.simulation.bdc.enitity.User;
import com.simulation.bdc.enitity.UserPlan;
import com.simulation.bdc.util.ConnecteURL;
import com.simulation.bdc.util.ParseJson;
import com.simulation.bdc.util.RequestURL;
import com.simulation.bdc.util.Session;

import org.json.JSONException;
import org.json.JSONObject;
import org.litepal.crud.DataSupport;

import java.io.File;
import java.util.List;

public class UserService {
    private static final String TAG = "UserService";

    private ConnecteURL urlConnect = new ConnecteURL();

    private RequestURL requestURL = new RequestURL();
    /**
     * 用户登录
     *
     * @param phoneNo
     * @param account
     * @param password
     * @return
     */
    public boolean userLogin(String phoneNo, String account, String password) {

        String url = requestURL.getUserLoginUrl(phoneNo, account, password);

        String responseData = urlConnect.connecteUrl(url);

        DataSupport.deleteAll(User.class,"phoneNo=?",phoneNo);
        DataSupport.deleteAll(UserPlan.class);

        Log.d(TAG, "userLogin: " + responseData);
        User user = ParseJson.parseUserJson(responseData);
        if (user != null) {
            user.setPassword(password);
            user.setIsLogin(User.LOGIN);
            user.save();
            return true;
        } else {
            return false;
        }
    }

    /**
     *
     * 用户注册
     *
     * @param phoneNo
     * @param password
     * @param verificationCode
     * @return
     */
    public boolean userRegisgte(String phoneNo, String password, String verificationCode) {
        String url = requestURL.getUserRegisterUrl(phoneNo, password, verificationCode);
        String responseData = urlConnect.connecteUrl(url);
        JSONObject jsonObject = null;
        try {
            if (responseData != null) {
                Log.d(TAG, "userRegisgte: " + responseData);
                jsonObject = new JSONObject(responseData);
                int result = jsonObject.getInt("result");
                return result == 1;
            }
        } catch (JSONException e) {
            Log.e(TAG, "userRegisgterTime: json数据解析错误", e);
        }
        return false;
    }

    /**
     * 修改用户数据
     *
     * @param user
     * @param file
     * @return
     */
    public boolean updateUserInfo(User user, File file) {

        return false;
    }

    /**
     * 在本地查询正在登陆的用户信息
     * @return
     */
    public User queryLoginUser(){
        List<User> users = DataSupport.where("isLogin = ?",User.LOGIN + "").find(User.class);
        if(users != null && !users.isEmpty()){
            return users.get(0);
        }else{
            return null;
        }
    }

    /**
     * 在本地查询用户计划信息
     * @param userId
     * @return
     */
    public List<UserPlan> queryUserPlanFromLocal(int userId){
        return DataSupport.where("userId=? and isDoing=?", userId+"",1+"").find(UserPlan.class);
    }

    /**
     * 在服务器上查询用户计划
     * @param userId
     * @return
     */
    public List<UserPlan> queryUserPlanFromServer(int userId){
        String url = requestURL.queryUserPlan(userId);
        String responseData = urlConnect.connecteUrl(url);
        return ParseJson.parseUserPlan(responseData);
    }

    /**
     * 修改用户记录信息
     * @param userPlan
     * @return
     */
    public boolean updateUserPlan(UserPlan userPlan){
        userPlan.save();
        String url = requestURL.updateUserPlan(userPlan);
        Log.d(TAG, "updateUserPlan: " + url);
        String responseData = urlConnect.connecteUrl(url);
        if(ParseJson.parseResult(responseData) != 0){
            return true;
        }else{
            return false;
        }
    }

    /**
     * 添加用户计划
     * @param userId
     * @param bookId
     * @param wordNumber
     * @return
     */
    public boolean addUserPlan(int userId,int bookId,int wordNumber){
        String url = requestURL.addUserPlan(userId,bookId,wordNumber);
        String responseData = urlConnect.connecteUrl(url);
        Log.d(TAG, "addUserPlan: " + responseData);
        if(ParseJson.parseResult(responseData) != 0){
            return true;
        }else{
            return false;
        }
    }

}
