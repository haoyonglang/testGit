package com.lang.zheren.callbacak;

import java.util.ArrayList;

/**
 * 自定义对话框的回调接口
 * Created by Administrator on 2017/8/29.
 */

public interface Callback {
    // 确定按钮回调函数
    public void onPositiveButton();

    // 中间按钮回调函数
    public void onNeutralButton();

    // 取消按钮回调函数
    public void onNegativeButton();

    // 参数中获得相应返回值
    public void onGetReturnValue(ArrayList<Object> ReturnValues);
}
