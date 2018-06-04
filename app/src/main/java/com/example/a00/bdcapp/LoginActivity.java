package com.example.a00.bdcapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.simulation.bdc.Service.UserService;
import com.simulation.bdc.util.Session;

/**
 * 登录 页面Activity 用户可以输入 账号和密码进行登录
 * 如果没有账号，可以点击注册按钮 跳转到注册页面进行注册
 * 如果忘记密码 点击 忘记密码 进入到找回密码页面
 */
public class LoginActivity extends AppCompatActivity {

    private final int LOGINFAIL = 0;    //登录失败
    private final int LOGINSUCESS = 1;  //登录成功

    private EditText userNameEditText; //账号 输入框
    private EditText passwordEditText; //密码输入框
    private Button loginButton; //登录按钮
    private Button registerButton;//注册按钮
    private TextView forgetPasswordTextView; //忘记密码
    private Handler handler = new Handler() {

        public void handleMessage(Message msg) {
            switch (msg.what) {
                case LOGINFAIL: {
                    Toast.makeText(LoginActivity.this, "电话号码或密码错误", Toast.LENGTH_SHORT).show();
                    break;
                }
                case LOGINSUCESS: {
                    Toast.makeText(LoginActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                    startActivity(intent);
                    break;
                }
            }
        }
    };
    private Runnable loginRunable = new Runnable() {
        @Override
        public void run() {
            String phoneNo = userNameEditText.getText().toString().trim();
            String password = passwordEditText.getText().toString().trim();
            UserService userService = new UserService();
            Message message = new Message();
            //登录结果
            boolean loginResult = userService.userLogin(phoneNo, null, password);
            if (loginResult) {
                message.what = LOGINSUCESS;
            } else {
                message.what = LOGINFAIL;
            }
            handler.sendMessage(message);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //找到登录注册的控件
        userNameEditText = findViewById(R.id.username);
        passwordEditText = findViewById(R.id.password);
        loginButton = findViewById(R.id.login);
        registerButton = findViewById(R.id.register);
        forgetPasswordTextView = findViewById(R.id.forgetpassword);


        //点击登录按钮 如果登录成功则跳转到首页 失败则提示“账号或者密码错误”
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Thread(loginRunable).start();
            }
        });

        //点击注册按钮跳转到注册界面
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });

        //待定
        //点击忘记密码按钮跳转到注册界面
        forgetPasswordTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });

    }

}
