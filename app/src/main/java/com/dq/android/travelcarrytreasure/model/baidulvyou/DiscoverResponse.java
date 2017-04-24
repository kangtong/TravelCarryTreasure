package com.dq.android.travelcarrytreasure.model.baidulvyou;

import java.io.Serializable;
import java.util.List;

/**
 * Created by DQDana on 2017/4/12
 */

public class DiscoverResponse implements Serializable {

  // http://lvyou.baidu.com/main/app/index?apiv=v4&sid=795ac511463263cf7ae3def3&y=40.001813&x=116.488117&format=app&d=android&w=1080&h=1830&u=HUAWEI+NXT-AL10&v=7.3.0&i=860482033314237&s=7.0&q=1028&m=8e66d8f81fdea5a65e83102dd354f290&netTpye=wifi&LVCODE=e455bf0783c16f858090d15499dbbe37&T=1491985553&locEnabled=YES&locType=GPS
  // 以上是地址, 之后再说

  /**
   * errno : 0 msg : data : {"is_local":1,"scene_info":{"sid":"795ac511463263cf7ae3def3","parent_sid":"5007715ac511463263cfd1f3","sname":"北京","surl":"beijing","map_cid":"131","scene_layer":4,"pic_url":"http://b.hiphotos.baidu.com/lvpics/wh%3D1280%2C720/sign=6bbf571e968fa0ec7f926c0c14af69c2/bd3eb13533fa828baf7108cefa1f4134970a5a48.jpg"},"config":{},"mod_list":[{"key":"banner","list":[{"type":"Discovery_Detail","title":"玻璃栈道","pic_url":"http://u.hiphotos.baidu.com/baidu/pic/item/0b46f21fbe096b63c36b638705338744eaf8acf2.png","pic_url_background":"http://b.hiphotos.baidu.com/baidu/pic/item/a2cc7cd98d1001e9532c29d8b10e7bec55e797d0.png","pic_url_text":"http://d.hiphotos.baidu.com/baidu/pic/item/b8014a90f603738d5c3931f5ba1bb051f919ecf2.png","ext":{"card_id":"6e0f323b51b55264c32ec9cc","channel_name":"新鲜事","type":"Discovery_Detail","user_info":{}}},{"type":"page_topic","title":"免费游台湾","pic_url":"http://p.hiphotos.baidu.com/baidu/pic/item/c995d143ad4bd113ad0684e853afa40f4afb0524.jpg","ext":{"url":"http://lvyou.baidu.com/static/event-cms/perfect-h5-100/page/index/index.html?webview=1&fr=&v=7.3.2","type":"page_topic","user_info":{}}},{"type":"Discovery_Detail","title":"水下古城","pic_url":"http://f.hiphotos.baidu.com/baidu/pic/item/91529822720e0cf39bcf90120346f21fbe09aa1b.png","pic_url_background":"http://m.hiphotos.baidu.com/baidu/pic/item/bd315c6034a85edf24e620ca40540923dc5475f5.png","pic_url_text":"http://r.hiphotos.baidu.com/baidu/pic/item/3812b31bb051f819911ddda3d3b44aed2e73e71b.png","ext":{"card_id":"b16b9509323b51b55264c8cc","channel_name":"新鲜事","type":"Discovery_Detail","user_info":{}}},{"type":"Notes","title":"西塘","pic_url":"http://w.hiphotos.baidu.com/baidu/pic/item/2fdda3cc7cd98d10d01199cf283fb80e7aec9003.png","pic_url_background":"http://n.hiphotos.baidu.com/baidu/pic/item/472309f790529822e7f6ac3ddeca7bcb0b46d403.png","pic_url_text":"http://q.hiphotos.baidu.com/baidu/pic/item/d53f8794a4c27d1efa5eaf3a12d5ad6edcc438d7.png","ext":{"nid":"5876f89235d883f5c6c17e25","uid":"c88093633a627f249452d5b5","type":"Notes","user_info":{"uid":"c88093633a627f249452d5b5","nickname":"男道说","avatar_pic":"http://hiphotos.baidu.com/lvpics/pic/item/9c16fdfaaf51f3de762f4af69deef01f3b297987.jpg"}}}]},{"key":"sub_mod","list":[{"key":"scene","title":"查攻略","sub_title":"60000+目的地"},{"key":"plan","title":"排行程","sub_title":"咨询师推荐，一键行程","float_layer":"0"},{"key":"pictravel","title":"发画册","sub_title":"发画册"}]},{"key":"season_scene","title":"本季热门","sub_title":"04月最热门的地方","icon":"","list":[{"surl":"xishuangbanna","abs_desc":"泼水节，你敢来么","pic_url":"http://l.hiphotos.baidu.com/baidu/pic/item/14ce36d3d539b600e865ba7be050352ac75cb757.jpg","sname":"西双版纳","sid":"289ab2b69a056d0754c87adb","parent_sid":"17070a5c91ca872746461bf4","scene_layer":4},{"surl":"shaoxing","abs_desc":"江南春色浓","pic_url":"http://q.hiphotos.baidu.com/baidu/pic/item/8644ebf81a4c510f1d8957d66959252dd52aa5a9.jpg","sname":"绍兴","sid":"36e466d6c4365b0af2c94bf1","parent_sid":"9520608cbb0e13551b12a1f1","scene_layer":4},{"surl":"changtandao","abs_desc":"全世界最浪漫的白沙滩","pic_url":"http://n.hiphotos.baidu.com/baidu/pic/item/8d5494eef01f3a29206260029025bc315d607c57.jpg","sname":"长滩岛","sid":"41f71e4019c66b17a516aafb","parent_sid":"060723d1dc24b39f0973b9fb","scene_layer":4}]},{"key":"theme_scene","title":"主题游","sub_title":"寻找最适合你的主题","icon":"","list":[{"key":"zijia","title":"自驾","pic_url":"http://lvyou.baidu.com/event/s/natheme/small/zijia.jpg"},{"key":"shanghua","title":"赏花","pic_url":"http://lvyou.baidu.com/event/s/natheme/small/shanghua.jpg"},{"key":"taqing","title":"踏青","pic_url":"http://lvyou.baidu.com/event/s/natheme/small/taqing.jpg"}]},{"key":"discovery","title":"每日·发现","icon":"","sub_title":"热门精选
   * 旅行谈","list":[{"channel_id":"59f997c08148833292b24cd9","card_id":"6e0f323b51b55264c32ec9cc","channel_name":"新鲜事","create_time":1490889600,"title":"【挑战刺激旅游胜地】","pic_url":"http://g.hiphotos.baidu.com/lvpics/wh%3D1280%2C720/sign=5c869fe67a8da9774e7a8e2a8269c835/4ec2d5628535e5ddf107f90a7fc6a7efce1b623f.jpg","desc":"你是否空有一颗挑战的心，却不知去哪里？别担心，小编为你整理出了国内的几条最值得挑战的玻璃栈道，这个黄金周就去接受挑\u201c栈\u201d吧！","type":1,"ext":{"user_info":{}}},{"channel_id":"59f997c08148833292b24cd9","card_id":"b16b9509323b51b55264c8cc","channel_name":"新鲜事","create_time":1490284800,"title":"【中国最美水下古城】","pic_url":"http://f.hiphotos.baidu.com/lvpics/wh%3D1280%2C720/sign=16466fd8a88b87d65017a31e35301814/8d5494eef01f3a2988af08279025bc315d607cf0.jpg","desc":"中国的水下古城比较少，可谁又知道在这么美丽的千岛湖下面竟然藏着一个千年古城，而这些古老的建筑，全部都隐藏在这片蔚蓝千岛湖之下，你能想像吗？","type":1,"ext":{"user_info":{}}}]},{"key":"notes_new","title":"精华游记","sub_title":"最新最精华精彩游记","icon":"","list":[{"type":"notes","ext":{"nid":"515df1e8bab4b0345237314f","title":"纯净峡湾\u2014北欧4国印象之旅
   * ","start_time":"1464710400","view_count":10485,"pic_url":"http://a.hiphotos.baidu.com/lvpics/wh%3D640%2C884/sign=4fb6f01f8a26cffc697fb7b48d3166a8/838ba61ea8d3fd1fb9c724bc384e251f94ca5f6e.jpg","recommend_num":75,"remark_num":20,"uid":"55691aa3e42ea622f73b5cea","user_info":{"uid":"55691aa3e42ea622f73b5cea","nickname":"yz9804wn","avatar_pic":"http://hiphotos.baidu.com/lvpics/pic/item/b999a9014c086e069a479add0a087bf40bd1cbbd.jpg"}}},{"type":"notes","ext":{"nid":"9a157af3b4381f3df6278d79","title":"2015漫行江南行记（瀚海尘烟迷望眼,天涯浪迹觅芳踪!）行者余笑人","start_time":"1425139200","view_count":14283,"pic_url":"http://f.hiphotos.baidu.com/lvpics/wh%3D640%2C480/sign=4e9f2363c9fc1e17fdea84377ea0da37/a71ea8d3fd1f41349a3c910b231f95cad1c85ebf.jpg","recommend_num":263,"remark_num":87,"uid":"e45c6b810445d9a3c91f70a6","user_info":{"uid":"e45c6b810445d9a3c91f70a6","nickname":"altjcl","avatar_pic":"http://hiphotos.baidu.com/lvpics/pic/item/7c1ed21b0ef41bd5453c379952da81cb39db3d74.jpg"}}},{"type":"notes","ext":{"nid":"f7e4e90799b2b62717ba559c","title":"2014北疆行记（一路向北，和小伙伴一起走世界）\u2014\u2014行者余笑人","start_time":"1414771200","view_count":10016,"pic_url":"http://f.hiphotos.baidu.com/lvpics/wh%3D854%2C640/sign=2e88c5b7ccfc1e17fdea84397fa4da35/4034970a304e251ff8eb9659a486c9177f3e5376.jpg","recommend_num":210,"remark_num":56,"uid":"e45c6b810445d9a3c91f70a6","user_info":{"uid":"e45c6b810445d9a3c91f70a6","nickname":"altjcl","avatar_pic":"http://hiphotos.baidu.com/lvpics/pic/item/7c1ed21b0ef41bd5453c379952da81cb39db3d74.jpg"}}},{"type":"pictravel","ext":{"ptid":"8e5e585d40d05de63df1c813","title":"【爱尔兰】在冰与火之歌中穿行","pic_url":"http://i.hiphotos.baidu.com/baidu/pic/item/8ad4b31c8701a18b4d4d6683972f07082938fe5c.jpg","user_info":{"uid":"9d0aa6a1fb2efc5148ff8a9a","nickname":"Triones_XM","avatar_pic":"http://hiphotos.baidu.com/lvpics/pic/item/d1160924ab18972bc9db8febefcd7b899e510a29.jpg"},"pictravel_info":{"title":"爱尔兰
   * 在冰与火之歌中穿行","uid":"9d0aa6a1fb2efc5148ff8a9a","pic_day_count":5,"min_date":"2015/05/08","pic_count":"140","view_count":"1367","cover_url":"http://h.hiphotos.baidu.com/lvpics/wh%3D1280%2C720/sign=18ff95347fc6a7efb973a027cfc29f7d/b151f8198618367a255a4f3627738bd4b21ce50e.jpg"}}}]}],"signin_activity":{"activity_url":"https://lvyou.baidu.com/main/event/signinshake?webview=1&fr=namain","activity_intro":"天天摇一摇
   * \u2022 100%有奖"},"spring_gift":{"activity_url":"http://lvyou.baidu.com/event/s/monkeylaile/","user_first":1,"start_time":20160129,"icon":"http://lvyou.baidu.com/event/s/app/redpack/redpack.png","icon_close":"http://lvyou.baidu.com/event/s/app/redpack/X.png","end_time":20160209,"is_open":0,"expire":30},"list":["banner","sub_mod","sales","season_scene","theme_scene","discovery","notes","adbanner","pictravel"]}
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

  /* 主体内容 */
  public static class DataBean {
    /**
     * is_local : 1 scene_info : { // 定位 "sid":"795ac511463263cf7ae3def3",
     * "parent_sid":"5007715ac511463263cfd1f3", "sname":"北京", "surl":"beijing", "map_cid":"131",
     * "scene_layer":4, "pic_url":"http://b.hiphotos.baidu.com/lvpics/wh%3D1280%2C720/sign=6bbf571e968fa0ec7f926c0c14af69c2/bd3eb13533fa828baf7108cefa1f4134970a5a48.jpg"
     * } config : {} // 不知道是啥 mod_list : [ { "key":"banner", // banner,上面四个轮播图 "list":[ {
     * "type":"Discovery_Detail", "title":"玻璃栈道", "pic_url":"http://u.hiphotos.baidu.com/baidu/pic/item/0b46f21fbe096b63c36b638705338744eaf8acf2.png","pic_url_background":"http://b.hiphotos.baidu.com/baidu/pic/item/a2cc7cd98d1001e9532c29d8b10e7bec55e797d0.png","pic_url_text":"http://d.hiphotos.baidu.com/baidu/pic/item/b8014a90f603738d5c3931f5ba1bb051f919ecf2.png","ext":{"card_id":"6e0f323b51b55264c32ec9cc","channel_name":"新鲜事","type":"Discovery_Detail","user_info":{}}},{"type":"page_topic","title":"免费游台湾","pic_url":"http://p.hiphotos.baidu.com/baidu/pic/item/c995d143ad4bd113ad0684e853afa40f4afb0524.jpg","ext":{"url":"http://lvyou.baidu.com/static/event-cms/perfect-h5-100/page/index/index.html?webview=1&fr=&v=7.3.2","type":"page_topic","user_info":{}}},{"type":"Discovery_Detail","title":"水下古城","pic_url":"http://f.hiphotos.baidu.com/baidu/pic/item/91529822720e0cf39bcf90120346f21fbe09aa1b.png","pic_url_background":"http://m.hiphotos.baidu.com/baidu/pic/item/bd315c6034a85edf24e620ca40540923dc5475f5.png","pic_url_text":"http://r.hiphotos.baidu.com/baidu/pic/item/3812b31bb051f819911ddda3d3b44aed2e73e71b.png","ext":{"card_id":"b16b9509323b51b55264c8cc","channel_name":"新鲜事","type":"Discovery_Detail","user_info":{}}},{"type":"Notes","title":"西塘","pic_url":"http://w.hiphotos.baidu.com/baidu/pic/item/2fdda3cc7cd98d10d01199cf283fb80e7aec9003.png","pic_url_background":"http://n.hiphotos.baidu.com/baidu/pic/item/472309f790529822e7f6ac3ddeca7bcb0b46d403.png","pic_url_text":"http://q.hiphotos.baidu.com/baidu/pic/item/d53f8794a4c27d1efa5eaf3a12d5ad6edcc438d7.png","ext":{"nid":"5876f89235d883f5c6c17e25","uid":"c88093633a627f249452d5b5","type":"Notes","user_info":{"uid":"c88093633a627f249452d5b5","nickname":"男道说","avatar_pic":"http://hiphotos.baidu.com/lvpics/pic/item/9c16fdfaaf51f3de762f4af69deef01f3b297987.jpg"}}}]},{"key":"sub_mod","list":[{"key":"scene","title":"查攻略","sub_title":"60000+目的地"},{"key":"plan","title":"排行程","sub_title":"咨询师推荐，一键行程","float_layer":"0"},{"key":"pictravel","title":"发画册","sub_title":"发画册"}]},{"key":"season_scene","title":"本季热门","sub_title":"04月最热门的地方","icon":"","list":[{"surl":"xishuangbanna","abs_desc":"泼水节，你敢来么","pic_url":"http://l.hiphotos.baidu.com/baidu/pic/item/14ce36d3d539b600e865ba7be050352ac75cb757.jpg","sname":"西双版纳","sid":"289ab2b69a056d0754c87adb","parent_sid":"17070a5c91ca872746461bf4","scene_layer":4},{"surl":"shaoxing","abs_desc":"江南春色浓","pic_url":"http://q.hiphotos.baidu.com/baidu/pic/item/8644ebf81a4c510f1d8957d66959252dd52aa5a9.jpg","sname":"绍兴","sid":"36e466d6c4365b0af2c94bf1","parent_sid":"9520608cbb0e13551b12a1f1","scene_layer":4},{"surl":"changtandao","abs_desc":"全世界最浪漫的白沙滩","pic_url":"http://n.hiphotos.baidu.com/baidu/pic/item/8d5494eef01f3a29206260029025bc315d607c57.jpg","sname":"长滩岛","sid":"41f71e4019c66b17a516aafb","parent_sid":"060723d1dc24b39f0973b9fb","scene_layer":4}]},{"key":"theme_scene","title":"主题游","sub_title":"寻找最适合你的主题","icon":"","list":[{"key":"zijia","title":"自驾","pic_url":"http://lvyou.baidu.com/event/s/natheme/small/zijia.jpg"},{"key":"shanghua","title":"赏花","pic_url":"http://lvyou.baidu.com/event/s/natheme/small/shanghua.jpg"},{"key":"taqing","title":"踏青","pic_url":"http://lvyou.baidu.com/event/s/natheme/small/taqing.jpg"}]},{"key":"discovery","title":"每日·发现","icon":"","sub_title":"热门精选
     * 旅行谈","list":[{"channel_id":"59f997c08148833292b24cd9","card_id":"6e0f323b51b55264c32ec9cc","channel_name":"新鲜事","create_time":1490889600,"title":"【挑战刺激旅游胜地】","pic_url":"http://g.hiphotos.baidu.com/lvpics/wh%3D1280%2C720/sign=5c869fe67a8da9774e7a8e2a8269c835/4ec2d5628535e5ddf107f90a7fc6a7efce1b623f.jpg","desc":"你是否空有一颗挑战的心，却不知去哪里？别担心，小编为你整理出了国内的几条最值得挑战的玻璃栈道，这个黄金周就去接受挑\u201c栈\u201d吧！","type":1,"ext":{"user_info":{}}},{"channel_id":"59f997c08148833292b24cd9","card_id":"b16b9509323b51b55264c8cc","channel_name":"新鲜事","create_time":1490284800,"title":"【中国最美水下古城】","pic_url":"http://f.hiphotos.baidu.com/lvpics/wh%3D1280%2C720/sign=16466fd8a88b87d65017a31e35301814/8d5494eef01f3a2988af08279025bc315d607cf0.jpg","desc":"中国的水下古城比较少，可谁又知道在这么美丽的千岛湖下面竟然藏着一个千年古城，而这些古老的建筑，全部都隐藏在这片蔚蓝千岛湖之下，你能想像吗？","type":1,"ext":{"user_info":{}}}]},{"key":"notes_new","title":"精华游记","sub_title":"最新最精华精彩游记","icon":"","list":[{"type":"notes","ext":{"nid":"515df1e8bab4b0345237314f","title":"纯净峡湾\u2014北欧4国印象之旅
     * ","start_time":"1464710400","view_count":10485,"pic_url":"http://a.hiphotos.baidu.com/lvpics/wh%3D640%2C884/sign=4fb6f01f8a26cffc697fb7b48d3166a8/838ba61ea8d3fd1fb9c724bc384e251f94ca5f6e.jpg","recommend_num":75,"remark_num":20,"uid":"55691aa3e42ea622f73b5cea","user_info":{"uid":"55691aa3e42ea622f73b5cea","nickname":"yz9804wn","avatar_pic":"http://hiphotos.baidu.com/lvpics/pic/item/b999a9014c086e069a479add0a087bf40bd1cbbd.jpg"}}},{"type":"notes","ext":{"nid":"9a157af3b4381f3df6278d79","title":"2015漫行江南行记（瀚海尘烟迷望眼,天涯浪迹觅芳踪!）行者余笑人","start_time":"1425139200","view_count":14283,"pic_url":"http://f.hiphotos.baidu.com/lvpics/wh%3D640%2C480/sign=4e9f2363c9fc1e17fdea84377ea0da37/a71ea8d3fd1f41349a3c910b231f95cad1c85ebf.jpg","recommend_num":263,"remark_num":87,"uid":"e45c6b810445d9a3c91f70a6","user_info":{"uid":"e45c6b810445d9a3c91f70a6","nickname":"altjcl","avatar_pic":"http://hiphotos.baidu.com/lvpics/pic/item/7c1ed21b0ef41bd5453c379952da81cb39db3d74.jpg"}}},{"type":"notes","ext":{"nid":"f7e4e90799b2b62717ba559c","title":"2014北疆行记（一路向北，和小伙伴一起走世界）\u2014\u2014行者余笑人","start_time":"1414771200","view_count":10016,"pic_url":"http://f.hiphotos.baidu.com/lvpics/wh%3D854%2C640/sign=2e88c5b7ccfc1e17fdea84397fa4da35/4034970a304e251ff8eb9659a486c9177f3e5376.jpg","recommend_num":210,"remark_num":56,"uid":"e45c6b810445d9a3c91f70a6","user_info":{"uid":"e45c6b810445d9a3c91f70a6","nickname":"altjcl","avatar_pic":"http://hiphotos.baidu.com/lvpics/pic/item/7c1ed21b0ef41bd5453c379952da81cb39db3d74.jpg"}}},{"type":"pictravel","ext":{"ptid":"8e5e585d40d05de63df1c813","title":"【爱尔兰】在冰与火之歌中穿行","pic_url":"http://i.hiphotos.baidu.com/baidu/pic/item/8ad4b31c8701a18b4d4d6683972f07082938fe5c.jpg","user_info":{"uid":"9d0aa6a1fb2efc5148ff8a9a","nickname":"Triones_XM","avatar_pic":"http://hiphotos.baidu.com/lvpics/pic/item/d1160924ab18972bc9db8febefcd7b899e510a29.jpg"},"pictravel_info":{"title":"爱尔兰
     * 在冰与火之歌中穿行","uid":"9d0aa6a1fb2efc5148ff8a9a","pic_day_count":5,"min_date":"2015/05/08","pic_count":"140","view_count":"1367","cover_url":"http://h.hiphotos.baidu.com/lvpics/wh%3D1280%2C720/sign=18ff95347fc6a7efb973a027cfc29f7d/b151f8198618367a255a4f3627738bd4b21ce50e.jpg"
     * } } } ] } ]
     *
     * signin_activity : {"activity_url":"https://lvyou.baidu.com/main/event/signinshake?webview=1&fr=namain","activity_intro":"天天摇一摇
     * \u2022 100%有奖"} spring_gift : {"activity_url":"http://lvyou.baidu.com/event/s/monkeylaile/","user_first":1,"start_time":20160129,"icon":"http://lvyou.baidu.com/event/s/app/redpack/redpack.png","icon_close":"http://lvyou.baidu.com/event/s/app/redpack/X.png","end_time":20160209,"is_open":0,"expire":30}
     * list : ["banner","sub_mod","sales","season_scene","theme_scene","discovery","notes","adbanner","pictravel"]
     */

