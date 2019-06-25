package com.terry.bthvi.uicalculate;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.bthvi.uicalulatelibrary.UIUtils;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
      UIUtils.getInstance(this);
      UIUtils.getInstance().register(this);
    }
}
