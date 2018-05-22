package com.simulation.bdc.enitity;

import java.util.Date;

public class User {

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
                '}';
    }
}