    private int is_local;
    private ConfigBean config;
    private SigninActivityBean signin_activity;
    private SpringGiftBean spring_gift;
    private List<ModListBean> mod_list; // 这个是关键
    private List<String> list;

    public int getIs_local() {
      return is_local;
    }

    public void setIs_local(int is_local) {
      this.is_local = is_local;
    }

    public ConfigBean getConfig() {
      return config;
    }

    public void setConfig(ConfigBean config) {
      this.config = config;
    }

    public SigninActivityBean getSignin_activity() {
      return signin_activity;
    }

    public void setSignin_activity(SigninActivityBean signin_activity) {
      this.signin_activity = signin_activity;
    }

    public SpringGiftBean getSpring_gift() {
      return spring_gift;
    }

    public void setSpring_gift(SpringGiftBean spring_gift) {
      this.spring_gift = spring_gift;
    }

    public List<ModListBean> getMod_list() {
      return mod_list;
    }

    public void setMod_list(List<ModListBean> mod_list) {
      this.mod_list = mod_list;
    }

    public List<String> getList() {
      return list;
    }

    public void setList(List<String> list) {
      this.list = list;
    }

    public static class ConfigBean {
    }

    public static class SigninActivityBean {
      /**
       * activity_url : https://lvyou.baidu.com/main/event/signinshake?webview=1&fr=namain
       * activity_intro : 天天摇一摇 • 100%有奖
       */

