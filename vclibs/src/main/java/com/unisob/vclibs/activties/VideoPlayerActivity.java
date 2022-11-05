package com.unisob.vclibs.activties;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.hardware.Camera;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.view.View;
import android.view.Window;
import android.view.WindowInsets;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.CheckBox;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.view.ViewCompat;

import com.devbrackets.android.exomedia.listener.OnCompletionListener;
import com.devbrackets.android.exomedia.listener.OnErrorListener;
import com.devbrackets.android.exomedia.listener.OnPreparedListener;
import com.devbrackets.android.exomedia.ui.widget.VideoView;
import com.unisob.vclibs.R;
import com.unisob.vclibs.mads.AppManage;
import com.unisob.vclibs.unitlity.TestActivity;
import com.unisob.vclibs.views.Preview;

import java.util.Iterator;

public class VideoPlayerActivity extends AppCompatActivity implements OnPreparedListener {

    VideoView videoView;
    LinearLayout txtwait;
    Boolean check_micro = true;
    Boolean check_video = true;
    ImageView iv_camera, iv_microphone, iv_videoicon;

    public int cameraid = 1;
    public Camera camera;
    FrameLayout camera_preview;
    public Preview preview;

    VideoPlayerActivity videoPlayerActivity;
    public boolean status = true;
    public static int routedid;

    CountDownTimer myCountdownTimer;

    TextView cntr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        savedInstanceState = getIntent().getExtras();
        if (savedInstanceState == null) {
            finish();
        }
        setContentView(R.layout.activity_video_player);

