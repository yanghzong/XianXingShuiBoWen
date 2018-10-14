package com.bw.zhoukao_moni1.model;

import com.bw.zhoukao_moni1.inter.ICallBack;
import com.bw.zhoukao_moni1.utils.HttpUtils;

import java.lang.reflect.Type;

/**
 * Created by 择木 on 2018/10/13.
 */

public class LoginModel {
    public void login(String url, ICallBack callBack, Type type) {
        HttpUtils.getInstance().get(url, callBack, type);
    }
}
