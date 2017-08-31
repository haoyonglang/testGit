package com.lang.zheren.test;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.lang.zheren.R;
import com.lang.zheren.filter.adapter.GoodsAttrListAdapter;
import com.lang.zheren.filter.adapter.GoodsAttrsAdapter;
import com.lang.zheren.filter.vo.SaleAttributeNameVo;
import com.lang.zheren.filter.vo.SaleAttributeVo;
import com.lang.zheren.filter.vo.Vo;
import com.lang.zheren.util.ActivityUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * 更多
 */
public class ViewMore extends LinearLayout implements ViewBaseAction {

    private Context mContext;
    private View rlHouseType, rlPrice, rlWeb, lyIndexView,
            lySelectHouseTypeView, lySetPriceView, rlHighPrice, rlLowPrice;
    private TextView tvFilter, tvBack, tvPrice, tvHouseType, tvPriceBack,
            tvPriceFinish;
    private ListView lvHouseTypes;
    private EditText etLowPrice, etHighPrice;
    private TextAdapter selectHoseTypeAdapter;
    private String lowPrice, highPrice;
    private List<Vo> data = new ArrayList<Vo>();

    private View contentView;

    private Context context;
    private View goodsNoView;
    private ListView selectionList;

    private TextView filterReset;
    private TextView filterSure;
    private String showText;
    private OnMoreSelectListener mOnMoreSelectListener;
    private GoodsAttrListAdapter adapter;
    private GoodsAttrsAdapter serviceAdapter;
    private List<SaleAttributeNameVo> itemData;
    private List<SaleAttributeVo> serviceList;
    private String[] serviceStr = new String[]{"仅看有货", "促销", "手机专享"};

    public ViewMore(Context context) {
        super(context);
        init(context);
    }

