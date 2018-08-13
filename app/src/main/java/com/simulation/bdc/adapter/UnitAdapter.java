package com.simulation.bdc.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.a00.bdcapp.R;
import com.simulation.bdc.enitity.Book;
import com.simulation.bdc.enitity.Unit;

import java.util.List;

public class UnitAdapter extends BaseAdapter {
    private List<Unit> units;
    private Context context;
    private LayoutInflater listContainer;     //视图容器
    private ListItemView listItemView;

    private int currentItem=0;  //当前选中的item
    private boolean isClick=false;


    public UnitAdapter(Context context, List<Unit> units){
        this.context = context;
        this.units = units;
        listContainer = LayoutInflater.from(context);    //创建视图容器并设置上下文
    }

    @Override
    public int getCount() {
        return units.size();
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
            convertView = listContainer.inflate(R.layout.unit_item,null);
            listItemView.unitName = convertView.findViewById(R.id.unit_name);
            convertView.setTag(listItemView);
        }else{
            listItemView = (ListItemView) convertView.getTag();
        }
        final Unit unit = units.get(position);
        listItemView.unit = unit;
        listItemView.unitName.setText(unit.getUnitName());

        if (currentItem == position) {
            //如果被点击，设置当前TextView被选中
            listItemView.unitName.setSelected(true);
        } else {
            //如果没有被点击，设置当前TextView未被选中
            listItemView.unitName.setSelected(false);
        }
/*        //被点击的一行颜色改变
        if (currentItem==position&&isClick){
            parent.setBackgroundColor(Color.parseColor("#53C7F0"));
        }else{
            parent.setBackgroundColor(Color.parseColor("#ffffff"));
        }*/
        return convertView;
    }
    public void setCurrentItem(int currentItem) {
        this.currentItem = currentItem;
    }

    class ListItemView{
        public Unit unit;
        public TextView unitName;
        public ListView unitList;
    }
}
