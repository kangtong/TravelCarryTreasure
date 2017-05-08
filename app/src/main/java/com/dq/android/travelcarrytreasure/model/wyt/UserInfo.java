package com.dq.android.travelcarrytreasure.model.wyt;

import java.io.Serializable;

/**
 * 作者：Create on 2017/5/8 23:12  by dq_dana
 * 邮箱：dqdanavera@gmail.com
 * 描述：
 */
public class UserInfo implements Serializable {

  private String userName;
  private String userAvatar;
  private String userPwd;
  private String userLevel; // 等级
  private String userResidence; // 居住地

  public UserInfo() {
  }

  public UserInfo(String userName, String userAvatar, String userPwd, String userLevel, String userResidence) {
    this.userName = userName;
    this.userAvatar = userAvatar;
    this.userPwd = userPwd;
    this.userLevel = userLevel;
    this.userResidence = userResidence;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getUserAvatar() {
    return userAvatar;
  }

  public void setUserAvatar(String userAvatar) {
    this.userAvatar = userAvatar;
  }

  public String getUserPwd() {
    return userPwd;
  }

  public void setUserPwd(String userPwd) {
    this.userPwd = userPwd;
  }

  public String getUserLevel() {
    return userLevel;
  }

  public void setUserLevel(String userLevel) {
    this.userLevel = userLevel;
  }

  public String getUserResidence() {
    return userResidence;
  }

  public void setUserResidence(String userResidence) {
    this.userResidence = userResidence;
  }
}
