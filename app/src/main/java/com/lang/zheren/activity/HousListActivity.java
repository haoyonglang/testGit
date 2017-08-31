package com.lang.zheren.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.lang.zheren.R;
import com.lang.zheren.adapter.HouseAdapter;
import com.lang.zheren.entity.HouseInfo;
import com.lang.zheren.listener.HouseScrollListener;
import com.lang.zheren.test.ExpandTabView;
import com.lang.zheren.test.ViewArea;
import com.lang.zheren.test.ViewList;
import com.lang.zheren.test.ViewMore;
import com.lang.zheren.view.PickerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/8/17.
 */

public class HousListActivity extends AppCompatActivity implements View.OnClickListener, HouseScrollListener.OnloadDataListener {

    private ImageView imgBack;
    private ImageView imgOther;

    private ListView lstHouse;
    private List<HouseInfo> mData;//listview的数据源（从服务器上获取）
    private HouseAdapter mHouseAdapter;
    private View mFooter;//底部加载更多

    private boolean isClicked = true;//是否点击

    private ExpandTabView expandTabView;
    private ViewArea viewArea;
    private ViewList viewRentalSales, viewType;
    private ViewMore viewMore;
    private Spinner refreshTime;//底部刷新选择
    private ArrayList<View> mViewArray = new ArrayList<View>();
    private ArrayAdapter<String> refreshAdapter;
    private String[] timeStr = {"无刷新", "15秒", "30秒", "1分钟", "3分钟", "5分钟", "10分钟"};
    private String[] rentalSalesItems = {"不限", "出售", "出租", "求购", "求租"};
    private String[] rentalSalesItemsVaule = {"1", "2", "3", "4", "5"};
    private String[] typeItems = {"不限", "住宅", "别墅", "店面", "写字楼", "厂房"};
    private String[] typeItemsVaule = {"1", "2", "3", "4", "5", "6"};

    private Button mBtnType1;
    private PickerView mPickerView;

