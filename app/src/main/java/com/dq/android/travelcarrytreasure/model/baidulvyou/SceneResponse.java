package com.dq.android.travelcarrytreasure.model.baidulvyou;

import java.io.Serializable;
import java.util.List;

/**
 * 作者：Create on 2017/5/6 12:00  by dq_dana
 * 邮箱：dqdanavera@gmail.com
 * 描述：全部景点的列表
 */
public class SceneResponse implements Serializable {

  /**
   * errno : 0
   * msg :
   * data : {}
   */

  private int errno;
  private String msg;
  private DataBean data;

  public int getErrno() {
    return errno;
  }

  public void setErrno(int errno) {
    this.errno = errno;
  }

  public String getMsg() {
    return msg;
  }

  public void setMsg(String msg) {
    this.msg = msg;
  }

  public DataBean getData() {
    return data;
  }

  public void setData(DataBean data) {
    this.data = data;
  }

  public static class DataBean {
    /**
     * pn : 0
     * rn : 10
     * nn : 0
     * total : 329
     * scene_list : []
     * favorite_list : null
     */

    private List<SceneListBean> scene_list;

    public List<SceneListBean> getScene_list() {
      return scene_list;
    }

    public void setScene_list(List<SceneListBean> scene_list) {
      this.scene_list = scene_list;
    }

    public static class SceneListBean {
      /**
       * sid : b7bcab240abb6cf9c05fcce3
       * pic_url : http://h.hiphotos.baidu.com/lvpics/wh%3D320%2C480/sign=4ed8fa68b051f819f1700b49e88466d9/6609c93d70cf3bc7e8f7b1afd100baa1cc112af9.jpg
       * distance : 0
       * ota : 1
       * price : 28
       * origin_price : 40
       * tags : [7001,7003]
       * new_tags : [7001,7003]
       * tag_array : [7001,7003]
       * tag_detail : ["人气","当季热门"]
       * desc : 西安最有名的景点，兵马俑十分精美。实在是很震撼，虽然只开发了一部分。
       * avg_remark_score : 4.5
       * remark : {"remark_id":"1673851fcf9b0e22e1ff31b4","uid":"c01ba8dc5aef0c5e5cd19be2","uname":"★月下雪影★","avatar_pic":"http://hiphotos.baidu.com/lvpics/pic/item/cefc1e178a82b9017d5a3e31778da9773912ef39.jpg","words":"如果是自己看的话完全可能看不懂，可以请导游或者租导游讲解机，这样才能领略兵马俑的恢宏气势。"}
       * sname : 秦始皇兵马俑
       * surl : qinshihuangbingmayong
       * scene_layer : 6
       * parent_sid : 9bb8ee381df41344144463f5
       * map_x : 109.29233985165
       * map_y : 34.394447117207
       * abstract : 西安最有名的景点，兵马俑十分精美。实在是很震撼，虽然只开发了一部分。
       * going_count : 1096
       * remark_count : 4510
       */

      private String sid; // 景点 sid
      private String pic_url; // 图片
      private int distance; // 距离
      private int ota;
      private String price; // 原价
      private String origin_price; // 现价
      private String desc; // 描述
      private float avg_remark_score; // 评分级别
      private String sname; // 标题
      private String surl; // 纯拼音
      private int scene_layer;
      private String parent_sid;
      private String map_x; // 坐标
      private String map_y;// 坐标
      private String remark_count;

      public String getSid() {
        return sid;
      }

      public void setSid(String sid) {
        this.sid = sid;
      }

      public String getPic_url() {
        return pic_url;
      }

      public void setPic_url(String pic_url) {
        this.pic_url = pic_url;
      }

      public int getDistance() {
        return distance;
      }

      public void setDistance(int distance) {
        this.distance = distance;
      }

      public int getOta() {
        return ota;
      }

      public void setOta(int ota) {
        this.ota = ota;
      }

      public String getPrice() {
        return price;
      }

      public void setPrice(String price) {
        this.price = price;
      }

      public String getOrigin_price() {
        return origin_price;
      }

      public void setOrigin_price(String origin_price) {
        this.origin_price = origin_price;
      }

      public String getDesc() {
        return desc;
      }

      public void setDesc(String desc) {
        this.desc = desc;
      }

      public float getAvg_remark_score() {
        return avg_remark_score;
      }

      public void setAvg_remark_score(float avg_remark_score) {
        this.avg_remark_score = avg_remark_score;
      }

      public String getSname() {
        return sname;
      }

      public void setSname(String sname) {
        this.sname = sname;
      }

      public String getSurl() {
        return surl;
      }

      public void setSurl(String surl) {
        this.surl = surl;
      }

      public int getScene_layer() {
        return scene_layer;
      }

      public void setScene_layer(int scene_layer) {
        this.scene_layer = scene_layer;
      }

      public String getParent_sid() {
        return parent_sid;
      }

      public void setParent_sid(String parent_sid) {
        this.parent_sid = parent_sid;
      }

      public String getMap_x() {
        return map_x;
      }

      public void setMap_x(String map_x) {
        this.map_x = map_x;
      }

      public String getMap_y() {
        return map_y;
      }

      public void setMap_y(String map_y) {
        this.map_y = map_y;
      }

      public String getRemark_count() {
        return remark_count;
      }

      public void setRemark_count(String remark_count) {
        this.remark_count = remark_count;
      }
    }
  }
}
