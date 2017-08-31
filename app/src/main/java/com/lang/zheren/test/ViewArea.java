package com.lang.zheren.test;

import android.content.Context;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.lang.zheren.R;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * 地区选择
 *
 * @author linmz
 */
public class ViewArea extends LinearLayout implements ViewBaseAction {

    private ListView lvRegion, lvPlate, lvPlate2;

    private ArrayList<String> groups = new ArrayList<String>();
    private SparseArray<LinkedList<String>> children = new SparseArray<LinkedList<String>>();
    private SparseArray<LinkedList<String>> children2 = new SparseArray<LinkedList<String>>();

    private LinkedList<String> childrenItem = new LinkedList<String>();
    private LinkedList<String> childrenItem2 = new LinkedList<String>();

    private TextAdapter plateListViewAdapter;
    private TextAdapter plateListViewAdapter2;
    private TextAdapter earaListViewAdapter;

    private int tEaraPosition = 0;
    private int tBlockPosition = 0;
    private String showString = "不限";
    private OnSelectListener mOnSelectListener;

    public ViewArea(Context context) {
        super(context);
        init(context);
    }

    public ViewArea(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public void updateShowText(String showArea, String showBlock) {
        if (showArea == null || showBlock == null) {
            return;
        }
        for (int i = 0; i < groups.size(); i++) {
            if (groups.get(i).equals(showArea)) {
                earaListViewAdapter.setSelectedPosition(i);
                childrenItem.clear();
                if (i < children.size()) {
                    childrenItem.addAll(children.get(i));
                }
                tEaraPosition = i;
                break;
            }
        }
        for (int j = 0; j < childrenItem.size(); j++) {
            if (childrenItem.get(j).replace("不限", "").equals(showBlock.trim())) {
                plateListViewAdapter.setSelectedPosition(j);
                tBlockPosition = j;
                break;
            }
        }
        for (int k = 0; k < childrenItem2.size(); k++) {
            if (childrenItem2.get(k).replace("不限", "").equals(showBlock.trim())) {
                plateListViewAdapter.setSelectedPosition(k);
                tBlockPosition = k;
                break;
            }
        }
        setDefaultSelect();
    }

    private void init(Context context) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.view_area_popupwindow, this, true);

        lvRegion = (ListView) findViewById(R.id.lvRegion);
        lvPlate = (ListView) findViewById(R.id.lvPlate);
        lvPlate2 = (ListView) findViewById(R.id.lvPlate2);

        //测试数据
        for (int i = 0; i < 10; i++) {
            groups.add(i + "行");
            LinkedList<String> tItem = new LinkedList<String>();
            for (int j = 0; j < 15; j++) {
                tItem.add(i + "行" + j + "列");
                LinkedList<String> tItem2 = new LinkedList<String>();
                for (int k = 0; k < 15; k++) {
                    tItem2.add(i + "行" + j + "列" + k + "个");
                }
                children2.put(j, tItem2);
            }
            children.put(i, tItem);
        }

        //一级菜单
        earaListViewAdapter = new TextAdapter(context, groups,
                R.color.white,
                R.drawable.choose_eara_item_selector, 1);
        earaListViewAdapter.setTextSize(13);
        earaListViewAdapter.setTextColor(context.getResources().getColor(R.color.app_custom_popup_view_area_text));
        earaListViewAdapter.setSelectedPositionNoNotify(tEaraPosition);
        lvRegion.setAdapter(earaListViewAdapter);

        earaListViewAdapter
                .setOnItemClickListener(new TextAdapter.OnItemClickListener() {

                    @Override
                    public void onItemClick(View view, int position) {
                        if (position < children.size()) {
                            lvPlate.setVisibility(VISIBLE);

                            childrenItem.clear();
                            childrenItem.addAll(children.get(position));
                            plateListViewAdapter.notifyDataSetChanged();
                        }
                    }
                });
        if (tEaraPosition < children.size())
            childrenItem.addAll(children.get(tEaraPosition));

        //二级菜单
        plateListViewAdapter = new TextAdapter(context, groups,
                R.color.white,
                R.drawable.choose_eara_item_selector, 1);
        plateListViewAdapter.setTextSize(13);
        plateListViewAdapter.setTextColor(context.getResources().getColor(R.color.app_custom_popup_view_area_text));
        plateListViewAdapter.setSelectedPositionNoNotify(tBlockPosition);

        lvPlate.setAdapter(plateListViewAdapter);
        plateListViewAdapter
                .setOnItemClickListener(new TextAdapter.OnItemClickListener() {

                    @Override
                    public void onItemClick(View view, final int position) {
                        if (position < children2.size()) {
                            lvPlate2.setVisibility(VISIBLE);

                            childrenItem2.clear();
                            childrenItem2.addAll(children2.get(position));
                            plateListViewAdapter2.notifyDataSetChanged();
                        }
                    }
                });

        //三级菜单
        plateListViewAdapter2 = new TextAdapter(context, childrenItem2, 0,
                R.drawable.choose_eara_item_selector, 2);
        plateListViewAdapter2.setTextSize(13);
        plateListViewAdapter2.setTextColor(context.getResources().getColor(R.color.app_custom_popup_view_area_text));
        plateListViewAdapter2.setSelectedPositionNoNotify(tBlockPosition);
        lvPlate2.setAdapter(plateListViewAdapter2);
        plateListViewAdapter2
                .setOnItemClickListener(new TextAdapter.OnItemClickListener() {

                    @Override
                    public void onItemClick(View view, final int position) {

                        showString = childrenItem2.get(position);
                        if (mOnSelectListener != null) {

                            mOnSelectListener.getValue(showString);
                        }

                    }
                });
        if (tBlockPosition < childrenItem.size())
            showString = childrenItem.get(tBlockPosition);
        if (showString.contains("不限")) {
            showString = showString.replace("不限", "");
        }
        setDefaultSelect();

    }

    public void setDefaultSelect() {
        lvRegion.setSelection(tEaraPosition);
        lvPlate.setSelection(tBlockPosition);
    }

    public void setOnSelectListener(OnSelectListener onSelectListener) {
        mOnSelectListener = onSelectListener;
    }

    public interface OnSelectListener {
        public void getValue(String showText);
    }

    @Override
    public void hide() {

    }

    @Override
    public void show() {

    }

}
