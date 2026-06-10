//Please don't replace listeners with lambda!
package com.Ehromtj.NativeClass;

import android.animation.ValueAnimator;
import android.annotation.TargetApi;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Service;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.text.Html;
import android.text.InputFilter;
import android.text.InputType;
import android.text.TextUtils;
import android.text.method.DigitsKeyListener;
import android.util.Base64;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import android.widget.FrameLayout.LayoutParams;
import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;
import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;
import static android.widget.RelativeLayout.ALIGN_PARENT_LEFT;
import static android.widget.RelativeLayout.ALIGN_PARENT_RIGHT;
import android.media.MediaPlayer;
import java.io.File;
import com.Ehromtj.RainbowAnimator.RainbowActivity;
import android.animation.ObjectAnimator;
import android.animation.Animator;
import android.content.Context;
import android.text.method.LinkMovementMethod;
import android.view.Window;
import android.animation.ArgbEvaluator;
import android.animation.TimeAnimator;
import android.graphics.drawable.StateListDrawable;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.view.animation.AlphaAnimation;
public class EhromtjActivity extends Service {

    //********** Here you can easly change the menu appearance **********//
    public static final String TAG = "Mod_Menu"; //Tag for logcat
    int TEXT_COLOR = Color.parseColor("#82CAFD");
    int TEXT_COLOR_2 = Color.parseColor("#FFFFFF");
    int BTN_COLOR = Color.parseColor("#1C262D");
    int MENU_BG_COLOR = Color.parseColor("#FF1C2A35"); //#AARRGGBB
    int MENU_FEATURE_BG_COLOR = Color.parseColor("#20000000"); //#AARRGGBB
    int MENU_WIDTH = 290;
    int MENU_HEIGHT = 300;
    float MENU_CORNER = 4f;
    int ICON_SIZE = 60; //Change both width and height of image
    float ICON_ALPHA = 1.0f; //Transparent
    int ToggleON = Color.GREEN;
    int ToggleOFF = Color.RED;
    int BtnON = Color.parseColor("#1b5e20");
    int BtnOFF = Color.parseColor("#7f0000");
    int CategoryBG = Color.parseColor("#2F3D4C");
    int SeekBarColor = Color.parseColor("#80CBC4");
    int SeekBarProgressColor = Color.parseColor("#80CBC4");
    int CheckBoxColor = Color.parseColor("#80CBC4");
    int RadioColor = Color.parseColor("#FFFFFF");
    String NumberTxtColor = "#41c300";
    //********************************************************************//
    RelativeLayout mCollapsed, mRootContainer;
    LinearLayout mExpanded, patches, mCollapse;
    LinearLayout.LayoutParams scrlLLExpanded, scrlLL;
    WindowManager mWindowManager;
    WindowManager.LayoutParams params;
    ImageView startimage;
    FrameLayout rootFrame;
    ScrollView scrollView;

    boolean stopChecking;
	MediaPlayer FXPlayer;
	boolean soundDelayed;
    String cacheDir;
	native void LoadSounds(String dir);
    native void setTitleText(TextView textView);
    native String Icon();
    native String EhromtjBG();
    native String EhromtjIC();
    native String EhromtjLG();
    native String EhromtjBGEDT();
    native String CloseB();
    native String HideB();
    native String IconWebViewData();
    native String[] getFeatureList();
    native boolean isGameLibLoaded();

