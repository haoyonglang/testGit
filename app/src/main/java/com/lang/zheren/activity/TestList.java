package com.lang.zheren.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;

import com.lang.zheren.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/8/24.
 */

public class TestList extends AppCompatActivity {
    private GridView gv;
    private int mCurrentPos = 0;
    private List<Map<String, Object>> mItems;
    private MyAdapter mMyAdapter;
    private List<String> mGridData;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
//        initList();
        initGridView();

    }

    private void initList() {
        final ListView listView1 = (ListView) findViewById(R.id.list1);
        List<Map<String, Object>> listItems = new ArrayList<Map<String, Object>>();
        for (int i = 0; i < 20; i++) {
            Map<String, Object> listItem = new HashMap<String, Object>();
            listItem.put("header", i);
            listItem.put("second", i);
            listItem.put("third", i);
            listItems.add(listItem);
        }

//        SimpleAdapter simpleAdapter = new SimpleAdapter(this, listItems, R.layout.item_area, new String[]{"header", "second", "third"}, new int[]{R.id.city, R.id.area, R.id.street});
//        listView1.setAdapter(simpleAdapter);

        listView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            }
        });
    }

    private void initGridView() {
        //准备要添加的数据条目
        mGridData = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            mGridData.add(i + "");
        }
        GridView gv = (GridView) findViewById(R.id.grid_view);
        //为GridView设置适配器
        mMyAdapter = new MyAdapter(this);

        gv.setAdapter(mMyAdapter);

        //注册监听事件
        gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                // 更新当前被选中的位置
                mCurrentPos = position;
                // 刷新listview
                mMyAdapter.notifyDataSetChanged();

            }
        });
    }

    //自定义适配器
    class MyAdapter extends BaseAdapter {
        //上下文对象
        private Context context;

        //图片数组
        MyAdapter(Context context) {
            this.context = context;
        }

        public int getCount() {
            return mGridData.size();
        }

        public Object getItem(int item) {
            return mGridData.get(item);
        }

        public long getItemId(int id) {
            return id;
        }

        //创建View方法
        public View getView(int position, View convertView, ViewGroup parent) {
            View inflate = View.inflate(getApplicationContext(), R.layout.item_area, null);
            TextView tv_title = (TextView) inflate.findViewById(R.id.city);

            tv_title.setText(mGridData.get(position));

            // 只有当更新的位置等于当前位置时，更改颜色
            if (mCurrentPos == position) {
                tv_title.setEnabled(true);
            } else {
                tv_title.setEnabled(false);
            }
            return inflate;
        }
    }
}

