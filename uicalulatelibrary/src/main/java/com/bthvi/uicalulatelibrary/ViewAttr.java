package com.bthvi.uicalulatelibrary;

import android.view.View;

/**
 * Copyright: Copyright (c) 2018
 * Project:
 * Author: bthvi
 * Date: 2019-06-24 19:46
 */
public class ViewAttr {
    private static final String LINERLAYOUTTYPE = "0";
    private static final String RELATIVELAYOUTTYPE = "1";
    private static final String FTRAMELAYOUTTYPE = "2";
    private static final String VIEWGROUPTYPE = "3";
    private int width;
    private int height;
    private String parentType = "0";
    private View view;
    public ViewAttr(View view,int w,int h){
        this.view = view;
        this.width = w;
        this.height = h;
        System.out.println("====height"+height+"--------width"+width);
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
    public void apply(){
        switch (parentType){
            case LINERLAYOUTTYPE:
                ViewCalculateUtil.setViewLinearLayoutParam(view,width,height);
                break;
            case RELATIVELAYOUTTYPE:
//                ViewCalculateUtil.setViewRelativeLayoutParam(view,width,height);
                break;
            case FTRAMELAYOUTTYPE:
//                ViewCalculateUtil.setViewFrameLayoutParam(view,width,height);
                break;
            case VIEWGROUPTYPE:
                ViewCalculateUtil.setViewGroupLayoutParam(view,width,height);
                break;
        }
    }

}
