package com.trans.latte_ec.main.index;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.trans.kuro_core.ui.recycle.DataConverter;
import com.trans.kuro_core.ui.recycle.ItemType;
import com.trans.kuro_core.ui.recycle.MultipleFildes;
import com.trans.kuro_core.ui.recycle.MultipleItemEntity;

import java.util.ArrayList;

public class IndexDataConverter extends DataConverter {

    @Override
    public ArrayList<MultipleItemEntity> convert() {
        final JSONArray dataArray=JSON.parseObject(getJsonData()).getJSONArray("data");
        final int size=dataArray.size();
        for (int i = 0; i < size; i++) {
            final JSONObject data=dataArray.getJSONObject(i);
            final String imageUrl=data.getString("imageUrl");
            final String text=data.getString("text");
            final int spanSize =data.getInteger("spanSize");
            final int id =data.getInteger("goodsId");
            final JSONArray banners=data.getJSONArray("banners");

            final ArrayList<String> bannerImages= new ArrayList<>();

            int type=0;
            if (imageUrl==null&&text!=null){
                type=ItemType.TEXT;
            }else if (imageUrl!=null&&text==null){
                type=ItemType.IMAGE;
            }else if (imageUrl!=null){
                type=ItemType.IMAGE_TEXT;
            }else if (banners!=null){
                type=ItemType.BANNER;
                //Banner初始化
                final int bannerSize=banners.size();
                for (int j=0;j<bannerSize;j++){
                    final String banner=banners.getString(j);
                    bannerImages.add(banner);
                }
            }
            final MultipleItemEntity entity=MultipleItemEntity.builder()
                    .setField(MultipleFildes.ITEM_TYPE,type)
                    .setField(MultipleFildes.SPAN_SIZE,spanSize)
                    .setField(MultipleFildes.ID,id)
                    .setField(MultipleFildes.TEXT,text)
                    .setField(MultipleFildes.IMAGE_URL,imageUrl)
                    .setField(MultipleFildes.BANNERS,bannerImages)
                    .build();

            ENTITYS.add(entity);
        }
        return ENTITYS;
    }
}