      private String activity_url;
      private String activity_intro;

      public String getActivity_url() {
        return activity_url;
      }

      public void setActivity_url(String activity_url) {
        this.activity_url = activity_url;
      }

      public String getActivity_intro() {
        return activity_intro;
      }

      public void setActivity_intro(String activity_intro) {
        this.activity_intro = activity_intro;
      }
    }

    public static class SpringGiftBean {
      /**
       * activity_url : http://lvyou.baidu.com/event/s/monkeylaile/
       * user_first : 1
       * start_time : 20160129
       * icon : http://lvyou.baidu.com/event/s/app/redpack/redpack.png
       * icon_close : http://lvyou.baidu.com/event/s/app/redpack/X.png
       * end_time : 20160209
       * is_open : 0
       * expire : 30
       */

      private String activity_url;
      private int user_first;
      private int start_time;
      private String icon;
      private String icon_close;
      private int end_time;
      private int is_open;
      private int expire;

      public String getActivity_url() {
        return activity_url;
      }

      public void setActivity_url(String activity_url) {
        this.activity_url = activity_url;
      }

      public int getUser_first() {
        return user_first;
      }

      public void setUser_first(int user_first) {
        this.user_first = user_first;
      }

      public int getStart_time() {
        return start_time;
      }

      public void setStart_time(int start_time) {
        this.start_time = start_time;
      }

