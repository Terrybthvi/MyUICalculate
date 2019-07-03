package com.bthvi.uicalulatelibrary;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Copyright: Copyright (c) 2018
 * Project:
 * Author: bthvi
 * Date: 2019-06-24 19:38
 */
class UIAttrSupport {
    /**
     * 根据传入的Activity获取屏幕所有的适配
     *   @param activity
     * @return
     */
    public static List<View> getLsitViews(Activity activity) {
        List<View> listViews = new ArrayList<>();
        ViewGroup context = (ViewGroup) activity.findViewById(android.R.id.content);
        addSkinView(context);
        return listViews;
    }

    /**
     * 遍历所有子view
     *
     * @param
     * @param
     */
    private static void addSkinView(View context) {
        if (context instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) context;
            for (int i = 0; i < viewGroup.getChildCount(); i++) {
                View view = viewGroup.getChildAt(i);
                System.out.println("if===="+view.getWidth() );
                ViewAttr attr = new ViewAttr(view);
                attr.setPadding_left(view.getPaddingLeft());
                attr.setPadding_top(view.getPaddingTop());
                attr.setPadding_right(view.getPaddingRight());
                attr.setPadding_bottom(view.getPaddingBottom());
                attr.apply();
                addSkinView(view);
            }
        }else {
            System.out.println("else=====");
        }

    }
}
