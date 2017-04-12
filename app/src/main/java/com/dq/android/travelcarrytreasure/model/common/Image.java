package com.dq.android.travelcarrytreasure.model.common;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by DQDana on 2017/4/5
 */

public class Image implements Parcelable {

  /**
   * id : 10
   * name : 手机/配件
   * url : http://l.xgimg.net/img/category/shoujipijian.png
   */

  private String id;
  private String name;
  private String url;

  public Image() {
  }

  public Image(String id, String name, String url) {
    this.id = id;
    this.name = name;
    this.url = url;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  @Override public int describeContents() {
    return 0;
  }

  @Override public void writeToParcel(Parcel dest, int flags) {
    dest.writeString(this.id);
    dest.writeString(this.name);
    dest.writeString(this.url);
  }

  protected Image(Parcel in) {
    this.id = in.readString();
    this.name = in.readString();
    this.url = in.readString();
  }

  public static final Creator<Image> CREATOR = new Creator<Image>() {
    @Override public Image createFromParcel(Parcel source) {
      return new Image(source);
    }

    @Override public Image[] newArray(int size) {
      return new Image[size];
    }
  };
}