      public String getIcon() {
        return icon;
      }

      public void setIcon(String icon) {
        this.icon = icon;
      }

      public String getIcon_close() {
        return icon_close;
      }

      public void setIcon_close(String icon_close) {
        this.icon_close = icon_close;
      }

      public int getEnd_time() {
        return end_time;
      }

      public void setEnd_time(int end_time) {
        this.end_time = end_time;
      }

      public int getIs_open() {
        return is_open;
      }

      public void setIs_open(int is_open) {
        this.is_open = is_open;
      }

      public int getExpire() {
        return expire;
      }

      public void setExpire(int expire) {
        this.expire = expire;
      }
    }

    /* 我们需要关注的,最重要的东西都在这里面 */
    public static class ModListBean {
      /**
       * key : banner list : [{"type":"Discovery_Detail","title":"玻璃栈道","pic_url":"http://u.hiphotos.baidu.com/baidu/pic/item/0b46f21fbe096b63c36b638705338744eaf8acf2.png","pic_url_background":"http://b.hiphotos.baidu.com/baidu/pic/item/a2cc7cd98d1001e9532c29d8b10e7bec55e797d0.png","pic_url_text":"http://d.hiphotos.baidu.com/baidu/pic/item/b8014a90f603738d5c3931f5ba1bb051f919ecf2.png","ext":{"card_id":"6e0f323b51b55264c32ec9cc","channel_name":"新鲜事","type":"Discovery_Detail","user_info":{}}},{"type":"page_topic","title":"免费游台湾","pic_url":"http://p.hiphotos.baidu.com/baidu/pic/item/c995d143ad4bd113ad0684e853afa40f4afb0524.jpg","ext":{"url":"http://lvyou.baidu.com/static/event-cms/perfect-h5-100/page/index/index.html?webview=1&fr=&v=7.3.2","type":"page_topic","user_info":{}}},{"type":"Discovery_Detail","title":"水下古城","pic_url":"http://f.hiphotos.baidu.com/baidu/pic/item/91529822720e0cf39bcf90120346f21fbe09aa1b.png","pic_url_background":"http://m.hiphotos.baidu.com/baidu/pic/item/bd315c6034a85edf24e620ca40540923dc5475f5.png","pic_url_text":"http://r.hiphotos.baidu.com/baidu/pic/item/3812b31bb051f819911ddda3d3b44aed2e73e71b.png","ext":{"card_id":"b16b9509323b51b55264c8cc","channel_name":"新鲜事","type":"Discovery_Detail","user_info":{}}},{"type":"Notes","title":"西塘","pic_url":"http://w.hiphotos.baidu.com/baidu/pic/item/2fdda3cc7cd98d10d01199cf283fb80e7aec9003.png","pic_url_background":"http://n.hiphotos.baidu.com/baidu/pic/item/472309f790529822e7f6ac3ddeca7bcb0b46d403.png","pic_url_text":"http://q.hiphotos.baidu.com/baidu/pic/item/d53f8794a4c27d1efa5eaf3a12d5ad6edcc438d7.png","ext":{"nid":"5876f89235d883f5c6c17e25","uid":"c88093633a627f249452d5b5","type":"Notes","user_info":{"uid":"c88093633a627f249452d5b5","nickname":"男道说","avatar_pic":"http://hiphotos.baidu.com/lvpics/pic/item/9c16fdfaaf51f3de762f4af69deef01f3b297987.jpg"}}}]
       * title : 本季热门 sub_title : 04月最热门的地方 icon :
       */