    private static final String[] PLANETS = new String[]{"一", "二", "三", "四", "五", "六", "七", "八"};
    private View mMain;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_house_list);
        ButterKnife.bind(this);
        initViews();
        initView();
        initData();
        initListener();
    }

    private void initView() {
        expandTabView = (ExpandTabView) findViewById(R.id.expandtabTab);
        viewArea = new ViewArea(this);
        viewRentalSales = new ViewList(this, rentalSalesItems, rentalSalesItemsVaule, 2);
        viewType = new ViewList(this, typeItems, typeItemsVaule, 3);
        viewMore = new ViewMore(this);
    }

    private void initData() {
        mViewArray.add(viewArea);
        mViewArray.add(viewRentalSales);
        mViewArray.add(viewType);
        mViewArray.add(viewMore);
        ArrayList<String> mTextArray = new ArrayList<String>();
        mTextArray.add("位置");
        mTextArray.add("房型");
        mTextArray.add("价格");
        mTextArray.add("筛选");
        expandTabView.setValue(mTextArray, mViewArray);
        //自动刷新时间选择
        refreshAdapter = new ArrayAdapter<String>(
                HousListActivity.this, R.layout.custom_spinner_checked_text, timeStr) {

            @Override
            public View getDropDownView(int position, View convertView,
                                        ViewGroup parent) {
                View view = LayoutInflater.from(HousListActivity.this)
                        .inflate(R.layout.custom_spinner_item, null);
                TextView label = (TextView) view
                        .findViewById(R.id.tv_spinner_item_label);
                if (timeStr != null && timeStr.length != 0) {
                    label.setText(timeStr[position]);
                }
                return view;
            }
        };
    }

    private void initListener() {
        viewArea.setOnSelectListener(new ViewArea.OnSelectListener() {

            @Override
            public void getValue(String showText) {
                onRefresh(viewArea, showText);

            }
        });

        viewRentalSales.setOnSelectListener(new ViewList.OnSelectListener() {

            @Override
            public void getValue(String distance, String showText) {
                onRefresh(viewRentalSales, showText);
            }
        });

        viewType.setOnSelectListener(new ViewList.OnSelectListener() {

            @Override
            public void getValue(String distance, String showText) {
                onRefresh(viewType, showText);
            }
        });

        viewMore.setOnMoreSelectListener(new ViewMore.OnMoreSelectListener() {

            @Override
            public void getValue(String distance, String showText) {
                onRefresh(viewMore, showText);
            }
        });
    }

    /**
     * 更新自定义ToggleButton上的文字
     *
     * @param view
     * @param showText
     */
    private void onRefresh(View view, String showText) {

        expandTabView.onPressBack();
        int position = getPositon(view);
        if (position >= 0 && !expandTabView.getTitle(position).equals(showText)) {
            expandTabView.setTitle(showText, position);
        }
//		Use.showToast(mContext, showText);

    }

    private int getPositon(View tView) {
        for (int i = 0; i < mViewArray.size(); i++) {
            if (mViewArray.get(i) == tView) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public void onBackPressed() {

        if (!expandTabView.onPressBack()) {
            finish();
        }

    }

    private void initViews() {

        imgBack = (ImageView) findViewById(R.id.top_back);
        imgOther = (ImageView) findViewById(R.id.top_other);

        mMain = findViewById(R.id.main);

        mData = getData();
        //显示到ListView上
        showListView(mData);
        //自定义的滚动监听事件
        HouseScrollListener onScrollListener = new HouseScrollListener(mFooter, mData);
        // 设置接口回调
        onScrollListener.setOnLoadDataListener(this);
        // 设置ListView的滚动监听事件
        lstHouse.setOnScrollListener(onScrollListener);
        //点击子条目
        lstHouse.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //Toast.makeText(HousListActivity.this, "房名为：" + mData.get(i).getHouse_name(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(HousListActivity.this, HouseDetailActivity.class);
                startActivity(intent);
            }
        });
        imgBack.setOnClickListener(this);
        imgOther.setOnClickListener(this);
    }

    /**
     * 获取房源列表信息
     * <p>
     * 分页加载，一次加载10条
     *
     * @return
     */
    public List<HouseInfo> getData() {
        mData = new ArrayList<>();
        HouseInfo houseInfo = null;
        for (int i = 0; i < 10; i++) {
            houseInfo = new HouseInfo();
            houseInfo.setHouse_name("腾春园小区.....");
            houseInfo.setHouse_num("100");
            houseInfo.setHouse_address("西安市");
            houseInfo.setHouse_type("俩室一厅");
            houseInfo.setHouse_area("100平米");
            houseInfo.setHouse_price("100万元");
            houseInfo.setHouse_config1("小区中心");
            houseInfo.setHouse_config2("配套优良");
            houseInfo.setHouse_config3("南北通透");
            mData.add(houseInfo);
        }
        return mData;
    }

    /**
     * 讲数据加载到listview上
     *
     * @param data
     */
    private void showListView(List<HouseInfo> data) {
        //首先判断适配器是否为空，首次运行肯定是为空的
        if (mHouseAdapter == null) {
            // 查到ListView控件
            lstHouse = (ListView) findViewById(R.id.house_list);
            // 将底部加载一个加载更多的布局
            mFooter = LayoutInflater.from(this).inflate(R.layout.footer, null);
            // 初始状态为隐藏
            mFooter.setVisibility(View.GONE);
            // 加入到ListView的底部
            lstHouse.addFooterView(mFooter);
            // 创建adpter数据
            mHouseAdapter = new HouseAdapter(mData);
            // 设置adapter
            lstHouse.setAdapter(mHouseAdapter);
        } else {
            //不为空，则刷新数据
            this.mData = data;
            //提醒ListView重新更新数据
            mHouseAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.top_back:
                finish();
                break;
            case R.id.top_other:
                Toast.makeText(this, "其他功能", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    public void onLoadData(List<HouseInfo> data) {
        //加载数据完成后，展示数据到ListView
        showListView(data);
    }
}
