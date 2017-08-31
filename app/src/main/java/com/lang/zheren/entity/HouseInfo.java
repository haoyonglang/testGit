package com.lang.zheren.entity;

import android.widget.ImageView;

/**
 * 房源列表信息
 * Created by Administrator on 2017/8/18.
 */

public class HouseInfo {

    private String house_name;       //房源名字

    public String getHouse_num() {
        return house_num;
    }

    public void setHouse_num(String house_num) {
        this.house_num = house_num;
    }

    private String house_num;       //房源数量
    private String house_address;    //房源地址
    private String house_type;       //房源类型
    private String house_area;       //房源面积
    private String house_price;      //房源价格
    private String house_config1;    //房源周边配置1
    private String house_config2;    //房源周边配置2
    private String house_config3;    //房源周边配置3

    private ImageView house_img;//房源图片

    public String getHouse_name() {
        return house_name;
    }

    public void setHouse_name(String house_name) {
        this.house_name = house_name;
    }

    public String getHouse_address() {
        return house_address;
    }

    public void setHouse_address(String house_address) {
        this.house_address = house_address;
    }

    public String getHouse_type() {
        return house_type;
    }

    public void setHouse_type(String house_type) {
        this.house_type = house_type;
    }

    public String getHouse_area() {
        return house_area;
    }

    public void setHouse_area(String house_area) {
        this.house_area = house_area;
    }

    public String getHouse_price() {
        return house_price;
    }

    public void setHouse_price(String house_price) {
        this.house_price = house_price;
    }

    public String getHouse_config1() {
        return house_config1;
    }

    public void setHouse_config1(String house_config1) {
        this.house_config1 = house_config1;
    }

    public String getHouse_config2() {
        return house_config2;
    }

    public void setHouse_config2(String house_config2) {
        this.house_config2 = house_config2;
    }

    public String getHouse_config3() {
        return house_config3;
    }

    public void setHouse_config3(String house_config3) {
        this.house_config3 = house_config3;
    }

    public ImageView getHouse_img() {
        return house_img;
    }

    public void setHouse_img(ImageView house_img) {
        this.house_img = house_img;
    }


}