      private String key;
      private String title;
      private String sub_title;
      private String icon;
      private List<ListBean> list;

      public String getKey() {
        return key;
      }

      public void setKey(String key) {
        this.key = key;
      }

      public String getTitle() {
        return title;
      }

      public void setTitle(String title) {
        this.title = title;
      }

      public String getSub_title() {
        return sub_title;
      }

      public void setSub_title(String sub_title) {
        this.sub_title = sub_title;
      }

      public String getIcon() {
        return icon;
      }

      public void setIcon(String icon) {
        this.icon = icon;
      }

      public List<ListBean> getList() {
        return list;
      }

      public void setList(List<ListBean> list) {
        this.list = list;
      }

      public static class ListBean {
        /**
         * type : Discovery_Detail title : 玻璃栈道 pic_url : http://u.hiphotos.baidu.com/baidu/pic/item/0b46f21fbe096b63c36b638705338744eaf8acf2.png
         * pic_url_background : http://b.hiphotos.baidu.com/baidu/pic/item/a2cc7cd98d1001e9532c29d8b10e7bec55e797d0.png
         * pic_url_text : http://d.hiphotos.baidu.com/baidu/pic/item/b8014a90f603738d5c3931f5ba1bb051f919ecf2.png
         * ext : {"card_id":"6e0f323b51b55264c32ec9cc","channel_name":"新鲜事","type":"Discovery_Detail","user_info":{}}
         */

