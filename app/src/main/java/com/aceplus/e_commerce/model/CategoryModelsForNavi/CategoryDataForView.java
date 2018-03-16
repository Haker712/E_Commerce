package com.aceplus.e_commerce.model.CategoryModelsForNavi;

import java.io.Serializable;
import java.util.List;

/**
 * Created by phonelin on 6/19/17.
 */

public class CategoryDataForView implements Serializable {

    int id;

    String Name;

    List<Sub1CategoryData> sub1CategoryDataList;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public List<Sub1CategoryData> getSub1CategoryDataList() {
        return sub1CategoryDataList;
    }

    public void setSub1CategoryDataList(List<Sub1CategoryData> sub1CategoryDataList) {
        this.sub1CategoryDataList = sub1CategoryDataList;
    }
}
