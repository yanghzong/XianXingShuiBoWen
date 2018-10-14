package com.bw.zhoukao_moni1;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.bw.zhoukao_moni1.bean.LoginBean;
import com.bw.zhoukao_moni1.presenter.LoginPresenter;
import com.bw.zhoukao_moni1.view.IView;

public class LoginActivity extends AppCompatActivity implements IView<LoginBean>, View.OnClickListener {

    private EditText edMobile;
    private EditText edPassword;
    private Button btnLogin;
    private LoginPresenter presenter;
    private ProgressDialog pdlog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();

        presenter = new LoginPresenter();
        presenter.attach(this);
        presenter.isFirst();
        pdlog = new ProgressDialog(this);
        pdlog.setMessage("正在登录，请稍候...");



    }

    private void initView() {
        edMobile = findViewById(R.id.ed_mobile);
        edPassword = findViewById(R.id.ed_password);
        btnLogin = findViewById(R.id.btn_login);
        btnLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_login:
                presenter.check();
                break;

        }

    }

    @Override
    public void success(LoginBean loginBean) {
        if (loginBean != null) {
            Toast.makeText(this, loginBean.getMsg(), Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void failed(Exception e) {
        Toast.makeText(this, "网络异常" + e.getMessage(), Toast.LENGTH_SHORT).show();

    }


    @Override
    public String getMoblie() {
        return edMobile.getText().toString().trim();
    }


    @Override
    public String getPassword() {
        return edPassword.getText().toString();
    }

    @Override
    public void setMobile(String mobile) {
        edMobile.setText(mobile);

    }

    @Override
    public void setPassword(String password) {
        edPassword.setText(password);

    }

    @Override
    public void check(boolean isChecked) {
        if (isChecked){
            presenter.login("https://www.zhaoapi.cn/user/login");
        }

    }

    @Override
    public void show() {
        pdlog.show();

    }

    @Override
    public void dissmiss() {
        pdlog.dismiss();

    }

    @Override
    public void gotoMain() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();

    }

    @Override
    public Context getContext() {
        return this;
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (presenter != null) {
            presenter.detach();
        }
    }
}
