package com.lang.zheren.util;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.widget.DatePicker;
import android.widget.TimePicker;

import java.util.ArrayList;
import java.util.Calendar;

import com.lang.zheren.callbacak.Callback;

/**
 * 自定义各种对话框
 * Created by Administrator on 2017/8/29.
 */

public class DialogManager {
    private Context mContext = null;
    private int mSingleChoiceID = -1;
    private Dialog mDialog = null;
    private ProgressDialog mProgressDialog = null;
    private ArrayList<Integer> MultiChoiceID = null;
    private ArrayList<Object> returnValues = null;
    private int mYear = 0;
    private int mMonth = 0;
    private int mDay = 0;
    private int mHour = 0;
    private int mMinute = 0;

    public DialogManager(Context context) {
        this.mContext = context;
    }


    /**
     * 简单确定对话框
     *
     * @param title
     * @param msg
     * @param btnPositiveName
     * @param isCancelable
     * @param callback
     * @return
     */
    public Dialog showConfirmDialog(
            String title,
            String msg,
            String btnPositiveName,
            boolean isCancelable,
            final Callback callback) {
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setTitle(title);
        builder.setMessage(msg);
        builder.setCancelable(isCancelable);
        builder.setPositiveButton(btnPositiveName, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                callback.onPositiveButton();
                mDialog.dismiss();
            }
        });
        mDialog = builder.create();
        mDialog.show();

        return mDialog;
    }


    /**
     * 简单信息提示框
     *
     * @param title
     * @param msg
     * @param btnPositiveName
     * @param btnNegativeName
     * @param isCancelable
     * @param callback
     * @return
     * @
     */
    public Dialog showMsgDialog(
            String title,
            String msg,
            String btnPositiveName,
            String btnNegativeName,
            boolean isCancelable,
            final Callback callback) {
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setTitle(title);
        builder.setMessage(msg);
        builder.setCancelable(isCancelable);
        builder.setPositiveButton(btnPositiveName, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                callback.onPositiveButton();
                mDialog.dismiss();
            }
        });
        // 取消
        builder.setNegativeButton(btnNegativeName, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                callback.onNegativeButton();
                mDialog.dismiss();
            }
        });
        mDialog = builder.create();
        mDialog.show();

        return mDialog;

    }


    /**
     * 有三个按钮的对话框
     *
     * @param title
     * @param msg
     * @param btnPositiveName
     * @param btnNeutralName
     * @param btnNegativeName
     * @param isCancelable
     * @param callback
     * @return
     * @
     */
    public Dialog showThreeBtnDialog(
            String title,
            String msg,
            String btnPositiveName,
            String btnNeutralName,
            String btnNegativeName,
            boolean isCancelable,
            final Callback callback) {
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setTitle(title);
        builder.setMessage(msg);
        builder.setCancelable(isCancelable);
        // 左侧按钮
        builder.setPositiveButton(btnPositiveName, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                callback.onPositiveButton();
                mDialog.dismiss();
            }
        });
        // 中间按钮
        builder.setNeutralButton(btnNeutralName, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                callback.onNeutralButton();
                mDialog.dismiss();
            }
        });
        // 右侧按钮
        builder.setNegativeButton(btnNegativeName, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                callback.onNegativeButton();
                mDialog.dismiss();
            }
        });
        mDialog = builder.create();
        mDialog.show();

        return mDialog;
    }


    /**
     * 单项选择框
     *
     * @param title
     * @param mItems
     * @param btnPositiveName
     * @param btnNegativeName
     * @param isCancelable
     * @param callback
     * @return
     * @
     */
    public Dialog showSingleChoiceDialog(
            String title,
            final String[] mItems,
            String btnPositiveName,
            String btnNegativeName,
            boolean isCancelable,
            final Callback callback) {
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setTitle(title);
        builder.setCancelable(isCancelable);
        builder.setSingleChoiceItems(mItems, 0, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                mSingleChoiceID = whichButton;
            }
        });
        builder.setPositiveButton(btnPositiveName, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                returnValues = new ArrayList<Object>();
                returnValues.add(mItems[mSingleChoiceID]);
                callback.onGetReturnValue(returnValues);
                mDialog.dismiss();
            }
        });
        builder.setNegativeButton(btnNegativeName, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                callback.onNegativeButton();
                mDialog.dismiss();
            }
        });
        mDialog = builder.create();
        mDialog.show();

        return mDialog;
    }


    /**
     * 列表选择框
     *
     * @param title
     * @param mItems
     * @param isCancelable
     * @param callback
     * @return
     * @
     */
    public Dialog showListDialog(
            String title,
            final String[] mItems,
            boolean isCancelable,
            final Callback callback) {
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setTitle(title);
        builder.setCancelable(isCancelable);
        builder.setItems(mItems, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                returnValues = new ArrayList<Object>();
                returnValues.add(mItems[which]);
                callback.onGetReturnValue(returnValues);
                mDialog.dismiss();
            }
        });
        mDialog = builder.create();
        mDialog.show();

        return mDialog;
    }


    /**
     * 多选对话框
     *
     * @param title
     * @param mItems
     * @param btnPositiveName
     * @param btnNegativeName
     * @param isCancelable
     * @param callback
     * @return
     * @
     */
    public Dialog showMultiChoiceDialog(
            String title,
            final String[] mItems,
            String btnPositiveName,
            String btnNegativeName,
            boolean isCancelable,
            final Callback callback) {
        MultiChoiceID = new ArrayList<Integer>();
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setTitle(title);
        builder.setCancelable(isCancelable);
        builder.setMultiChoiceItems(mItems,
                new boolean[mItems.length],
                new DialogInterface.OnMultiChoiceClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton, boolean isChecked) {
                        if (isChecked) {
                            MultiChoiceID.add(whichButton);
                        } else {
                               /*ArrayList 的 remove() 函数有两个重载版本，一个参数为要删除的位置，一个参数为要删除的对象
                                * 此处若不提示系统调用的参数为对象时，有有时调用另一个版本，可能会导致越界错误，要注意*/
                            MultiChoiceID.remove((Integer) whichButton);
                        }

                    }
                });
        builder.setPositiveButton(btnPositiveName, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                returnValues = new ArrayList<Object>();
                int size = MultiChoiceID.size();
                for (int i = 0; i < size; i++) {
                    returnValues.add(mItems[MultiChoiceID.get(i)]);
                }
                callback.onGetReturnValue(returnValues);

                mDialog.dismiss();
            }
        });
        builder.setNegativeButton(btnNegativeName, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                callback.onNegativeButton();
                mDialog.dismiss();
            }
        });
        mDialog = builder.create();
        mDialog.show();

        return mDialog;
    }


    /**
     * 显示进度条对话框
     *
     * @param title
     * @param msg
     * @param isCancelable
     * @param callback
     * @return
     * @
     */
    public ProgressDialog showProgressDialog(
            String title,
            String msg,
            String btnNegativeName,
            boolean isCancelable,
            final Callback callback) {
        mProgressDialog = new ProgressDialog(mContext);
        mProgressDialog.setTitle(title);
        mProgressDialog.setMessage(msg);
        mProgressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        mProgressDialog.setCancelable(isCancelable);
        mProgressDialog.setButton(ProgressDialog.BUTTON_NEGATIVE, btnNegativeName, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                callback.onNegativeButton();
                mProgressDialog.dismiss();
            }
        });
        mProgressDialog.show();

        return mProgressDialog;
    }


    /**
     * 日期选择提示框
     *
     * @param title
     * @param btnPositiveName
     * @param btnNegativeName
     * @param isCancelable
     * @param callback
     * @return
     * @
     */
    public DatePickerDialog showDatePickerDialog(
            String title,
            String btnPositiveName,
            String btnNegativeName,
            boolean isCancelable,
            final Callback callback) {
        Calendar mCalendar = Calendar.getInstance();
        final DatePickerDialog mDatePickerDialog = new DatePickerDialog(
                mContext,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        returnValues = new ArrayList<Object>();
                        returnValues.add(format(year));
                        returnValues.add(format(monthOfYear + 1));
                        returnValues.add(format(dayOfMonth));
                        callback.onGetReturnValue(returnValues);
                    }
                },
                mCalendar.get(mCalendar.YEAR),
                mCalendar.get(mCalendar.MONTH),
                mCalendar.get(mCalendar.DAY_OF_MONTH));
        mDatePickerDialog.setTitle(title);
        mDatePickerDialog.setCancelable(isCancelable);
        mDatePickerDialog.show();

        return mDatePickerDialog;
    }


    /**
     * 时间选择对话框
     *
     * @param title
     * @param is24Hour
     * @param btnPositiveName
     * @param btnNegativeName
     * @param isCancelable
     * @param callback
     * @return
     * @
     */
    public TimePickerDialog showTimerPickerDialog(
            String title,
            int paramHour,
            int prarmMinute,
            boolean is24Hour,
            String btnPositiveName,
            String btnNegativeName,
            boolean isCancelable,
            final Callback callback) {
        final TimePickerDialog mTimerPickerDialog = new TimePickerDialog(
                mContext,
                new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        returnValues = new ArrayList<Object>();
                        returnValues.add(format(hourOfDay));
                        returnValues.add(format(minute));
                        callback.onGetReturnValue(returnValues);
                    }
                }, paramHour, prarmMinute, is24Hour);
        mTimerPickerDialog.setTitle(title);
        mTimerPickerDialog.setCancelable(isCancelable);
        mTimerPickerDialog.show();

        return mTimerPickerDialog;
    }


    /**
     * 转换日期时间格式
     *
     * @param original
     * @return
     */
    private String format(int original) {
        String str = "" + original;
        if (1 == str.length()) {
            str = "0" + str;
        }

        return str;
    }
}
