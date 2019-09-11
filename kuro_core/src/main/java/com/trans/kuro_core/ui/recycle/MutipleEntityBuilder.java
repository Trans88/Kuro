package com.trans.kuro_core.ui.recycle;

import java.util.LinkedHashMap;
/**
 * author : TRS透明
 * created on : 2019/9/11
 */
public class MutipleEntityBuilder {
    private static final LinkedHashMap<Object,Object>FIELDS=new LinkedHashMap<>();

    public MutipleEntityBuilder(){
        //先清除之前的数据
        FIELDS.clear();
    }

    public final MutipleEntityBuilder setItemType(int itemType){
        FIELDS.put(MultipleFildes.ITEM_TYPE,itemType);
        return this;
    }

    public final MutipleEntityBuilder setField(Object key,Object value){
        FIELDS.put(key,value);
        return this;
    }

    public final MutipleEntityBuilder setFields(LinkedHashMap<?,?> map){
        FIELDS.putAll(map);
        return this;
    }

    public final MultipleItemEntity build(){
       return new MultipleItemEntity(FIELDS);
    }
}
