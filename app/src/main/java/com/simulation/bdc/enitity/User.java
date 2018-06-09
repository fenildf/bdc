package com.simulation.bdc.enitity;

import android.util.Log;

import org.litepal.crud.DataSupport;

import java.util.Date;
import java.util.List;

public class User extends DataSupport {

    private static final String TAG = "User";

    public static final int LOGIN = 1;

    private long id;//主键

    private int userId; //用户Id

    private String account; //用户账号

    private String password; //用户密码

    private String nickname; //用户昵称

    private String pic; //用户头像地址

    private Date birthday; //用户生日

    private int age; //用户年龄

    private String gender; //用户性别

    private Date registerTime; //用户注册时间

    private String phoneNo; //用户电话号码

    private String email; //用户邮箱

    private int isLogin; //是否正在登陆

    private List<UserPlan> plans;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public static int getLOGIN() {
        return LOGIN;
    }

    public int getIsLogin() {
        return isLogin;
    }

    public void setIsLogin(int isLogin) {
        this.isLogin = isLogin;
    }

    public List<UserPlan> getPlans() {
        if(plans == null  || plans.isEmpty()){
            plans = DataSupport.where("user_id = ?" ,id + "").find(UserPlan.class);
            Log.d(TAG, "getPlans: " +  plans + "userId=?" + getUserId()+"");
        }
        return plans;
    }

    public void setPlans(List<UserPlan> plans) {
        DataSupport.deleteAll(UserPlan.class,"userId=?" , userId + "");
        this.plans = plans;
        DataSupport.saveAll(plans);
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(Date registerTime) {
        this.registerTime = registerTime;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", account='" + account + '\'' +
                ", password='" + password + '\'' +
                ", nickname='" + nickname + '\'' +
                ", pic='" + pic + '\'' +
                ", birthday=" + birthday +
                ", age=" + age +
                ", gender='" + gender + '\'' +
                ", registerTime=" + registerTime +
                ", phoneNo='" + phoneNo + '\'' +
                ", email='" + email + '\'' +
                ", plans=" + plans +
                '}';
    }
}
