package com.lang.zheren.test;

import android.widget.PopupWindow;

import java.lang.reflect.Method;

public class UIHelper {
    /**
     * Set whether this window is touch modal or if outside touches will be sent to
     * other windows behind it.
     */
    public static void setPopupWindowTouchModal(PopupWindow popupWindow,
                                                boolean touchModal) {
        if (null == popupWindow) {
            return;
        }
        Method method;
        try {

            method = PopupWindow.class.getDeclaredMethod("setTouchModal",
                    boolean.class);
            method.setAccessible(true);
            method.invoke(popupWindow, touchModal);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
