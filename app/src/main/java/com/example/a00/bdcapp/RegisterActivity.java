package com.example.a00.bdcapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class RegisterActivity extends AppCompatActivity {
    private EditText phoneNumber;
    private EditText verifycode;
    private Button forverifycode;
    private Button relogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        phoneNumber = findViewById(R.id.phonenumber);
        verifycode = findViewById(R.id.verifycode);
        forverifycode = findViewById(R.id.forverifycode);
        relogin = findViewById(R.id.reg_or_login);

    }
}
