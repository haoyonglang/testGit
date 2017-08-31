package com.lang.zheren.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.lang.zheren.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/8/14.
 */

public class test extends AppCompatActivity {
    private DrawerLayout mDrawerLayout;
    private Toolbar mToolbar;

    private int[] imagesId = {R.mipmap.back, R.mipmap.back, R.mipmap.back, R.mipmap.back, R.mipmap.back, R.mipmap.back};
    private String[] titles = {"首页", "发现", "关注", "收藏", "草稿", "提问"};
    private ListView mLv_titles;

    private MyAdapter mMyAdapter;

    // 默认当前被选中的item的位置为0
    private int mCurrentPos = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test);

        // 初始化控件
        initView();

        // 初始化数据
        initData();

        initList();

    }


    // 初始化对象
    private void initView() {
        // 初始化Toolbar、DrawerLayout，生成相应的对象
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);

        // 初始化ListView
        mLv_titles = (ListView) findViewById(R.id.lv_title);
        mMyAdapter = new MyAdapter();
        mLv_titles.setAdapter(mMyAdapter);

        mLv_titles.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // 更新当前被选中的位置
                mCurrentPos = position;
                // 刷新listview
                mMyAdapter.notifyDataSetChanged();

            }
        });
    }

    // 设置应用title
    private void initData() {
        // 设置Toolbar标题，需在setSupportActionBar之前，不然会失效
        mToolbar.setTitle("首页");
        mToolbar.setTitleTextColor(Color.TRANSPARENT);

        // 设置toolbar支持actionbar
        setSupportActionBar(mToolbar);

        // 实现按钮开关的显示及打开关闭功能并同步动画
        initDrawerToggle();
    }

    private void initDrawerToggle() {
        // 参数：开启抽屉的activity、DrawerLayout的对象、toolbar按钮打开关闭的对象、描述open drawer、描述close drawer
        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, R.string.sell_sure, R.string.cancel);
        // 添加抽屉按钮，通过点击按钮实现打开和关闭功能; 如果不想要抽屉按钮，只允许在侧边边界拉出侧边栏，可以不写此行代码
        drawerToggle.syncState();
        // 设置按钮的动画效果; 如果不想要打开关闭抽屉时的箭头动画效果，可以不写此行代码
        mDrawerLayout.setDrawerListener(drawerToggle);
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
    }

    class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return titles.length;
        }

        @Override
        public Object getItem(int position) {
            return titles[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = View.inflate(getApplicationContext(), R.layout.left_list, null);
            ImageView iv_photo = (ImageView) view.findViewById(R.id.iv_photo);
            TextView tv_title = (TextView) view.findViewById(R.id.tv_title);

            iv_photo.setBackgroundResource(imagesId[position]);
            tv_title.setText(titles[position]);

            // 只有当更新的位置等于当前位置时，更改颜色
            if (mCurrentPos == position) {
                tv_title.setEnabled(true);
            } else {
                tv_title.setEnabled(false);
            }
            return view;
        }
    }

}
