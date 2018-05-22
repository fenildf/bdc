package com.simulation.bdc.Service;

import android.util.Log;

import com.simulation.bdc.enitity.User;
import com.simulation.bdc.util.ConnecteURL;
import com.simulation.bdc.util.ParseJson;
import com.simulation.bdc.util.RequestURL;
import com.simulation.bdc.util.Session;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;

public class UserService {
    private static final String TAG = "UserService";

    private ConnecteURL urlConnect = new ConnecteURL();

    /**
     * 用户登录
     *
     * @param phoneNo
     * @param account
     * @param password
     * @return
     */
    public boolean userLogin(String phoneNo, String account, String password) {

        String url = RequestURL.getUserLoginUrl(phoneNo, account, password);

        String responseData = urlConnect.getJosnString(url);
        User user = ParseJson.parseUserJson(responseData);
        if (user != null) {
            Session.put("user", user);
            return true;
        } else {
            return false;
        }
    }

    /**
     * s
     * 用户注册
     *
     * @param phoneNo
     * @param password
     * @param verificationCode
     * @return
     */
    public boolean userRegisgte(String phoneNo, String password, String verificationCode) {
        String url = RequestURL.getUserRegisterUrl(phoneNo, password, verificationCode);
        String responseData = urlConnect.getJosnString(url);
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

}
