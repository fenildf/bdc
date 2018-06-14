package com.simulation.bdc.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
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
    private UserService userService = new UserService();
    private int index; //当前正在进行计划的下标
    private View view;



    public PlanAdapter(Context context, List<UserPlan> userPlans){
        this.context = context;
        this.userPlans = userPlans;
        listContainer = LayoutInflater.from(context);    //创建视图容器并设置上下文
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
            listItemView = new ListItemView()
            ;
            convertView = listContainer.inflate(R.layout.plan_item,null);
            listItemView.bookName = (TextView)convertView.findViewById(R.id.book_name);
            listItemView.bookCoverPicture = (ImageView) convertView.findViewById(R.id.book_cover_picture);
            listItemView.progressBar = (ProgressBar) convertView.findViewById(R.id.progressBar);
            listItemView.isDoing = (Button) convertView.findViewById(R.id.set_is_doing);
            listItemView.isDoing.setOnClickListener(new MyClickListener(position));

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

        @Override
        public String toString() {
            return "ListItemView{" +
                    "userPlan=" + userPlan +
                    ", bookName=" + bookName +
                    ", bookCoverPicture=" + bookCoverPicture +
                    ", progressBar=" + progressBar +
                    ", isDoing=" + isDoing +
                    '}';
        }
    }

    class MyClickListener implements View.OnClickListener{
        int position;
        public MyClickListener(int position){
            this.position = position;
        }
        @Override
        public void onClick(View v) {
            UserPlan userPlan = userPlans.get(position);
            userPlan.setIsDoing(1);

            updateUserPlanToServer(userPlan);
            userPlans.get(index).setIsDoing(0);
            updateUserPlanToServer(userPlans.get(index));
            index = position;
            updateView(view);
            notifyDataSetChanged();
        }
    }

    public void updateView(View view){
        ListItemView listItemView = (ListItemView) view.getTag();
        Log.d("planAdapter", "updateView: " + listItemView);
        listItemView.isDoing.setText("设为当前");
        listItemView.isDoing.setEnabled(true);
    }
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
