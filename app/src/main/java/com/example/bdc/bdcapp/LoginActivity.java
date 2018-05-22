package com.example.bdc.bdcapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a00.bdcapp.R;
import com.simulation.lab.bdc.Service.UserService;
import com.simulation.lab.bdc.util.Session;

public class LoginActivity extends AppCompatActivity {
    private EditText userNameEditText;
    private EditText passwordEditText;
    private Button loginButton;
    private Button registerButton;
    private TextView forgetPasswordTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //找到登录注册的控件
        userNameEditText = findViewById(R.id.username);
        passwordEditText = findViewById(R.id.password);
        loginButton = findViewById(R.id.login);
        registerButton = findViewById(R.id.register);
        forgetPasswordTextView=findViewById(R.id.forgetpassword);

        //点击登录按钮跳转到首页
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Thread(loginRunable).start();
            }
        });

       //点击注册按钮跳转到注册界面，注册界面也适用于忘记密码
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });

       //点击忘记密码按钮跳转到注册界面
        forgetPasswordTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });

    }

    private Runnable loginRunable = new Runnable() {
        @Override
        public void run() {
            String phoneNo =  userNameEditText.getText().toString().trim();
            String password = passwordEditText.getText().toString().trim();
            UserService userService = new UserService();
            Message message = new Message();
            if(userService.userLogin(phoneNo,null,password)){
                message.what = LOGINSUCESS;
            }else{
                message.what = LOGINFAIL;
            }
            handler.sendMessage(message);
        }
    };

    private final int LOGINFAIL = 0;    //登录失败
    private final int LOGINSUCESS = 1;  //登录成功

    private Handler handler = new Handler(){

        public void handleMessage(Message msg){
            switch(msg.what){
                case LOGINFAIL:{
                    Toast.makeText(LoginActivity.this,"电话号码或密码错误",Toast.LENGTH_SHORT).show();
                    break;
                }
                case LOGINSUCESS:{
                    Toast.makeText(LoginActivity.this,"登录成功",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(LoginActivity.this,HomeActivity.class);
                    startActivity(intent);
                    break;
                }
            }
        }

    };
}
