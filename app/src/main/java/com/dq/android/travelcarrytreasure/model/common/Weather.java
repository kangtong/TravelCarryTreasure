package com.dq.android.travelcarrytreasure.model.common;

import java.io.Serializable;
import java.util.List;

/**
 * Created by DQDana on 2017/4/5
 */

public class Weather implements Serializable {

  /**
   * status : 1
   * count : 1
   * info : OK
   * infocode : 10000
   * lives : [
   * {
   * "province":"北京",
   * "city":"北京市",
   * "adcode":"110000",
   * "weather":"多云",
   * "temperature":"19",
   * "winddirection":"北",
   * "windpower":"6",
   * "humidity":"12",
   * "reporttime":"2017-04-26 22:00:00"
   * }
   * ]
   */

  private String status;
  private String count;
  private String info;
  private String infocode;
  private List<LivesBean> lives;

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public String getCount() {
    return count;
  }

  public void setCount(String count) {
    this.count = count;
  }

  public String getInfo() {
    return info;
  }

  public void setInfo(String info) {
    this.info = info;
  }

  public String getInfocode() {
    return infocode;
  }

  public void setInfocode(String infocode) {
    this.infocode = infocode;
  }

  public List<LivesBean> getLives() {
    return lives;
  }

  public void setLives(List<LivesBean> lives) {
    this.lives = lives;
  }

  public static class LivesBean {
    /**
     * province : 北京
     * city : 北京市
     * adcode : 110000
     * weather : 多云
     * temperature : 19
     * winddirection : 北
     * windpower : 6
     * humidity : 12
     * reporttime : 2017-04-26 22:00:00
     */

    private String province;
    private String city;
    private String adcode;
    private String weather;
    private String temperature;
    private String winddirection;
    private String windpower;
    private String humidity;
    private String reporttime;

    public String getProvince() {
      return province;
    }

    public void setProvince(String province) {
      this.province = province;
    }

    public String getCity() {
      return city;
    }

    public void setCity(String city) {
      this.city = city;
    }

    public String getAdcode() {
      return adcode;
    }

    public void setAdcode(String adcode) {
      this.adcode = adcode;
    }

    public String getWeather() {
      return weather;
    }

    public void setWeather(String weather) {
      this.weather = weather;
    }

    public String getTemperature() {
      return temperature;
    }

    public void setTemperature(String temperature) {
      this.temperature = temperature;
    }

    public String getWinddirection() {
      return winddirection;
    }

    public void setWinddirection(String winddirection) {
      this.winddirection = winddirection;
    }

    public String getWindpower() {
      return windpower;
    }

    public void setWindpower(String windpower) {
      this.windpower = windpower;
    }

    public String getHumidity() {
      return humidity;
    }

    public void setHumidity(String humidity) {
      this.humidity = humidity;
    }

    public String getReporttime() {
      return reporttime;
    }

    public void setReporttime(String reporttime) {
      this.reporttime = reporttime;
    }

    public String getDescribe() {
      return weather + " " +
          temperature + "℃ " +
          winddirection + "风 " +
          (windpower.equals("0") ? "≤3级" : (Integer.parseInt(windpower) + 3) + "级");
    }
  }
}
