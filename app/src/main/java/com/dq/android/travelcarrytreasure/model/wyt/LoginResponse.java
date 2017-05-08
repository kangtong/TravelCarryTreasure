package com.dq.android.travelcarrytreasure.model.wyt;

import java.io.Serializable;

/**
 * 作者：Create on 2017/5/8 22:47  by dq_dana
 * 邮箱：dqdanavera@gmail.com
 * 描述：
 */
public class LoginResponse implements Serializable {

  /**
   * ret_code : 0
   * user_id : 60
   * user_name : 123456
   * user_icon : http://youmehe.wang/imgs/测试账号_1494070422.png
   * user_sexual :
   * user_birthday :
   * user_type : 0
   * user_area :
   */

  private String ret_code;
  private String user_id;
  private String user_name; // 用户名
  private String user_icon; // 头像
  private String user_sexual; // 用来存储 level
  private String user_birthday;
  private String user_type;
  private String user_area; // 居住地

  public String getRet_code() {
    return ret_code;
  }

  public void setRet_code(String ret_code) {
    this.ret_code = ret_code;
  }

  public String getUser_id() {
    return user_id;
  }

  public void setUser_id(String user_id) {
    this.user_id = user_id;
  }

  public String getUser_name() {
    return user_name;
  }

  public void setUser_name(String user_name) {
    this.user_name = user_name;
  }

  public String getUser_icon() {
    return user_icon;
  }

  public void setUser_icon(String user_icon) {
    this.user_icon = user_icon;
  }

  public String getUser_sexual() {
    return user_sexual;
  }

  public void setUser_sexual(String user_sexual) {
    this.user_sexual = user_sexual;
  }

  public String getUser_birthday() {
    return user_birthday;
  }

  public void setUser_birthday(String user_birthday) {
    this.user_birthday = user_birthday;
  }

  public String getUser_type() {
    return user_type;
  }

  public void setUser_type(String user_type) {
    this.user_type = user_type;
  }

  public String getUser_area() {
    return user_area;
  }

  public void setUser_area(String user_area) {
    this.user_area = user_area;
  }
}
