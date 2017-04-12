package com.dq.android.travelcarrytreasure.model.common;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by DQDana on 2017/4/5
 */

public class City implements Parcelable {

  /**
   * sname : 拉萨
   * surl : lasa
   */

  private String sname = "";
  private String surl = "";
  private String sinitials = "";

  public City() {
  }

  public City(String sname, String surl) {
    this.sname = sname;
    this.surl = surl;
  }

  public City(String sname, String surl, String sinitials) {
    this.sname = sname;
    this.surl = surl;
    this.sinitials = sinitials;
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

  public String getSinitials() {
    return sinitials;
  }

  public void setSinitials(String sinitials) {
    this.sinitials = sinitials;
  }

  @Override public int describeContents() {
    return 0;
  }

  @Override public void writeToParcel(Parcel dest, int flags) {
    dest.writeString(this.sname);
    dest.writeString(this.surl);
    dest.writeString(this.sinitials);
  }

  protected City(Parcel in) {
    this.sname = in.readString();
    this.surl = in.readString();
    this.sinitials = in.readString();
  }

  public static final Creator<City> CREATOR = new Creator<City>() {
    @Override public City createFromParcel(Parcel source) {
      return new City(source);
    }

    @Override public City[] newArray(int size) {
      return new City[size];
    }
  };
}