        Window window = getWindow();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        window.addFlags(WindowManager.LayoutParams.FLAGS_CHANGED);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(Color.BLACK);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView().setSystemUiVisibility(View.VISIBLE);//  set status text dark
        }

        cntr = findViewById(R.id.cntr_down);

        myCountdownTimer = new CountDownTimer(30000, 1000) {

            public void onTick(long millisUntilFinished) {
                cntr.setText("00:" + millisUntilFinished / 1000);
            }

            public void onFinish() {
                myCountdownTimer.cancel();
                Toast.makeText(VideoPlayerActivity.this, "No User Found!", Toast.LENGTH_SHORT).show();
                next_call();
            }
        }.start();

        myCountdownTimer.start();


        String title = (savedInstanceState.getString("title"));
        camera_preview = (FrameLayout) findViewById(R.id.camera_preview);
        videoView = (VideoView) findViewById(R.id.video_view);
        videoView.setOnPreparedListener(this);
        videoView.setVideoURI(Uri.parse(title.replace("http://", "https://")));
        videoView.setOnCompletionListener(new OnCompletionListener() {
            @Override
            public void onCompletion() {
                onBackPressed();
            }
        });

        findViewById(R.id.tv_reportf).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FBtnReportUser(v);
            }
        });

        videoView.setOnErrorListener(new OnErrorListener() {
            @Override
            public boolean onError(Exception e) {
                Toast.makeText(videoPlayerActivity, "Something want to wrong!!!", Toast.LENGTH_SHORT).show();
                return false;
            }
        });

        findViewById(R.id.iv_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        txtwait = (LinearLayout) findViewById(R.id.txtwait);
        Animation anim = new AlphaAnimation(0.0f, 1.0f);
        anim.setDuration(500); //You can manage the blinking time with this parameter
        anim.setStartOffset(50);
        anim.setRepeatMode(Animation.REVERSE);
        anim.setRepeatCount(Animation.INFINITE);
        findViewById(R.id.anim_text).startAnimation(anim);

        findViewById(R.id.cancel_connect).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


        iv_microphone = (ImageView) findViewById(R.id.iv_microphone);
        iv_microphone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!check_micro) {
                    iv_microphone.setImageResource(R.drawable.mic_on);
                    check_micro = true;
                    // Toast.makeText(VideoPlayerActivity.this, "unmute", Toast.LENGTH_SHORT).show();
                } else {
                    iv_microphone.setImageResource(R.drawable.mic_off);
                    check_micro = false;
                    // Toast.makeText(VideoPlayerActivity.this, "mute", Toast.LENGTH_SHORT).show();
                }
            }
        });

        iv_camera = (ImageView) findViewById(R.id.iv_camera);
        iv_camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (status) {
                    if (cameraid == 0) {
                        cameraid = 1;
                    } else {
                        cameraid = 0;
                    }
                    routedCamera();
                    CameraView();
                }
                int i = routedid + 1;
                routedid = i;
                if (i >= Integer.parseInt("0")) {
                    routedid = 0;
                }
            }
        });
        iv_videoicon = (ImageView) findViewById(R.id.iv_videoicon);
        iv_videoicon.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("UseCompatLoadingForDrawables")
            @Override
            public void onClick(View v) {

                if (!check_video) {
                    check_video = true;
                    iv_videoicon.setImageResource(R.drawable.on_videos);
                    routedCamera();
                    CameraView();
                } else {
                    check_video = false;
                    iv_videoicon.setImageResource(R.drawable.off_videos);
                    routedCamera();
                    if (camera_preview.getChildCount() > 0) {
                        camera_preview.removeAllViews();
                    }
                }
                int i = routedid + 1;
                routedid = i;
                if (i >= Integer.parseInt("0")) {
                    routedid = 0;
                }
            }
        });


        CameraView();
    }

    public void routedCamera() {
        Camera camera = this.camera;
        if (camera != null) {
            camera.release();
            this.camera = null;
        }
    }

    public void onDestroy() {
        Camera camera = this.camera;
        if (camera != null) {
            camera.release();
            this.camera = null;
        }
        super.onDestroy();
    }

    public final void CameraView() {
        try {
            Camera open = Camera.open(this.cameraid);
            camera = open;
            Camera.Parameters parameters = open.getParameters();
            Camera.Size size = parameters.getSupportedPreviewSizes().get(0);
            Iterator<Camera.Size> it = parameters.getSupportedPreviewSizes().iterator();
            while (true) {
                if (it.hasNext()) {
                    Camera.Size next = it.next();
                    if (next.width >= 150 && next.height >= 200) {
                        size = next;
                        break;
                    }
                } else {
                    break;
                }
            }
            parameters.setPreviewSize(size.width, size.height);
            Camera.Size size2 = parameters.getSupportedPictureSizes().get(0);
            Iterator<Camera.Size> it2 = parameters.getSupportedPictureSizes().iterator();
            while (true) {
                if (it2.hasNext()) {
                    Camera.Size next2 = it2.next();
                    if (next2.width == size.width && next2.height == size.height) {
                        size2 = next2;
                        break;
                    }
                } else {
                    break;
                }
            }
            parameters.setPictureSize(size2.width, size2.height);
            camera.setDisplayOrientation(90);
        } catch (Exception unused) {
        }
        if (camera_preview.getChildCount() > 0) {
            camera_preview.removeAllViews();
        }
        Preview dVar = new Preview(this, camera);
        preview = dVar;
        camera_preview.addView(dVar);
    }

    @Override
    public void onPrepared() {

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                txtwait.setVisibility(View.GONE);
                int Vibration = 200;
                myCountdownTimer.cancel();
                Toast.makeText(VideoPlayerActivity.this, "Call Connected", Toast.LENGTH_SHORT).show();
                Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    v.vibrate(VibrationEffect.createOneShot(Vibration, VibrationEffect.DEFAULT_AMPLITUDE));
                } else {
                    //deprecated in API 26
                    v.vibrate(Vibration);
                }
                videoView.start();
            }
        }, 2000);

    }

    public void FBtnReportUser(View view) {
        CheckBox c1, c2, c3, c4, c5, c6, c7;
        Dialog dialog = new Dialog(this, R.style.Transparent);
        dialog.setContentView(R.layout.report_dailog);

        c1 = dialog.findViewById(R.id.r1);
        c2 = dialog.findViewById(R.id.r2);
        c3 = dialog.findViewById(R.id.r3);
        c4 = dialog.findViewById(R.id.r4);
        c5 = dialog.findViewById(R.id.r5);
        c6 = dialog.findViewById(R.id.r6);
        c7 = dialog.findViewById(R.id.r7);

        ((TextView) dialog.findViewById(R.id.no)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        ((TextView) dialog.findViewById(R.id.reyes)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (c1.isChecked() || c2.isChecked() || c3.isChecked() || c4.isChecked() || c5.isChecked() || c6.isChecked() || c7.isChecked()) {
                    Toast.makeText(VideoPlayerActivity.this, "Report Submitted", Toast.LENGTH_SHORT).show();
                    onBackPressed();
                    dialog.dismiss();
                } else {
                    Toast.makeText(VideoPlayerActivity.this, "Select Reason", Toast.LENGTH_SHORT).show();
                }
            }
        });
        dialog.show();
    }

    @Override
    public void onBackPressed() {
        try {
            Toast.makeText(VideoPlayerActivity.this, "Call Disconnected", Toast.LENGTH_SHORT).show();
            int Vibration = 200;
            myCountdownTimer.cancel();
            Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                v.vibrate(VibrationEffect.createOneShot(Vibration, VibrationEffect.DEFAULT_AMPLITUDE));
            } else {
                //deprecated in API 26
                v.vibrate(Vibration);
            }

            next_call();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void next_call() {
        myCountdownTimer.cancel();
        startActivity(new Intent(VideoPlayerActivity.this, HomePageActivity.class).putExtra("rate", true));
        finish();
    }

}
