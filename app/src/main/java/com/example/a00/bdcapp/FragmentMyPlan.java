package com.example.a00.bdcapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.simulation.bdc.Service.BookService;
import com.simulation.bdc.Service.UserService;
import com.simulation.bdc.adapter.PlanAdapter;
import com.simulation.bdc.enitity.Book;
import com.simulation.bdc.enitity.User;
import com.simulation.bdc.enitity.UserPlan;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class FragmentMyPlan extends Fragment {
    private View view;
    private ListView planList;
    private static final String TAG = "FragmentMyPlan";
    private UserService userService = new UserService();
    private BookService bookService = new BookService();
    private List<UserPlan> userPlans; //用户计划列表
    private UserPlan isDoingPlan; //当前正在进行的计划
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_my_plan,container, false);
        planList = view.findViewById(R.id.plan_list);
        initDataList();//初始化数据
//        Log.d(TAG, "onCreateView: " + userPlans);
        new Thread(new Runnable() {
            @Override
            public void run() {
                for(UserPlan userPlan : userPlans){
                    Book book = userPlan.getBook();
                    bookService.getBookCoverPicture(book);
                }
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        planList.setAdapter(new PlanAdapter(getActivity(),userPlans));
                    }
                });
            }
        }).start();
        return view;
    }
    /**
     * 初始化适配器需要的数据格式
     */
    private void initDataList() {
        //图片资源
        User user = userService.queryLoginUser();
        userPlans = user.getPlans();
    }

    /**
     * 更新计划列表
     */
    public void updateUIPlanList(){
        for(UserPlan userPlan : userPlans) {
            if (userPlan.getIsDoing() == 1) {
                isDoingPlan = userPlan;
            }
        }
        planList.setAdapter(new PlanAdapter(getActivity(),userPlans));
    }

    @Override
    public void onResume() {
        super.onResume();
        updateUIPlanList();
    }
}