        // 第一部分 感觉不需要
        private String type;
        private String title;
        private String pic_url;
        private String pic_url_background;
        private String pic_url_text;
        private ExtBean ext;

        // 第二部分 放弃 不需要

        // 第三部分 本季热门
        private String sname;
        private String abs_desc;

        // 第四部分 主题游
        private String key;

        // 第五部分 每日发现
        private String desc; // 描述
        private String channel_name; // 新鲜事
        private long create_time; // 时间
        private String card_id; // ce3d51b55264c32e32cacecc

        public String getType() {
          return type;
        }

        public void setType(String type) {
          this.type = type;
        }

        public String getTitle() {
          return title;
        }

        public void setTitle(String title) {
          this.title = title;
        }

        public String getPic_url() {
          return pic_url;
        }

        public void setPic_url(String pic_url) {
          this.pic_url = pic_url;
        }

        public String getPic_url_background() {
          return pic_url_background;
        }

        public void setPic_url_background(String pic_url_background) {
          this.pic_url_background = pic_url_background;
        }

        public String getPic_url_text() {
          return pic_url_text;
        }

        public void setPic_url_text(String pic_url_text) {
          this.pic_url_text = pic_url_text;
        }

        public ExtBean getExt() {
          return ext;
        }

        public void setExt(ExtBean ext) {
          this.ext = ext;
        }

