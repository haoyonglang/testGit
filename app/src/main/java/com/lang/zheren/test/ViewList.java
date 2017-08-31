package com.lang.zheren.test;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.lang.zheren.R;
import com.lang.zheren.util.ActivityUtil;
import com.lang.zheren.view.WheelView;

import java.util.Arrays;

public class ViewList extends LinearLayout implements ViewBaseAction {

    private Context mContext;
    private ListView mListView;
    private ImageView ivUpOne, ivUpTwo, ivUpThree, ivUpFour;
    private TextAdapter adapter;
    private String[] items;//= new String[] {"不限","出售","出租","求购","求租"};//显示字段
    private String[] itemsVaule;//= new String[] { "1", "2", "3", "4", "5"};//隐藏id
    private String showText1;
    private String showText2;
    private String showText3;
    private String showText;
    private OnSelectListener mOnSelectListener;
    private int showWhichUp = 1;

    private LinearLayout mType;
    private LinearLayout mPrice;

    //数字滚动
    private static final String[] PLANETS = new String[]{"一", "二", "三", "四", "五", "六", "七", "八", "九"};
    private Button mBtnPriceFinish;
    private Button mBtnTypeFinish;
    private EditText mtLowest;
    private EditText mHighest;
    private String lowPrice, highPrice;

    public ViewList(Context context, String[] items, String[] itemsVaule, int showWhichUp) {
        super(context);
        this.items = items;
        this.itemsVaule = itemsVaule;
        this.showWhichUp = showWhichUp;
        init(context);
    }

    public ViewList(Context context, AttributeSet attrs,
                    String[] items, String[] itemsVaule, int showWhichUp) {
        super(context, attrs);
        this.items = items;
        this.itemsVaule = itemsVaule;
        this.showWhichUp = showWhichUp;
        init(context);
    }

    public void init(Context context) {
        mContext = context;
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.view_list_popuwindow, this, true);

        //初始化wheelview
        final WheelView wheelView1 = (WheelView) findViewById(R.id.wheel_1);
        wheelView1.setOffset(1);
        wheelView1.setItems(Arrays.asList(PLANETS));
        wheelView1.setOnWheelViewListener(new WheelView.OnWheelViewListener() {
            @Override
            public void onSelected(int selectedIndex, String item) {
                ActivityUtil.showToast((Activity) mContext, item);
                showText1 = item;
            }
        });
        final WheelView wheelView2 = (WheelView) findViewById(R.id.wheel_2);
        wheelView2.setOffset(1);
        wheelView2.setItems(Arrays.asList(PLANETS));
        wheelView2.setOnWheelViewListener(new WheelView.OnWheelViewListener() {
            @Override
            public void onSelected(int selectedIndex, String item) {
                ActivityUtil.showToast((Activity) mContext, item);
                showText2 = item;
            }
        });
        final WheelView wheelView3 = (WheelView) findViewById(R.id.wheel_3);
        wheelView3.setOffset(1);
        wheelView3.setItems(Arrays.asList(PLANETS));
        wheelView3.setOnWheelViewListener(new WheelView.OnWheelViewListener() {
            @Override
            public void onSelected(int selectedIndex, String item) {
                ActivityUtil.showToast((Activity) mContext, item);
                showText3 = item;
            }
        });

        mtLowest = (EditText) findViewById(R.id.edt_price_low);
        mHighest = (EditText) findViewById(R.id.edt_price_high);

        mBtnPriceFinish = (Button) findViewById(R.id.price_finish);
        mBtnPriceFinish.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                // 点击价格设置界面的完成按钮
                lowPrice = mtLowest.getText().toString().trim();
                highPrice = mHighest.getText().toString().trim();
                if (!TextUtils.isEmpty(lowPrice)
                        && !TextUtils.isEmpty(highPrice)) {
                    showText = lowPrice + "-" + highPrice + "万";
                    mOnSelectListener.getValue("", showText);
                } else {
                    ActivityUtil.showToast((Activity) mContext, "请选择价格区间");
                }
            }
        });
        mBtnTypeFinish = (Button) findViewById(R.id.type_finish);
        mBtnTypeFinish.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (showText1 == null && showText2 == null && showText3 == null) {
                    showText1 = showText2 = showText3 = "一";
                    showText = showText1 + "室" + showText2 + "厅" + showText3 + "卫";
                    mOnSelectListener.getValue("", showText);
                }
                showText = showText1 + "室" + showText2 + "厅" + showText3 + "卫";
                mOnSelectListener.getValue("", showText);

            }
        });


        mListView = (ListView) findViewById(R.id.list);
        ivUpOne = (ImageView) findViewById(R.id.ivUpOne);
        ivUpTwo = (ImageView) findViewById(R.id.ivUpTwo);
        ivUpThree = (ImageView) findViewById(R.id.ivUpThree);
        ivUpFour = (ImageView) findViewById(R.id.ivUpFour);

        mType = (LinearLayout) findViewById(R.id.layout_pop_type);
        mPrice = (LinearLayout) findViewById(R.id.layout_pop_price);

        ivUpOne.setImageResource(R.color.transparent);
        ivUpTwo.setImageResource(R.color.transparent);
        ivUpThree.setImageResource(R.color.transparent);
        ivUpFour.setImageResource(R.color.transparent);
        if (showWhichUp == 1) {
            ivUpOne.setImageResource(R.mipmap.icon_popuwindow_up);
        } else if (showWhichUp == 2) {
            ivUpTwo.setImageResource(R.mipmap.icon_popuwindow_up);
            mType.setVisibility(VISIBLE);
            mPrice.setVisibility(GONE);
        } else if (showWhichUp == 3) {
            ivUpThree.setImageResource(R.mipmap.icon_popuwindow_up);
            mType.setVisibility(GONE);
            mPrice.setVisibility(VISIBLE);
        } else if (showWhichUp == 4) {
            ivUpFour.setImageResource(R.mipmap.icon_popuwindow_up);
        }
        if (items != null && itemsVaule != null) {
            adapter = new TextAdapter(mContext, items, R.color.white, R.color.white, 0);
            adapter.setTextSize(14);
            adapter.setTextColor(mContext.getResources()
                    .getColor(R.color.app_custom_popup_view_list_text));
            mListView.setAdapter(adapter);
            adapter.setOnItemClickListener(new TextAdapter.OnItemClickListener() {

                @Override
                public void onItemClick(View view, int position) {
                    if (mOnSelectListener != null) {
                        showText = items[position];
                        mOnSelectListener.getValue(itemsVaule[position], items[position]);
                    }
                }
            });
        }
    }

    /**
     * 默认选择项显示
     *
     * @param showText
     */
    public void setDefaultShowText(String showText) {
        this.showText = showText;
    }

    public void setOnSelectListener(OnSelectListener onSelectListener) {
        mOnSelectListener = onSelectListener;
    }

    public interface OnSelectListener {
        public void getValue(String distance, String showText);
    }

    @Override
    public void hide() {

    }

    @Override
    public void show() {

    }

}
