package com.dq.android.travelcarrytreasure.ui.mine;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.alibaba.fastjson.JSON;
import com.dq.android.travelcarrytreasure.R;
import com.dq.android.travelcarrytreasure.base.BaseActivity;
import com.dq.android.travelcarrytreasure.model.wyt.CheckNameResponse;
import com.dq.android.travelcarrytreasure.model.wyt.LoginResponse;
import com.dq.android.travelcarrytreasure.model.wyt.UserInfo;
import com.dq.android.travelcarrytreasure.service.wyt.CheckNameCallBack;
import com.dq.android.travelcarrytreasure.service.wyt.LoginCallBack;
import com.dq.android.travelcarrytreasure.ui.main.MainActivity;
import com.dq.android.travelcarrytreasure.util.SPUtils;
import com.dq.android.travelcarrytreasure.widget.CustomProgressDialog;
import com.zhy.http.okhttp.OkHttpUtils;
import okhttp3.Call;
import support.ui.utilities.ToastUtils;

/**
 * 作者：Create on 2017/5/8 18:08  by dq_dana
 * 邮箱：dqdanavera@gmail.com
 * 描述：
 */
public class LoginActivity extends BaseActivity implements View.OnClickListener {

  private static final String TAG = LoginActivity.class.getSimpleName();
  private static final String KEY_TYPE = "type";
  public static final String KEY_LOGIN = "login";
  public static final String KEY_REGISTER = "register";
  public static final String KEY_USER_INFO = "user_info";

  /* 通用 */
  private EditText mEdtUserName;
  private EditText mEdtPassword;

  /* 登录 */
  private Button mBtnLogin;
  private TextView mTvForgetPwd;
  private TextView mTvLoginWithoutPwd;

  /* 注册 */
  private RelativeLayout mRlPasswordAgain;
  private EditText mEdtPasswordAgain;
  private Button mBtnRegister;

  /* 进度条 */
  private Dialog mDialog;

