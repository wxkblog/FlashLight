package com.wang.administrator.flashlight;

import android.graphics.Point;
import android.graphics.drawable.TransitionDrawable;
import android.os.Bundle;
import android.transition.Transition;
import android.view.View;
import android.view.ViewGroup.LayoutParams;

/**
 * Created by Administrator on 2016/3/8.
 */
public class Bulb extends Morse {
    protected boolean mBulbCrossFadeFlag;
    protected TransitionDrawable mDrawable;
    protected HideTextView mHideTextViewBulb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDrawable = (TransitionDrawable) mImageViewBulb.getDrawable();
        mHideTextViewBulb = (HideTextView) findViewById(R.id.textview_hide_bulb);
        Point point = new Point();
        getWindowManager().getDefaultDisplay().getSize(point);

        LayoutParams layoutParams = (LayoutParams) mImageViewBulb.getLayoutParams();
        layoutParams.width = point.x / 2;
        layoutParams.height = layoutParams.width;
        mImageViewBulb.setLayoutParams(layoutParams);
        mImageViewBulb.setX(point.x / 4);
        mImageViewBulb.setY(point.y / 3);
    }

    //控制灯泡的明暗变化
    public void onClick_BulbCrossFade(View view) {
        mHideTextViewBulb.setVisibility(View.GONE);
        if (!mBulbCrossFadeFlag) {
            mDrawable.startTransition(500);
            mBulbCrossFadeFlag = true;
        } else {
            mDrawable.reverseTransition(500);
            mBulbCrossFadeFlag = false;
        }
    }
}
