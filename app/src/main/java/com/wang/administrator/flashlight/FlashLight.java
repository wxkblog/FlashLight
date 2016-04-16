package com.wang.administrator.flashlight;

import android.content.pm.PackageManager;
import android.graphics.Point;
import android.graphics.SurfaceTexture;
import android.graphics.drawable.TransitionDrawable;
import android.hardware.Camera;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.Toast;

import java.security.Policy;

/**
 * Created by Administrator on 2016/3/1.
 */
public class FlashLight extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Point point = new Point();

        getWindowManager().getDefaultDisplay().getSize(point);

        LayoutParams layoutParams = (LayoutParams) mImageViewFlashlight.getLayoutParams();
        layoutParams.width = point.x / 2;
        layoutParams.height = layoutParams.width;
        mImageViewFlashlight.setLayoutParams(layoutParams);
        mImageViewFlashlight.setX(point.x / 4);
        mImageViewFlashlight.setY(point.y / 2);
        mImageViewFlashlight.setTag(false);
    }

    public void onClick_flashlight(View view) {
        if (!getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH)) {
            Toast.makeText(this, "当前设备没有闪光灯", Toast.LENGTH_SHORT).show();
            return;
        }
        if (((Boolean) mImageViewFlashlight.getTag() == false)) {
            openFlashlight();
        } else {
            closeFlashlight();
        }
    }

    //    open闪光灯
    protected void openFlashlight() {

        //设置图片变化时间为200ms
        TransitionDrawable transitionDrawable = (TransitionDrawable) mImageViewFlashlight.getDrawable();
        transitionDrawable.startTransition(200);

        //设置标签
        mImageViewFlashlight.setTag(true);

        try {
            mCamera = Camera.open();
            int textureId = 0;
            mCamera.setPreviewTexture(new SurfaceTexture(textureId));
            mCamera.startPreview();

            mParameters = mCamera.getParameters();

            mParameters.setFlashMode(mParameters.FLASH_MODE_TORCH);
            mCamera.setParameters(mParameters);
        } catch (Exception e) {
            //TODO: handle exception
        }
    }

    //    close闪光灯
    protected void closeFlashlight() {
        TransitionDrawable transitionDrawable = (TransitionDrawable) mImageViewFlashlight.getDrawable();
        if (((Boolean) mImageViewFlashlight.getTag())) {
            transitionDrawable.reverseTransition(200);
            mImageViewFlashlight.setTag(false);
            if (mCamera != null) {
                mParameters = mCamera.getParameters();
                mParameters.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
                mCamera.setParameters(mParameters);
                mCamera.stopPreview();
                mCamera.release();
                mCamera = null;
            }
        }
    }

}
