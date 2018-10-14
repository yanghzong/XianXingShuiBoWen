package com.bw.zhoukao_moni1.view;

import android.content.Context;

/**
 * Created by 择木 on 2018/10/13.
 */

public interface IView<T> {
    void success(T t);

    void failed(Exception e);

    String getMoblie();

    String getPassword();

    void setMobile(String mobile);

    void setPassword(String password);

    void check(boolean isChecked);

    void show();

    void dissmiss();

    void gotoMain();

    Context getContext();
}
