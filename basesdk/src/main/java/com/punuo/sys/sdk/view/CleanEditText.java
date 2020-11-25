package com.punuo.sys.sdk.view;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.AppCompatEditText;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.animation.Animation;
import android.view.animation.CycleInterpolator;
import android.view.animation.TranslateAnimation;

public class CleanEditText extends AppCompatEditText {
	
	private final String TAG = "editText";
	
	private Drawable dRight;

	public CleanEditText(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		initEditText();
	}

	public CleanEditText(Context context, AttributeSet attrs) {
		super(context, attrs);
		initEditText();
	}

	public CleanEditText(Context context) {
		super(context);
		initEditText();
	}

	private void initEditText() {
		setEditTextDrawable();
		addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {

			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {

			}

			@Override
			public void afterTextChanged(Editable s) {
				CleanEditText.this.setEditTextDrawable();
			}
		});

	}

	public void setEditTextDrawable() {
		if (getText().toString().length() == 0) {
			setCompoundDrawables(null, null, null, null);
		} else {
			setCompoundDrawables(null, null, this.dRight, null);
		}
	}

	@Override
	protected void onDetachedFromWindow() {
		super.onDetachedFromWindow();
		this.dRight = null;
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if ((this.dRight != null) && (event.getAction() == MotionEvent.ACTION_DOWN)) {
			if (event.getX()> getWidth() - getPaddingRight() - dRight.getIntrinsicWidth()) {
				setText("");
			}
		}
		return super.onTouchEvent(event);
	}

	@Override
	protected void onFocusChanged(boolean focused, int direction,
			Rect previouslyFocusedRect) {
		if (focused) {
			CleanEditText.this.setEditTextDrawable();
		} else {
			setCompoundDrawables(null, null, null, null);
		}
		
		super.onFocusChanged(focused, direction, previouslyFocusedRect);

	}

	@Override
	public void setCompoundDrawables(Drawable left, Drawable top,
			Drawable right, Drawable bottom) {
		if (right != null) {
			this.dRight = right;
		}
		super.setCompoundDrawables(left, top, right, bottom);
	}

	public void setShakeAnimation() {
		this.setAnimation(shakeAnimation(5));
	}

	public static Animation shakeAnimation(int counts) {
		Animation translateAnimation = new TranslateAnimation(0, 10, 0, 0);
		translateAnimation.setInterpolator(new CycleInterpolator(counts));
		translateAnimation.setDuration(500);
		return translateAnimation;
	}

}
