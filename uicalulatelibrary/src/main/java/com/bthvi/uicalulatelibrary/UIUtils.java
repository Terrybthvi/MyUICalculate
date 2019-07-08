package com.bthvi.uicalulatelibrary;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Copyright: Copyright (c) 2018
 * Project:  屏幕适配工具类
 * Author: bthvi
 * Date: 2019-06-21 09:27
 */
public class UIUtils {
    //屏幕基准分辨率值
    public static final float STANDARD_WIDTH=1080f;
    public static final float STANDARD_HEIGHT=1920f;

    //当前设备信息
    public static float displayMetricsWidth;
    public static float displayMetricsHeight;

    public static float stateBarHeight;  ;
    private static UIUtils instance ;


    //保存现在存活的activity
    private List<Activity> mActivities = new ArrayList<>();

    public static UIUtils getInstance(Context context){
        if(instance==null){
            instance=new UIUtils(context);
        }
        return instance;
    }

    public static UIUtils getInstance() {
        if (instance == null) {
            throw new RuntimeException("UiUtils应该先调用含有构造方法进行初始化");
        }
        return instance;
    }

    /**
     * 注册activity并适配
     *
     * @param activity
     */
    public void register(final Activity activity) {
        mActivities.add(activity);
        activity.findViewById(android.R.id.content).post(new Runnable() {
            @Override
            public void run() {
                apply(activity);
            }
        });
    }
    /**
     * 注册View并适配
     *
     * @param activity
     */
    public void register(final View layout) {
        UIAttrSupport.addUICalculateView(layout);
    }
    private void apply(Activity a) {
        List<View> listViews = UIAttrSupport.getLsitViews(a);
        if (listViews == null) return;
        for (View view : listViews) {

        }
    }

    private UIUtils(Context context) {
        //获取当前手机屏幕的信息
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics displayMetrics=new DisplayMetrics();
        if(displayMetricsWidth==0.0f || displayMetricsHeight==0.0f){
            windowManager.getDefaultDisplay().getMetrics(displayMetrics);
            int systemBarHeight=getSystemBarHeight(context);
            //判断当前手机横竖屏
            if(displayMetrics.widthPixels>displayMetrics.heightPixels){
                //TODO 注意这里要减去状态栏的高度 bthvi
                displayMetricsWidth=(float)(displayMetrics.heightPixels);
                displayMetricsHeight=(float)(displayMetrics.widthPixels-systemBarHeight);
            }else{
                displayMetricsWidth=(float)(displayMetrics.widthPixels);
                displayMetricsHeight=(float)(displayMetrics.heightPixels-systemBarHeight);
            }
            stateBarHeight = getSystemBarHeight(context);
        }
    }

    /**
     * 获取水平缩放比例
     * @return
     */
    public float getHorizontalScaleValue(){
        return ((float)(displayMetricsWidth))/STANDARD_WIDTH;
    }

    /**
     * 获取垂直缩放比例
     * @return
     */
    public float getVerticalScaleValue(){
        return ((float)(displayMetricsHeight))/(STANDARD_HEIGHT-stateBarHeight);
    }
    /**
     * 用于得到状态框的高度
     */
    public int getSystemBarHeight(Context context){
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        int h = context.getResources().getDimensionPixelSize(resourceId);
        if (h != -1) {
            return h;
        }
        return getHeight(context,"com.android.internal.R$dimen","system_bar_height",48);
    }

    /**
     * 通过反射得到状态栏的高度
     * @param context
     * @param dimeClass
     * @param system_bar_height
     * @param defaultValue
     * @return
     */
    private int getHeight(Context context, String dimeClass, String system_bar_height, int defaultValue) {

        try {
            Class<?> clz= Class.forName(dimeClass);
            Object object = clz.newInstance();
            Field field=clz.getField(system_bar_height);
            int id= Integer.parseInt(field.get(object).toString());
            return context.getResources().getDimensionPixelSize(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return defaultValue;
    }

    /**
     * 获取当前屏幕的控件的宽
     * @param width
     * @return
     */
    public int getWidth(int width) {
        return Math.round((float)width * displayMetricsWidth / STANDARD_WIDTH);
    }

    /**
     * 获取当前屏幕控件的高
     * @param height
     * @return
     */
    public int getHeight(int height) {
        return Math.round((float)height * displayMetricsHeight / (STANDARD_HEIGHT-stateBarHeight));
    }

}
