package com.dq.android.travelcarrytreasure.util;

import android.app.Activity;
import android.net.Uri;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.dq.android.travelcarrytreasure.R;
import com.lzy.imagepicker.loader.ImageLoader;
import java.io.File;

/**
 * 作者：Create on 2017/5/8 13:18  by dq_dana
 * 邮箱：dqdanavera@gmail.com
 * 描述：图片选择的工具类
 */
public class GlideImageLoader implements ImageLoader {

  @Override public void displayImage(Activity activity, String path, final ImageView imageView, int width, int height) {
    Glide.with(activity)
        .load(Uri.fromFile(new File(path)))
        .placeholder(R.mipmap.default_image)
        .error(R.mipmap.default_image)
        .centerCrop()
        .override(width, height)
        .into(imageView);
  }

  @Override
  public void clearMemoryCache() {
    //这里是清除缓存的方法,根据需要自己实现
  }
}
