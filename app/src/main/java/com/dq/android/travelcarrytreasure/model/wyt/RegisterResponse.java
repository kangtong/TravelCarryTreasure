package com.dq.android.travelcarrytreasure.model.wyt;

import java.io.Serializable;

/**
 * 作者：Create on 2017/5/8 23:04  by dq_dana
 * 邮箱：dqdanavera@gmail.com
 * 描述：
 */
public class RegisterResponse implements Serializable {

  /**
   * ret_code : 0
   * result : 0
   */

  private String ret_code;
  private String result;

  public String getRet_code() {
    return ret_code;
  }

  public void setRet_code(String ret_code) {
    this.ret_code = ret_code;
  }

  public String getResult() {
    return result;
  }

  public void setResult(String result) {
    this.result = result;
  }
}
