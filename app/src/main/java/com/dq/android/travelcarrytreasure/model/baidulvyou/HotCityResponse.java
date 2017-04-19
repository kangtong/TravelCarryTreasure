package com.dq.android.travelcarrytreasure.model.baidulvyou;

import java.io.Serializable;
import java.util.List;

/**
 * Created by DQDana on 2017/4/19
 */

public class HotCityResponse implements Serializable {

  /**
   * errno : 0 msg : data : {"list":[{"sid":"9bb8ee381df41344144463f5","sname":"西安","map_x":108.894457,"map_y":34.289254027919,"mapid":"233"},{"sid":"5ecd8727464640351f1219f4","sname":"丽江","map_x":100.230203,"map_y":26.887724977122,"mapid":"114"},{"sid":"79c0adc41efa15d8330ab4f5","sname":"香港","map_x":114.201072,"map_y":22.367401996074,"mapid":"2912"},{"sid":"795ac511463263cf7ae3def3","sname":"北京","map_x":116.393525,"map_y":39.969654072571,"mapid":"131"},{"sid":"4c13526fb820db6e9728fbf1","sname":"大连","map_x":121.610725,"map_y":38.923564104012,"mapid":"167"},{"sid":"41fd1e4019c66b17a516aaf1","sname":"杭州","map_x":120.18603,"map_y":30.226796083053,"mapid":"179"},{"sid":"46748b2de74f2ebeee3864f0","sname":"厦门","map_x":118.093537,"map_y":24.491862019549,"mapid":"194"},{"sid":"289d2b074d001b3184b27ef7","sname":"上海","map_x":121.474101,"map_y":31.237799867406,"mapid":"289"},{"sid":"fa093f154ff17fe4381b10f6","sname":"青岛","map_x":120.323882,"map_y":36.076934891607,"mapid":"236"}]}
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

    private List<ListBean> list;

    public List<ListBean> getList() {
      return list;
    }

    public void setList(List<ListBean> list) {
      this.list = list;
    }

    public static class ListBean {
      /**
       * sid : 9bb8ee381df41344144463f5
       * sname : 西安
       * map_x : 108.894457
       * map_y : 34.289254027919
       * mapid : 233
       */

      private String sid;
      private String sname;
      private double map_x;
      private double map_y;
      private String mapid;

      public String getSid() {
        return sid;
      }

      public void setSid(String sid) {
        this.sid = sid;
      }

      public String getSname() {
        return sname;
      }

      public void setSname(String sname) {
        this.sname = sname;
      }

      public double getMap_x() {
        return map_x;
      }

      public void setMap_x(double map_x) {
        this.map_x = map_x;
      }

      public double getMap_y() {
        return map_y;
      }

      public void setMap_y(double map_y) {
        this.map_y = map_y;
      }

      public String getMapid() {
        return mapid;
      }

      public void setMapid(String mapid) {
        this.mapid = mapid;
      }
    }
  }
}
