package com.lang.zheren.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.lang.zheren.MainActivity;
import com.lang.zheren.R;
import com.lang.zheren.adapter.AreaAdapter;
import com.lang.zheren.adapter.CityAdapter;
import com.lang.zheren.adapter.CommunityAdapter;
import com.lang.zheren.adapter.DealAdapter;
import com.lang.zheren.adapter.FloorAdapter;
import com.lang.zheren.adapter.HuXingAdapter;
import com.lang.zheren.adapter.OrientationAdapter;
import com.lang.zheren.adapter.PropertyAdapter;
import com.lang.zheren.adapter.StreetAdapter;
import com.lang.zheren.adapter.TimeAdapter;
import com.lang.zheren.adapter.ZhuZhaiAdapter;
import com.lang.zheren.adapter.ZhuangXiuAdapter;
import com.lang.zheren.util.ActivityUtil;
import com.lang.zheren.util.SharePreferenceUtils;
import com.lang.zheren.util.ToastUtil;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 我要卖房页面
 * Created by Administrator on 2017/8/21.
 */

public class BuyHouseActivity extends AppCompatActivity {
    @BindView(R.id.top_back)
    ImageView mTopBack;
    @BindView(R.id.top_title)
    TextView mTopTitle;
    @BindView(R.id.top_other)
    ImageView mTopOther;
    @BindView(R.id.buy_select_area)
    ImageView mBuySelectArea;
    @BindView(R.id.buy_select_property)
    ImageView mBuySelectProperty;
    @BindView(R.id.buy_select_time)
    ImageView mBuySelectTime;
    @BindView(R.id.buy_select_floor)
    ImageView mBuySelectFloor;
    @BindView(R.id.buy_select_deal)
    ImageView mBuySelectDeal;
    @BindView(R.id.edt_des)
    EditText mEdtDes;
    @BindView(R.id.txt_house_img)
    TextView mTxtHouseImg;
    @BindView(R.id.img_buy_add)
    ImageView mImgBuyAdd;
    @BindView(R.id.buy_publish)
    Button mBuyPublish;
    @BindView(R.id.layout_area)
    LinearLayout mLayoutArea;
    @BindView(R.id.toolBar)
    Toolbar mToolBar;
    @BindView(R.id.lst_city)
    ListView mLstCity;
    @BindView(R.id.lst_area)
    ListView mLstArea;
    @BindView(R.id.lst_street)
    ListView mLstStreet;
    @BindView(R.id.txt_des)
    TextView mTxtDes;
    @BindView(R.id.scroll_view)
    ScrollView mScrollView;
    @BindView(R.id.txt_address)
    TextView mTxtAddress;
    @BindView(R.id.btn_select_area)
    Button mBtnSelectArea;
    @BindView(R.id.select_address)
    TextView mSelectAddress;
    @BindView(R.id.rel_select)
    RelativeLayout mRelSelect;
    @BindView(R.id.buy_select_community)
    ImageView mBuySelectCommunity;
    @BindView(R.id.edt_area_value)
    EditText mEdtAreaValue;
    @BindView(R.id.edt_price_all)
    EditText mEdtPriceAll;
    @BindView(R.id.edt_title)
    EditText mEdtTitle;
    @BindView(R.id.buy_house_type)
    ImageView mBuyHouseType;
    @BindView(R.id.buy_zhuzhai_type)
    ImageView mBuyZhuzhaiType;
    @BindView(R.id.buy_zhuangxiu_type)
    ImageView mBuyZhuangxiuType;
    @BindView(R.id.buy_orientation)
    ImageView mBuyOrientation;
    @BindView(R.id.layout_add_img)
    LinearLayout mLayoutAddImg;
    @BindView(R.id.rel_1)
    RelativeLayout mRel1;
    @BindView(R.id.community_value)
    TextView mCommunityValue;
    @BindView(R.id.rel_community_select)
    RelativeLayout mRelCommunitySelect;
    @BindView(R.id.rel_2)
    RelativeLayout mRel2;
    @BindView(R.id.space)
    TextView mSpace;
    @BindView(R.id.price_total)
    TextView mPriceTotal;
    @BindView(R.id.huxing_value)
    TextView mHuxingValue;
    @BindView(R.id.rel_huxing_select)
    RelativeLayout mRelHuxingSelect;
    @BindView(R.id.rel_3)
    RelativeLayout mRel3;
    @BindView(R.id.zhuzhai_value)
    TextView mZhuzhaiValue;
    @BindView(R.id.rel_zhuzhai_select)
    RelativeLayout mRelZhuZhai;
    @BindView(R.id.rel_4)
    RelativeLayout mRel4;
    @BindView(R.id.zhuangxiu_value)
    TextView mZhuangxiuValue;
    @BindView(R.id.rel_zhuangxiu_select)
    RelativeLayout mRelZhuangxiuSelect;
    @BindView(R.id.rel_5)
    RelativeLayout mRel5;
    @BindView(R.id.orientation_value)
    TextView mOrientationValue;
    @BindView(R.id.rel_orientation_select)
    RelativeLayout mRelOrientationSelect;
    @BindView(R.id.rel_6)
    RelativeLayout mRel6;
    @BindView(R.id.property_value)
    TextView mPropertyValue;
    @BindView(R.id.rel_property_select)
    RelativeLayout mRelPropertySelect;
    @BindView(R.id.rel_7)
    RelativeLayout mRel7;
    @BindView(R.id.time_value)
    TextView mTimeValue;
    @BindView(R.id.rel_time_select)
    RelativeLayout mRelTimeSelect;
    @BindView(R.id.rel_8)
    RelativeLayout mRel8;
    @BindView(R.id.floor_value)
    TextView mFloorValue;
    @BindView(R.id.rel_floor_select)
    RelativeLayout mRelFloorSelect;
    @BindView(R.id.rel_9)
    RelativeLayout mRel9;
    @BindView(R.id.deal_value)
    TextView mDealValue;
    @BindView(R.id.rel_deal_select)
    RelativeLayout mRelDealSelect;
    @BindView(R.id.rel_10)
    RelativeLayout mRel10;
    @BindView(R.id.rel_11)
    RelativeLayout mRel11;
    @BindView(R.id.lst_community)
    ListView mLstCommunity;
    @BindView(R.id.btn_select_community)
    Button mBtnSelectCommunity;
    @BindView(R.id.layout_community)
    LinearLayout mLayoutCommunity;
    @BindView(R.id.lst_huxing)
    ListView mLstHuxing;
    @BindView(R.id.btn_select_huxing)
    Button mBtnSelectHuxing;
    @BindView(R.id.layout_huxing)
    LinearLayout mLayoutHuxing;
    @BindView(R.id.lst_zhuzhai)
    ListView mLstZhuzhai;
    @BindView(R.id.btn_select_zhuzhai)
    Button mBtnSelectZhuzhai;
    @BindView(R.id.layout_zhuzhai)
    LinearLayout mLayoutZhuzhai;
    @BindView(R.id.lst_zhuangxiu)
    ListView mLstZhuangxiu;
    @BindView(R.id.btn_select_zhuangxiu)
    Button mBtnSelectZhuangxiu;
    @BindView(R.id.layout_zhuangxiu)
    LinearLayout mLayoutZhuangxiu;
    @BindView(R.id.lst_orientation)
    ListView mLstOrientation;
    @BindView(R.id.btn_select_orientation)
    Button mBtnSelectOrientation;
    @BindView(R.id.layout_orientation)
    LinearLayout mLayoutOrientation;
    @BindView(R.id.lst_property)
    ListView mLstProperty;
    @BindView(R.id.btn_select_property)
    Button mBtnSelectProperty;
    @BindView(R.id.layout_property)
    LinearLayout mLayoutProperty;
    @BindView(R.id.lst_time)
    ListView mLstTime;
    @BindView(R.id.btn_select_time)
    Button mBtnSelectTime;
    @BindView(R.id.layout_time)
    LinearLayout mLayoutTime;
    @BindView(R.id.lst_floor)
    ListView mLstFloor;
    @BindView(R.id.btn_select_floor)
    Button mBtnSelectFloor;
    @BindView(R.id.layout_floor)
    LinearLayout mLayoutFloor;
    @BindView(R.id.lst_deal)
    ListView mLstDeal;
    @BindView(R.id.btn_select_deal)
    Button mBtnSelectDeal;
    @BindView(R.id.layout_deal)
    LinearLayout mLayoutDeal;

