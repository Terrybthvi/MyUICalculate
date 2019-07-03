package com.bthvi.uicalulatelibrary;

import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Copyright: Copyright (c) 2018
 * Project:
 * Author: bthvi
 * Date: 2019-06-24 19:46
 */
public class ViewAttr {
    /**线性布局**/
    private static final String LINERLAYOUTTYPE = "0";
    /**相对布局**/
    private static final String RELATIVELAYOUTTYPE = "1";
    /**帧布局**/
    private static final String FTRAMELAYOUTTYPE = "2";
    /**ViewGroup**/
    private static final String VIEWGROUPTYPE = "3";
    //View
    private View view;
    //宽
    private int width;
    //高
    private int height;
    //TextView
    private int textSize = 0;
    //padding
    private int padding_left;
    private int padding_top;
    private int padding_right;
    private int padding_bottom;
    //margin
    private int margin_left;
    private int margin_top;
    private int margin_right;
    private int margin_bottom;

    private String parentType = LINERLAYOUTTYPE;

    public ViewAttr(){
    }

    public ViewAttr(View view,int w,int h){
        this.view = view;
        this.width = w;
        this.height = h;
        String paramString = view.getLayoutParams().toString();
        if (paramString.contains("android.widget.FrameLayout")){
            parentType = FTRAMELAYOUTTYPE;
        }else  if (paramString.contains("android.widget.LinearLayout")){
            parentType = LINERLAYOUTTYPE;
        }else  if (paramString.contains("android.widget.RelativeLayout")){
            parentType = RELATIVELAYOUTTYPE;
        }else  if (paramString.contains("android.view.ViewGroup")){
            parentType = VIEWGROUPTYPE;
        }
    }

    public ViewAttr(View view) {
        this.view = view;
        String paramString = view.getLayoutParams().toString();
        String viewStr = view.getClass().getName();
        if (viewStr.contains("TextView")){
           TextView textview = (TextView)view;
           textSize = (int) textview.getTextSize();
        }
        this.width = view.getLayoutParams().width;
        this.height = view.getLayoutParams().height;
        if (paramString.contains("android.widget.FrameLayout")){
            parentType = FTRAMELAYOUTTYPE;
        }else  if (paramString.contains("android.widget.LinearLayout")){
            parentType = LINERLAYOUTTYPE;
        }else  if (paramString.contains("android.widget.RelativeLayout")){
            parentType = RELATIVELAYOUTTYPE;
        }else  if (paramString.contains("android.view.ViewGroup")){
            parentType = VIEWGROUPTYPE;
        }
        getMargins();
    }

