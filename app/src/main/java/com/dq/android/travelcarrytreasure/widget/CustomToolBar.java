package com.dq.android.travelcarrytreasure.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.dq.android.travelcarrytreasure.R;

/**
 * 作者：Create on 2017/4/19 21:33  by dq_dana
 * 邮箱：dqdanavera@gmail.com
 * 描述：自定义的ToolBar
 */
public class CustomToolBar extends RelativeLayout implements View.OnClickListener {

  private boolean mIsShowLeft, mIsShowLeftImg, mIsShowRight;
  private String mTitle, mLeftTxt, mRightTxt;
  private int mTitleResColor;
  private boolean mIsTransparent;

  private Context mContext;
  private ImageView mImgBack;
  private TextView mTvTitle, mTvBack, mTvAction;
  private RelativeLayout mLayoutContainer;
  private View mViewDivider;
  private onToolBarListener mListener;

  public CustomToolBar(Context context) {
    super(context);
    initView(context);
  }

  public CustomToolBar(Context context, AttributeSet attrs) {
    super(context, attrs);
    TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.CustomToolBar);
    mTitle = array.getString(R.styleable.CustomToolBar_toolbarTitle);
    mLeftTxt = array.getString(R.styleable.CustomToolBar_leftTxt);
    mRightTxt = array.getString(R.styleable.CustomToolBar_rightTxt);
    mIsShowLeft = array.getBoolean(R.styleable.CustomToolBar_isShowLeft, true); // 默认显示
    mIsShowLeftImg = array.getBoolean(R.styleable.CustomToolBar_isShowLeftImg, false); // 默认不显示
    mIsShowRight = array.getBoolean(R.styleable.CustomToolBar_isShowRight, false); // 默认不显示
    mTitleResColor = array.getColor(R.styleable.CustomToolBar_titleColor, getResources().getColor(R.color.black));
    mIsTransparent = array.getBoolean(R.styleable.CustomToolBar_isTransparent, false);
    array.recycle();
    initView(context);
  }

  public CustomToolBar(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    initView(context);
  }

  private void initView(Context context) {
    this.mContext = context;

    View.inflate(context, R.layout.layout_tool_bar, this);
    mTvTitle = (TextView) findViewById(R.id.tv_title);
    mTvBack = (TextView) findViewById(R.id.tv_back);
    mImgBack = (ImageView) findViewById(R.id.img_back);
    mTvAction = (TextView) findViewById(R.id.tv_action);
    mLayoutContainer = (RelativeLayout) findViewById(R.id.rl_container);
    mViewDivider = findViewById(R.id.view_divider);

    mTvBack.setOnClickListener(this);
    mImgBack.setOnClickListener(this);
    mTvAction.setOnClickListener(this);

    if (mIsTransparent) {
      mLayoutContainer.getBackground().mutate().setAlpha(0);
      mViewDivider.getBackground().mutate().setAlpha(0);
    }
    mTvTitle.setText(mTitle);
    mTvTitle.setTextColor(mTitleResColor);
    mTvBack.setText(mLeftTxt);
    mTvAction.setText(mRightTxt);
    mTvBack.setVisibility(mIsShowLeft ? VISIBLE : INVISIBLE);
    mImgBack.setVisibility(mIsShowLeftImg ? VISIBLE : INVISIBLE);
    mTvAction.setVisibility(mIsShowRight ? VISIBLE : INVISIBLE);
  }

  @Override public void onClick(View v) {
    switch (v.getId()) {
      case R.id.tv_back:
      case R.id.img_back:
        if (mListener != null) {
          mListener.onBackListener();
        }
        break;
      case R.id.tv_action:
        if (mListener != null) {
          mListener.onActionListener();
        }
        break;
    }
  }

  public interface onToolBarListener {
    void onBackListener();

    void onActionListener();
  }

  public void setOnToolBarListener(onToolBarListener listener) {
    this.mListener = listener;
  }
}
