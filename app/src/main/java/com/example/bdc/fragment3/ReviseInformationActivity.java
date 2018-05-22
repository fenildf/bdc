package com.example.bdc.fragment3;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.example.a00.bdcapp.R;

import java.util.Calendar;

public class ReviseInformationActivity extends AppCompatActivity {
    private String[] sexArry = new String[]{"女孩", "男孩"};// 性别选择
    private EditText sexView;
    private EditText name;
    private TextView yearday = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_revise_information);

        name = findViewById(R.id.changename);
        sexView = findViewById(R.id.changesex);
/*        //监听对象时EditText，点击事件时，不想弹出键盘而是弹出对话框，加上下面这条语句，
            相比关掉EditText的焦点，这个方法的灵敏度不高
        sexView.setInputType(InputType.TYPE_NULL);*/

        sexView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSexChooseDialog();
            }
        });
        yearday = findViewById(R.id.yearday);
        Calendar nowdate = Calendar.getInstance();
        final int mYear = nowdate.get(Calendar.YEAR);
        final int mMonth = nowdate.get(Calendar.MONTH);
        final int mDay = nowdate.get(Calendar.DAY_OF_MONTH);
        yearday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(ReviseInformationActivity.this, onDateSetListener, mYear, mMonth, mDay).show();
            }
        });
    }

    /* 性别选择框 */
    private void showSexChooseDialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);// 自定义对话框
        builder.setSingleChoiceItems(sexArry, 0, new DialogInterface.OnClickListener() {// 2默认的选中

            @Override
            public void onClick(DialogInterface dialog, int which) {// which是被选中的位置
                // showToast(which+"");
                sexView.setText(sexArry[which]);
                dialog.dismiss();// 随便点击一个item消失对话框，不用点击确认取消
            }
        });
        builder.show();// 让弹出框显示
    }

    private DatePickerDialog.OnDateSetListener onDateSetListener = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            int mYear = year;
            int mMonth = monthOfYear;
            int mDay = dayOfMonth;
            TextView yearday =findViewById(R.id.yearday);
            String days;
            days = new StringBuffer().append(mYear).append("年").append(mMonth).append("月").append(mDay).append("日").toString();
            yearday.setText(days);
        }
    };

}

