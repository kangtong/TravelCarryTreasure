package com.dq.android.travelcarrytreasure.model.baidulvyou;

import java.io.Serializable;
import java.util.List;

/**
 * 作者：Create on 2017/5/3 09:17  by dq_dana
 * 邮箱：dqdanavera@gmail.com
 * 描述：当地此刻的列表实体
 */
public class LocationNowResponse implements Serializable  {

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
     * sname : 西安
     * scene_layer : 4
     * parent_sid : 1fb09a056d0754c85f197bf5
     * pn : 0
     * rn : 20
     * number : 20
     * total : 1000
     * count : 1000
     * list : []
     */

    private String sname; // 城市名
    private String scene_layer;
    private String parent_sid;
    private int pn;
    private int rn;
    private int number;
    private int total;
    private int count; // 观光总人数
    private List<ListBean> list;

    public String getSname() {
      return sname;
    }

    public void setSname(String sname) {
      this.sname = sname;
    }

    public String getScene_layer() {
      return scene_layer;
    }

    public void setScene_layer(String scene_layer) {
      this.scene_layer = scene_layer;
    }

    public String getParent_sid() {
      return parent_sid;
    }

    public void setParent_sid(String parent_sid) {
      this.parent_sid = parent_sid;
    }

    public int getPn() {
      return pn;
    }

    public void setPn(int pn) {
      this.pn = pn;
    }

    public int getRn() {
      return rn;
    }

    public void setRn(int rn) {
      this.rn = rn;
    }

    public int getNumber() {
      return number;
    }

    public void setNumber(int number) {
      this.number = number;
    }

    public int getTotal() {
      return total;
    }

    public void setTotal(int total) {
      this.total = total;
    }

    public int getCount() {
      return count;
    }

    public void setCount(int count) {
      this.count = count;
    }

    public List<ListBean> getList() {
      return list;
    }

    public void setList(List<ListBean> list) {
      this.list = list;
    }

    public static class ListBean {
      /**
       * type : 2
       * id : b97352de1fcd274cfd4976c3
       * union_id : 37766378
       * create_time : 1484733441
       * token_time : 1484725501
       * desc : 只发图，不说话~
       * xid : 9bb8ee381df41344144463f5
       * pics : [{"pic_url":"http://h.hiphotos.baidu.com/lvpics/wh%3D640%2C480/sign=79638e9ac71b9d168a929267c7ee98bb/9922720e0cf3d7ca746e2333fb1fbe096a63a9de.jpg","ext":{"width":640,"height":480}}]
       * user : {"uid":"ba6b10420213c7f436ae1dba","nickname":"hqzxlp","avatar_pic":"http://hiphotos.baidu.com/lvpics/pic/item/377adab44aed2e73f7ed3d5a8101a18b87d6fa44.jpg"}
       * sname : 西安
       */

      private int type;
      private String id;
      private String union_id;
      private String create_time;
      private String token_time;
      private String desc; // 一句话描述
      private String xid;
      private UserBean user;
      private String sname; // 景点
      private List<PicsBean> pics; // 通常只有一张图

      public int getType() {
        return type;
      }

      public void setType(int type) {
        this.type = type;
      }

      public String getId() {
        return id;
      }

      public void setId(String id) {
        this.id = id;
      }

      public String getUnion_id() {
        return union_id;
      }

      public void setUnion_id(String union_id) {
        this.union_id = union_id;
      }

      public String getCreate_time() {
        return create_time;
      }

      public void setCreate_time(String create_time) {
        this.create_time = create_time;
      }

      public String getToken_time() {
        return token_time;
      }

      public void setToken_time(String token_time) {
        this.token_time = token_time;
      }

      public String getDesc() {
        return desc;
      }

      public void setDesc(String desc) {
        this.desc = desc;
      }

      public String getXid() {
        return xid;
      }

      public void setXid(String xid) {
        this.xid = xid;
      }

      public UserBean getUser() {
        return user;
      }

      public void setUser(UserBean user) {
        this.user = user;
      }

      public String getSname() {
        return sname;
      }

      public void setSname(String sname) {
        this.sname = sname;
      }

      public List<PicsBean> getPics() {
        return pics;
      }

      public void setPics(List<PicsBean> pics) {
        this.pics = pics;
      }

      public static class UserBean {
        /**
         * uid : ba6b10420213c7f436ae1dba
         * nickname : hqzxlp
         * avatar_pic : http://hiphotos.baidu.com/lvpics/pic/item/377adab44aed2e73f7ed3d5a8101a18b87d6fa44.jpg
         */

        private String uid;
        private String nickname; // 用户名
        private String avatar_pic; // 用户头像

        public String getUid() {
          return uid;
        }

        public void setUid(String uid) {
          this.uid = uid;
        }

        public String getNickname() {
          return nickname;
        }

        public void setNickname(String nickname) {
          this.nickname = nickname;
        }

        public String getAvatar_pic() {
          return avatar_pic;
        }

        public void setAvatar_pic(String avatar_pic) {
          this.avatar_pic = avatar_pic;
        }
      }

      public static class PicsBean {
        /**
         * pic_url : http://h.hiphotos.baidu.com/lvpics/wh%3D640%2C480/sign=79638e9ac71b9d168a929267c7ee98bb/9922720e0cf3d7ca746e2333fb1fbe096a63a9de.jpg
         * ext : {"width":640,"height":480}
         */

        private String pic_url; // 图片
        private ExtBean ext;

        public String getPic_url() {
          return pic_url;
        }

        public void setPic_url(String pic_url) {
          this.pic_url = pic_url;
        }

        public ExtBean getExt() {
          return ext;
        }

        public void setExt(ExtBean ext) {
          this.ext = ext;
        }

        public static class ExtBean {
          /**
           * width : 640
           * height : 480
           */

          private int width;
          private int height;

          public int getWidth() {
            return width;
          }

          public void setWidth(int width) {
            this.width = width;
          }

          public int getHeight() {
            return height;
          }

          public void setHeight(int height) {
            this.height = height;
          }
        }
      }
    }
  }
}