        public String getSname() {
          return sname;
        }

        public void setSname(String sname) {
          this.sname = sname;
        }

        public String getAbs_desc() {
          return abs_desc;
        }

        public void setAbs_desc(String abs_desc) {
          this.abs_desc = abs_desc;
        }

        public String getKey() {
          return key;
        }

        public void setKey(String key) {
          this.key = key;
        }

        public String getDesc() {
          return desc;
        }

        public void setDesc(String desc) {
          this.desc = desc;
        }

        public String getChannel_name() {
          return channel_name;
        }

        public void setChannel_name(String channel_name) {
          this.channel_name = channel_name;
        }

        public long getCreate_time() {
          return create_time;
        }

        public void setCreate_time(long create_time) {
          this.create_time = create_time;
        }

        public String getCard_id() {
          return card_id;
        }

        public void setCard_id(String card_id) {
          this.card_id = card_id;
        }

        public static class ExtBean {
          /**
           * card_id : 6e0f323b51b55264c32ec9cc
           * channel_name : 新鲜事
           * type : Discovery_Detail
           * user_info : {}
           */

          private String card_id;
          private String channel_name;
          private String type;
          private UserInfoBean user_info;

          // 第六部分
          private String title;
          private String start_time;
          private int view_count;
          private String pic_url;
          private int recommend_num;
          private int remark_num;

