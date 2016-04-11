package com.wang.administrator.flashlight;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.widget.TextView;


/**
 * Created by Administrator on 2016/3/8.
 */
public class HideTextView extends TextView {
    public HideTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    protected Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 0) {
                setVisibility(GONE);
            } else if (msg.what == 1) {
                setVisibility(VISIBLE);
            }
        }
    };

    class TextViewThread extends Thread {
        public void run() {
            mHandler.sendEmptyMessage(1);
            try {
                sleep(3000);
                mHandler.sendEmptyMessage(0);
            } catch (Exception e) {

            }
        }
    }

    public void Hide(){
        new TextViewThread().start();
    }
}
