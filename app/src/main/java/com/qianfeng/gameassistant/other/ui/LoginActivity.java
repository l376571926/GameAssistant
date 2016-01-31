package com.qianfeng.gameassistant.other.ui;

import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.qianfeng.gameassistant.R;

/**
 * Created by Li Yiwei
 *
 * @date :  2016/1/16.
 */
public class LoginActivity extends BaseActivity
{
    private EditText etPhone, etPsw;
    private CheckBox cbAuto;
    private TextView tvForget;
    private Button btnLogin;

    @Override
    protected int getLayout()
    {
        return R.layout.act_login;
    }

    @Override
    protected void initView()
    {
        setTitleText("用户登录");
        showLeftImage();
        setRightText("注册");

        etPhone = (EditText) findViewById(R.id.activity_login_phone_et);
        etPsw = (EditText) findViewById(R.id.activity_login_psw_et);
        cbAuto = (CheckBox) findViewById(R.id.activity_login_next_time_auto_login_cb);
        tvForget = (TextView) findViewById(R.id.activity_login_forget_password_tv);
        btnLogin = (Button) findViewById(R.id.activity_login_login_btn);
    }

    @Override
    protected void initEvents()
    {

    }

    @Override
    protected void initData()
    {

    }
}
