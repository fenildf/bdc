package com.example.a00.bdcapp;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.simulation.bdc.Service.UserService;

import java.util.regex.Pattern;

/**
 * 注册页面 Activity
 * 允许用户通过电话号码进行注册，并判断用户输入
 * 用户注册成功后跳转到 登录页面
 */
public class RegisterActivity extends AppCompatActivity {

    private static final String TAG = "RegisterActivity";
    private final int REGITE_SCUESS = 1; //注册成功
    private final int REGIT_EFAIL = 0; //注册失败
    final private int PHONE_NUMBER_IS_NULL = 1; //电话号码为空
    final private int PHONE_NUMBER_IS_WORRY = 2;//电话号码格式错误
    final private int PASSWORD_NOT_SAME = 3;//两次密码输入不同
    final private int PASSWORD_IS_NULL = 4;//密码为空
    final private int VERIFYCODE_IS_NULL = 5;//验证码为空
    final private int INPUT_RIGHT = 6;//输入成功
    private EditText phoneNumberEdiText;
    private EditText verifycodeEditext;
    private Button verifycodeButton;
    private Button registerButton;
    private EditText passwordEdiText;
    private EditText checkPasswordEditText;

    private Handler handler = new Handler() {

        public void handleMessage(Message msg) {
            Log.d(TAG, "handleMessage: " + msg.what);
            switch (msg.what) {
                case REGITE_SCUESS: {
                    Toast.makeText(RegisterActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                    startActivity(intent);
                    break;
                }
                case REGIT_EFAIL: {
                    Toast.makeText(RegisterActivity.this, "注册失败", Toast.LENGTH_SHORT).show();
                }
            }
            if (msg.obj != null) {
                Toast.makeText(RegisterActivity.this, (String) msg.obj, Toast.LENGTH_SHORT).show();
            }
        }
    };
    /**
     * 注册按钮点击事件
     */
    private View.OnClickListener registerButtonListener = new View.OnClickListener() {
        @Override
        public void onClick(final View v) {
            /**
             * 获取布局中的组件中的内容
             */
            final String phoneNumber = phoneNumberEdiText.getText().toString().trim();
            final String password = passwordEdiText.getText().toString().trim();
            final String checkPassword = checkPasswordEditText.getText().toString().trim();
            final String verifycode = verifycodeEditext.getText().toString().trim();

            /**
             * 开启子线程 判读用户输入和将用户注册信息提交给服务端，并进行用户注册
             */
            new Thread(new Runnable() {
                @Override
                public void run() {
                    String responseString = responseInputResultUser(checkInput(phoneNumber, password, checkPassword, verifycode));
                    Message message = new Message();
                    if (responseString == null) {
                        UserService userService = new UserService();
                        if (userService.userRegisgte(phoneNumber, password, verifycode)) {
                            message.what = REGITE_SCUESS;
                        } else {
                            message.what = REGIT_EFAIL;
                        }
                    } else {
                        message.obj = responseString;
                    }
                    handler.sendMessage(message);
                }
            }).start();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        phoneNumberEdiText = findViewById(R.id.register_phone_number);
        passwordEdiText = findViewById(R.id.register_password);
        checkPasswordEditText = findViewById(R.id.register_check_password);
        verifycodeEditext = findViewById(R.id.register_verifycode);
        verifycodeButton = findViewById(R.id.register_forverifycode);
        registerButton = findViewById(R.id.register);

        registerButton.setOnClickListener(registerButtonListener);


    }

    /**
     * 判断用户输入
     *
     * @param phoneNumber
     * @param password
     * @param checkPassword
     * @param verifycode
     * @return
     */
    public int checkInput(String phoneNumber, String password, String checkPassword, String verifycode) {
        Pattern pattern = Pattern.compile("[0-9]*");
        if (phoneNumber.equals("") || phoneNumber == null) {
            return PHONE_NUMBER_IS_NULL;
        } else if (phoneNumber.length() != 11 || !pattern.matcher(phoneNumber).matches()) {
            return PHONE_NUMBER_IS_WORRY;
        }
        if (password == null || password.equals("")) {
            return PASSWORD_IS_NULL;
        }
        if (!password.equals(checkPassword)) {
            return PASSWORD_NOT_SAME;
        }
        if (verifycode.equals("") || verifycode == null) {
            return VERIFYCODE_IS_NULL;
        }
        return INPUT_RIGHT;
    }


    public String responseInputResultUser(int inputResponse) {
        String responseString = null;
        switch (inputResponse) {
            case PHONE_NUMBER_IS_NULL: {
                responseString = "电话号码不能为空";
                break;
            }
            case PHONE_NUMBER_IS_WORRY: {
                responseString = "电话号码格式错误";
                break;
            }
            case PASSWORD_NOT_SAME: {
                responseString = "两次输入密码不相同";
                break;
            }
            case PASSWORD_IS_NULL: {
                responseString = "密码不能为空";
                break;
            }
            case VERIFYCODE_IS_NULL: {
                responseString = "验证码不能为空";
                break;
            }
        }
        return responseString;
    }

}

