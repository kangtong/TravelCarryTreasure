package com.dq.android.travelcarrytreasure.model;

import com.dq.android.travelcarrytreasure.model.common.City;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by DQDana on 2017/4/5
 */

public class Constant {

  private List<City> cityList = new ArrayList<>();
  private String gaodewebkey = "2a38b88634373039be63970073305145";
  private String baidulvyoukey = "LVCODE=f86c276e35a2785406edbf03cbcb6421&T=1494232033";
  private String baidulvyouXY = "x=108.912932&y=34.219538";

  private static final Constant ourInstance = new Constant();

  public static Constant getInstance() {
    return ourInstance;
  }

  private Constant() {
    // 初始化一些，常量数据
    resetCity();
  }

  private void resetCity() {
    cityList.add(new City("澳门", "aomen", "A"));
    cityList.add(new City("阿里山", "alishan"));
    cityList.add(new City("北海", "beihai", "B"));
    cityList.add(new City("北京", "beijing"));
    cityList.add(new City("成都", "chengdu", "C"));
    cityList.add(new City("长沙", "changsha"));
    cityList.add(new City("承德", "chengde"));
    cityList.add(new City("长白山", "changbaishan"));
    cityList.add(new City("长春", "changchun"));
    cityList.add(new City("敦煌", "dunhuang", "D"));
    cityList.add(new City("凤凰", "fenghuang", "F"));
    cityList.add(new City("甘南", "gannan", "G"));
    cityList.add(new City("桂林", "guilin"));
    cityList.add(new City("广州", "guangzhou"));
    cityList.add(new City("杭州", "hangzhou", "H"));
    cityList.add(new City("黄山", "huangshan"));
    cityList.add(new City("海口", "haikou"));
    cityList.add(new City("哈尔滨", "haerbin"));
    cityList.add(new City("呼伦贝尔", "hulunbeier"));
    cityList.add(new City("九寨沟", "jiuzhaigou", "J"));
    cityList.add(new City("开封", "kaifeng", "K"));
    cityList.add(new City("垦丁", "kending"));
    cityList.add(new City("拉萨", "lasa", "L"));
    cityList.add(new City("丽江", "lijiang"));
    cityList.add(new City("洛阳", "luoyang"));
    cityList.add(new City("漠河", "mohe", "M"));
    cityList.add(new City("南宁", "nanning", "N"));
    cityList.add(new City("平遥", "pingyao", "P"));
    cityList.add(new City("黔东南", "qiandongnan", "Q"));
    cityList.add(new City("青海湖", "qinghaihu"));
    cityList.add(new City("青岛", "qingdao"));
    cityList.add(new City("秦皇岛", "qinhuangdao"));
    cityList.add(new City("上海", "shanghai", "S"));
    cityList.add(new City("三亚", "sanya"));
    cityList.add(new City("沈阳", "shenyang"));
    cityList.add(new City("天津", "tianjin", "T"));
    cityList.add(new City("唐山", "tangshan"));
    cityList.add(new City("台北", "taibei"));
    cityList.add(new City("台中", "taizhong"));
    cityList.add(new City("台南", "tainan"));
    cityList.add(new City("乌鲁木齐", "wulumuqi", "W"));
    cityList.add(new City("乌镇", "wuzhen"));
    cityList.add(new City("武汉", "wuhan"));
    cityList.add(new City("武当山", "wudangshan"));
    cityList.add(new City("五台山", "wutaishan"));
    cityList.add(new City("西双版纳", "xishuangbanna", "X"));
    cityList.add(new City("西安", "xian"));
    cityList.add(new City("厦门", "xiamen"));
    cityList.add(new City("香港", "xianggang"));
    cityList.add(new City("银川", "yinchuan", "Y"));
    cityList.add(new City("扬州", "yangzhou"));
    cityList.add(new City("珠海", "zhuhai", "Z"));
    cityList.add(new City("张家界", "zhangjiajie"));
  }

  public List<City> getCityList() {
    return cityList;
  }

  public String getGaodewebkey() {
    return gaodewebkey;
  }

  public String getBaidulvyoukey() {
    return baidulvyoukey;
  }

  public String getBaidulvyouXY() {
    return baidulvyouXY;
  }
}