package com.Ehromtj.RainbowAnimator;

import android.content.res.Resources;
import android.util.DisplayMetrics;

public final class RainbowMath {
    public static int dp2px(float f) {
        return Math.round(f * getDisplayMetrics().density);
    }

    public static DisplayMetrics getDisplayMetrics() {
        return Resources.getSystem().getDisplayMetrics();
    }

    public static int getScreenHeight() {
        return getDisplayMetrics().heightPixels;
    }

    public static int getScreenWidth() {
        return getDisplayMetrics().widthPixels;
    }
}

