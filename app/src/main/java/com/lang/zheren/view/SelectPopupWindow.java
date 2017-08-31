package com.lang.zheren.view;

import android.app.Activity;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.PopupWindow;

import com.lang.zheren.MyApplication;
import com.lang.zheren.R;
import com.lang.zheren.adapter.ChildrenCategoryAdapter;
import com.lang.zheren.adapter.ChildrenCategoryAdapter2;
import com.lang.zheren.adapter.ParentCategoryAdapter;

/**
 * 选择PopupWindow
 *
 * @author ansen
 * @create time 2015-10-09
 */
public class SelectPopupWindow extends PopupWindow {

    private SelectCategory selectCategory;
    private SelectCategory selectCategory2;

    private String[] parentStrings;
    private String[][] childrenStrings;
    private String[][] childrenStrings2;

    private ListView lvParentCategory = null;
    private ListView lvChildrenCategory = null;
    private ListView lvChildrenCategory2 = null;

    private ListView mLstType = null;
    private ListView mLstPrice = null;
    private ListView mLstFilter = null;

    private ParentCategoryAdapter parentCategoryAdapter = null;
    private ChildrenCategoryAdapter childrenCategoryAdapter = null;
    private ChildrenCategoryAdapter2 childrenCategoryAdapter2 = null;
    private View mLine = null;

    /**
     * 一层菜单
     *
     * @param parentStrings
     * @param activity
     */
    public SelectPopupWindow(String[] parentStrings, Activity activity) {
        this.parentStrings = parentStrings;

        View contentView = LayoutInflater.from(activity).inflate(R.layout.item_select_one, null);
        DisplayMetrics dm = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(dm); // 获取手机屏幕的大小

        this.setContentView(contentView);
        this.setWidth(dm.widthPixels);
        this.setHeight(dm.heightPixels);

        this.setContentView(contentView);
        this.setWidth(dm.widthPixels);
        this.setHeight(dm.heightPixels * 6 / 10);

		/* 设置背景显示 */
        setBackgroundDrawable(activity.getResources().getDrawable(R.drawable.pop_bg));
        /* 设置触摸外面时消失 */
        setOutsideTouchable(true);
        setTouchable(true);
        setFocusable(true); /*设置点击menu以外其他地方以及返回键退出 */

        /**
         * 1.解决再次点击MENU键无反应问题
         */
        contentView.setFocusableInTouchMode(true);

        mLstType = (ListView) contentView.findViewById(R.id.lst_house_one);
        parentCategoryAdapter = new ParentCategoryAdapter(activity, parentStrings);
        mLstType.setAdapter(parentCategoryAdapter);

        mLstType.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {


                parentCategoryAdapter.setSelectedPosition(i);
                parentCategoryAdapter.notifyDataSetChanged();
            }
        });

    }

    public void setPopupWindow(int layout) {

    }

    /**
     * 三级菜单展示
     *
     * @param parentStrings   字类别数据
     * @param childrenStrings 字类别二位数组
     * @param activity
     * @param selectCategory  回调接口注入
     */
    public SelectPopupWindow(String[] parentStrings, String[][] childrenStrings, String[][] children2Strings, Activity activity, SelectCategory selectCategory) {
        this.selectCategory = selectCategory;


        this.parentStrings = parentStrings;
        this.childrenStrings = childrenStrings;
        this.childrenStrings2 = children2Strings;

        View contentView = LayoutInflater.from(activity).inflate(R.layout.item_location, null);
        DisplayMetrics dm = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(dm); // 获取手机屏幕的大小

        this.setContentView(contentView);
        this.setWidth(dm.widthPixels);
        this.setHeight(dm.heightPixels * 6 / 10);

		/* 设置背景显示 */
        setBackgroundDrawable(activity.getResources().getDrawable(R.drawable.pop_bg));
        /* 设置触摸外面时消失 */
        setOutsideTouchable(true);
        setTouchable(true);
        setFocusable(true); /*设置点击menu以外其他地方以及返回键退出 */

        /**
         * 1.解决再次点击MENU键无反应问题
         */
        contentView.setFocusableInTouchMode(true);

        mLine = contentView.findViewById(R.id.view_2);

        //父类别适配器
        lvParentCategory = (ListView) contentView.findViewById(R.id.lv_parent_category);
        parentCategoryAdapter = new ParentCategoryAdapter(activity, parentStrings);
        lvParentCategory.setAdapter(parentCategoryAdapter);

        //子类别适配器
        lvChildrenCategory = (ListView) contentView.findViewById(R.id.lv_children_category);
        childrenCategoryAdapter = new ChildrenCategoryAdapter(activity);
        lvChildrenCategory.setAdapter(childrenCategoryAdapter);
        //子类2别适配器
        lvChildrenCategory2 = (ListView) contentView.findViewById(R.id.lv_children_category2);
        childrenCategoryAdapter2 = new ChildrenCategoryAdapter2(activity);
        lvChildrenCategory2.setAdapter(childrenCategoryAdapter2);

        lvParentCategory.setOnItemClickListener(parentItemClickListener);
        lvChildrenCategory.setOnItemClickListener(childrenItemClickListener);
        lvChildrenCategory2.setOnItemClickListener(childrenItemClickListener2);
    }


    /**
     * 子类别2点击事件
     */
    private OnItemClickListener childrenItemClickListener2 = new OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            if (selectCategory != null) {
                selectCategory.selectCategory(parentCategoryAdapter.getPos(), position);

                //(获取layout1控件)把activity的layout1设置成文字
                View inflate = LayoutInflater.from(MyApplication.getContextObject()).inflate(R.layout.activity_house_list, null);
//                LinearLayout layout1 = (LinearLayout) inflate.findViewById(R.id.layout1);
//                TextView location = (TextView) inflate.findViewById(R.id.pop_total);
//                layout1.setVisibility(View.GONE);
//                location.setVisibility(View.VISIBLE);
//                location.setText(childrenStrings2[position] + "");
            }

            dismiss();
        }
    };

    /**
     * 父类别点击事件
     */
    private OnItemClickListener parentItemClickListener = new OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            lvChildrenCategory.setVisibility(View.VISIBLE);
            mLine.setVisibility(View.VISIBLE);

            childrenCategoryAdapter.setDatas(childrenStrings[position]);
            childrenCategoryAdapter.notifyDataSetChanged();

            parentCategoryAdapter.setSelectedPosition(position);
            parentCategoryAdapter.notifyDataSetChanged();
        }
    };
    /**
     * 子类别点击事件
     */
    private OnItemClickListener childrenItemClickListener = new OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            lvChildrenCategory2.setVisibility(View.VISIBLE);

            childrenCategoryAdapter2.setDatas(childrenStrings2[position]);
            childrenCategoryAdapter2.notifyDataSetChanged();

            childrenCategoryAdapter.setSelectedPosition(position);
            childrenCategoryAdapter.notifyDataSetChanged();
        }
    };

    /**
     * 选择成功回调
     *
     * @author apple
     */
    public interface SelectCategory {
        /**
         * 把选中的下标通过方法回调回来
         *
         * @param parentSelectposition 父类别选中下标
         *                             childrenSelectposition 子类别选中下标
         */
        public void selectCategory(int parentSelectposition);

        public void selectCategory(int parentSelectposition, int childrenSelectposition);

        public void selectCategory(int parentSelectposition, int childrenSelectposition, int childrenSelectposition2);
    }

}
