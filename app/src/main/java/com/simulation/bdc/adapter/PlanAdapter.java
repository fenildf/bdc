package com.simulation.bdc.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.a00.bdcapp.R;
import com.simulation.bdc.Service.UserService;
import com.simulation.bdc.enitity.Book;
import com.simulation.bdc.enitity.UserPlan;

import java.util.List;

public class PlanAdapter extends BaseAdapter{

    private Context context;
    private LayoutInflater listContainer;     //视图容器
    private List<UserPlan> userPlans;
    private ListItemView listItemView = null;
    private UserPlan isDoingUserPlan; //当前计划
    private UserService userService = new UserService();
    private int index;//当前正在进行计划的下标
    private View view;


    public PlanAdapter(Context context, List<UserPlan> userPlans){
        this.context = context;
        this.userPlans = userPlans;
        listContainer = LayoutInflater.from(context);    //创建视图容器并设置上下文
        this.isDoingUserPlan = isDoingUserPlan;
    }

    @Override
    public int getCount() {
        return userPlans.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            listItemView = new ListItemView();
            convertView = listContainer.inflate(R.layout.plan_item,null);
            listItemView.bookName = (TextView)convertView.findViewById(R.id.book_name);
            listItemView.bookCoverPicture = (ImageView) convertView.findViewById(R.id.book_cover_picture);
            listItemView.progressBar = (ProgressBar) convertView.findViewById(R.id.progressBar);
            listItemView.isDoing = (Button) convertView.findViewById(R.id.set_is_doing);
            listItemView.isDoing.setOnClickListener(new MyClickListener(position,convertView));
            convertView.setTag(listItemView);
        }else{
            listItemView = (ListItemView) convertView.getTag();
        }
        final UserPlan userPlan = userPlans.get(position);
        Book book = userPlan.getBook();
        listItemView.userPlan = userPlan;
        listItemView.bookName.setText(book.getBookName());
        listItemView.bookCoverPicture.setImageBitmap(book.getCoverPictureBitmap());

        listItemView.progressBar.setMax(book.getWordNumber());
        listItemView.progressBar.setProgress(userPlan.getHasDone());
        if(userPlan.getIsDoing() == 1){
            isDoingUserPlan = userPlan;
            index = position;
            view = convertView;
            listItemView.isDoing.setText("当前计划");
            listItemView.isDoing.setEnabled(false);
        }
        return convertView;
    }
    public final class ListItemView{
        public UserPlan userPlan;
        public TextView bookName;
        public ImageView bookCoverPicture;
        public ProgressBar progressBar;
        public Button isDoing;
    }

    class MyClickListener implements View.OnClickListener{
        int position;
        View clickView;
        public MyClickListener(int position,View clickView){
            this.position = position;
            this.clickView = clickView;
        }
        @Override
        public void onClick(View v) {
            listItemView.userPlan.setIsDoing(1);
            isDoingUserPlan.setIsDoing(0);
            updateUserPlanToServer(listItemView.userPlan);
            updateUserPlanToServer(isDoingUserPlan);
            isDoingUserPlan = listItemView.userPlan;

//            ListItemView listItemView = (ListItemView) view.getTag();
//            listItemView.isDoing.setText("设为当前");
//            listItemView.isDoing.setClickable(true);
            Log.d("test", "onClick: " + userPlans);
            notifyDataSetChanged();
        }
    }

    /**
     * 按钮的监听器
     */
    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            listItemView.userPlan.setIsDoing(1);
            isDoingUserPlan.setIsDoing(0);
            updateUserPlanToServer(listItemView.userPlan);
            updateUserPlanToServer(isDoingUserPlan);
            isDoingUserPlan = listItemView.userPlan;
            Log.d("test", "onClick: " + userPlans);
            notifyDataSetChanged();
        }
    };

    /**
     * 更新用户计划到服务器
     * @param userPlan
     */
    public void updateUserPlanToServer(final UserPlan userPlan){
        new Thread(new Runnable() {
            @Override
            public void run() {
                userService.updateUserPlan(userPlan);
            }
        }).start();
    }

}
