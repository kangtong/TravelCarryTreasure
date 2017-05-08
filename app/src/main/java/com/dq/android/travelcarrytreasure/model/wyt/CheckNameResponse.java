package com.dq.android.travelcarrytreasure.model.wyt;

import java.io.Serializable;

/**
 * 作者：Create on 2017/5/8 21:56  by dq_dana
 * 邮箱：dqdanavera@gmail.com
 * 描述：
 */
public class CheckNameResponse implements Serializable {

  /**
   * ret_code : -1
   * return_user_name : -1
   * code : -1
   */

  private String ret_code; // 0, 没用过。 -1  用过， 2 失败
  private String return_user_name;
  private String code;

  public String getRet_code() {
    return ret_code;
  }

  public void setRet_code(String ret_code) {
    this.ret_code = ret_code;
  }

  public String getReturn_user_name() {
    return return_user_name;
  }

  public void setReturn_user_name(String return_user_name) {
    this.return_user_name = return_user_name;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }
}
