package com.terry.bthvi.uicalculate;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.bthvi.uicalulatelibrary.UIUtils;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RelativeLayout linearLayout = findViewById(R.id.middle_layout);
      //初始化
        UIUtils.getInstance(this);
      //根据布局适配
      UIUtils.getInstance().register(linearLayout);
        //根据Activity适配
        UIUtils.getInstance().register(this);
    }
}
