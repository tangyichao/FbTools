package com.fb.tangyc.fbtools.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.fb.tangyc.fbtools.R;
import com.fb.tangyc.fbtools.utils.SystemUtil;


public class FloatButtonLayout extends FrameLayout {
	private static final String TAG = "FloatButtonLayout";

	private GestureDetector mGestureDetector;
	private OnFBLGestureListener mOnFBLGestureListener;
	private boolean mIsScrolling;
	private Context mContext;
	private WindowManager mWindowManager;
	private SharedPreferences mSharedPreferences;
	private ImageView mFloatImageView;
	// private ImageView mFloatImageBack;

	private OnSingleTapConfirmedListener mOnSingleTapConfirmedListener;
	private OnLongPressListener mOnLongPressListener;
	private int mOrientation;

	private enum ScreenEdge {
		TOP, DOWN, LEFT, RIGHT
	};

	private int mWidth, mHeight;

	public FloatButtonLayout(Context context) {
		super(context);

		mContext = context;
		init();
	}

	public FloatButtonLayout(Context context, AttributeSet attrs) {
		super(context, attrs);

		mContext = context;
		// Load attributes

		init();
	}

	@Override
	protected void onFinishInflate() {
		super.onFinishInflate();
		mFloatImageView = (ImageView) this.findViewById(R.id.iv_icon);
		// mFloatImageBack = (ImageView)
		// this.findViewById(R.id.float_image_back);
	}

	@SuppressLint("NewApi")
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:

			break;
		case MotionEvent.ACTION_UP:
			mIsScrolling = false;

			break;
		default:
			break;
		}

		return mGestureDetector.onTouchEvent(event);
	}

	private void init() {
		mSharedPreferences = mContext.getSharedPreferences("config", Context.MODE_PRIVATE);
		mWindowManager = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
		mOnFBLGestureListener = new OnFBLGestureListener();
		mGestureDetector = new GestureDetector(mContext, mOnFBLGestureListener);
		mOrientation = mContext.getResources().getConfiguration().orientation;

	}

	@Override
	protected void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);

		mOrientation = newConfig.orientation;
		WindowManager.LayoutParams layoutParams = (WindowManager.LayoutParams) getLayoutParams();

		adjustLayoutParams(layoutParams);
		mWindowManager.updateViewLayout(FloatButtonLayout.this, layoutParams);
	}

	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		super.onSizeChanged(w, h, oldw, oldh);

		mWidth = w;
		mHeight = h;
	}

	private void adjustLayoutParams(WindowManager.LayoutParams layoutParams) {
		int width, height;
		if (mOrientation == Configuration.ORIENTATION_PORTRAIT) {
			width = SystemUtil.mScreenWidth;
			height = SystemUtil.mScreenHeight;
		} else {
			width = SystemUtil.mScreenHeight;
			height = SystemUtil.mScreenWidth;
		}
		ScreenEdge screenEdge = closeTo(layoutParams.x, layoutParams.y, width, height);
		switch (screenEdge) {
		case TOP:
			layoutParams.y = 0;
			break;
		case DOWN:
			layoutParams.y = height - mHeight;
			break;
		case LEFT:
			layoutParams.x = 0;
			break;
		case RIGHT:
			layoutParams.x = width - mWidth;
			break;
		}
	}

	private ScreenEdge closeTo(int x, int y, int width, int height) {
		ScreenEdge screenEdge = ScreenEdge.TOP;
		if (x > width / 2) {
			if (y > height / 2) {
				if (width - x > height - y) {
					screenEdge = ScreenEdge.DOWN;
				} else {
					screenEdge = ScreenEdge.RIGHT;
				}
			} else {
				if (width - x > y) {
					screenEdge = ScreenEdge.TOP;
				} else {
					screenEdge = ScreenEdge.RIGHT;
				}
			}
		} else {
			if (y > height / 2) {
				if (x > height - y) {
					screenEdge = ScreenEdge.DOWN;
				} else {
					screenEdge = ScreenEdge.LEFT;
				}
			} else {
				if (x > y) {
					screenEdge = ScreenEdge.TOP;
				} else {
					screenEdge = ScreenEdge.LEFT;
				}
			}
		}
		return screenEdge;
	}



	private class OnFBLGestureListener extends GestureDetector.SimpleOnGestureListener {
		@Override
		public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {

			WindowManager.LayoutParams layoutParams = (WindowManager.LayoutParams) getLayoutParams();
			layoutParams.x = (int) (e2.getRawX() - mWidth / 2f);
			layoutParams.y = (int) (e2.getRawY() - mHeight / 2f);
			mWindowManager.updateViewLayout(FloatButtonLayout.this, layoutParams);
			mIsScrolling = true;
			return true;
		}

		@Override
		public boolean onSingleTapConfirmed(MotionEvent e) {
			if (mOnSingleTapConfirmedListener != null) {
				mOnSingleTapConfirmedListener.onSingleTapConfirmed();
				return true;
			}
			return super.onSingleTapConfirmed(e);
		}

		@Override
		public void onLongPress(MotionEvent e) {
			super.onLongPress(e);
			if (mOnLongPressListener != null) {
				mOnLongPressListener.onLongPress();
			}
		}
	}

	public void setOnSingleTapConfirmedListener(OnSingleTapConfirmedListener onSingleTapConfirmedListener) {
		mOnSingleTapConfirmedListener = onSingleTapConfirmedListener;
	}

	public interface OnSingleTapConfirmedListener {
		public void onSingleTapConfirmed();
	}

	public void setOnLongPressListenerListener(OnLongPressListener onLongPressListener) {
		mOnLongPressListener = onLongPressListener;
	}

	public interface OnLongPressListener {
		public void onLongPress();
	}

	public void setImageResource(int resId) {
		mFloatImageView.setImageResource(resId);
	} 

	public void setVisibility(int visibility) {
	
		super.setVisibility(visibility);
		AlphaAnimation animation = null;
		if (visibility == View.VISIBLE) {
			animation = new AlphaAnimation(0.0f, 1.0f);

		} else {
			animation = new AlphaAnimation(1.0f, 0.0f);
		}
		animation.setFillAfter(true);
		animation.setDuration(2000);
		startAnimation(animation);
	}

}