    private boolean isSelect = true;//是否点击选择图标（向右的图标）

    private ArrayList<String> mCityData;
    private ArrayList<String> mAreaData;
    private ArrayList<String> mStreetData;
    private ArrayList<String> mCommunityData;
    private ArrayList<String> mHuXingData;
    private ArrayList<String> mZhuZhaiData;
    private ArrayList<String> mZhuangXiuData;
    private ArrayList<String> mOrientationData;
    private ArrayList<String> mPropertyData;
    private ArrayList<String> mTimeData;
    private ArrayList<String> mFloorData;
    private ArrayList<String> mDealData;

    private CityAdapter mCityAdapter;
    private AreaAdapter mAreaAdapter;
    private StreetAdapter mStreetAdapter;
    private CommunityAdapter mCommunityAdapter;
    private HuXingAdapter mHuXingAdapter;
    private ZhuZhaiAdapter mZhuZhaiAdapter;
    private ZhuangXiuAdapter mZhuangXiuAdapter;
    private OrientationAdapter mOrientationAdapter;
    private PropertyAdapter mPropertyAdapter;
    private TimeAdapter mTimeAdapter;
    private FloorAdapter mFloorAdapter;
    private DealAdapter mDealAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_house);
        ButterKnife.bind(this);
        //设置标题
        mTopTitle.setText(R.string.top_house_buy);
        initListData();
        initAdapter();
        setAdapter();
        initItemListener();
        initTouch();
    }

    /**
     * 初始化listview的数据
     */
    private void initListData() {
        mCityData = new ArrayList<>();
        mAreaData = new ArrayList<>();
        mStreetData = new ArrayList<>();
        mCommunityData = new ArrayList<>();
        mHuXingData = new ArrayList<>();
        mZhuZhaiData = new ArrayList<>();
        mZhuangXiuData = new ArrayList<>();
        mOrientationData = new ArrayList<>();
        mPropertyData = new ArrayList<>();
        mTimeData = new ArrayList<>();
        mFloorData = new ArrayList<>();
        mDealData = new ArrayList<>();
    }

    /**
     * 初始化listview的adapter
     */
    private void initAdapter() {
        mCityAdapter = new CityAdapter(mCityData);
        mAreaAdapter = new AreaAdapter(mAreaData);
        mStreetAdapter = new StreetAdapter(mStreetData);
        mCommunityAdapter = new CommunityAdapter(mCommunityData);
        mHuXingAdapter = new HuXingAdapter(mHuXingData);
        mZhuZhaiAdapter = new ZhuZhaiAdapter(mZhuZhaiData);
        mZhuangXiuAdapter = new ZhuangXiuAdapter(mZhuangXiuData);
        mOrientationAdapter = new OrientationAdapter(mOrientationData);
        mPropertyAdapter = new PropertyAdapter(mPropertyData);
        mTimeAdapter = new TimeAdapter(mTimeData);
        mFloorAdapter = new FloorAdapter(mFloorData);
        mDealAdapter = new DealAdapter(mDealData);
    }

    /**
     * 给listview设置adapter
     */
    private void setAdapter() {
        mLstCity.setAdapter(mCityAdapter);
        mLstArea.setAdapter(mAreaAdapter);
        mLstStreet.setAdapter(mStreetAdapter);
        mLstCommunity.setAdapter(mCommunityAdapter);
        mLstHuxing.setAdapter(mHuXingAdapter);
        mLstZhuzhai.setAdapter(mZhuZhaiAdapter);
        mLstZhuangxiu.setAdapter(mZhuangXiuAdapter);
        mLstOrientation.setAdapter(mOrientationAdapter);
        mLstProperty.setAdapter(mPropertyAdapter);
        mLstTime.setAdapter(mTimeAdapter);
        mLstFloor.setAdapter(mFloorAdapter);
        mLstDeal.setAdapter(mDealAdapter);
    }

    /**
     * 初始化子条目的点击事件
     */
    private void initItemListener() {
        mLstCity.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                // 更新当前被选中的位置
                mCityAdapter.setCurrentItem(i);
                mCityAdapter.setClick(true);
                mTxtAddress.setText(mCityData.get(i).toString());
                // 刷新listview
                mCityAdapter.notifyDataSetChanged();
            }
        });
        mLstArea.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                // 更新当前被选中的位置
                mAreaAdapter.setCurrentItem(i);
                mAreaAdapter.setClick(true);
                mTxtAddress.setText(mCityData.get(i).toString() + mAreaData.get(i).toString());
                // 刷新listview
                mAreaAdapter.notifyDataSetChanged();
            }
        });
        mLstStreet.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                // 更新当前被选中的位置
                mStreetAdapter.setCurrentItem(i);
                mStreetAdapter.setClick(true);
                mTxtAddress.setText(mCityData.get(i).toString() + mAreaData.get(i).toString() + mStreetData.get(i).toString());
                // 刷新listview
                mStreetAdapter.notifyDataSetChanged();
            }
        });
        mLstCommunity.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                // 更新当前被选中的位置
                mCommunityAdapter.setCurrentItem(i);
                mCommunityAdapter.setClick(true);
                mCommunityValue.setText(mCommunityData.get(i).toString());
                // 刷新listview
                mCommunityAdapter.notifyDataSetChanged();
            }
        });
        mLstHuxing.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                // 更新当前被选中的位置
                mHuXingAdapter.setCurrentItem(i);
                mHuXingAdapter.setClick(true);
                mHuxingValue.setText(mHuXingData.get(i).toString());
                // 刷新listview
                mHuXingAdapter.notifyDataSetChanged();
            }
        });
        mLstZhuzhai.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                // 更新当前被选中的位置
                mZhuZhaiAdapter.setCurrentItem(i);
                mZhuZhaiAdapter.setClick(true);
                mZhuzhaiValue.setText(mZhuZhaiData.get(i).toString());
                // 刷新listview
                mZhuZhaiAdapter.notifyDataSetChanged();
            }
        });
        mLstZhuangxiu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                // 更新当前被选中的位置
                mZhuangXiuAdapter.setCurrentItem(i);
                mZhuangXiuAdapter.setClick(true);
                mZhuangxiuValue.setText(mZhuangXiuData.get(i).toString());
                // 刷新listview
                mZhuangXiuAdapter.notifyDataSetChanged();
            }
        });
        mLstOrientation.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                // 更新当前被选中的位置
                mOrientationAdapter.setCurrentItem(i);
                mOrientationAdapter.setClick(true);
                mOrientationValue.setText(mOrientationData.get(i).toString());
                // 刷新listview
                mOrientationAdapter.notifyDataSetChanged();
            }
        });
        mLstProperty.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                // 更新当前被选中的位置
                mPropertyAdapter.setCurrentItem(i);
                mPropertyAdapter.setClick(true);
                mPropertyValue.setText(mPropertyData.get(i).toString());
                // 刷新listview
                mPropertyAdapter.notifyDataSetChanged();
            }
        });
        mLstTime.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                // 更新当前被选中的位置
                mTimeAdapter.setCurrentItem(i);
                mTimeAdapter.setClick(true);
                mTimeValue.setText(mTimeData.get(i).toString());
                // 刷新listview
                mTimeAdapter.notifyDataSetChanged();
            }
        });
        mLstFloor.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                // 更新当前被选中的位置
                mFloorAdapter.setCurrentItem(i);
                mFloorAdapter.setClick(true);
                mFloorValue.setText(mFloorData.get(i).toString());
                // 刷新listview
                mFloorAdapter.notifyDataSetChanged();
            }
        });
        mLstDeal.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                // 更新当前被选中的位置
                mDealAdapter.setCurrentItem(i);
                mDealAdapter.setClick(true);
                mDealValue.setText(mDealData.get(i).toString());
                // 刷新listview
                mDealAdapter.notifyDataSetChanged();
            }
        });
    }

    /**
     * 解决listview不能滑动（原因是listview和scrollview的事件冲突了）
     */
    private void initTouch() {
        //解决listview不能滑动（原因是listview和scrollview的事件冲突了）
        mLstCity.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_MOVE) {
                    mScrollView.requestDisallowInterceptTouchEvent(true);
                }
                return false;
            }
        });
        mLstArea.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_MOVE) {
                    mScrollView.requestDisallowInterceptTouchEvent(true);
                }
                return false;
            }
        });
        mLstStreet.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_MOVE) {
                    mScrollView.requestDisallowInterceptTouchEvent(true);
                }
                return false;
            }
        });
        mLstCommunity.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_MOVE) {
                    mScrollView.requestDisallowInterceptTouchEvent(true);
                }
                return false;
            }
        });
        mLstHuxing.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_MOVE) {
                    mScrollView.requestDisallowInterceptTouchEvent(true);
                }
                return false;
            }
        });
        mLstZhuzhai.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_MOVE) {
                    mScrollView.requestDisallowInterceptTouchEvent(true);
                }
                return false;
            }
        });
        mLstZhuangxiu.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_MOVE) {
                    mScrollView.requestDisallowInterceptTouchEvent(true);
                }
                return false;
            }
        });
        mLstOrientation.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_MOVE) {
                    mScrollView.requestDisallowInterceptTouchEvent(true);
                }
                return false;
            }
        });
        mLstProperty.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_MOVE) {
                    mScrollView.requestDisallowInterceptTouchEvent(true);
                }
                return false;
            }
        });
        mLstTime.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_MOVE) {
                    mScrollView.requestDisallowInterceptTouchEvent(true);
                }
                return false;
            }
        });
        mLstFloor.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_MOVE) {
                    mScrollView.requestDisallowInterceptTouchEvent(true);
                }
                return false;
            }
        });
        mLstDeal.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_MOVE) {
                    mScrollView.requestDisallowInterceptTouchEvent(true);
                }
                return false;
            }
        });
    }

    /**
     * 点击事件
     *
     * @param view
     */
    @OnClick({R.id.top_back, R.id.top_other, R.id.buy_select_area, R.id.buy_select_property,
            R.id.buy_select_time, R.id.buy_select_floor, R.id.buy_select_deal, R.id.btn_select_deal,
            R.id.img_buy_add, R.id.buy_select_community, R.id.edt_area_value,
            R.id.edt_price_all, R.id.edt_title, R.id.buy_house_type, R.id.buy_zhuzhai_type,
            R.id.buy_zhuangxiu_type, R.id.buy_orientation, R.id.layout_add_img, R.id.edt_des,
            R.id.rel_1, R.id.community_value, R.id.rel_2, R.id.space, R.id.price_total,
            R.id.huxing_value, R.id.rel_3, R.id.zhuzhai_value, R.id.rel_4, R.id.btn_select_zhuzhai,
            R.id.zhuangxiu_value, R.id.rel_5, R.id.orientation_value, R.id.rel_6,
            R.id.property_value, R.id.rel_7, R.id.time_value, R.id.rel_8, R.id.btn_select_floor,
            R.id.btn_select_community, R.id.btn_select_huxing, R.id.btn_select_zhuangxiu,
            R.id.btn_select_orientation, R.id.btn_select_property, R.id.btn_select_time,
            R.id.floor_value, R.id.rel_9, R.id.deal_value, R.id.rel_10, R.id.rel_11,
            R.id.btn_select_area, R.id.select_address, R.id.buy_publish})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.top_back:
                finish();
                break;
            case R.id.top_other:
                ToastUtil.show(this, "其他功能");
                break;

            case R.id.rel_1://所在小区
                break;

            case R.id.rel_2://您的小区
                //点击 请选择 图片 显示布局，再次点击隐藏
                if (isSelect) {
                    mLayoutCommunity.setVisibility(View.VISIBLE);
                    isSelect = false;
                } else {
                    mLayoutCommunity.setVisibility(View.GONE);
                    isSelect = true;
                }
                break;
            case R.id.community_value:
                mLayoutCommunity.setVisibility(View.VISIBLE);
                mCommunityValue.setVisibility(View.GONE);
                mRelCommunitySelect.setVisibility(View.VISIBLE);
                break;
            case R.id.btn_select_community:
                //点击确定按钮，布局隐藏，并设置所选中的地址以文字显示
                mLayoutCommunity.setVisibility(View.GONE);
                mCommunityValue.setVisibility(View.VISIBLE);
                mRelCommunitySelect.setVisibility(View.GONE);
                mCommunityValue.setText(mCommunityValue.getText());
                break;

            case R.id.rel_3://户型
                //点击 请选择 图片 显示布局，再次点击隐藏
                if (isSelect) {
                    mLayoutHuxing.setVisibility(View.VISIBLE);
                    isSelect = false;
                } else {
                    mLayoutHuxing.setVisibility(View.GONE);
                    isSelect = true;
                }
                break;
            case R.id.huxing_value:
                mLayoutHuxing.setVisibility(View.VISIBLE);
                mHuxingValue.setVisibility(View.GONE);
                mRelHuxingSelect.setVisibility(View.VISIBLE);
                break;
            case R.id.btn_select_huxing:
                //点击确定按钮，布局隐藏，并设置所选中的地址以文字显示
                mLayoutHuxing.setVisibility(View.GONE);
                mHuxingValue.setVisibility(View.VISIBLE);
                mRelHuxingSelect.setVisibility(View.GONE);
                mHuxingValue.setText(mHuxingValue.getText());
                break;

            case R.id.rel_4://住宅类型
                //点击 请选择 图片 显示布局，再次点击隐藏
                if (isSelect) {
                    mLayoutZhuzhai.setVisibility(View.VISIBLE);
                    isSelect = false;
                } else {
                    mLayoutZhuzhai.setVisibility(View.GONE);
                    isSelect = true;
                }
                break;
            case R.id.zhuzhai_value:
                mLayoutZhuzhai.setVisibility(View.VISIBLE);
                mZhuzhaiValue.setVisibility(View.GONE);
                mRelZhuZhai.setVisibility(View.VISIBLE);
                break;
            case R.id.btn_select_zhuzhai:
                //点击确定按钮，布局隐藏，并设置所选中的地址以文字显示
                mLayoutZhuzhai.setVisibility(View.GONE);
                mZhuzhaiValue.setVisibility(View.VISIBLE);
                mRelZhuZhai.setVisibility(View.GONE);
                mZhuzhaiValue.setText(mZhuzhaiValue.getText());
                break;

            case R.id.rel_5://装修类型
                //点击 请选择 图片 显示布局，再次点击隐藏
                if (isSelect) {
                    mLayoutZhuangxiu.setVisibility(View.VISIBLE);
                    isSelect = false;
                } else {
                    mLayoutZhuangxiu.setVisibility(View.GONE);
                    isSelect = true;
                }
                break;
            case R.id.zhuangxiu_value:
                mLayoutZhuangxiu.setVisibility(View.VISIBLE);
                mZhuangxiuValue.setVisibility(View.GONE);
                mRelZhuangxiuSelect.setVisibility(View.VISIBLE);
                break;
            case R.id.btn_select_zhuangxiu:
                //点击确定按钮，布局隐藏，并设置所选中的地址以文字显示
                mLayoutZhuangxiu.setVisibility(View.GONE);
                mZhuangxiuValue.setVisibility(View.VISIBLE);
                mRelZhuangxiuSelect.setVisibility(View.GONE);
                mZhuangxiuValue.setText(mZhuangxiuValue.getText());
                break;

            case R.id.rel_6://房屋朝向
                //点击 请选择 图片 显示布局，再次点击隐藏
                if (isSelect) {
                    mLayoutOrientation.setVisibility(View.VISIBLE);
                    isSelect = false;
                } else {
                    mLayoutOrientation.setVisibility(View.GONE);
                    isSelect = true;
                }
                break;
            case R.id.orientation_value:
                mLayoutOrientation.setVisibility(View.VISIBLE);
                mOrientationValue.setVisibility(View.GONE);
                mRelOrientationSelect.setVisibility(View.VISIBLE);
                break;
            case R.id.btn_select_orientation:
                //点击确定按钮，布局隐藏，并设置所选中的地址以文字显示
                mLayoutOrientation.setVisibility(View.GONE);
                mOrientationValue.setVisibility(View.VISIBLE);
                mRelOrientationSelect.setVisibility(View.GONE);
                mOrientationValue.setText(mOrientationValue.getText());
                break;

            case R.id.rel_7://产权
                //点击 请选择 图片 显示布局，再次点击隐藏
                if (isSelect) {
                    mLayoutProperty.setVisibility(View.VISIBLE);
                    isSelect = false;
                } else {
                    mLayoutProperty.setVisibility(View.GONE);
                    isSelect = true;
                }
                break;
            case R.id.property_value:
                mLayoutProperty.setVisibility(View.VISIBLE);
                mPropertyValue.setVisibility(View.GONE);
                mRelPropertySelect.setVisibility(View.VISIBLE);
                break;
            case R.id.btn_select_property:
                mLayoutProperty.setVisibility(View.GONE);
                mPropertyValue.setVisibility(View.VISIBLE);
                mRelPropertySelect.setVisibility(View.GONE);
                mPropertyValue.setText(mPropertyValue.getText());
                break;


            case R.id.rel_8://建筑年代
                //点击 请选择 图片 显示布局，再次点击隐藏
                if (isSelect) {
                    mLayoutTime.setVisibility(View.VISIBLE);
                    isSelect = false;
                } else {
                    mLayoutTime.setVisibility(View.GONE);
                    isSelect = true;
                }
                break;
            case R.id.time_value:
                mLayoutTime.setVisibility(View.VISIBLE);
                mTimeValue.setVisibility(View.GONE);
                mRelTimeSelect.setVisibility(View.VISIBLE);
                break;
            case R.id.btn_select_time:
                mLayoutTime.setVisibility(View.GONE);
                mTimeValue.setVisibility(View.VISIBLE);
                mRelTimeSelect.setVisibility(View.GONE);
                mTimeValue.setText(mTimeValue.getText());
                break;

            case R.id.rel_9://楼层
                //点击 请选择 图片 显示布局，再次点击隐藏
                if (isSelect) {
                    mLayoutFloor.setVisibility(View.VISIBLE);
                    isSelect = false;
                } else {
                    mLayoutFloor.setVisibility(View.GONE);
                    isSelect = true;
                }
                break;
            case R.id.floor_value:
                mLayoutFloor.setVisibility(View.VISIBLE);
                mFloorValue.setVisibility(View.GONE);
                mRelFloorSelect.setVisibility(View.VISIBLE);
                break;
            case R.id.btn_select_floor:
                mLayoutFloor.setVisibility(View.GONE);
                mFloorValue.setVisibility(View.VISIBLE);
                mRelFloorSelect.setVisibility(View.GONE);
                mFloorValue.setText(mFloorValue.getText());
                break;

            case R.id.rel_10://交易权属
                //点击 请选择 图片 显示布局，再次点击隐藏
                if (isSelect) {
                    mLayoutDeal.setVisibility(View.VISIBLE);
                    isSelect = false;
                } else {
                    mLayoutDeal.setVisibility(View.GONE);
                    isSelect = true;
                }
                break;
            case R.id.deal_value:
                mLayoutDeal.setVisibility(View.VISIBLE);
                mDealValue.setVisibility(View.GONE);
                mRelDealSelect.setVisibility(View.VISIBLE);
                break;
            case R.id.btn_select_deal:
                mLayoutDeal.setVisibility(View.GONE);
                mDealValue.setVisibility(View.VISIBLE);
                mRelDealSelect.setVisibility(View.GONE);
                mDealValue.setText(mDealValue.getText());
                break;

            case R.id.buy_select_area:
                //点击 请选择 图片 显示布局，再次点击隐藏
                if (isSelect) {
                    mLayoutArea.setVisibility(View.VISIBLE);
                    isSelect = false;
                } else {
                    mLayoutArea.setVisibility(View.GONE);
                    isSelect = true;
                }
                break;
            case R.id.select_address:
                mLayoutArea.setVisibility(View.VISIBLE);
                mSelectAddress.setVisibility(View.GONE);
                mRelSelect.setVisibility(View.VISIBLE);
                break;
            case R.id.btn_select_area:
                //点击确定按钮，布局隐藏，并设置所选中的地址以文字显示
                mLayoutArea.setVisibility(View.GONE);
                mSelectAddress.setVisibility(View.VISIBLE);
                mRelSelect.setVisibility(View.GONE);
                mSelectAddress.setText(mTxtAddress.getText());

                break;

            case R.id.edt_area_value://面积
                break;
            case R.id.edt_price_all://总价
                break;
            case R.id.edt_title://标题
                break;

            case R.id.edt_des://g个性描述
                break;
            case R.id.layout_add_img://添加房源图片
                break;
            case R.id.buy_publish://发布
                //如果没有登录则跳转到登录页面，否则直接跳转到发布页面
                boolean isLogin = SharePreferenceUtils.getInstance().getState(this);
                if (isLogin) {
                    ActivityUtil.gotoActivity(this, MainActivity.class);
                } else {
                    ActivityUtil.gotoActivity(this, LoginActivity.class);
                }

                break;
        }
    }
}
