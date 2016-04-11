package com.wang.administrator.flashlight;

import android.app.Activity;
import android.hardware.Camera;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

/**
 * Created by Administrator on 2016/3/1.
 */
public class BaseActivity extends Activity {
    protected ImageView mImageViewFlashlight;//手电筒
    protected ImageView mImageViewWaringLight1;//上面的闪光灯
    protected ImageView mImageViewWaringLight2;//下面的闪光灯
    protected EditText mEditTextMorseCode;//morse的编辑框
    protected ImageView mImageViewBulb;//灯泡的图片
//    protected HideTextView mHideTextViewColorLight;//彩色灯的文字显示

    //    protected ImageView mImageViewFlashlightController;
    //设置一个照相机
    protected Camera mCamera;
    protected android.hardware.Camera.Parameters mParameters;

    protected RelativeLayout mFlashlightUI;//手电筒界面UI
    protected LinearLayout mMainUI;//主界面（功能界面）UI
    protected LinearLayout mWarningLightUI;//闪光灯界面UI
    protected LinearLayout mMorseUI;//morse界面UI
    protected FrameLayout mBulbUI;//灯泡界面UI
    protected FrameLayout mColorLightUI;//彩色灯的UI

    //设置一个枚举类，枚举每个ui界面
    protected enum UIType {
        MAIN, FLASHLIGHT, WARNING, MORSE, BLUB, COLOR, POLICE, SETTINGS
        //warning,morse,blub,color,police,settings
    }

    protected UIType mCurrentUIType = UIType.FLASHLIGHT;//当前所在UI
    protected UIType mLastUIType = UIType.FLASHLIGHT;//上一个UI

    protected int mDefaultScreenBrightness;//存储屏幕默认亮度

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mImageViewFlashlight = (ImageView) findViewById(R.id.imageview_flashlight);
//        mImageViewFlashlightController = (ImageView) findViewById(R.id.imageview_flashlight_controller);
        mImageViewWaringLight1 = (ImageView) findViewById(R.id.waring_light1);
        mImageViewWaringLight2 = (ImageView) findViewById(R.id.waring_light2);
        mImageViewBulb = (ImageView) findViewById(R.id.imageview_bulb);
        mEditTextMorseCode = (EditText) findViewById(R.id.morse_code);

        mFlashlightUI = (RelativeLayout) findViewById(R.id.flashlight_ui);
        mMainUI = (LinearLayout) findViewById(R.id.mian_ui);
        mWarningLightUI = (LinearLayout) findViewById(R.id.warning_ui);
        mMorseUI = (LinearLayout) findViewById(R.id.morse_ui);
        mBulbUI = (FrameLayout) findViewById(R.id.bulb_ui);
        mColorLightUI=(FrameLayout)findViewById(R.id.colorlight_ui);


        mDefaultScreenBrightness = getScreenBrightness();//获取原本的屏幕亮度

    }


    //隐藏UI
    protected void hideALLUI() {
        mFlashlightUI.setVisibility(View.GONE);
        mMainUI.setVisibility(View.GONE);
        mWarningLightUI.setVisibility(View.GONE);
        mMorseUI.setVisibility(View.GONE);
        mBulbUI.setVisibility(View.GONE);
        mColorLightUI.setVisibility(View.GONE);
    }

    //设置屏幕亮度
    protected void screenBrightness(float value) {
        try {
            WindowManager.LayoutParams layoutParams = getWindow().getAttributes();
            layoutParams.screenBrightness = value;
            getWindow().setAttributes(layoutParams);
        } catch (Exception e) {

        }
    }

    //返回的亮度是0~255,获得屏幕亮度
    protected int getScreenBrightness() {
        int value = 0;
        try {
            value = android.provider.Settings.System.getInt(getContentResolver(),
                    android.provider.Settings.System.SCREEN_BRIGHTNESS);
        } catch (Exception e) {

        }
        return value;
    }
}