          public String getTitle() {
            return title;
          }

          public void setTitle(String title) {
            this.title = title;
          }

          public String getCard_id() {
            return card_id;
          }

          public void setCard_id(String card_id) {
            this.card_id = card_id;
          }

          public String getChannel_name() {
            return channel_name;
          }

          public void setChannel_name(String channel_name) {
            this.channel_name = channel_name;
          }

          public String getType() {
            return type;
          }

          public void setType(String type) {
            this.type = type;
          }

          public UserInfoBean getUser_info() {
            return user_info;
          }

          public void setUser_info(UserInfoBean user_info) {
            this.user_info = user_info;
          }

          public String getStart_time() {
            return start_time;
          }

          public void setStart_time(String start_time) {
            this.start_time = start_time;
          }

          public int getView_count() {
            return view_count;
          }

          public void setView_count(int view_count) {
            this.view_count = view_count;
          }

          public String getPic_url() {
            return pic_url;
          }

          public void setPic_url(String pic_url) {
            this.pic_url = pic_url;
          }

          public int getRecommend_num() {
            return recommend_num;
          }

          public void setRecommend_num(int recommend_num) {
            this.recommend_num = recommend_num;
          }

          public int getRemark_num() {
            return remark_num;
          }

          public void setRemark_num(int remark_num) {
            this.remark_num = remark_num;
          }

          public static class UserInfoBean {
          }
        }
      }
    }
  }
}
