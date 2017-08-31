package com.lang.zheren.holder;

import android.view.View;

/**
 * 对viewHolder的封装
 * <p>
 *     首先先一个抽象方法,initView(),这个方法暴露给子类,让子类在这个方法里面完成步骤1.2即:
 *     加载布局,初始化控件,这个方法返回的mRootView就是ListView的item的布局.然后将此方法写在BaseHolder的构造方法里面,
 *     只要一new BaseHolder或者其子类,该方法就会调用.
     在构造方法里完成settag方法.
     getRootView()方法用于外界的getView方法返回item的view
     refreshView()抽象方法,是根据数据刷新界面的方法,也就是步骤4.
     setData()方法内部调用的也是refreshView方法.
 * Created by acer on 2017/8/23.
 */

public abstract class MyViewHolder<T> {
    private View mRootView;
    private T data;

    //在构造方法中初始化布局
    public MyViewHolder() {
        //1.加载布局文件
        //2.在initView方法中初始化控件
        mRootView = initView();
        //3.打标记
        mRootView.setTag(this);
    }

    //基类不知道具体的实现,需要子类去具体的实现
    public abstract View initView();

    //让外界拿到跟item布局mRootView的方法
    public View getRootView() {
        return mRootView;
    }

    //4.根据数据甩你界面的方法,由于基类不知道具体的实现,所以让子类完成
    public abstract void refreshView(T data);
    public void setData(T data) {
        this.data = data;
        refreshView(data);
    }

    public T getData() {
        return data;
    }
}
