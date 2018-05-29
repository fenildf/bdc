//package com.example.a00.fragment2;
//
//import android.support.v7.app.AppCompatActivity;
//import android.os.Bundle;
//import android.widget.ListView;
//import android.widget.SimpleAdapter;
//
//import com.example.a00.bdcapp.R;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//
//public class CYLEActivity extends AppCompatActivity {
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_cyle);
//        ListView list = findViewById(R.id.lv1);
//        ArrayList<HashMap<String,String>> mylist = new ArrayList<HashMap<String, String>>();
//        for (int i=0;i<12;i++){
//            HashMap<String,String> map = new HashMap<String,String>();
//            map.put("item","单元");
//            mylist.add(map);
//        }
//        SimpleAdapter mSchedule = new SimpleAdapter(this,mylist,R.layout.itemcourse,new String[]{"item"},new int[]{R.id.item});
//        list.setAdapter(mSchedule);
//    }
//}
