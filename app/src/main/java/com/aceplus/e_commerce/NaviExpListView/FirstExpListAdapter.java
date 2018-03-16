package com.aceplus.e_commerce.NaviExpListView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.aceplus.e_commerce.R;
import com.aceplus.e_commerce.model.CategoryModelsForNavi.CategoryDataForView;
import com.aceplus.e_commerce.model.CategoryModelsForNavi.Sub1CategoryData;
import com.aceplus.e_commerce.model.CategoryModelsForNavi.Sub2CategoryData;

import java.util.List;

/**
 * Created by phonelin on 7/17/17.
 */
public class FirstExpListAdapter extends BaseExpandableListAdapter {

    private Context context;
    private List<CategoryDataForView> HeaderList;
    private MyLinkedMap<CategoryDataForView, List<Sub1CategoryData>> Sub1list;

    public FirstExpListAdapter(Context context, List<CategoryDataForView> HeaderList, MyLinkedMap<CategoryDataForView, List<Sub1CategoryData>> Sub1list) {

        this.context = context;
        this.HeaderList = HeaderList;
        this.Sub1list = Sub1list;


    }

    @Override
    public int getGroupCount() {
        return HeaderList.size();
    }

    @Override
    public int getChildrenCount(int i) {

        int mapSize = 0;

        List<Sub1CategoryData> childList = Sub1list.getValue(i);

        if(childList != null && childList.size() !=0) {
            mapSize = childList.size();
        }

        return mapSize;

    }

    @Override
    public Object getGroup(int i) {
        return HeaderList.get(i);
    }

    @Override
    public Object getChild(int i, int i1) {
        return Sub1list.get(this.HeaderList.get(i)).get(i1);
    }

    @Override
    public long getGroupId(int i) {
        return i;
    }

    @Override
    public long getChildId(int i, int i1) {
        return i1;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {

        String HeaderTitle = HeaderList.get(i).getName();

        if (view == null) {

            LayoutInflater inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            view = inflater.inflate(R.layout.list_group, null);

        }
        TextView Header = (TextView) view.findViewById(R.id.Header);
        Header.setText(HeaderTitle);


        return view;
    }

    @Override
    public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {

        SecondLevelExpandableListView secondLevelELV = new SecondLevelExpandableListView(context);
        Sub1CategoryData secondItemTitle = Sub1list.getValue(i).get(i1);


        List<Sub2CategoryData> secondList = secondItemTitle.getSub2CategoryDataList();

            secondLevelELV.setAdapter(new SecondLevelAdapter(context, secondItemTitle, secondList));
/*
        } else {
            secondLevelELV.setAdapter(new SecondLevelAdapter(context, secondItemTitle, null));
        }
*/
        secondLevelELV.setGroupIndicator(null);
        return secondLevelELV;

        //return view;

    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }
}