    @Override
    public void onCreate() {
        super.onCreate();
        EhromtjPref.context = this;
		cacheDir = getCacheDir().getPath() + "/";
        LoadSounds(cacheDir);

        StartMenuActivity();

        //Create a handler for this Class
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            public void run() {
                Thread();
                handler.postDelayed(this, 1000);
            }
        });
    }

    public static Animation fadeout() { 
        ScaleAnimation alphaAnimation = new ScaleAnimation(0, 1, 0, 1, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 1.0f); 
        alphaAnimation.setDuration((long)500); 
        return alphaAnimation; 
    } 
    public static Animation fadein() { 
        ScaleAnimation alphaAnimation = new ScaleAnimation(1, 0, 1, 0, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 1.0f); 
        alphaAnimation.setDuration((long)500); 
        return alphaAnimation; 
    }
    
    private void RainbowToast(Context context, String message, String typeface) {
        Toast toast = Toast.makeText(context, message, Toast.LENGTH_LONG);

        RelativeLayout ToastLayout = new RelativeLayout(context);
        ToastLayout.setVerticalGravity(Gravity.CENTER);

        ImageView ToastImg = new ImageView(context);
        ToastImg.setPadding(dp(5), dp(5), dp(10), dp(10));
        ToastImg.setAdjustViewBounds(true);
        ToastImg.setLayoutParams(new LayoutParams(dp(50), dp(50)));

        GradientDrawable ToastG = new GradientDrawable();
        ToastG.setShape(GradientDrawable.RECTANGLE);
        ToastG.setCornerRadius(dp(7));
        ToastG.setSize(dp(45),dp(45));
        ToastG.setStroke(dp(2), Color.WHITE);

        RainbowActivity ToastR = new RainbowActivity(context);
        ToastR.setText("ㅤ  ㅤ" + message + "  ");
        ToastR.setTextSize(20.0f);
        ToastR.setSingleLine(true);
        ToastR.setTypeface(Typeface.createFromAsset(context.getAssets(), "Ehromtj/DaisyFonts/" + typeface));
        ToastR.setBackgroundDrawable(ToastG);
        ToastR.setGravity(Gravity.CENTER);
        ToastR.setTextColor(Color.WHITE);
        byte[] decode1 = Base64.decode(EhromtjLG(), 0);
        ToastImg.setImageBitmap(BitmapFactory.decodeByteArray(decode1, 0, decode1.length));
        ToastG.setColor(Color.parseColor("#90FF00FF"));
            
        ToastLayout.addView(ToastR);
        ToastLayout.addView(ToastImg);
        toast.setView(ToastLayout);
        toast.show();
    }
    
    public void StartMenuActivity() {
        rootFrame = new FrameLayout(this);
        rootFrame.setOnTouchListener(onTouchListener());
        mRootContainer = new RelativeLayout(this);
        mCollapsed = new RelativeLayout(this);
        ObjectAnimator animation = ObjectAnimator.ofFloat(mCollapsed, "alpha", 0, 1.0f);
        animation.setDuration(3000);
        animation.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animation) {
                }
                @Override
                public void onAnimationEnd(Animator animation) {
                }
                @Override
                public void onAnimationCancel(Animator animation) {
                }
                @Override
                public void onAnimationRepeat(Animator animation) {
                }
            });
        animation.start();
        mCollapsed.setVisibility(View.VISIBLE);
        mCollapsed.setAlpha(ICON_ALPHA);

        //********** The box of the mod menu **********
        mExpanded = new LinearLayout(this); // Menu markup (when the menu is expanded)
        mExpanded.setVisibility(View.GONE);
        mExpanded.setOrientation(LinearLayout.VERTICAL);
        mExpanded.setLayoutParams(new LinearLayout.LayoutParams(dp(MENU_WIDTH),dp(MENU_HEIGHT)));
        byte[] bgdecode = Base64.decode(EhromtjBG(), 0);
        Bitmap bgimg = BitmapFactory.decodeByteArray(bgdecode,0,bgdecode.length);
        BitmapDrawable bgdrawable = new BitmapDrawable(bgimg);
        bgdrawable.setGravity(Gravity.FILL);
        mExpanded.setBackgroundDrawable(bgdrawable);

        //********** The icon to open mod menu **********
        startimage = new ImageView(this);
        startimage.setLayoutParams(new RelativeLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT));
        int applyDimension = (int) TypedValue.applyDimension(1, ICON_SIZE, getResources().getDisplayMetrics()); //Icon size
        startimage.getLayoutParams().height = applyDimension;
        startimage.getLayoutParams().width = applyDimension;
        //startimage.requestLayout();
        startimage.setScaleType(ImageView.ScaleType.FIT_XY);
        byte[] decode = Base64.decode(EhromtjIC(), 0);
        startimage.setImageBitmap(BitmapFactory.decodeByteArray(decode, 0, decode.length));
        ((ViewGroup.MarginLayoutParams) startimage.getLayoutParams()).topMargin = convertDipToPixels(10);
        //Initialize event handlers for buttons, etc.
        startimage.setOnTouchListener(onTouchListener());
        startimage.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                mCollapsed.setVisibility(View.GONE);
                mExpanded.setVisibility(View.VISIBLE);
            }
        });

        //********** The icon in Webview to open mod menu **********
        WebView wView = new WebView(this); //Icon size width=\"50\" height=\"50\"
        wView.setLayoutParams(new RelativeLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT));
        int applyDimension2 = (int) TypedValue.applyDimension(1, ICON_SIZE, getResources().getDisplayMetrics()); //Icon size
        wView.getLayoutParams().height = applyDimension2;
        wView.getLayoutParams().width = applyDimension2;
        wView.loadData("<html>" +
                "<head></head>" +
                "<body style=\"margin: 0; padding: 0\">" +
                "<img src=\"" + IconWebViewData() + "\" width=\"" + ICON_SIZE + "\" height=\"" + ICON_SIZE + "\" >" +
                "</body>" +
                "</html>", "text/html", "utf-8");
        wView.setBackgroundColor(0x00000000); //Transparent
        wView.setAlpha(ICON_ALPHA);
        wView.setOnTouchListener(onTouchListener());

        //********** Title text **********
        LinearLayout titleText = new LinearLayout(this);
        titleText.setOrientation(LinearLayout.HORIZONTAL);

        final LinearLayout blankicon = new LinearLayout(this);
        blankicon.setOrientation(LinearLayout.HORIZONTAL);
        blankicon.setGravity(Gravity.CENTER | Gravity.LEFT);
        blankicon.setLayoutParams(new LayoutParams(dp(35),dp(35)));
        
        RainbowActivity title = new RainbowActivity(this);
        title.setTextColor(TEXT_COLOR);
        title.setTextSize(27.25f);
        title.setPadding(dp(0),dp(2),dp(0),dp(0));
        title.setGravity(Gravity.CENTER);
        RelativeLayout.LayoutParams rl = new RelativeLayout.LayoutParams(dp(220),WRAP_CONTENT);
        rl.addRule(RelativeLayout.CENTER_HORIZONTAL);
        title.setLayoutParams(rl);
        title.setTypeface(Typeface.createFromAsset(getAssets(), "Ehromtj/DaisyFonts/DaisyFont6.ttf"));
        setTitleText(title);

        final LinearLayout exiticon = new LinearLayout(this);
        exiticon.setOrientation(LinearLayout.HORIZONTAL);
        exiticon.setGravity(Gravity.CENTER | Gravity.RIGHT);
        exiticon.setX(dp(-1));
        exiticon.setLayoutParams(new LayoutParams(dp(35),dp(35)));
        
        final ImageView exit = new ImageView(this);
        byte[] exdecode = Base64.decode(CloseB(), 0);
        exit.setImageBitmap(BitmapFactory.decodeByteArray(exdecode, 0, exdecode.length));
        exit.setPadding(dp(1),dp(1),dp(1),dp(1));
        exit.setAdjustViewBounds(true);
        RelativeLayout.LayoutParams rlsettings = new RelativeLayout.LayoutParams(dp(33),dp(33));
        rlsettings.addRule(ALIGN_PARENT_RIGHT);
        exit.setLayoutParams(rlsettings);
        exit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    playSound("OpenMenu.ogg");
                    Builder builder = new Builder((Context)EhromtjActivity.this, 5);
                    TextView textView2 = new TextView(EhromtjActivity.this);
                    textView2.setText("\n\n⚠WARNING︎⚠\n\n︎ARE YOU SURE YOU WANT TO\nEXIT THIS MOD MENU?\n");
                    ObjectAnimator animation = ObjectAnimator.ofFloat(textView2, "alpha", 0, 1.0f);
                    animation.setDuration(1000);
                    animation.addListener(new Animator.AnimatorListener() {
                            @Override
                            public void onAnimationStart(Animator animation) {
                            }
                            @Override
                            public void onAnimationEnd(Animator animation) {
                            }
                            @Override
                            public void onAnimationCancel(Animator animation) {
                            }
                            @Override
                            public void onAnimationRepeat(Animator animation) {

                            }
                        });
                    animation.start();
                    textView2.setPadding(0, 0, 10, 10);
                    textView2.setTypeface(Typeface.createFromAsset(getAssets(), "Ehromtj/DaisyFonts/DaisyFont2.ttf"));
                    textView2.setShadowLayer(20.0f, 0.0f, 0.0f, Color.parseColor("#FF000000"));
                    textView2.setGravity(1);
                    textView2.setTextColor(Color.WHITE);
                    textView2.setTextSize(15.0f);
                    builder.setCancelable(false);
                    builder.setView(textView2);
                    textView2.setMovementMethod(LinkMovementMethod.getInstance());
                    builder.setPositiveButton(Html.fromHtml("<font color=green>𝐘𝐄𝐒</font>"), new DialogInterface.OnClickListener(){
                            public void onClick(DialogInterface dialogInterface, int n) {
                                EhromtjActivity.this.stopSelf();
                                playSound("Back.ogg");
                            }
                        });
                    builder.setNegativeButton(Html.fromHtml("<font color=red>𝐍𝐎</font>"), new DialogInterface.OnClickListener(){
                            public void onClick(DialogInterface dialogInterface, int n) {
                                dialogInterface.dismiss();
                                playSound("Back.ogg");
                            }
                        });
                    AlertDialog alertDialog = builder.create();
                    if (Build.VERSION.SDK_INT >= 26) {
                        Window window = alertDialog.getWindow();
                        final GradientDrawable tabdr3 = new GradientDrawable();
                        final int start3 = Color.parseColor("#FFB000CD");
                        final int middle3 = Color.parseColor("#ffff0900");
                        final int end3 = Color.parseColor("#FF4E00FF");
                        final ArgbEvaluator evaluator3 = new ArgbEvaluator();
                        tabdr3.setCornerRadius(dp(10));
                        tabdr3.setStroke(5, Color.YELLOW);
                        tabdr3.setOrientation(GradientDrawable.Orientation.TL_BR);
                        final GradientDrawable gradient3 = tabdr3;
                        ValueAnimator animator3 = TimeAnimator.ofFloat(0.0f, 1.0f);
                        animator3.setDuration(2500);
                        animator3.setRepeatCount(ValueAnimator.INFINITE);
                        animator3.setRepeatMode(ValueAnimator.REVERSE);
                        animator3.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                                @Override
                                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                                    Float fraction = valueAnimator.getAnimatedFraction();
                                    int newStrat3 = (int) evaluator3.evaluate(fraction, start3, end3);
                                    int newMiddle3 = (int) evaluator3.evaluate(fraction, middle3, end3);
                                    int newEnd3 = (int) evaluator3.evaluate(fraction, end3, start3);
                                    int[] newArray = {newStrat3, newEnd3};
                                    gradient3.setColors(newArray);
                                }
                            });
                        animator3.start();
                        if (window != null) {
                            window.setType(2038);
                            window.setBackgroundDrawable(tabdr3);
                        }
                    }
                    alertDialog.show();
                }
            });
        
        //********** Mod menu feature list **********
        scrollView = new ScrollView(this);
        //Auto size. To set size manually, change the width and height example 500, 500
        scrlLL = new LinearLayout.LayoutParams(MATCH_PARENT,MATCH_PARENT);
        scrlLLExpanded = new LinearLayout.LayoutParams(mExpanded.getLayoutParams());
        scrlLLExpanded.weight = 1.0f;
        scrollView.setLayoutParams(scrlLLExpanded);
        scrollView.setBackgroundColor(MENU_FEATURE_BG_COLOR);
        patches = new LinearLayout(this);
        patches.setOrientation(LinearLayout.VERTICAL);

        //********** RelativeLayout for buttons **********
        LinearLayout closeBtn = new LinearLayout(this);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(MATCH_PARENT,dp(50));
        layoutParams.setMargins(dp(18),dp(3),dp(18),dp(3));
        closeBtn.setLayoutParams(layoutParams);
        closeBtn.setOrientation(LinearLayout.HORIZONTAL);
        closeBtn.setPadding(dp(0),dp(3),dp(0),dp(3));
        closeBtn.setGravity(Gravity.CENTER);
        final GradientDrawable gradientColor = new GradientDrawable();
        gradientColor.setColor(Color.parseColor("#20000000"));
        gradientColor.setCornerRadius(dp(10));
        closeBtn.setBackgroundDrawable(gradientColor);
        closeBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ObjectAnimator animation = ObjectAnimator.ofFloat(mCollapsed, "alpha", 0, 1.0f);
                    animation.setDuration(750);
                    animation.addListener(new Animator.AnimatorListener() {
                            @Override
                            public void onAnimationStart(Animator animation) {
                            }
                            @Override
                            public void onAnimationEnd(Animator animation) {
                            }
                            @Override
                            public void onAnimationCancel(Animator animation) {
                            }
                            @Override
                            public void onAnimationRepeat(Animator animation) {
                            }
                        });
                    animation.start();
                    mCollapsed.setVisibility(View.VISIBLE);
                    mCollapsed.setAlpha(ICON_ALPHA);
                    mExpanded.setVisibility(View.GONE);
                    playSound("Back.ogg");
                    RainbowToast(getApplicationContext(),"CLOSE MENU","DaisyFont5.ttf");
                }
            });
        
        final ImageView closeImg = new ImageView(this);
        byte[] cldecode = Base64.decode(HideB(), 0);
        closeImg.setImageBitmap(BitmapFactory.decodeByteArray(cldecode, 0, cldecode.length));
        closeImg.setAdjustViewBounds(true);
        closeImg.setX(dp(9));
        closeImg.setLayoutParams(new LayoutParams(dp(33),dp(33)));
        
        final TextView closeTxt = new TextView(this);
        closeTxt.setTextSize(18.5f);
        closeTxt.setText(" CLOSE MENU ");
        closeTxt.setX(dp(9));
        closeTxt.setTypeface(Typeface.createFromAsset(getAssets(), "Ehromtj/DaisyFonts/DaisyFont5.ttf"));
        
        int RED = Color.RED;
        int GREEN = Color.GREEN;
        int BLUE = Color.BLUE;
        int WHITE = Color.WHITE;
        int YELLOW = Color.YELLOW;
        ValueAnimator colorAnimation = ValueAnimator.ofObject(new ArgbEvaluator(), RED,GREEN,BLUE,WHITE, YELLOW);
        colorAnimation.setDuration(3000); // milliseconds
        colorAnimation.setRepeatCount(ValueAnimator.INFINITE);
        colorAnimation.setRepeatMode(ValueAnimator.REVERSE);
        colorAnimation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animator) {
                    gradientColor.setStroke(dp(2),(int) animator.getAnimatedValue());
                    closeTxt.setTextColor((int) animator.getAnimatedValue());
                    closeTxt.setShadowLayer(dp(12),dp(1),dp(1),(int) animator.getAnimatedValue());
                }

            });
        colorAnimation.start();
        
        //********** Params **********
        //Variable to check later if the phone supports Draw over other apps permission
        int iparams = Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O ? 2038 : 2002;
        params = new WindowManager.LayoutParams(WRAP_CONTENT, WRAP_CONTENT, iparams, 8, -3);
        params.gravity = 51;
        params.x = 0;
        params.y = 100;

        //********** Adding view components **********
        rootFrame.addView(mRootContainer);
        mRootContainer.addView(mCollapsed);
        mRootContainer.addView(mExpanded);
        if (IconWebViewData() != null) {
            mCollapsed.addView(wView);
        } else {
            mCollapsed.addView(startimage);
        }
        titleText.addView(blankicon);
        titleText.addView(title);
        titleText.addView(exiticon);
        exiticon.addView(exit);
        mExpanded.addView(titleText);
        scrollView.addView(patches);
        mExpanded.addView(scrollView);
        closeBtn.addView(closeImg);
        closeBtn.addView(closeTxt);
        mExpanded.addView(closeBtn);
        mWindowManager = (WindowManager) getSystemService(WINDOW_SERVICE);
        mWindowManager.addView(rootFrame, params);

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            boolean viewLoaded = false;

            @Override
            public void run() {
                //If the save preferences is enabled, it will check if game lib is loaded before starting menu
                //Comment the if-else code out except startService if you want to run the app and test preferences
                if (EhromtjPref.loadPref && !isGameLibLoaded() && !stopChecking) {
                    if (!viewLoaded) {
                        patches.addView(Category("Save preferences was been enabled. Waiting for game lib to be loaded...\n\nForce load menu may not apply mods instantly. You would need to reactivate them again"));
                        patches.addView(Button(-100, "Force load menu"));
                        viewLoaded = true;
                    }
                    handler.postDelayed(this, 600);
                } else {
                    patches.removeAllViews();
                    featureList(getFeatureList(), patches);
                }
            }
        }, 500);
    }

    private View.OnTouchListener onTouchListener() {
        return new View.OnTouchListener() {
            final View collapsedView = mCollapsed;
            final View expandedView = mExpanded;
            private float initialTouchX, initialTouchY;
            private int initialX, initialY;

            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        initialX = params.x;
                        initialY = params.y;
                        initialTouchX = motionEvent.getRawX();
                        initialTouchY = motionEvent.getRawY();
                        return true;
                    case MotionEvent.ACTION_UP:
                        int rawX = (int) (motionEvent.getRawX() - initialTouchX);
                        int rawY = (int) (motionEvent.getRawY() - initialTouchY);
                        if (rawX < 10 && rawY < 10 && isViewCollapsed()) {
                            try {
                                expandedView.setAnimation(fadein());
                                expandedView.setAnimation(fadeout());
								playSound("OpenMenu.ogg");
                                collapsedView.setVisibility(View.GONE);
                                expandedView.setVisibility(View.VISIBLE);
                            } catch (NullPointerException e) {
                          }
                        }
                        return true;
                    case MotionEvent.ACTION_MOVE:
                        params.x = initialX + ((int) (motionEvent.getRawX() - initialTouchX));
                        params.y = initialY + ((int) (motionEvent.getRawY() - initialTouchY));
                        mWindowManager.updateViewLayout(rootFrame, params);
                        return true;
                    default:
                        return false;
                }
            }
        };
    }

    private void featureList(String[] listFT, LinearLayout linearLayout) {
        //Currently looks messy right now. Let me know if you have improvements
        int featNum, subFeat = 0;
        LinearLayout llBak = linearLayout;

        for (int i = 0; i < listFT.length; i++) {
            boolean switchedOn = false;
            //Log.i("featureList", listFT[i]);
            String feature = listFT[i];
            if (feature.contains("True_")) {
                switchedOn = true;
                feature = feature.replaceFirst("True_", "");
            }

            linearLayout = llBak;
            if (feature.contains("CollapseAdd_")) {
                //if (collapse != null)
                linearLayout = mCollapse;
                feature = feature.replaceFirst("CollapseAdd_", "");
            }
            String[] str = feature.split("_");

            //Assign feature number
            if (TextUtils.isDigitsOnly(str[0]) || str[0].matches("-[0-9]*")) {
                featNum = Integer.parseInt(str[0]);
                feature = feature.replaceFirst(str[0] + "_", "");
                subFeat++;
            } else {
                //Subtract feature number. We don't want to count ButtonLink, Category, RichTextView and RichWebView
                featNum = i - subFeat;
            }
            String[] strSplit = feature.split("_");
            switch (strSplit[0]) {
                case "Toggle":
                    linearLayout.addView(Switch(featNum, strSplit[1], switchedOn));
                    break;
                case "SeekBar":
                    linearLayout.addView(SeekBar(featNum, strSplit[1], Integer.parseInt(strSplit[2]), Integer.parseInt(strSplit[3])));
                    break;
                case "Button":
                    linearLayout.addView(Button(featNum, strSplit[1]));
                    break;
                case "ButtonOnOff":
                    linearLayout.addView(ButtonOnOff(featNum, strSplit[1], switchedOn));
                    break;
                case "Spinner":
                    linearLayout.addView(Spinner(featNum, strSplit[1], strSplit[2]));
                    break;
                case "InputText":
                    linearLayout.addView(InputText(featNum, strSplit[1]));
                    break;
                case "InputValue":
                    if (strSplit.length == 3)
                        linearLayout.addView(InputValue(featNum, strSplit[2], Integer.parseInt(strSplit[1])));
                    if (strSplit.length == 2)
                        linearLayout.addView(InputValue(featNum, strSplit[1], 0));
                    break;
                case "CheckBox":
                    linearLayout.addView(CheckBox(featNum, strSplit[1], switchedOn));
                    break;
                case "RadioButton":
                    linearLayout.addView(RadioButton(featNum, strSplit[1], strSplit[2]));
                    break;
                case "Collapse":
                    Collapse(linearLayout, strSplit[1]);
                    subFeat++;
                    break;
                case "Category":
                    subFeat++;
                    linearLayout.addView(Category(strSplit[1]));
                    break;
                case "RichTextView":
                    subFeat++;
                    linearLayout.addView(RichTextView(strSplit[1]));
                    break;
                case "RichWebView":
                    subFeat++;
                    linearLayout.addView(RichWebView(strSplit[1]));
                    break;
                }
            }
        }

    private View Switch(final int featNum, final String featName, boolean swiOn) {
        final Switch switchR = new Switch(this);
        final GradientDrawable GD_THUMB_ON = new GradientDrawable();
        GD_THUMB_ON.setSize(dp(20),dp(20));
        GD_THUMB_ON.setShape(1);
        GD_THUMB_ON.setStroke(dp(2), Color.parseColor("#FF5000FF"));
        GD_THUMB_ON.setColor(Color.parseColor("#FFFF00FF"));

        final GradientDrawable GD_THUMB_OFF = new GradientDrawable();
        GD_THUMB_OFF.setSize(dp(20),dp(20));
        GD_THUMB_OFF.setShape(1);
        GD_THUMB_OFF.setStroke(dp(2), Color.parseColor("#FF5000FF"));
        GD_THUMB_OFF.setColor(Color.parseColor("#FF00FFFF"));

        final GradientDrawable GD_TRACK = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, new int[]{Color.parseColor("#FF00FFFF"), Color.parseColor("#FFFF00FF")});
        GD_TRACK.setSize(dp(10), dp(10));
        GD_TRACK.setCornerRadius(100);
        GD_TRACK.setStroke(dp(2), Color.parseColor("#FF5000FF"));      

        StateListDrawable thumbStates = new StateListDrawable();
        thumbStates.addState(new int[]{android.R.attr.state_checked},GD_THUMB_OFF);
        thumbStates.addState(new int[]{-android.R.attr.state_checked}, GD_THUMB_ON);
        thumbStates.addState(new int[]{}, GD_THUMB_OFF);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            switchR.setThumbDrawable(thumbStates);
        }
        switchR.setText(Html.fromHtml("<font color=white>➢ " + featName));
        switchR.setTypeface(Typeface.createFromAsset(getAssets(), "Ehromtj/DaisyFonts/DaisyFont2.ttf"));
        switchR.setTextColor(Color.WHITE);
        switchR.setPadding(dp(7),dp(5),dp(7),dp(5));
        switchR.setTextSize(12.5f);
        switchR.setSingleLine(true);
        switchR.setElevation((float) 5);
        switchR.setAllCaps(true);
        switchR.setGravity(Gravity.CENTER | Gravity.LEFT);
        switchR.setLayoutParams(new LinearLayout.LayoutParams(MATCH_PARENT,MATCH_PARENT));
        switchR.setTrackDrawable(GD_TRACK);
        switchR.setChecked(EhromtjPref.loadPrefBool(featName, featNum, swiOn));
        switchR.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton compoundButton, boolean bool) {
                EhromtjPref.changeFeatureBool(featName, featNum, bool);
				if (bool) {
                    switchR.setText(Html.fromHtml("<font color=white>➢ " + featName + "</font><font color='#00FFFF'> : ON"));
                    playSound("On.ogg");
                } else {
                    switchR.setText(Html.fromHtml("<font color=white>➢ " + featName + "</font><font color='#FF00FF'> : OFF"));
                    playSound("Off.ogg");
                }
				switch (featNum) {
                    case -1: //Save perferences
                        EhromtjPref.with(switchR.getContext()).writeBoolean(-1, bool);
                        if (bool == false)
                            EhromtjPref.with(switchR.getContext()).clear(); //Clear perferences if switched off
                        break;
					case -10:
                        EhromtjPref.isSoundEnabled = bool;
                        break;
                    case -3:
                        EhromtjPref.isExpanded = bool;
                        scrollView.setLayoutParams(bool ? scrlLLExpanded : scrlLL);
                        break;
                    
                }
            }
        });
        return switchR;
    }

    private View SeekBar(final int featNum, final String featName, final int min, int max) {
        int loadedProg = EhromtjPref.loadPrefInt(featName, featNum);
        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setPadding(dp(7),dp(2),dp(7),dp(2));
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        linearLayout.setGravity(Gravity.CENTER);

        final TextView textView = new TextView(this);
        textView.setText(Html.fromHtml("<font color=white>➢ " + featName + "</font><br/><font color='#FF00FF'>╰╴( " + min + " )"));
        textView.setTypeface(Typeface.createFromAsset(getAssets(), "Ehromtj/DaisyFonts/DaisyFont2.ttf"));
        textView.setTextSize(12.5f);
        textView.setAllCaps(true);
        textView.setGravity(Gravity.CENTER | Gravity.LEFT);
        textView.setTextColor(Color.WHITE);

        SeekBar seekBar = new SeekBar(this);
        GradientDrawable thumbDrawable = new GradientDrawable();
        thumbDrawable.setShape(GradientDrawable.RECTANGLE);
        thumbDrawable.setColor(Color.parseColor("#905000FF"));
        thumbDrawable.setStroke(dp(2), Color.parseColor("#FF5000FF"));
        thumbDrawable.setSize(dp(15), dp(15));
        seekBar.setThumb(thumbDrawable);
        GradientDrawable progressDrawable = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, new int[]{Color.parseColor("#90FF00FF"), Color.parseColor("#9000FFFF")});
        progressDrawable.setShape(GradientDrawable.RECTANGLE);
        progressDrawable.setStroke(dp(2), Color.parseColor("#FF5000FF"));
        seekBar.setProgressDrawable(progressDrawable);
        seekBar.setPadding(dp(8),dp(3),dp(8),dp(3));
        seekBar.setMin(min);
        seekBar.setMax(max);
        seekBar.setProgress(min);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            public void onStartTrackingTouch(SeekBar seekBar) {
            }
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
	        int l;
            public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
				if (l < i) {
                    playSound("SliderIncrease.ogg");
                } else {
                    playSound("SliderDecrease.ogg");
                }
                l = i;
				//if progress is greater than minimum, don't go below. Else, set progress
                seekBar.setProgress(i < min ? min : i);
                EhromtjPref.changeFeatureInt(featName, featNum, i < min ? min : i);
                textView.setText(Html.fromHtml("<font color=white>➢ " + featName + "</font><br/><font color='#FF00FF'>╰╴( " + (i < min ? min : i) + " )"));
                textView.setTypeface(Typeface.createFromAsset(getAssets(), "Ehromtj/DaisyFonts/DaisyFont2.ttf"));
                textView.setTextSize(12.5f);
            }
        });
        linearLayout.addView(textView);
        linearLayout.addView(seekBar);

        return linearLayout;
    }

    private View Button(final int featNum, final String featName) {
        final Button button = new Button(this);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(MATCH_PARENT, MATCH_PARENT);
        layoutParams.setMargins(dp(5),dp(2),dp(5),dp(2));
        button.setLayoutParams(layoutParams);
        button.setPadding(dp(4),dp(5),dp(4),dp(4));
        button.setTextSize(12.5f);
        button.setSingleLine(true);
        button.setTextColor(Color.WHITE);
        button.setGravity(Gravity.CENTER);
        button.setText(featName);
        button.setAllCaps(true);
        GradientDrawable gdMenuBody = new GradientDrawable();
        gdMenuBody.setColor(Color.parseColor("#90FF00FF"));
        gdMenuBody.setCornerRadius(dp(10));
        gdMenuBody.setStroke(dp(2), Color.parseColor("#FF5000FF"));
        button.setBackgroundDrawable(gdMenuBody);
        button.setTypeface(Typeface.createFromAsset(getAssets(), "Ehromtj/DaisyFonts/DaisyFont7.ttf"));
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
				playSound("Select.ogg");
                switch (featNum) {
                    case -4:
                        EhromtjLogcat.Save(getApplicationContext());
                        break;
                    case -5:
                        EhromtjLogcat.Clear(getApplicationContext());
                        break;
                    case -100:
                        stopChecking = true;
                        break;
                }
                EhromtjPref.changeFeatureInt(featName, featNum, 0);
            }
        });

        return button;
    }
    private View ButtonOnOff(final int featNum, String featName, boolean switchedOn) {
        final Button button = new Button(this);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(MATCH_PARENT,MATCH_PARENT);
        layoutParams.setMargins(dp(5),dp(2),dp(5),dp(2));
        button.setLayoutParams(layoutParams);
        button.setTextSize(12.5f);
        button.setTextColor(Color.WHITE);
        GradientDrawable gdMenuBody = new GradientDrawable();
        gdMenuBody.setColor(Color.parseColor("#90FF00FF"));
        gdMenuBody.setCornerRadius(dp(10));
        gdMenuBody.setStroke(dp(2), Color.parseColor("#FF5000FF"));
        button.setGravity(Gravity.CENTER);
        button.setBackground(gdMenuBody);
        button.setPadding(dp(0),dp(3),dp(0),dp(3));
        button.setTypeface(Typeface.createFromAsset(getAssets(), "Ehromtj/DaisyFonts/DaisyFont2.ttf"));
        final String finalfeatName = featName.replace("OnOff_", "");
        boolean isOn = EhromtjPref.loadPrefBool(featName, featNum, switchedOn);
        if (isOn) {
            button.setText(finalfeatName + "\n╰╴( ON )╶╯");
            gdMenuBody = new GradientDrawable();
            gdMenuBody.setColor(Color.parseColor("#9000FFFF"));
            gdMenuBody.setCornerRadius(dp(10));
            gdMenuBody.setStroke(dp(2), Color.parseColor("#FF5000FF"));
            button.setBackground(gdMenuBody);
            isOn = false;
        } else {
            button.setText(finalfeatName + "\n╰╴( OFF )╶╯");
            gdMenuBody = new GradientDrawable();
            gdMenuBody.setColor(Color.parseColor("#90FF00FF"));
            gdMenuBody.setCornerRadius(dp(10));
            gdMenuBody.setStroke(dp(2), Color.parseColor("#FF5000FF"));
            button.setBackground(gdMenuBody);
            isOn = true;
        }
        final boolean finalIsOn = isOn;
        button.setOnClickListener(new View.OnClickListener() {
            boolean isOn = finalIsOn;

            public void onClick(View v) {
                EhromtjPref.changeFeatureBool(finalfeatName, featNum, isOn);
                //Log.d(TAG, finalfeatName + " " + featNum + " " + isActive2);
                if (isOn) {
					playSound("On.ogg");
                    button.setText(finalfeatName + "\n╰╴( ON )╶╯");
                    GradientDrawable gdMenuBody = new GradientDrawable();
                    gdMenuBody.setColor(Color.parseColor("#9000FFFF"));
                    gdMenuBody.setCornerRadius(dp(10));
                    gdMenuBody.setStroke(dp(2), Color.parseColor("#FF5000FF"));
                    button.setBackground(gdMenuBody);
                    isOn = false;
                } else {
					playSound("Off.ogg");
                    button.setText(finalfeatName + "\n╰╴( OFF )╶╯");
                    GradientDrawable gdMenuBody = new GradientDrawable();
                    gdMenuBody.setColor(Color.parseColor("#90FF00FF"));
                    gdMenuBody.setCornerRadius(dp(10));
                    gdMenuBody.setStroke(dp(2), Color.parseColor("#FF5000FF"));
                    button.setBackground(gdMenuBody);
                    isOn = true;
                }
            }
        });
        return button;
    }

    private View Spinner(final int featNum, final String featName, final String list) {
        Log.d(TAG, "spinner " + featNum + " " + featName + " " + list);
        final List<String> lists = new LinkedList<>(Arrays.asList(list.split(",")));

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(MATCH_PARENT,MATCH_PARENT);
        layoutParams.setMargins(dp(5),dp(2),dp(5),dp(2));
        
        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setLayoutParams(layoutParams);
        linearLayout.setPadding(dp(5),dp(2),dp(5),dp(2));
        linearLayout.setOrientation(LinearLayout.HORIZONTAL);
        linearLayout.setGravity(Gravity.CENTER);
        GradientDrawable gdMenuBody = new GradientDrawable();
        gdMenuBody.setColor(Color.parseColor("#20000000"));
        gdMenuBody.setCornerRadius(dp(10));
        gdMenuBody.setSize(dp(48),dp(48));
        gdMenuBody.setStroke(dp(2), Color.parseColor("#FF5000FF"));
        linearLayout.setBackgroundDrawable(gdMenuBody);

        final TextView textView = new TextView(this);
        textView.setText(Html.fromHtml("<font color=white>➢ " + featName + "</font><br/><font color='#FF00FF'>╰╴( ? )"));
        textView.setTextColor(Color.WHITE);
        textView.setGravity(Gravity.CENTER | Gravity.LEFT);
        textView.setAllCaps(true);
        textView.setTypeface(Typeface.createFromAsset(getAssets(), "Ehromtj/DaisyFonts/DaisyFont2.ttf"));
        textView.setTextSize(12.5f);
        textView.setX(dp(11));
        textView.setY(dp(1));
        
        final Spinner spinner = new Spinner(this,Spinner.MODE_DROPDOWN);
        spinner.setLayoutParams(layoutParams);
        spinner.setGravity(Gravity.CENTER | Gravity.RIGHT);
        spinner.getBackground().setColorFilter(1, PorterDuff.Mode.SRC_ATOP);
        ArrayAdapter aa = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, lists);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(aa);
        spinner.setX(dp(11));
        spinner.setSelection(EhromtjPref.loadPrefInt(featName, featNum));
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                ((TextView) parentView.getChildAt(0)).setText("");
                textView.setText(Html.fromHtml("<font color=white>➢ " + featName + "</font><br/><font color='#FF00FF'>╰╴( " + spinner.getSelectedItem().toString() + " )"));
                EhromtjPref.changeFeatureInt(spinner.getSelectedItem().toString(), featNum, position);
				playSound("Select.ogg");
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
				}
        });
        linearLayout.addView(textView);
        linearLayout.addView(spinner);
        return linearLayout;
    }

    private View InputText(final int featNum, final String featName) {
        final EditTextString edittextstring = new EditTextString();
        LinearLayout linearLayout = new LinearLayout(this);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(MATCH_PARENT,MATCH_PARENT);
        layoutParams.setMargins(dp(5),dp(2),dp(5),dp(2));

        final Button button = new Button(this);
        String string = EhromtjPref.loadPrefString(featName, featNum);
        edittextstring.setString((string == "") ? "" : string);
        button.setLayoutParams(layoutParams);
        button.setPadding(dp(4),dp(6),dp(4),dp(4));
        button.setTextSize(12.5f);
        button.setTextColor(Color.WHITE);
        button.setGravity(Gravity.CENTER);
        button.setAllCaps(false);
        button.setText(Html.fromHtml("<font color=white>" + featName + "</font><br/><font color='#FF00FF'>╰╴( ? )╶╯"));
        GradientDrawable gdMenuBody = new GradientDrawable();
        gdMenuBody.setColor(Color.parseColor("#20000000"));
        gdMenuBody.setCornerRadius(dp(10));
        gdMenuBody.setStroke(dp(2), Color.parseColor("#FF5000FF"));
        button.setBackgroundDrawable(gdMenuBody);
        button.setTypeface(Typeface.createFromAsset(getAssets(), "Ehromtj/DaisyFonts/DaisyFont2.ttf"));
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playSound("OpenMenu.ogg");
                final AlertDialog alert = new AlertDialog.Builder(getApplicationContext(), 2).create();
                LinearLayout linTextField = new LinearLayout(getApplicationContext());
                linTextField.setOrientation(LinearLayout.VERTICAL);

                byte[] editbgdecode = Base64.decode(EhromtjBGEDT(), 0);
                Bitmap editbg = BitmapFactory.decodeByteArray(editbgdecode,0,editbgdecode.length);
                BitmapDrawable editbgdrawa = new BitmapDrawable(editbg);
                editbgdrawa.setGravity(Gravity.FILL);
                linTextField.setBackgroundDrawable(editbgdrawa);

                Animation alphaAnimation = new AlphaAnimation(0.0f, 1.0f);
                alphaAnimation.setDuration(1000);
                alphaAnimation.setFillAfter(true);
                linTextField.startAnimation(alphaAnimation);

                //TextView
                final TextView textView = new TextView(getApplicationContext());
                textView.setText("\nENTER TEXT");
                textView.setGravity(Gravity.CENTER);
                textView.setTextColor(Color.WHITE);
                textView.setTextSize(14.5f);
                textView.setTypeface(Typeface.createFromAsset(getAssets(), "Ehromtj/DaisyFonts/DaisyFont9.ttf"));
                //Edit text
                final EditText edittextstring1 = new EditText(getApplicationContext());
                edittextstring1.setMaxLines(1);
                edittextstring1.setHint("ENTER TEXT");
                edittextstring1.setWidth(convertDipToPixels(300));
                edittextstring1.setTextColor(Color.WHITE);
                edittextstring1.setTextSize(14.5f);
                edittextstring1.setHintTextColor(Color.parseColor("#434d52"));
                edittextstring1.setTypeface(Typeface.createFromAsset(getAssets(), "Ehromtj/DaisyFonts/DaisyFont2.ttf"));
                edittextstring1.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                        @Override
                        public void onFocusChange(View v, boolean hasFocus) {
                            InputMethodManager imm = (InputMethodManager) getApplicationContext().getSystemService(getApplicationContext().INPUT_METHOD_SERVICE);
                            if (hasFocus) {
                                imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);
                            } else {
                                imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
                            }
                        }
                    });
                edittextstring1.requestFocus();

                //Button
                Button modif = new Button(getApplicationContext());
                modif.setTextColor(Color.WHITE);
                modif.setText("MODIFY");
                modif.setTextSize(14.5f);
                modif.setTypeface(Typeface.createFromAsset(getAssets(), "Ehromtj/DaisyFonts/DaisyFont3.ttf"));
                GradientDrawable gdMenuBody2 = new GradientDrawable();
                gdMenuBody2.setColor(Color.parseColor("#90003300"));
                gdMenuBody2.setStroke(dp(2), Color.WHITE);
                gdMenuBody2.setCornerRadii(new float[]{dp(10),dp(10),dp(10),dp(10),dp(0),dp(0),dp(0),dp(0)});
                modif.setBackground(gdMenuBody2);
                modif.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            String str = edittextstring1.getText().toString();
                            edittextstring.setString(str);
                            button.setText(Html.fromHtml("<font color=white>" + featName + "</font><br/><font color='#FF00FF'>╰╴( " + str + " )╶╯"));
                            alert.dismiss();
                            EhromtjPref.changeFeatureString(featName, featNum, str);
                            playSound("Select.ogg");
                        }
                    });

                //Cancel
                Button cancel = new Button(getApplicationContext());
                cancel.setTextColor(Color.WHITE);
                cancel.setText("CANCEL");
                cancel.setTextSize(14.5f);
                cancel.setTypeface(Typeface.createFromAsset(getAssets(), "Ehromtj/DaisyFonts/DaisyFont3.ttf"));
                GradientDrawable gdMenuBody3 = new GradientDrawable();
                gdMenuBody3.setColor(Color.parseColor("#907F0000"));
                gdMenuBody3.setStroke(dp(2), Color.WHITE);
                gdMenuBody3.setCornerRadii(new float[]{dp(0),dp(0),dp(0),dp(0),dp(10),dp(10),dp(10),dp(10)});
                cancel.setBackground(gdMenuBody3);
                cancel.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            alert.dismiss();
                            playSound("Back.ogg");
                        }
                    });
                alert.setCancelable(false);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                    Objects.requireNonNull(alert.getWindow()).setType(Build.VERSION.SDK_INT >= 26 ? 2038 : 2002);
                }
                linTextField.addView(textView);
                linTextField.addView(edittextstring1);
                linTextField.addView(modif);
                linTextField.addView(cancel);
                alert.setView(linTextField);
                alert.show();
            }
        });
        linearLayout.addView(button);
        return linearLayout;
    }

    private View InputValue(final int featNum, final String featName, final int maxValue) {
        final EditTextNum edittextnum = new EditTextNum();
        edittextnum.setNum(0);
        RelativeLayout relativeLayout2 = new RelativeLayout(this);
        relativeLayout2.setPadding(dp(3),dp(3),dp(3),dp(3));
        relativeLayout2.setHorizontalGravity(16);
        GradientDrawable gdMenuBody = new GradientDrawable();
        gdMenuBody.setColor(Color.parseColor("#20000000"));
        gdMenuBody.setCornerRadius(dp(10));
        gdMenuBody.setStroke(dp(2), Color.parseColor("#FF5000FF"));
        relativeLayout2.setBackgroundDrawable(gdMenuBody);

        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(WRAP_CONTENT,WRAP_CONTENT);
        layoutParams.setMargins(dp(5),dp(2),dp(5),dp(2));
        relativeLayout2.setLayoutParams(layoutParams);

        final TextView textView = new TextView(this);
        textView.setText(Html.fromHtml("<font color=white>➢ " + featName + "</font><br/><font color='#FF00FF'>╰╴( ? )"));
        textView.setTextColor(Color.WHITE);
        textView.setPadding(dp(0),dp(8),dp(0),dp(0));
        textView.setTypeface(Typeface.createFromAsset(getAssets(), "Ehromtj/DaisyFonts/DaisyFont2.ttf"));
        textView.setTextSize(12.5f);
        textView.setX(dp(8));
        textView.setY(dp(-3));
        textView.setAllCaps(true);
        textView.setLayoutParams(layoutParams);
        
        RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(WRAP_CONTENT,WRAP_CONTENT);
        layoutParams2.setMargins(dp(0),dp(0),dp(0),dp(0));
        layoutParams2.addRule(ALIGN_PARENT_RIGHT);

        TextView button2 = new TextView(this);
        button2.setText("ENTER");
        button2.setSingleLine(true);
        button2.setTypeface(Typeface.createFromAsset(getAssets(), "Ehromtj/DaisyFonts/DaisyFont8.ttf"));
        button2.setTextColor(Color.WHITE);
        button2.setLayoutParams(layoutParams2);
        button2.setGravity(Gravity.CENTER);
        button2.setTextSize(14.5f);
        gdMenuBody = new GradientDrawable();
        gdMenuBody.setCornerRadius(dp(8));
        gdMenuBody.setSize(dp(60),dp(42));
        gdMenuBody.setColor(Color.parseColor("#90FF00FF"));
        gdMenuBody.setStroke(dp(2), Color.parseColor("#FF5000FF"));
		button2.setBackground(gdMenuBody);
        button2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    playSound("OpenMenu.ogg");
                    final AlertDialog alert = new AlertDialog.Builder(getApplicationContext(), 2).create();
                    
                    LinearLayout linTextField = new LinearLayout(getApplicationContext());
                    linTextField.setOrientation(LinearLayout.VERTICAL);

                    byte[] editbgdecode = Base64.decode(EhromtjBGEDT(), 0);
                    Bitmap editbg = BitmapFactory.decodeByteArray(editbgdecode,0,editbgdecode.length);
                    BitmapDrawable editbgdrawa = new BitmapDrawable(editbg);
                    editbgdrawa.setGravity(Gravity.FILL);
                    linTextField.setBackgroundDrawable(editbgdrawa);

                    Animation alphaAnimation = new AlphaAnimation(0.0f, 1.0f);
                    alphaAnimation.setDuration(1000);
                    alphaAnimation.setFillAfter(true);
                    linTextField.startAnimation(alphaAnimation);
                    
                    //TextView
                    final TextView textView2 = new TextView(getApplicationContext());
                    if (maxValue != 0) {
                    textView2.setText("\nINPUT VALUE/MAX VALUE : " + maxValue);
                    } else {
                    textView2.setText("\nINPUT VALUE");
                    }
                    textView2.setGravity(Gravity.CENTER);
                    textView2.setTextColor(Color.WHITE);
                    textView2.setTextSize(14.5f);
                    textView2.setTypeface(Typeface.createFromAsset(getAssets(), "Ehromtj/DaisyFonts/DaisyFont9.ttf"));
                    //Edit text
                    final EditText edittextvalue = new EditText(getApplicationContext());
                    edittextvalue.setMaxLines(1);
                    edittextvalue.setHint("ENTER VALUE");
                    edittextvalue.setWidth(convertDipToPixels(300));
                    edittextvalue.setTextColor(Color.WHITE);
                    edittextvalue.setTextSize(14.5f);
                    edittextvalue.setHintTextColor(Color.parseColor("#434d52"));
                    edittextvalue.setInputType(InputType.TYPE_CLASS_NUMBER);
                    edittextvalue.setKeyListener(DigitsKeyListener.getInstance("0123456789-"));
                    edittextvalue.setTypeface(Typeface.createFromAsset(getAssets(), "Ehromtj/DaisyFonts/DaisyFont2.ttf"));
                    InputFilter[] FilterArray = new InputFilter[1];
                    FilterArray[0] = new InputFilter.LengthFilter(10);
                    edittextvalue.setFilters(FilterArray);
                    edittextvalue.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                            @Override
                            public void onFocusChange(View v, boolean hasFocus) {
                                InputMethodManager imm = (InputMethodManager) getApplicationContext().getSystemService(getApplicationContext().INPUT_METHOD_SERVICE);
                                if (hasFocus) {
                                    imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);
                                } else {
                                    imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
                                }
                            }
                        });
                    edittextvalue.requestFocus();

                    //Button
                    Button button = new Button(getApplicationContext());
                    button.setTextColor(Color.WHITE);
                    button.setText("MODIFY");
                    button.setTextSize(14.5f);
                    button.setTypeface(Typeface.createFromAsset(getAssets(), "Ehromtj/DaisyFonts/DaisyFont3.ttf"));
                    GradientDrawable gdMenuBody2 = new GradientDrawable();
                    gdMenuBody2.setColor(Color.parseColor("#90003300"));
                    gdMenuBody2.setStroke(dp(2), Color.WHITE);
                    gdMenuBody2.setCornerRadii(new float[]{dp(10),dp(10),dp(10),dp(10),dp(0),dp(0),dp(0),dp(0)});
                    button.setBackground(gdMenuBody2);
                    button.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                playSound("Select.ogg");
                                int num = EhromtjPref.loadPrefInt(featName, featNum);
                                try {
                                    num = Integer.parseInt(TextUtils.isEmpty(edittextvalue.getText().toString()) ? "0" : edittextvalue.getText().toString());
                                    if (maxValue != 0 &&  num >= maxValue)
                                        num = maxValue;
                                } catch (NumberFormatException ex) {
                                    if (maxValue != 0)
                                        num = maxValue;
                                    else
                                        num = 2147483640;
                                }
                                edittextnum.setNum(num);
                                textView.setText(Html.fromHtml("<font color=white>➢ " + featName + "</font><br/><font color='#FF00FF'>╰╴( " + num + " )"));
                                alert.dismiss();
                                EhromtjPref.changeFeatureInt(featName, featNum, num);
                            }
                        });

                    //Cancel
                    Button cancel = new Button(getApplicationContext());
                    cancel.setTextColor(Color.WHITE);
                    cancel.setText("CANCEL");
                    cancel.setTextSize(14.5f);
                    cancel.setTypeface(Typeface.createFromAsset(getAssets(), "Ehromtj/DaisyFonts/DaisyFont3.ttf"));
                    GradientDrawable gdMenuBody3 = new GradientDrawable();
                    gdMenuBody3.setColor(Color.parseColor("#907F0000"));
                    gdMenuBody3.setStroke(dp(2), Color.WHITE);
                    gdMenuBody3.setCornerRadii(new float[]{dp(0),dp(0),dp(0),dp(0),dp(10),dp(10),dp(10),dp(10)});
                    cancel.setBackground(gdMenuBody3);
                    cancel.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                alert.dismiss();
                                playSound("Back.ogg");
                            }
                        });
                    alert.setCancelable(false);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                        Objects.requireNonNull(alert.getWindow()).setType(Build.VERSION.SDK_INT >= 26 ? 2038 : 2002);
                    }
                    linTextField.addView(textView2);
                    linTextField.addView(edittextvalue);
                    linTextField.addView(button);
                    linTextField.addView(cancel);
                    alert.setView(linTextField);
                    alert.show();
                }
            });

        relativeLayout2.addView(textView);
        relativeLayout2.addView(button2);
        return relativeLayout2;
    }
    
    private View CheckBox(final int featNum, final String featName, boolean switchedOn) {
        final CheckBox checkBox = new CheckBox(this);
        ColorStateList buttonStates = new ColorStateList(
            new int[][]{
                new int[]{-android.R.attr.state_enabled},
                new int[]{android.R.attr.state_checked},
                new int[]{}
            },
            new int[]{
                Color.BLUE,
                Color.parseColor("#FF5000FF"),
                Color.parseColor("#FF5000FF"),
            }
        );
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            checkBox.setButtonTintList(buttonStates);
            checkBox.setButtonTintList(buttonStates);
        }
        checkBox.setText(Html.fromHtml("<font color=white>➢ " + featName));
        checkBox.setTextColor(Color.WHITE);
        checkBox.setGravity(Gravity.CENTER | Gravity.LEFT);
        checkBox.setTextSize(12.5f);
        checkBox.setX(dp(1));
        checkBox.setSingleLine(true);
        checkBox.setAllCaps(true);
        checkBox.setLayoutParams(new LinearLayout.LayoutParams(MATCH_PARENT,MATCH_PARENT));
        checkBox.setTypeface(Typeface.createFromAsset(getAssets(), "Ehromtj/DaisyFonts/DaisyFont2.ttf"));
        checkBox.setPadding(dp(0),dp(4),dp(0),dp(4));
        checkBox.setChecked(EhromtjPref.loadPrefBool(featName, featNum, switchedOn));
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (checkBox.isChecked()) {
                    checkBox.setText(Html.fromHtml("<font color=white>➢ " + featName + "</font><font color='#00FFFF'> : ON"));
					playSound("On.ogg");
                    EhromtjPref.changeFeatureBool(featName, featNum, isChecked);
                } else {
                    checkBox.setText(Html.fromHtml("<font color=white>➢ " + featName + "</font><font color='#FF00FF'> : OFF"));
					playSound("Off.ogg");
                    EhromtjPref.changeFeatureBool(featName, featNum, isChecked);
                }
            }
        });
        return checkBox;
    }

    private View RadioButton(final int featNum, String featName, final String list) {
        final List<String> lists = new LinkedList<>(Arrays.asList(list.split(",")));
        final TextView textView = new TextView(this);
        textView.setText(Html.fromHtml("<font color=white>➢ " + featName + "</font><br/><font color='#FF00FF'>╰╴( ? )"));
        textView.setTypeface(Typeface.createFromAsset(getAssets(), "Ehromtj/DaisyFonts/DaisyFont2.ttf"));
        textView.setTextColor(Color.WHITE);
        textView.setGravity(Gravity.CENTER | Gravity.LEFT);
        textView.setTextSize(12.5f);
        textView.setAllCaps(true);
        final RadioGroup radioGroup = new RadioGroup(this);
        radioGroup.setPadding(dp(7),dp(2),dp(7),dp(2));
        radioGroup.setOrientation(LinearLayout.VERTICAL);
        radioGroup.addView(textView);
        radioGroup.setLayoutParams(new LinearLayout.LayoutParams(MATCH_PARENT,MATCH_PARENT));
        for (int i = 0; i < lists.size(); i++) {
            final RadioButton Radioo = new RadioButton(this);
            final String finalfeatName = featName, radioName = lists.get(i);
            View.OnClickListener first_radio_listener = new View.OnClickListener() {
                public void onClick(View v) {
                    textView.setText(Html.fromHtml("<font color=white>➢ " + finalfeatName + "</font><br/><font color='#FF00FF'>╰╴( " + radioName + " )"));
                    playSound("Select.ogg");
                    EhromtjPref.changeFeatureInt(finalfeatName, featNum, radioGroup.indexOfChild(Radioo));
                }
            };
            System.out.println(lists.get(i));
            Radioo.setText(lists.get(i));
            Radioo.setGravity(Gravity.CENTER | Gravity.LEFT);
            Radioo.setTypeface(Typeface.createFromAsset(getAssets(), "Ehromtj/DaisyFonts/DaisyFont2.ttf"));
            Radioo.setTextColor(Color.LTGRAY);
            Radioo.setTextSize(12.5f);
            Radioo.setAllCaps(true);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
            Radioo.setButtonTintList(ColorStateList.valueOf(Color.parseColor("#FF5000FF")));
            Radioo.setOnClickListener(first_radio_listener);
            Radioo.setX(dp(-6));
            radioGroup.addView(Radioo);
        }

        int index = EhromtjPref.loadPrefInt(featName, featNum);
        if (index > 0) {
            textView.setText(Html.fromHtml("<font color=white>➢ " + featName + "</font><br/><font color='#FF00FF'>╰╴( " + lists.get(index - 1) + " )"));
            ((RadioButton) radioGroup.getChildAt(index)).setChecked(true);
        }

        return radioGroup;
    }

    private void Collapse(LinearLayout linLayout, final String text) {
        LinearLayout.LayoutParams layoutParamsLL = new LinearLayout.LayoutParams(MATCH_PARENT, MATCH_PARENT);
        layoutParamsLL.setMargins(dp(5),dp(2),dp(5),dp(2));

        LinearLayout collapse = new LinearLayout(this);
        collapse.setLayoutParams(layoutParamsLL);
        collapse.setVerticalGravity(Gravity.CENTER);
        collapse.setOrientation(LinearLayout.VERTICAL);
        final GradientDrawable gdMenuBody2 = new GradientDrawable();
        gdMenuBody2.setColor(Color.parseColor("#20000000"));
        gdMenuBody2.setCornerRadius(dp(10));
        gdMenuBody2.setStroke(dp(2), Color.parseColor("#FFFF00FF"));
        collapse.setBackgroundDrawable(gdMenuBody2);

        final LinearLayout collapseSub = new LinearLayout(this);
        collapseSub.setVerticalGravity(Gravity.CENTER);
        collapseSub.setPadding(dp(4),dp(4),dp(4),dp(4));
        collapseSub.setOrientation(LinearLayout.VERTICAL);
        collapseSub.setBackgroundColor(Color.TRANSPARENT);
        collapseSub.setVisibility(View.GONE);
        mCollapse = collapseSub;

        final Button textView = new Button(this);
        textView.setText("▽ " + text + " ▽");
        textView.setBackgroundColor(Color.TRANSPARENT);
        textView.setGravity(Gravity.CENTER);
        textView.setTextColor(Color.WHITE);
        textView.setTextSize(12.5f);
        textView.setSingleLine(true);
        textView.setAllCaps(true);
        textView.setShadowLayer(dp(12),dp(1),dp(1), Color.parseColor("#FF000000"));
        textView.setPadding(dp(0),dp(3),dp(0),dp(3));
        textView.setTypeface(Typeface.createFromAsset(getAssets(), "Ehromtj/DaisyFonts/DaisyFont7.ttf"));
        textView.setOnClickListener(new View.OnClickListener() {
            boolean isChecked;
            @Override
            public void onClick(View v) {
                boolean z = !this.isChecked;
                this.isChecked = z;
                if (z) {
                    ObjectAnimator animation = ObjectAnimator.ofFloat(collapseSub, "alpha", 0, 1.0f);
                    animation.setDuration(750);
                    animation.addListener(new Animator.AnimatorListener() {
                            @Override
                            public void onAnimationStart(Animator animation) {
                            }
                            @Override
                            public void onAnimationEnd(Animator animation) {
                            }
                            @Override
                            public void onAnimationCancel(Animator animation) {
                            }
                            @Override
                            public void onAnimationRepeat(Animator animation) {
                            }
                        });
                    animation.start();
					playSound("SliderIncrease.ogg");
                    collapseSub.setVisibility(View.VISIBLE);
                    textView.setText("△ " + text + " △");
                    gdMenuBody2.setStroke(dp(2), Color.parseColor("#FF00FFFF"));
                    return;
                }
                collapseSub.setVisibility(View.GONE);
				playSound("SliderDecrease.ogg");
                textView.setText("▽ " + text + " ▽");
                gdMenuBody2.setStroke(dp(2), Color.parseColor("#FFFF00FF"));
            }
        });
        collapse.addView(textView);
        collapse.addView(collapseSub);
        linLayout.addView(collapse);
    }

    private View Category(String text) {
        int upColor = Color.parseColor("#90FF00FF");
        int downColor = Color.parseColor("#9000FFFF");
        TextView textView = new TextView(this);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(MATCH_PARENT, MATCH_PARENT);
        layoutParams.setMargins(dp(4),dp(2),dp(4),dp(2));
        textView.setLayoutParams(layoutParams);
        textView.setText(text);
        textView.setGravity(Gravity.CENTER);
        textView.setShadowLayer(dp(12),dp(1),dp(1), Color.parseColor("#FF000000"));
        textView.setTextSize(15.5f);
        textView.setSingleLine(true);
        textView.setAllCaps(true);
        GradientDrawable gdMenuBody = new GradientDrawable(GradientDrawable.Orientation.TOP_BOTTOM, new int[]{upColor, downColor});
        gdMenuBody.setCornerRadius(dp(5));
        gdMenuBody.setStroke(dp(2), Color.parseColor("#FF5000FF"));
        textView.setBackground(gdMenuBody);
        textView.setTextColor(Color.WHITE);
        textView.setTypeface(Typeface.createFromAsset(getAssets(), "Ehromtj/DaisyFonts/DaisyFont4.ttf"));
        textView.setPadding(dp(7),dp(3),dp(7),dp(3));
        return textView;
    }

    private View RichTextView(String text) {
        TextView textView = new TextView(this);
        textView.setText(Html.fromHtml(text));
        textView.setTextColor(TEXT_COLOR_2);
        textView.setPadding(10, 5, 10, 5);
        return textView;
    }

    private View RichWebView(String text) {
        WebView wView = new WebView(this);
        wView.loadData(text, "text/html", "utf-8");
        wView.setBackgroundColor(0x00000000); //Transparent
        wView.setPadding(0, 5, 0, 5);
        return wView;
    }

	
	//Play sounds
    public void playSound(String uri) {
        if (EhromtjPref.isSoundEnabled) {
            if (!soundDelayed) {
                soundDelayed = true;
                if (FXPlayer != null) {
                    FXPlayer.stop();
                    FXPlayer.release();
                }
                FXPlayer = MediaPlayer.create(this, Uri.fromFile(new File(cacheDir + uri)));
                if (FXPlayer != null) {
                    //Volume reduced so sounds are not too loud
                    FXPlayer.setVolume(0.4f, 0.4f);
                    FXPlayer.start();
                }

                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
						@Override
						public void run() {
							soundDelayed = false;
						}
					}, 100);
            }
        }
    }
	
    //Override our Start Command so the Service doesnt try to recreate itself when the App is closed
    public int onStartCommand(Intent intent, int i, int i2) {
        return Service.START_NOT_STICKY;
    }

    private boolean isViewCollapsed() {
        return rootFrame == null || mCollapsed.getVisibility() == View.VISIBLE;
    }

    //For our image a little converter
    private int convertDipToPixels(int i) {
        return (int) ((((float) i) * getResources().getDisplayMetrics().density) + 0.5f);
    }

    private int dp(int i) {
        return (int) TypedValue.applyDimension(1, (float) i, getResources().getDisplayMetrics());
    }

    //Destroy our View
    public void onDestroy() {
        super.onDestroy();
        if (rootFrame != null) {
            mWindowManager.removeView(rootFrame);
        }
    }

    //Same as above so it wont crash in the background and therefore use alot of Battery life
    public void onTaskRemoved(Intent intent) {
        super.onTaskRemoved(intent);
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        stopSelf();
    }

    private void Thread() {
        if (rootFrame == null) {
            return;
        }
            rootFrame.setVisibility(View.VISIBLE);
    }

    private class EditTextString {
        private String text;

        public void setString(String s) {
            text = s;
        }

        public String getString() {
            return text;
        }
    }

    private class EditTextNum {
        private int val;

        public void setNum(int i) {
            val = i;
        }

        public int getNum() {
            return val;
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
