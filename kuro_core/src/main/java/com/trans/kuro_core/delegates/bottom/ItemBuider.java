package com.trans.kuro_core.delegates.bottom;

import java.util.LinkedHashMap;

public class ItemBuider {

    private final LinkedHashMap<BottomTabBean,BottomItemDelegate> ITEMS=new LinkedHashMap<>();

    static ItemBuider builder(){
        return new ItemBuider();
    }

    public final ItemBuider addItem(BottomTabBean bean ,BottomItemDelegate delegate){
        ITEMS.put(bean,delegate);
        return this;
    }

    public final ItemBuider addItem(LinkedHashMap<BottomTabBean,BottomItemDelegate> maps){
        ITEMS.putAll(maps);
        return this;
    }

    public final LinkedHashMap<BottomTabBean,BottomItemDelegate> build(){
        return ITEMS;
    }
}
