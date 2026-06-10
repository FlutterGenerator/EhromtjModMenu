package com.Ehromtj.RainbowAnimator;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

public abstract class RainbowStart extends TextView {
    public RainbowStart(Context context) {
        this(context, null);
    }

    public RainbowStart(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public RainbowStart(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public abstract void animateText(CharSequence charSequence);

    public abstract void setAnimationListener(RainbowActivation animationListener);

    public abstract void setProgress(float f);
}