    public ViewMore(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public void init(Context context) {
        mContext = context;
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        contentView = inflater.inflate(R.layout.pop_house_filter, this, true);
        selectionList = (ListView) contentView.findViewById(R.id.selection_list);
        filterReset = (TextView) contentView.findViewById(R.id.filter_reset);
        filterSure = (TextView) contentView.findViewById(R.id.filter_sure);
        contentView.setOnKeyListener(new OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_BACK) {
//                    dismiss();
                }
                return true;
            }
        });

        serviceList = new ArrayList<SaleAttributeVo>();
        for (int i = 0; i < serviceStr.length; i++) {
            SaleAttributeVo vo = new SaleAttributeVo();
            vo.setValue(serviceStr[i]);
            serviceList.add(vo);
        }
        serviceAdapter = new GoodsAttrsAdapter(context);
        serviceAdapter.notifyDataSetChanged(true, serviceList);
        itemData = new ArrayList<SaleAttributeNameVo>();
        adapter = new GoodsAttrListAdapter(context, itemData);
        selectionList.setAdapter(adapter);
        String str = "["
                + "{\"nameId\":\"V2QASD\",\"saleVo\":["
                + "{\"value\":\"50以下\",\"goods\":null,\"goodsAndValId\":\"C6VOWQ\",\"checkStatus\":\"1\"},"
                + "{\"value\":\"50-100\",\"goods\":null,\"goodsAndValId\":\"C6VOWQ\",\"checkStatus\":\"0\"},"
                + "{\"value\":\"50-100\",\"goods\":null,\"goodsAndValId\":\"C6VOWQ\",\"checkStatus\":\"0\"},"
                + "{\"value\":\"50以下\",\"goods\":null,\"goodsAndValId\":\"C6VOWQ\",\"checkStatus\":\"0\"},"
                + "{\"value\":\"50-100\",\"goods\":null,\"goodsAndValId\":\"C6VOWQ\",\"checkStatus\":\"0\"},"
                + "{\"value\":\"50-100\",\"goods\":null,\"goodsAndValId\":\"C6VOWQ\",\"checkStatus\":\"0\"},"
                + "{\"value\":\"50-100\",\"goods\":null,\"goodsAndValId\":\"C6VOWQ\",\"checkStatus\":\"0\"},"
                + "{\"value\":\"300以上\",\"goods\":null,\"goodsAndValId\":\"C6VOWQ\",\"checkStatus\":\"0\"}"
                + "],\"name\":\"面积（平米）\"},"
                + "{\"nameId\":\"V2QASD\",\"saleVo\":["
                + "{\"value\":\"2年\",\"goods\":null,\"goodsAndValId\":\"C6VOWQ\",\"checkStatus\":\"1\"},"
                + "{\"value\":\"2-5年\",\"goods\":null,\"goodsAndValId\":\"C6VOWQ\",\"checkStatus\":\"0\"},"
                + "{\"value\":\"2-5年\",\"goods\":null,\"goodsAndValId\":\"C6VOWQ\",\"checkStatus\":\"0\"},"
                + "{\"value\":\"5年以上\",\"goods\":null,\"goodsAndValId\":\"C6VOWQ\",\"checkStatus\":\"0\"}"
                + "],\"name\":\"房龄\"},"
                + "{\"nameId\":\"V2QASD\",\"saleVo\":["
                + "{\"value\":\"2层以上\",\"goods\":null,\"goodsAndValId\":\"C6VOWQ\",\"checkStatus\":\"1\"},"
                + "{\"value\":\"2-5层\",\"goods\":null,\"goodsAndValId\":\"C6VOWQ\",\"checkStatus\":\"0\"},"
                + "{\"value\":\"12层以上\",\"goods\":null,\"goodsAndValId\":\"C6VOWQ\",\"checkStatus\":\"0\"}"
                + "],\"name\":\"楼层\"},"
                + "{\"nameId\":\"V2QASD\",\"saleVo\":["
                + "{\"value\":\"2年\",\"goods\":null,\"goodsAndValId\":\"C6VOWQ\",\"checkStatus\":\"1\"},"
                + "{\"value\":\"2年\",\"goods\":null,\"goodsAndValId\":\"C6VOWQ\",\"checkStatus\":\"0\"},"
                + "{\"value\":\"2年\",\"goods\":null,\"goodsAndValId\":\"C6VOWQ\",\"checkStatus\":\"0\"},"
                + "{\"value\":\"2年\",\"goods\":null,\"goodsAndValId\":\"C6VOWQ\",\"checkStatus\":\"0\"}"
                + "],\"name\":\"产权\"},"
                + "{\"nameId\":\"V2QLAH\",\"saleVo\":["
                + "{\"value\":\"面积从小到大\",\"goods\":null,\"goodsAndValId\":\"C6VOWQ\",\"checkStatus\":\"1\"},"
                + "{\"value\":\"面积从小到大\",\"goods\":null,\"goodsAndValId\":\"C6VOWQ\",\"checkStatus\":\"0\"},"
                + "{\"value\":\"面积从小到大\",\"goods\":null,\"goodsAndValId\":\"C6VOWQ\",\"checkStatus\":\"0\"},"
                + "{\"value\":\"面积从小到大\",\"goods\":null,\"goodsAndValId\":\"C6VOWQ\",\"checkStatus\":\"0\"}"
                + "],\"name\":\"排序\"}" + "]";
        JSONArray json = null;
        try {
            json = new JSONArray(str);
            refreshAttrs(json);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        // 重置的点击监听，将所有选项全设为false
        filterReset.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                for (int i = 0; i < itemData.size(); i++) {
                    for (int j = 0; j < itemData.get(i).getSaleVo().size(); j++) {
                        //设置每一项的第一个为默认选中
                        itemData.get(i).getSaleVo().get(j).setChecked(false);
                        itemData.get(i).getSaleVo().get(0).setChecked(true);
                    }
                }
                adapter.notifyDataSetChanged();
                ActivityUtil.showToast((Activity) mContext, "重置成功");
            }
        });
        // 确定的点击监听，将所有已选中项列出
        filterSure.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                String str = "";
                for (int i = 0; i < itemData.size(); i++) {
                    for (int j = 0; j < itemData.get(i).getSaleVo().size(); j++) {
                        if (itemData.get(i).getSaleVo().get(j).isChecked()) {
                            str = str + itemData.get(i).getSaleVo().get(j).getValue();
                            showText = str;
                            mOnMoreSelectListener.getValue("", showText.substring(0, 2) + "...");
                            ActivityUtil.showToast((Activity) mContext, "您的选择是：" + str);
                            //防止全部不选中时点击确定，程序崩了
                        } else if (!itemData.get(i).getSaleVo().get(j).isChecked()) {
                            ActivityUtil.showToast((Activity) mContext, "请先选择：");
                        }
                    }
                }
            }
        });

        ColorDrawable dw = new ColorDrawable(00000000);
        this.setBackgroundDrawable(dw);
        this.setFocusable(true);

    }

    /**
     * 默认选择项显示
     *
     * @param showText
     */
    public void setDefaultShowText(String showText) {
        this.showText = showText;
    }

    /**
     * 刷新商品属性
     *
     * @param json
     * @throws JSONException
     */
    public void refreshAttrs(JSONArray json) throws JSONException {
        itemData.clear();
        for (int i = 0; i < json.length(); i++) {
            SaleAttributeNameVo saleName = new SaleAttributeNameVo();
            JSONObject obj = (JSONObject) json.opt(i);
            saleName.setName(obj.getString("name"));
            List<SaleAttributeVo> list = new ArrayList<SaleAttributeVo>();
            net.sf.json.JSONArray array = new net.sf.json.JSONArray();
            array = net.sf.json.JSONArray.fromObject(obj.getString("saleVo"));
            for (int j = 0; j < array.size(); j++) {
                net.sf.json.JSONObject object = array.getJSONObject(j);
                SaleAttributeVo vo = new SaleAttributeVo();
                vo.setGoods(object.getString("goods"));
                vo.setValue(object.getString("value"));
                vo.setGoodsAndValId(object.getString("goodsAndValId"));
                if ("1".equals(object.getString("checkStatus"))) {
                    vo.setChecked(true);
                } else {
                    vo.setChecked(false);
                }
                list.add(vo);
            }
            saleName.setSaleVo(list);
            // 是否展开
            saleName.setNameIsChecked(false);
            itemData.add(saleName);
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    public void hide() {

    }

    @Override
    public void show() {

    }

    public class CancelOnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
//        dismiss();
        }

    }

    public boolean onKeyDown(Context context, int keyCode, KeyEvent event) {
        this.context = context;
        if (event.getAction() == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_BACK) {
//            dismiss();
        }
        return true;
    }

    public void showFilterPopup(View parent) {
//        if (!this.isShowing()) {
//            this.showAsDropDown(parent);
//        } else {
//            this.dismiss();
//        }
    }

    public void setOnMoreSelectListener(OnMoreSelectListener onMoreSelectListener) {
        mOnMoreSelectListener = onMoreSelectListener;
    }

    public interface OnMoreSelectListener {
        public void getValue(String distance, String showText);

    }
}