    private void getMargins() {
        switch (parentType){
            case LINERLAYOUTTYPE:
                LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) view.getLayoutParams();
                this.margin_left = layoutParams.leftMargin;
                this.margin_right = layoutParams.rightMargin;
                this.margin_top = layoutParams.topMargin;
                this.margin_bottom = layoutParams.bottomMargin;
                break;
            case RELATIVELAYOUTTYPE:
                RelativeLayout.LayoutParams layoutParams2 = (RelativeLayout.LayoutParams) view.getLayoutParams();
                this.margin_left = layoutParams2.leftMargin;
                this.margin_right = layoutParams2.rightMargin;
                this.margin_top = layoutParams2.topMargin;
                this.margin_bottom = layoutParams2.bottomMargin;
                break;
            case FTRAMELAYOUTTYPE:
                FrameLayout.LayoutParams layoutParams3 = (FrameLayout.LayoutParams) view.getLayoutParams();
                this.margin_left = layoutParams3.leftMargin;
                this.margin_right = layoutParams3.rightMargin;
                this.margin_top = layoutParams3.topMargin;
                this.margin_bottom = layoutParams3.bottomMargin;
                break;
            case VIEWGROUPTYPE:
                ViewGroup.MarginLayoutParams layoutParams4 = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
                this.margin_left = layoutParams4.leftMargin;
                this.margin_right = layoutParams4.rightMargin;
                this.margin_top = layoutParams4.topMargin;
                this.margin_bottom = layoutParams4.bottomMargin;
                break;
        }
    }

    public void apply(){
        switch (parentType){
            case LINERLAYOUTTYPE:
                ViewCalculateUtil.setViewLinearLayoutParam(view,width,height,margin_top,margin_bottom,margin_left,margin_right);
                ViewCalculateUtil.setViewPadding(view,padding_top,padding_bottom,padding_left,padding_right);
                if (view.getClass().getName().contains("TextView") && textSize > 0){
                    ViewCalculateUtil.setTextSize((TextView) view,textSize);
                }
                break;
            case RELATIVELAYOUTTYPE:
                ViewCalculateUtil.setViewRelativeLayoutParam(view,width,height,margin_top,margin_bottom,margin_left,margin_right);
                ViewCalculateUtil.setViewPadding(view,padding_top,padding_bottom,padding_left,padding_right);
                if (view.getClass().getName().contains("TextView") && textSize > 0){
                    ViewCalculateUtil.setTextSize((TextView) view,textSize);
                }
                break;
            case FTRAMELAYOUTTYPE:
                ViewCalculateUtil.setViewFrameLayoutParam(view,width,height,margin_top,margin_bottom,margin_left,margin_right);
                ViewCalculateUtil.setViewPadding(view,padding_top,padding_bottom,padding_left,padding_right);
                if (view.getClass().getName().contains("TextView") && textSize > 0){
                    ViewCalculateUtil.setTextSize((TextView) view,textSize);
                }
                break;
            case VIEWGROUPTYPE:
                ViewCalculateUtil.setViewGroupLayoutParam(view,width,height);
                ViewCalculateUtil.setViewPadding(view,padding_top,padding_bottom,padding_left,padding_right);
                if (view.getClass().getName().contains("TextView") && textSize > 0){
                    ViewCalculateUtil.setTextSize((TextView) view,textSize);
                }
                break;
        }
    }

    /**
     * 设置View的Margin
     * @param left
     * @param right
     * @param top
     * @param bottom
     */
    public void setMargin(int left,int right ,int top ,int bottom){
        this.margin_left = left;
        this.margin_right = right;
        this.margin_top = top;
        this.margin_bottom = bottom;
    }

    /**
     * 设置View的padding
     * @param left
     * @param right
     * @param top
     * @param bottom
     */
    public void setPadding(int left,int right ,int top ,int bottom){
        this.padding_left = left;
        this.padding_right = right;
        this.padding_top = top;
        this.padding_bottom = bottom;
    }
    public int getHeight() {
        return height;
    }

    public int getMargin_left() {
        return margin_left;
    }

    public int getPadding_bottom() {
        return padding_bottom;
    }

    public int getPadding_left() {
        return padding_left;
    }

    public int getPadding_top() {
        return padding_top;
    }

    public int getWidth() {
        return width;
    }

    public int getPadding_right() {
        return padding_right;
    }

    public int getMargin_bottom() {
        return margin_bottom;
    }

    public int getMargin_right() {
        return margin_right;
    }

    public int getMargin_top() {
        return margin_top;
    }

    public View getView() {
        return view;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setMargin_bottom(int margin_bottom) {
        this.margin_bottom = margin_bottom;
    }

    public void setMargin_left(int margin_left) {
        this.margin_left = margin_left;
    }

    public void setMargin_right(int margin_right) {
        this.margin_right = margin_right;
    }

    public void setMargin_top(int margin_top) {
        this.margin_top = margin_top;
    }

    public void setPadding_bottom(int padding_bottom) {
        this.padding_bottom = padding_bottom;
    }

    public void setPadding_left(int padding_left) {
        this.padding_left = padding_left;
    }

    public void setPadding_right(int padding_right) {
        this.padding_right = padding_right;
    }

    public void setPadding_top(int padding_top) {
        this.padding_top = padding_top;
    }

    public void setParentType(String parentType) {
        this.parentType = parentType;
    }

    public void setView(View view) {
        this.view = view;
        String paramString = view.getLayoutParams().toString();
        if (paramString.contains("android.widget.FrameLayout")){
            parentType = FTRAMELAYOUTTYPE;
        }else  if (paramString.contains("android.widget.LinearLayout")){
            parentType = LINERLAYOUTTYPE;
        }else  if (paramString.contains("android.widget.RelativeLayout")){
            parentType = RELATIVELAYOUTTYPE;
        }else  if (paramString.contains("android.view.ViewGroup")){
            parentType = VIEWGROUPTYPE;
        }
    }

    public void setWidth(int width) {
        this.width = width;
    }
}
