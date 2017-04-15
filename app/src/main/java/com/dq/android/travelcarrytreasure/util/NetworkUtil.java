package com.dq.android.travelcarrytreasure.util;

import android.content.Context;
import android.net.ConnectivityManager;
import com.dq.android.travelcarrytreasure.app.TravelApp;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class NetworkUtil {
  public static final boolean ping() {
    try {
      String ip = "www.baidu.com";// ping 的地址，可以换成任何一种可靠的外网
      Process p = Runtime.getRuntime().exec("ping -c 3 -w 100 " + ip);// ping网址3次
      // 读取ping的内容，可以不加
      InputStream input = p.getInputStream();
      BufferedReader in = new BufferedReader(new InputStreamReader(input));
      StringBuffer stringBuffer = new StringBuffer();
      String content = "";
      while ((content = in.readLine()) != null) {
        stringBuffer.append(content);
      }

      // ping的状态
      int status = p.waitFor();
      if (status == 0) {
        return true;
      } else {
      }
    } catch (IOException e) {

    } catch (InterruptedException e) {

    } finally {
    }
    return false;
  }

  public static boolean isNetworkAvailable() {
    // 得到网络连接信息
    ConnectivityManager manager = (ConnectivityManager) TravelApp.getInstance().getSystemService(
        Context.CONNECTIVITY_SERVICE);
    // 去进行判断网络是否连接
    if (manager.getActiveNetworkInfo() != null) {
      return manager.getActiveNetworkInfo().isAvailable();
    }
    return false;
  }
}