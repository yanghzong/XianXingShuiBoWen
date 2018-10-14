package com.bw.zhoukao_moni1.presenter;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import com.bw.zhoukao_moni1.bean.LoginBean;
import com.bw.zhoukao_moni1.inter.ICallBack;
import com.bw.zhoukao_moni1.model.LoginModel;
import com.bw.zhoukao_moni1.view.IView;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

/**
 * Created by 择木 on 2018/10/13.
 */

public class LoginPresenter {
    private IView iv;
    private LoginModel loginModel;
    public void attach(IView iv) {
        this.iv = iv;
        loginModel = new LoginModel();
    }

    public void detach() {
        if (iv != null) {
            iv = null;
        }
    }


    // 检验
    public void check() {
        if (TextUtils.isEmpty(iv.getMoblie()) || TextUtils.isEmpty(iv.getPassword())) {
            iv.check(false);
        } else {
            iv.check(true);
        }
    }


    public void isFirst() {
        SharedPreferences sp = iv.getContext().getSharedPreferences("config", Context.MODE_PRIVATE);
        String mobile = sp.getString("mobile", "");
        String password = sp.getString("password", "");
        // 不是第一次登录
        if (!TextUtils.isEmpty(mobile) && !TextUtils.isEmpty(password)) {
//            iv.gotoMain();
            iv.setMobile(mobile);
            iv.setPassword(password);
        }
    }


    public void login(String url) {
        iv.show();

        final String mobile = iv.getMoblie();
        final String password = iv.getPassword();

        url = url.concat("?mobile=").concat(mobile).concat("&password=").concat(password);
        Type type = new TypeToken<LoginBean>() {
        }.getType();
        loginModel.login(url, new ICallBack() {
            @Override
            public void onSuccess(Object obj) {
                iv.dissmiss();
                iv.success(obj);

                SharedPreferences sp = iv.getContext().getSharedPreferences("config", Context.MODE_PRIVATE);
               sp.edit().putString("mobile",mobile)
                       .putString("password",password)
                       .commit();

                      iv.gotoMain();
            }

            @Override
            public void onFailed(Exception e) {
                iv.dissmiss();
                iv.failed(e);
            }
        }, type);
    }
}
