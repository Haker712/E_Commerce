package com.aceplus.e_commerce.model.CategoryModelsForNavi;

import java.util.List;

/**
 * Created by phonelin on 7/17/17.
 */

public class Sub1CategoryData {

    int sub1_id;

    String sub1_Name;

    List<Sub2CategoryData> sub2CategoryDataList;


    public int getSub1_id() {
        return sub1_id;
    }

    public void setSub1_id(int sub1_id) {
        this.sub1_id = sub1_id;
    }

    public String getSub1_Name() {
        return sub1_Name;
    }

    public void setSub1_Name(String sub1_Name) {
        this.sub1_Name = sub1_Name;
    }

    public List<Sub2CategoryData> getSub2CategoryDataList() {
        return sub2CategoryDataList;
    }

    public void setSub2CategoryDataList(List<Sub2CategoryData> sub2CategoryDataList) {
        this.sub2CategoryDataList = sub2CategoryDataList;
    }
}