  public static void start(Context context, String type) {
    Intent starter = new Intent(context, LoginActivity.class);
    starter.putExtra(KEY_TYPE, type);
    context.startActivity(starter);
  }

  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_login);
    initView();
  }

  private void initView() {
    mEdtUserName = (EditText) findViewById(R.id.edt_user_name);
    mEdtPassword = (EditText) findViewById(R.id.edt_password);
    mEdtPasswordAgain = (EditText) findViewById(R.id.edt_password_again);

    mBtnLogin = (Button) findViewById(R.id.btn_login);
    mBtnRegister = (Button) findViewById(R.id.btn_register);

    mTvForgetPwd = (TextView) findViewById(R.id.tv_forget_pwd);
    mTvLoginWithoutPwd = (TextView) findViewById(R.id.tv_login_without_pwd);
    // TODO: 2017/5/9 dengqi: 这里,忘记密码功能+短信验证码登录 暂时先隐藏
    mTvForgetPwd.setVisibility(View.GONE);
    mTvLoginWithoutPwd.setVisibility(View.GONE);

    mRlPasswordAgain = (RelativeLayout) findViewById(R.id.rl_password_again);

    /* 根据 type 控制显示 */
    String type = getIntent().getStringExtra(KEY_TYPE);
    if (type.equals(KEY_LOGIN)) {
      showLoginView();
    } else {
      showRegisterView();
    }

    /* 逻辑处理 */
    mBtnLogin.setOnClickListener(this);
    mBtnRegister.setOnClickListener(this);
    mTvForgetPwd.setOnClickListener(this);
    mTvLoginWithoutPwd.setOnClickListener(this);
  }

  private void showLoginView() {
    mRlPasswordAgain.setVisibility(View.GONE);
    mBtnRegister.setVisibility(View.GONE);
  }

  private void showRegisterView() {
    mBtnLogin.setVisibility(View.GONE);
    mTvForgetPwd.setVisibility(View.GONE);
    mTvLoginWithoutPwd.setVisibility(View.GONE);
  }

  @Override public void onClick(View v) {
    switch (v.getId()) {
      case R.id.btn_login:
        onPreLogining();
        break;
      case R.id.btn_register:
        onPreRegistering();
        break;
      case R.id.tv_forget_pwd:
      case R.id.tv_login_without_pwd:
        ToastUtils.toast("此功能正在开发，敬请期待~");
        break;
      default:
        break;
    }
  }

  private void onPreLogining() {
    if (mEdtUserName.getText().toString().isEmpty() || mEdtPassword.getText().toString().isEmpty()) {
      ToastUtils.toast("用户名 or 密码 不能为空，请检查！");
      return;
    }
    if (mEdtPassword.getText().toString().length() < 6) {
      ToastUtils.toast("密码不能少于6位！");
      return;
    }
    onLogining();
  }

  private void onPreRegistering() {
    if (onCheckPwd()) {
      onCheckName(mEdtUserName.getText().toString());
    }
  }

  private boolean onCheckPwd() {
    if (mEdtUserName.getText().toString().isEmpty() || mEdtPassword.getText().toString().isEmpty() || mEdtPasswordAgain.getText().toString()
        .isEmpty()) {
      ToastUtils.toast("用户名 or 密码 为空，请检查！");
      return false;
    }
    if (mEdtPassword.getText().toString().length() < 6) {
      ToastUtils.toast("密码不能少于6位！");
      return false;
    }
    if (mEdtPassword.getText().toString().equals(mEdtPasswordAgain.getText().toString())) {
      return true;
    } else {
      ToastUtils.toast("两次密码不一致，请检查！");
      return false;
    }
  }

  private void onCheckName(String userName) {
    mDialog = CustomProgressDialog.createLoadingDialog(LoginActivity.this, "请稍等...");
    mDialog.setCancelable(false);//允许返回
    mDialog.show();//显示

    String url = "http://youmehe.wang/universityWork/check_name.php";
    OkHttpUtils
        .post()
        .url(url)
        .addParams("user_name", userName)
        .build()
        .execute(new CheckNameCallBack() {
          @Override public void onError(Call call, Exception e, int id) {
            Log.d(TAG, "onError: " + "检测用户名是否已被使用,网络错误");
            Log.d(TAG, "onError: " + e.toString());
            mDialog.dismiss();
            ToastUtils.toast("网络异常，请稍后重试~");
          }

          @Override public void onResponse(CheckNameResponse response, int id) {
            if (response.getRet_code().equals("0")) { // 成功
              Log.d(TAG, "onResponse: " + "检测用户名是否已被使用,api返回数据成功~");
              onRegistering();
            } else {
              Log.d(TAG, "onResponse: " + "检测用户名是否已被使用,api返回数据失败!!!");
              mDialog.dismiss();
              ToastUtils.toast("用户名已被使用，请更改~");
            }
          }
        });
  }

  private void onLogining() {
    mDialog = CustomProgressDialog.createLoadingDialog(LoginActivity.this, "请稍等...");
    mDialog.setCancelable(false);//允许返回
    mDialog.show();//显示
    // 开始请求 网络
    String userName = mEdtUserName.getText().toString();
    String pwd = mEdtPassword.getText().toString();
    String url = "http://youmehe.wang/universityWork/user_login.php";
    OkHttpUtils
        .post()
        .url(url)
        .addParams("user_name", userName)
        .addParams("user_pwd", pwd)
        .build()
        .execute(new LoginCallBack() {
          @Override public void onError(Call call, Exception e, int id) {
            Log.d(TAG, "onError: " + "用户登录,网络错误");
            Log.d(TAG, "onError: " + e.toString());
            mDialog.dismiss();
            ToastUtils.toast("网络异常，请稍后重试~");
          }

          @Override public void onResponse(LoginResponse response, int id) {
            if (response.getRet_code().equals("0")) { // 成功
              Log.d(TAG, "onResponse: " + "用户登录,api返回数据成功~");
              ToastUtils.toast("登录成功~");
              // 保存至SP
              String discover = JSON.toJSONString(new UserInfo(userName, "", pwd, "1", ""));
              SPUtils.setString(LoginActivity.this, KEY_USER_INFO, discover);
              // 重启app
              MainActivity.start(LoginActivity.this, true);
            } else {
              Log.d(TAG, "onResponse: " + "用户登录,api返回数据失败!!!");
              mDialog.dismiss();
              ToastUtils.toast("用户名 or 密码 错误，请重试~");
            }
          }
        });
  }

  private void onRegistering() {
    String userName = mEdtUserName.getText().toString();
    String pwd = mEdtPassword.getText().toString();
    // 开始请求 网络
    String url = "http://youmehe.wang/universityWork/user_sign_up.php";
    OkHttpUtils
        .post()
        .url(url)
        .addParams("user_name", userName)
        .addParams("user_pwd", pwd)
        .addParams("user_type", "0")
        .build()
        .execute(new LoginCallBack() {
          @Override public void onError(Call call, Exception e, int id) {
            Log.d(TAG, "onError: " + "新用户注册,网络错误");
            Log.d(TAG, "onError: " + e.toString());
            mDialog.dismiss();
            ToastUtils.toast("网络异常，请稍后重试~");
          }

          @Override public void onResponse(LoginResponse response, int id) {
            if (response.getRet_code().equals("0")) { // 成功
              Log.d(TAG, "onResponse: " + "新用户注册,api返回数据成功~");
              ToastUtils.toast("新用户注册成功~");
              // 保存至SP
              String discover = JSON.toJSONString(new UserInfo(userName, "", pwd, "1", ""));
              SPUtils.setString(LoginActivity.this, KEY_USER_INFO, discover);
              // 重启app
              MainActivity.start(LoginActivity.this, true);
            } else {
              Log.d(TAG, "onResponse: " + "新用户注册,api返回数据失败!!!");
              mDialog.dismiss();
              ToastUtils.toast("参数不全，请重试~");
            }
          }
        });
  }
}
