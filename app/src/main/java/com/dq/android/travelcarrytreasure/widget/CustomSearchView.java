package com.dq.android.travelcarrytreasure.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.Editable;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.dq.android.travelcarrytreasure.R;
import com.dq.android.travelcarrytreasure.util.InputMethodUtils;

/**
 * Created by DQDana on 2017/4/5
 * 自定义的搜索框
 */

public class CustomSearchView extends LinearLayout
    implements TextView.OnEditorActionListener {

  private Context mContext;
  private EditText mEdtSearch; // 搜索按钮
  private TextView mTvCover; // 遮盖
  private TextView mTvAction; // 右上角的 搜索/取消
  private boolean isShowAction; // 是否显示 Action ?
  private SearchListener mListener;

  public CustomSearchView(Context context) {
    super(context);
    initView(context);
  }

  public CustomSearchView(Context context, AttributeSet attrs) {
    super(context, attrs);
    TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.SearchView);
    isShowAction = array.getBoolean(R.styleable.SearchView_showAction, false); // 默认不显示
    array.recycle();
    initView(context);
  }

  public CustomSearchView(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    initView(context);
  }

  private void initView(Context context) {
    this.mContext = context;

    View.inflate(context, R.layout.layout_search, this);
    mEdtSearch = (EditText) findViewById(R.id.edt_search);
    mTvCover = (TextView) findViewById(R.id.tv_cover);
    mTvAction = (TextView) findViewById(R.id.tv_action);
    if (!isShowAction) mTvAction.setVisibility(GONE);

    mEdtSearch.setOnEditorActionListener(this);
    mEdtSearch.addTextChangedListener(new CustomWatcher() {
      @Override public void afterTextChanged(Editable s) {
        super.afterTextChanged(s);
        mTvAction.setText(s.toString().isEmpty() ? R.string.cancel : R.string.search);
      }
    });

    mTvAction.setOnClickListener(new OnClickListener() {
      @Override public void onClick(View v) {
        String keyword = mTvAction.getText().toString();
        if (mContext.getString(R.string.cancel).equals(keyword)) {
          mListener.cancel();
        } else {
          mListener.search(mEdtSearch.getText().toString());
        }
      }
    });
  }

  @Override public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
    if (actionId == EditorInfo.IME_ACTION_SEARCH) {
      // 先判断搜索条件是否为空
      String keyword = mEdtSearch.getText().toString();
      if (!keyword.isEmpty()) {
        InputMethodUtils.hideInputMethod(mContext, v);
        mListener.search(keyword);
      }
    }
    return true;
  }

  public interface SearchListener {
    void search(String keyWords);

    void cancel();
  }

  public void setSearchListener(SearchListener listener) {
    this.mListener = listener;
  }

  public void setHint(String hint) {
    mEdtSearch.setHint(hint);
    mTvCover.setText(hint);
  }

  /**
   * 设置优先跳转
   */
  public void setPriorityJump(OnClickListener listener) {
    mEdtSearch.setVisibility(GONE);
    mTvCover.setVisibility(VISIBLE);
    mTvCover.setOnClickListener(listener);
  }
}
