package com.dq.android.travelcarrytreasure.model.common;

import java.io.Serializable;

/**
 * 作者：Create on 2017/4/26 21:47  by dq_dana
 * 邮箱：dqdanavera@gmail.com
 * 描述：获取公网地址的返回实体
 */
public class IPAddress implements Serializable {

  /**
   * cip : 123.66.223.241
   * cid : CN
   * cname : CHINA
   */

  private String cip;
  private String cid;
  private String cname;

  public String getCip() {
    return cip;
  }

  public void setCip(String cip) {
    this.cip = cip;
  }

  public String getCid() {
    return cid;
  }

  public void setCid(String cid) {
    this.cid = cid;
  }

  public String getCname() {
    return cname;
  }

  public void setCname(String cname) {
    this.cname = cname;
  }
}
