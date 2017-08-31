package com.lang.zheren.adapter;

import com.lang.zheren.R;
import com.lang.zheren.entity.HouseInfo;
import com.lang.zheren.viewholder.ViewHolder;

import java.util.List;

/**
 * 房源列表的适配器
 * Created by Administrator on 2017/8/18.
 */

public class HouseAdapter extends MyBaseAdapter<HouseInfo> {

    public HouseAdapter(List<HouseInfo> data) {
        super(data);
    }


    @Override
    public void setData(ViewHolder holder, HouseInfo houseInfo) {
        holder.setText(R.id.item_title, houseInfo.getHouse_name())
                .setText(R.id.item_address, houseInfo.getHouse_address())
                .setText(R.id.item_house_type, houseInfo.getHouse_type())
                .setText(R.id.item_house_area, houseInfo.getHouse_area())
                .setText(R.id.item_house_price, houseInfo.getHouse_price())
                .setText(R.id.item_house_config1, houseInfo.getHouse_config1())
                .setText(R.id.item_house_config2, houseInfo.getHouse_config2())
                .setText(R.id.item_house_config3, houseInfo.getHouse_config3());
    }
}
