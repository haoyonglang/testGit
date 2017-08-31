package com.lang.zheren.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.lang.zheren.R;
import com.lang.zheren.view.wheelview.LoopView;
import com.lang.zheren.view.wheelview.OnItemSelectedListener;

import java.util.ArrayList;

/**
 * Created by Weidongjian on 2017/3/25.
 */

public class ScrollViewActivity extends AppCompatActivity {

    private Toast toast;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroll_view);

        final LoopView loopView = (LoopView) findViewById(R.id.loopView);

        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add("item " + i);
        }
        // 滚动监听
        loopView.setListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(int index) {
                if (toast == null) {
                    toast = Toast.makeText(ScrollViewActivity.this, "item " + index, Toast.LENGTH_SHORT);
                    View inflate = getLayoutInflater().inflate(R.layout.pop_house_type, null);
                    Button btnType = (Button) inflate.findViewById(R.id.type1);
                    btnType.setText(index);
                }
                toast.setText("item " + index);
                toast.show();
            }
        });
        // 设置原始数据
        loopView.setItems(list);

        //设置初始位置
        loopView.setInitPosition(0);
    }
}
