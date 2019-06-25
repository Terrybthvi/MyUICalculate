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
        addSkinView(context, listViews);
        return listViews;
    }

    /**
     * 遍历所有子view
     *
     * @param
     * @param
     */
    private static void addSkinView(View context, List<View> listViews) {
        List<ViewAttr> viewAttrList = new ArrayList<ViewAttr>();
        if (context instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) context;
            for (int i = 0; i < viewGroup.getChildCount(); i++) {
                View view = viewGroup.getChildAt(i);
                ViewAttr attr = new ViewAttr(view, context.getWidth(), context.getHeight());
                attr.apply();
                addSkinView(view,listViews);
            }
        }

    }
}
