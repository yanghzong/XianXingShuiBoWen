package com.bw.zhoukao_moni1;

import com.uuzuche.lib_zxing.activity.ZXingLibrary;

/**
 * Created by 择木 on 2018/10/13.
 */

public class Application extends android.app.Application {
    @Override
    public void onCreate() {
        super.onCreate();

        ZXingLibrary.initDisplayOpinion(this);
    }
}
