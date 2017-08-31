package com.lang.zheren.callbacak;

import android.widget.Toast;

import com.lang.zheren.MyApplication;

import java.util.ArrayList;

/**
 * 自定义多选框回调按钮
 * Created by Administrator on 2017/8/29.
 */

public class MyMultiChoiceDialogCallback implements Callback {
    @Override
    public void onPositiveButton() {
        Toast.makeText(MyApplication.getContextObject(), "您点击了确定", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNeutralButton() {
    }

    @Override
    public void onNegativeButton() {
        Toast.makeText(MyApplication.getContextObject(), "您点击了取消", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onGetReturnValue(ArrayList<Object> ReturnValues) {
        int count = ReturnValues.size();
        String strReturnValue = "";
        for (int i = 0; i < count - 1; i++) {
            strReturnValue += ReturnValues.get(i) + "\n";
        }
        strReturnValue += ReturnValues.get(count - 1);

        Toast.makeText(MyApplication.getContextObject(), strReturnValue, Toast.LENGTH_LONG).
                show();
    }
}
