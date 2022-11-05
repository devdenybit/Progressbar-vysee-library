package com.unisob.vclibs.views;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatButton;


public class CustomButton extends AppCompatButton {


    private static Typeface typeface;

    public CustomButton(Context context) {
        super(context);
        init(context);
    }

    public CustomButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public CustomButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }


    private void init(Context context) {
        /*if (typeface == null) {
            typeface = Typeface.createFromAsset(context.getAssets(), "fonts/calibri.ttf");
        }
        this.setTypeface(typeface);*/
    }
}
