package com.alpha.baiduduerdemo.nim;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.alpha.baiduduerdemo.R;
import com.alpha.baiduduerdemo.nim.model.DemoCache;
import com.alpha.baiduduerdemo.tools.SPUtils;
import com.netease.nimlib.sdk.NIMClient;
import com.netease.nimlib.sdk.RequestCallback;
import com.netease.nimlib.sdk.auth.AuthService;
import com.netease.nimlib.sdk.auth.LoginInfo;
import com.netease.nimlib.sdk.avchat.AVChatManager;

/**
 * Created by kenway on 17/12/27 11:10
 * Email : xiaokai090704@126.com
 */

public class LoginActivity extends Activity {

    private EditText et_account;
    private EditText et_pw;
    private Button btn_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initViews();
        initEvents();
    }



    private void initViews() {
        et_account= (EditText) findViewById(R.id.login_et_account);
        et_pw= (EditText) findViewById(R.id.login_et_pw);
        btn_login= (Button) findViewById(R.id.login_btn);

    }

    private void initEvents() {

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                doLogin();

                doLogin();
//                AVChatManager.getInstance().call2("",AVChatTypeEnum.)
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    public void doLogin() {
        LoginInfo info = new LoginInfo(et_account.getText().toString().toLowerCase(), et_pw.getText().toString()); // config...
        RequestCallback<LoginInfo> callback =
                new RequestCallback<LoginInfo>() {
                    @Override
                    public void onSuccess(LoginInfo param) {
                        SPUtils.put(LoginActivity.this, SPUtils.KEY_ACCOUNT, param.getAccount());
                        SPUtils.put(LoginActivity.this, SPUtils.KEY_APPKEY, param.getAppKey());
                        SPUtils.put(LoginActivity.this, SPUtils.KEY_TOKEN, param.getToken());

                        DemoCache.setAccount(et_account.getText().toString().toLowerCase());
//                        startActivity(new Intent(LoginActivity.this, MessageActivity.class));
                        finish();
                    }

                    @Override
                    public void onFailed(int code) {

                    }

                    @Override
                    public void onException(Throwable exception) {

                    }
                    // 可以在此保存LoginInfo到本地，下次启动APP做自动登录用
                };
        NIMClient.getService(AuthService.class).login(info)
                .setCallback(callback);
    }

    public static void actionStartClearStack(Context context) {
        Intent intent = new Intent(context, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }
}
