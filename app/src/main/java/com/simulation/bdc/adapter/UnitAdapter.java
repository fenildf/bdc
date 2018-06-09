package com.simulation.bdc.adapter;

import android.content.Context;
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
        return convertView;
    }

    class ListItemView{
        public Unit unit;
        public TextView unitName;
        public ListView unitList;
    }
}
