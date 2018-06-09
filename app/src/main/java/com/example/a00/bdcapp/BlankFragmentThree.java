package com.example.a00.bdcapp;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.a00.fragment1.ReviewPlanActivity;
import com.example.a00.fragment3.GetPictureActivity;
import com.example.a00.fragment3.ReviewPlanSettingActivity;
import com.example.a00.fragment3.ReviseInformationActivity;
import com.example.a00.fragment3.WordlistActivity;

public class BlankFragmentThree extends Fragment {
    private TextView userNameShow;
    private ImageView userPicture;
    private Button userRevise;
    private Button setting;
    private Button logout;
    private Button wordList;
    private View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_blank_fragment_three, container, false);
        return view;

    }

}
