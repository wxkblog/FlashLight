package com.wang.administrator.flashlight;

import android.graphics.Color;
import android.view.View;

public class MainActivity extends ColorLight {

    //为手电筒设置监控
    public void onClick_ToFlashlight(View view) {
        hideALLUI();
        mFlashlightUI.setVisibility(View.VISIBLE);
        mLastUIType = UIType.FLASHLIGHT;
        mCurrentUIType = UIType.FLASHLIGHT;
    }

    //为闪光灯设置监控
    public void onClick_ToWarningLight(View view) {
        hideALLUI();
        mWarningLightUI.setVisibility(View.VISIBLE);
        mLastUIType = UIType.WARNING;
        mCurrentUIType = mLastUIType;
        screenBrightness(1f);//0是最暗，1是最亮
        new WarningLightThread().start();
    }

    //为Morse设置监控
    public void onClick_ToMorseCode(View view) {
        hideALLUI();
        mMorseUI.setVisibility(View.VISIBLE);
        mLastUIType = UIType.MORSE;
        mCurrentUIType = mLastUIType;
    }

    //为电灯泡设置监控
    public void onClick_ToBlub(View view) {
        hideALLUI();
        mBulbUI.setVisibility(View.VISIBLE);
        mLastUIType = UIType.BLUB;
        mCurrentUIType = mLastUIType;
    }

    //为彩色灯设置监控
    public void onClick_ToColor(View view) {
        hideALLUI();
        mColorLightUI.setVisibility(View.VISIBLE);
        screenBrightness(1f);
        mLastUIType = UIType.COLOR;
        mCurrentUIType = mLastUIType;

        mHideTextViewColorLight.setTextColor(Color.BLACK);
    }

    //为主界面设置监控
    public void onClick_Controller(View view) {
        hideALLUI();
        if (mCurrentUIType != UIType.MAIN) {
            mMainUI.setVisibility(View.VISIBLE);
            mCurrentUIType = UIType.MAIN;
            mWaringFlicker = false;
            //回答主界面时，屏幕亮度改为原来的亮度，这里要将值转换一下
            screenBrightness(mDefaultScreenBrightness / 255f);

            //返回主界面时，应该将灯泡关掉
            if (mBulbCrossFadeFlag) mDrawable.reverseTransition(0);
            mBulbCrossFadeFlag = false;

        } else {
            switch (mLastUIType) {
                case FLASHLIGHT:
                    mFlashlightUI.setVisibility(View.VISIBLE);
                    mCurrentUIType = UIType.FLASHLIGHT;
                    break;

                case WARNING:
                    mWarningLightUI.setVisibility(View.VISIBLE);
                    mCurrentUIType = UIType.WARNING;
                    screenBrightness(1f);
                    new WarningLightThread().start();
                    break;

                case MORSE:
                    mMorseUI.setVisibility(View.VISIBLE);
                    mCurrentUIType = UIType.MORSE;
                    break;

                case BLUB:
                    mBulbUI.setVisibility(View.VISIBLE);
                    mCurrentUIType = UIType.BLUB;
                    break;
                default:
                    break;
            }
        }
    }
}
