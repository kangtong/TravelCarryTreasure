package com.dq.android.travelcarrytreasure.retrofit;

import java.io.Serializable;

/**
 * Created by Jam on 16-8-12
 * Description:
 */
public class BaseModel<T> implements Serializable {
  public String resultcode;
  public String reason;
  public T result;

  public boolean success() {
    return resultcode.equals("200");
  }
}
