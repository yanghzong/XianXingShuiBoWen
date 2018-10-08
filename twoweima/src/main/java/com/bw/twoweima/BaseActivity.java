package com.bw.twoweima;

import android.app.Application;
import android.os.Bundle;

/**
 * Created by 择木 on 2018/10/8.
 */

public class BaseActivity  extends Application{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
    }
}
