package com.unisob.vclibs.views;

import android.content.Context;
import android.hardware.Camera;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.io.IOException;

public class Preview extends SurfaceView implements SurfaceHolder.Callback {
 /*   SurfaceHolder mHolder;
    Camera mCamera;
    
    public Preview(Context context, Camera camera) {
        super(context);
        
        // Install a SurfaceHolder.Callback so we get notified when the
        // underlying surface is created and destroyed.
        mHolder = getHolder();
        mCamera = camera;
        mHolder.addCallback(this);
        mHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
    }

    public void surfaceCreated(SurfaceHolder holder) {
        // The Surface has been created, acquire the camera and tell it where
        // to draw.
        mCamera = Camera.open();
        try {
            mCamera.setPreviewDisplay(holder);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void surfaceDestroyed(SurfaceHolder holder) {
        // Surface will be destroyed when we return, so stop the preview.
        // Because the CameraDevice object is not a shared resource, it's very
        // important to release it when the activity is paused.
        mCamera.stopPreview();
        mCamera = null;
    }

    public void surfaceChanged(SurfaceHolder holder, int format, int w, int h) {
        // Now that the size is known, set up the camera parameters and begin
        // the preview.
        Camera.Parameters parameters = mCamera.getParameters();
        parameters.setPreviewSize(w, h);
        mCamera.setParameters(parameters);
        mCamera.startPreview();
    }*/

    /* renamed from: a */
    public Camera camera;

    /* renamed from: b */
    public SurfaceHolder surfaceHolder;

    public Preview(Context context, Camera camera) {
        super(context);
        SurfaceHolder holder = getHolder();
        this.surfaceHolder = holder;
        this.camera = camera;
        holder.addCallback(this);
        this.surfaceHolder.setType(3);
    }

    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
        if (this.surfaceHolder.getSurface() != null) {
            try {
                this.camera.setPreviewDisplay(this.surfaceHolder);
                this.camera.startPreview();
            } catch (IOException unused) {
                this.camera.stopPreview();
            }
        }
    }

    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        try {
            this.camera.setPreviewDisplay(surfaceHolder);
            this.camera.startPreview();
        } catch (IOException unused) {
        }
    }

    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
    }
}