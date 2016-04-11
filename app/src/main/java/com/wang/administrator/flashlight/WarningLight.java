package com.wang.administrator.flashlight;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

/**
 * Created by Administrator on 2016/3/3.
 */
public class WarningLight extends FlashLight{

    protected boolean mWaringFlicker;//true:闪烁 false：停止闪烁
    protected boolean mWaringState;//true: on-off false:off-on


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mWaringFlicker = true;

    }

    class WarningLightThread extends Thread{
        @Override
        public void run() {
            mWaringFlicker = true;
            while (mWaringFlicker){
                try{
                    Thread.sleep(300);
                    mWarningHandler.sendEmptyMessage(0);

                }catch (Exception e){

                }
            }
        }
    }

    private Handler mWarningHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            if(mWaringState){
                mImageViewWaringLight1.setImageResource(R.drawable.warning_on);
                mImageViewWaringLight2.setImageResource(R.drawable.warning_off);
                mWaringState = false;
            }else{
                mImageViewWaringLight1.setImageResource(R.drawable.warning_off);
                mImageViewWaringLight2.setImageResource(R.drawable.warning_on);
                mWaringState = true;
            }
        }
    };
}
