package com.lang.zheren.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lang.zheren.R;
import com.lang.zheren.adapter.DetailVPAdapter;
import com.lang.zheren.callbacak.MyMultiChoiceDialogCallback;
import com.lang.zheren.util.ActivityUtil;
import com.lang.zheren.util.DialogManager;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ScheduledExecutorService;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 房源详情页面
 * Created by Administrator on 2017/8/21.
 */

public class HouseDetailActivity extends AppCompatActivity {

    @BindView(R.id.top_back)
    ImageView mTopBack;
    @BindView(R.id.top_title)
    TextView mTopTitle;
    @BindView(R.id.top_other)
    ImageView mTopOther;
    @BindView(R.id.detail_view_pager)
    ViewPager mDetailViewPager;
    @BindView(R.id.detail_star)
    ImageView mDetailStar;
    @BindView(R.id.current_house)
    TextView mCurrentHouse;
    @BindView(R.id.total_house)
    TextView mTotalHouse;
    @BindView(R.id.layout_detail_report)
    LinearLayout mLayoutDetailReport;
    @BindView(R.id.buy)
    Button mBuy;
    private LinearLayout mReport;
    private DialogManager mDialogManager;
    private String[] mItems;
    private MyMultiChoiceDialogCallback mDialogCallback;
    //viewpager的图资源
    private List<ImageView> mPictures;
    //自动轮播启用开关
    private static boolean isAutoPlay = true;
    //当前轮播页
    private static int mCurrentPager = 0;
    //定时任务
    private static ScheduledExecutorService mSchedule;
    private DetailVPAdapter mVPAdapter;
    private int prePosition;
    private int curPosition;
    private int mTotalNum;//总的图片数量
    private int mCurrentNum;//当前的图片数量

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_house_detail);
        ButterKnife.bind(this);
        init();
        initViewPager();
    }

    private void init() {
        //设置标题
        mTopTitle.setText(getResources().getString(R.string.top_house_detail));
        //多选对话框
        mDialogManager = new DialogManager(this);
        //举报内容
        mItems = new String[]{"原因一", "原因二", "原因三", "原因四", "其他"};
        //多选对话框的回调
        mDialogCallback = new MyMultiChoiceDialogCallback();
    }

    private void initViewPager() {
        mPictures = new ArrayList<>();
        mPictures.add(CreateImageView(R.mipmap.detail_vp_1));
        mPictures.add(CreateImageView(R.mipmap.detail_vp_2));
        mPictures.add(CreateImageView(R.mipmap.detail_vp_3));
        mPictures.add(CreateImageView(R.mipmap.detail_vp_4));
        mPictures.add(CreateImageView(R.mipmap.detail_vp_5));

        //初始化textView的默认值
        mTotalNum = mPictures.size();
        mCurrentNum = 1;

        mVPAdapter = new DetailVPAdapter(mPictures);
        mDetailViewPager.setAdapter(mVPAdapter);
        mDetailViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                prePosition = position;
                curPosition = position;
                //数字动态变化
                mTotalHouse.setText(mTotalNum + "");
                mCurrentHouse.setText(curPosition + 1 + "");
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    /**
     * 创建图片资源
     *
     * @param resourceId
     * @return
     */
    private ImageView CreateImageView(int resourceId) {
        ImageView imageView = new ImageView(this);
        imageView.setBackgroundResource(resourceId);
        return imageView;
    }

    @OnClick({R.id.top_back, R.id.top_other, R.id.detail_star, R.id.layout_detail_report, R.id.buy})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.top_back:
                finish();
                break;
            case R.id.top_other:
                ActivityUtil.showToast(this, "其他功能");
                break;
            case R.id.detail_star:
                ActivityUtil.showToast(this, "其他功能");
                break;
            case R.id.layout_detail_report://虚假举报
                //多选对话框
                mDialogManager.showMultiChoiceDialog("提示", mItems, "确定", "取消", true, mDialogCallback);
                break;
            case R.id.buy://我要买房按钮
                startActivity(new Intent(HouseDetailActivity.this, BuyHouseActivity.class));
                break;
        }
    }
}