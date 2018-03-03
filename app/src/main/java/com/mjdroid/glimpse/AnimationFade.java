package com.mjdroid.glimpse;

import android.view.View;
import android.view.animation.AlphaAnimation;


public class AnimationFade{
    long mDuration;
    View mView;

    public AnimationFade (long duration, View view) {
        mDuration = duration;
        mView = view;
    }

    public void startFadeIn() {
        //Animation constructor params: starting alpha and ending alpha
        AlphaAnimation fadeIn = new AlphaAnimation(0.0f, 1.0f);
        fadeIn.setDuration(mDuration);
        //To not let the animation persist after ending setFillAfter false
        fadeIn.setFillAfter(false);
        mView.startAnimation(fadeIn);
        mView.setVisibility(View.VISIBLE);

    }

    public void startFadeOut(){
        AlphaAnimation fadeOut = new AlphaAnimation(1.0f, 0.0f);
        fadeOut.setDuration(mDuration);
        fadeOut.setFillAfter(false);
        mView.startAnimation(fadeOut);
        mView.setVisibility(View.INVISIBLE);
    }
}
