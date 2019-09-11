package com.trans.kuro_core.ui.recycle;

import java.util.ArrayList;

public abstract class DataConverter {
    protected final ArrayList<MultipleItemEntity> ENTITYS= new ArrayList<>();
    private String mJsonData= null;

    public abstract ArrayList<MultipleItemEntity> convert();

    public DataConverter setJsonData(String json){
        this.mJsonData=json;
        return this;
    }

    protected String getJsonData(){
        if (mJsonData==null||mJsonData.isEmpty()){
            throw new NullPointerException("DATA IS NULL!");
        }
        return mJsonData;
    }
}
