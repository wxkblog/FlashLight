package com.wang.administrator.flashlight;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

/**
 * Created by Administrator on 2016/3/8.
 */
public class ColorLight extends Bulb{
    protected int mCurrentColorLight = Color.WHITE;//当前颜色，默认为白色
    protected HideTextView mHideTextViewColorLight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mHideTextViewColorLight=(HideTextView)findViewById(R.id.textview_hide_colorlight);
    }

    //显示调色板的方法
    public void onClick_ShowColorPicker(View view){

    }
}
