package com.example.loading;


import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.text.TextUtils.TruncateAt;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import kotlin.Unit;
import kotlin.jvm.functions.Function0;

public class BaseLoadingView extends FrameLayout {
    private TextView tvHint;
    private TextView tvRefresh;
    private ImageView noData;
    private ImageView noNetwork;
    private ProgressBar loading;
    private DisplayMode oldMode;
    private Map<DisplayMode, Float> disPlayViews;
    private View rootView;
    private View blvChildBg;
    private Button btnRefresh;
    private OnTapListener refresh;
    private OnChangeListener onMode;
    private final Handler handler;
    private Drawable bg;
    private Drawable bgOnContent;
    private Drawable btnBg;
    private int animateDuration;
    private int shownModeDefault;
    private int noDataRes;
    private int noNetworkRes;
    private int loadingRes;
    private int hintTextColor;
    private int refreshTextColor;
    private int btnTextColor;
    private String loadingHint;
    private String noDataHint;
    private String networkErrorHint;
    private String refreshNoDataText;
    private String refreshNetworkText;
    private String btnText;
    private float hintTextSize;
    private float refreshTextSize;
    private float btnTextSize;
    private float drawerWidth;
    private float drawerHeight;
    private float maxRefreshTextWidth;
    private float maxHintTextWidth;
    private int maxRefreshTextLines;
    private int maxHintTextLines;
    private boolean refreshEnable;
    private boolean btnEnable;
    private boolean refreshEnableWithView;
    private DisplayMode modeDefault;
    private BaseLoadingView.BaseLoadingValueAnimator valueAnimator;
    private final BaseLoadingView.BaseLoadingAnimatorListener listener;

    public BaseLoadingView(Context context) {
        this(context, (AttributeSet)null, 0);
    }

    public BaseLoadingView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BaseLoadingView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.oldMode = DisplayMode.NONE;
        this.animateDuration = 0;
        this.shownModeDefault = 0;
        this.noDataRes = -1;
        this.noNetworkRes = -1;
        this.loadingRes = -1;
        this.loadingHint = "";
        this.noDataHint = "";
        this.networkErrorHint = "";
        this.refreshNoDataText = "";
        this.refreshNetworkText = "";
        this.btnText = "";
        this.refreshEnable = true;
        this.btnEnable = false;
        this.refreshEnableWithView = false;
        this.modeDefault = DisplayMode.NONE;
        this.listener = new BaseLoadingView.BaseLoadingAnimatorListener() {
            public void onDurationChange(ValueAnimator animation, float offset, DisplayMode mode, OverLapMode overLapMode) {
                synchronized(BaseLoadingView.this) {
                    BaseLoadingView.this.onAnimationFraction(animation.getAnimatedFraction(), offset, mode, overLapMode);
                }
            }

            public void onAnimEnd(Animator animation, DisplayMode mode, OverLapMode overLapMode) {
                synchronized(BaseLoadingView.this) {
                    BaseLoadingView.this.onAnimationFraction(1.0F, 1.0F, mode, overLapMode);
                }
            }
        };
        this.init(context, attrs);
        this.initView(context);
        this.handler = new Handler(Looper.getMainLooper());
        this.setMode(this.modeDefault, true);
    }

    public void setRefreshEnable(boolean enable) {
        this.refreshEnable = enable;
    }

    private OverLapMode getMode(int mode) {
        switch(mode) {
            case 0:
                return OverLapMode.OVERLAP;
            case 1:
                return OverLapMode.FLOATING;
            case 2:
                return OverLapMode.FO;
            default:
                return OverLapMode.OVERLAP;
        }
    }

    public void setOnTapListener(OnTapListener refresh ) {
        this.refresh = refresh;
        ((View)(this.btnEnable ? this.btnRefresh : this)).setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                if (BaseLoadingView.this.refreshEnable && BaseLoadingView.this.refreshEnableWithView && BaseLoadingView.this.refresh != null) {
                    BaseLoadingView.this.refresh.onTap();
                }
            }
        });
    }

    public void setOnChangeListener(OnChangeListener mode) {
        this.onMode = mode;
    }

    private void init(Context context, AttributeSet attrs) {
        if (attrs != null) {
            TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.BaseLoadingView);

            try {
                this.bg = array.getDrawable(R.styleable.BaseLoadingView_backgroundFill);
                this.bgOnContent = array.getDrawable(R.styleable.BaseLoadingView_backgroundUnderTheContent);
                this.noDataRes = array.getResourceId(R.styleable.BaseLoadingView_noDataRes, -1);
                this.noNetworkRes = array.getResourceId(R.styleable.BaseLoadingView_noNetworkRes, -1);
                this.loadingRes = array.getResourceId(R.styleable.BaseLoadingView_loadingRes, -1);
                this.refreshTextSize = array.getDimension(R.styleable.BaseLoadingView_refreshTextSize, 48.0F);
                this.drawerWidth = array.getDimension(R.styleable.BaseLoadingView_drawerWidth, -1.0F);
                this.drawerHeight = array.getDimension(R.styleable.BaseLoadingView_drawerHeight, -1.0F);
                this.refreshTextColor = array.getColor(R.styleable.BaseLoadingView_refreshTextColor, -1);
                this.loadingHint = array.getString(R.styleable.BaseLoadingView_loadingText);
                this.noDataHint = array.getString(R.styleable.BaseLoadingView_noDataText);
                this.networkErrorHint = array.getString(R.styleable.BaseLoadingView_networkErrorText);
                this.shownModeDefault = array.getInt(R.styleable.BaseLoadingView_shownMode, 0);
                this.refreshEnable = array.getBoolean(R.styleable.BaseLoadingView_refreshEnable, true);
                this.refreshNoDataText = array.getString(R.styleable.BaseLoadingView_refreshNoDataText);
                this.refreshNetworkText = array.getString(R.styleable.BaseLoadingView_refreshNetworkText);
                this.animateDuration = array.getInt(R.styleable.BaseLoadingView_changeAnimDuration, 400);
                this.hintTextColor = array.getColor(R.styleable.BaseLoadingView_hintColor, -1);
                this.hintTextSize = array.getDimension(R.styleable.BaseLoadingView_hintTextSize, 24.0F);
                this.btnEnable = array.getBoolean(R.styleable.BaseLoadingView_btnEnable, false);
                this.maxRefreshTextWidth = array.getDimension(R.styleable.BaseLoadingView_maxRefreshTextWidth, -1.0F);
                this.maxRefreshTextLines = array.getInt(R.styleable.BaseLoadingView_maxRefreshTextLines, -1);
                this.maxHintTextWidth = array.getDimension(R.styleable.BaseLoadingView_maxHintTextWidth, -1.0F);
                this.maxHintTextLines = array.getInt(R.styleable.BaseLoadingView_maxHintTextLines, -1);
                if (this.btnEnable) {
                    this.btnBg = array.getDrawable(R.styleable.BaseLoadingView_btnBackground);
                    this.btnText = array.getString(R.styleable.BaseLoadingView_btnText);
                    this.btnTextSize = array.getDimension(R.styleable.BaseLoadingView_btnTextSize, 36.0F);
                    this.btnTextColor = array.getColor(R.styleable.BaseLoadingView_btnTextColor, -16777216);
                }

                int mode = array.getInt(R.styleable.BaseLoadingView_modeDefault, DisplayMode.NONE.getValue());
                DisplayMode[] var5 = DisplayMode.values();
                int var6 = var5.length;

                for(int var7 = 0; var7 < var6; ++var7) {
                    DisplayMode m = var5[var7];
                    if (mode == m.getValue()) {
                        this.modeDefault = m;
                        break;
                    }
                }
            } finally {
                array.recycle();
            }
        }

    }

    private void initView(Context context) {
        this.rootView = inflate(context, R.layout.loading_view, this);
        this.noData = (ImageView)this.f(R.id.blv_vNoData);
        this.noNetwork = (ImageView)this.f(R.id.blv_vNoNetwork);
        this.loading = (ProgressBar)this.f(R.id.blv_pb);
        this.tvHint = (TextView)this.f(R.id.blv_tvHint);
        this.btnRefresh = (Button)this.f(R.id.blv_btnRefresh);
        View blvFlDrawer = this.f(R.id.blv_fl_drawer);
        if (this.drawerWidth > 0.0F && this.drawerHeight > 0.0F) {
            LayoutParams lp = (LayoutParams) blvFlDrawer.getLayoutParams();
            lp.width = (int)(this.drawerWidth + 0.5F);
            lp.height = (int)(this.drawerHeight + 0.5F);
            blvFlDrawer.setLayoutParams(lp);
        }

        this.tvRefresh = (TextView)this.f(R.id.blv_tvRefresh);
        this.blvChildBg = this.f(R.id.blv_child_bg);
        this.disPlayViews = new HashMap();
        this.disPlayViews.put(this.modeDefault, 0.0F);
        this.tvHint.setTextSize(0, this.hintTextSize);
        if (this.hintTextColor != 0) {
            this.tvHint.setTextColor(this.hintTextColor);
        }

        if (this.maxHintTextWidth > 0.0F) {
            this.tvHint.setMaxWidth((int)this.maxHintTextWidth);
        }

        if (this.maxHintTextLines > 0) {
            this.tvHint.setMaxLines(this.maxHintTextLines);
            this.tvHint.setEllipsize(TruncateAt.END);
        }

        if (this.refreshTextColor != 0) {
            this.tvRefresh.setTextColor(this.refreshTextColor);
        }

        this.tvRefresh.setTextSize(0, this.refreshTextSize);
        if (this.maxRefreshTextWidth > 0.0F) {
            this.tvRefresh.setMaxWidth((int)this.maxRefreshTextWidth);
        }

        if (this.maxRefreshTextLines > 0) {
            this.tvRefresh.setMaxLines(this.maxRefreshTextLines);
            this.tvRefresh.setEllipsize(TruncateAt.END);
        }

        if (this.btnEnable) {
            if (this.btnTextColor != 0) {
                this.btnRefresh.setTextColor(this.btnTextColor);
            }

            this.btnRefresh.setTextSize(0, this.btnTextSize);
            this.btnRefresh.setBackground(this.btnBg);
        }

        this.resetUi();
        OverLapMode defaultMode = this.getMode(this.shownModeDefault);
    }

    private void resetBackground(OverLapMode mode) {
        this.setBackground(mode != OverLapMode.OVERLAP && mode != OverLapMode.FO ? null : this.bg);
        this.blvChildBg.setBackground(mode != OverLapMode.FLOATING && mode != OverLapMode.FO ? null : this.bgOnContent);
    }

    public BaseLoadingView setLoadingDrawable(int drawableRes) {
        this.loadingRes = drawableRes;
        return this;
    }

    public BaseLoadingView setNoDataDrawable(int drawableRes) {
        this.noDataRes = drawableRes;
        return this;
    }

    public BaseLoadingView setNoNetworkDrawable(int drawableRes) {
        this.noNetworkRes = drawableRes;
        return this;
    }

    private void resetUi() {
        if (this.loadingRes != -1) {
            Drawable drawable = this.getContext().getDrawable(this.loadingRes);
            if (drawable != null) {
                Rect rect = this.loading.getIndeterminateDrawable().getBounds();
                drawable.setBounds(rect);
                this.loading.setIndeterminateDrawable(drawable);
            }
        } else {
            this.loading.setProgressDrawable((Drawable)null);
        }

        if (this.noDataRes != -1) {
            this.noData.setImageResource(this.noDataRes);
        }

        if (this.noNetworkRes != -1) {
            this.noNetwork.setImageResource(this.noNetworkRes);
        }

    }

    public void setMode(DisplayMode mode, boolean isSetNow) {
        this.setMode(mode, "", "", isSetNow);
    }

    public void setMode(DisplayMode mode, String hint, boolean isSetNow) {
        this.setMode(mode, hint, "");
    }

    public void setMode(DisplayMode mode, OverLapMode overlapMode, boolean isSetNow) {
        this.setMode(mode, "", "", overlapMode, isSetNow);
    }

    public void setMode(DisplayMode mode, String hint, String subHint, boolean isSetNow) {
        this.setMode(mode, hint, subHint, (OverLapMode)null, isSetNow);
    }

    public void setMode(DisplayMode mode, String hint, OverLapMode overlapMode, boolean isSetNow) {
        this.setMode(mode, hint, "", overlapMode, isSetNow);
    }

    public void setMode(DisplayMode mode) {
        this.setMode(mode, "", "");
    }

    public void setMode(DisplayMode mode, String hint) {
        this.setMode(mode, hint, "");
    }

    public void setMode(DisplayMode mode, OverLapMode overlapMode) {
        this.setMode(mode, "", "", overlapMode, false);
    }

    public void setMode(DisplayMode mode, String hint, String subHint) {
        this.setMode(mode, hint, subHint, (OverLapMode)null, false);
    }

    public void setMode(DisplayMode mode, String hint, OverLapMode overlapMode) {
        this.setMode(mode, hint, "", overlapMode, false);
    }

    public void setMode(DisplayMode mode, String hint, String subHint, OverLapMode overlapMode) {
        this.setMode(mode, hint, subHint, overlapMode, false);
    }

    public TextView getHintView() {
        return this.tvHint;
    }

    public TextView getRefreshView() {
        return this.tvRefresh;
    }

    public void setMode(final DisplayMode mode, final String hint, final String subHint, final OverLapMode overlapMode, final boolean isSetNow) {
        this.handler.removeCallbacksAndMessages((Object)null);
        long delay = mode.getDelay();
        if (this.valueAnimator != null) {
            this.valueAnimator.end();
        }

        if (delay > 0L) {
            this.handler.postDelayed(new Runnable() {
                public void run() {
                    BaseLoadingView.this.setLoadingMode(mode, hint, subHint, overlapMode, isSetNow);
                }
            }, delay);
        } else {
            this.setLoadingMode(mode, hint, subHint, overlapMode, isSetNow);
        }

        mode.reset();
    }

    private void setLoadingMode(DisplayMode mode, String hint, String subHint, OverLapMode overlapMode, boolean isSetNow) {
        this.refreshEnableWithView = this.refreshEnable && (mode == DisplayMode.NO_DATA || mode == DisplayMode.NO_NETWORK);
        if (overlapMode == null) {
            overlapMode = this.getMode(this.shownModeDefault);
        }

        if (mode == DisplayMode.NONE) {
            mode = DisplayMode.NORMAL;
        }

        int newCode = overlapMode.getValue() + mode.getValue();
        int oldCode = overlapMode.getValue() + this.oldMode.getValue();
        this.oldMode = mode;
        boolean isSameMode = newCode == oldCode;
        String hintText = !TextUtils.isEmpty(hint) ? hint : this.getHintString(mode);
        if (!TextUtils.isEmpty(hintText)) {
            this.tvHint.setVisibility(View.VISIBLE);
            this.tvHint.setText(hintText);
        } else {
            this.tvHint.setVisibility(View.INVISIBLE);
        }

        this.btnRefresh.setVisibility(this.refreshEnableWithView && this.btnEnable ? VISIBLE : INVISIBLE);
        if (this.btnEnable) {
            this.btnRefresh.setText(this.btnText);
        }

        String refreshHint = null;
        if (mode == DisplayMode.NO_DATA || mode == DisplayMode.NO_NETWORK) {
            refreshHint = mode == DisplayMode.NO_DATA ? this.refreshNoDataText : this.refreshNetworkText;
        }

        boolean hasHint = TextUtils.isEmpty(subHint) || TextUtils.isEmpty(refreshHint);
        this.tvRefresh.setVisibility(hasHint && this.refreshEnableWithView ? VISIBLE : INVISIBLE);
        if (hasHint) {
            this.tvRefresh.setText(TextUtils.isEmpty(subHint) ? refreshHint : subHint);
        }

        if (!isSameMode) {
            this.disPlayViews.put(mode, 0.0F);
            if (!isSetNow && this.animateDuration > 0) {
                if (this.valueAnimator == null) {
                    this.valueAnimator = new BaseLoadingView.BaseLoadingValueAnimator(this.listener);
                    this.valueAnimator.setDuration((long)this.animateDuration);
                } else {
                    this.valueAnimator.end();
                }

                this.valueAnimator.start(mode, overlapMode);
            } else {
                if (this.valueAnimator != null) {
                    this.valueAnimator.end();
                }

                this.onAnimationFraction(1.0F, 1.0F, mode, overlapMode);
            }

            this.resetBackground(overlapMode);
            if (this.onMode != null) {
                this.onMode.onModeChange(mode);
            }

        }
    }

    private String getHintString(DisplayMode mode) {
        switch(mode) {
            case LOADING:
                return this.loadingHint != null && !this.loadingHint.isEmpty() ? this.loadingHint : "";
            case NO_DATA:
                return this.noDataHint != null && !this.noDataHint.isEmpty() ? this.noDataHint : "";
            case NO_NETWORK:
                return this.networkErrorHint != null && !this.networkErrorHint.isEmpty() ? this.networkErrorHint : "";
            default:
                return "";
        }
    }

    private synchronized void onAnimationFraction(float duration, float offset, DisplayMode curMode, OverLapMode overLapMode) {
        this.setViews(offset, curMode);
        this.setBackground(duration, curMode, overLapMode);
    }

    private void setViews(float offset, DisplayMode curMode) {
        Iterator var3 = this.disPlayViews.entrySet().iterator();

        while(var3.hasNext()) {
            Entry<DisplayMode, Float> entry = (Entry)var3.next();
            View curSetView = this.getDisplayView((DisplayMode)entry.getKey());
            if (curSetView != null) {
                float curAlpha = (Float)entry.getValue();
                float newAlpha;
                if (entry.getKey() == curMode) {
                    if (curSetView.getVisibility() != View.VISIBLE) {
                        curSetView.setVisibility(View.VISIBLE);
                        curSetView.setAlpha(0.0F);
                    }

                    newAlpha = Math.min(1.0F, Math.max(0.0F, curAlpha) + offset);
                    curSetView.setAlpha(newAlpha);
                } else {
                    newAlpha = Math.max(Math.min(1.0F, curAlpha) - offset, 0.0F);
                    curSetView.setAlpha(newAlpha);
                    if (newAlpha == 0.0F && curSetView.getVisibility() != View.GONE) {
                        curSetView.setVisibility(View.GONE);
                    }
                }

                this.disPlayViews.put(entry.getKey(), newAlpha);
            }
        }

    }

    private void setBackground(float duration, DisplayMode curMode, OverLapMode overLapMode) {
        if (curMode != DisplayMode.NORMAL) {
            if (this.getVisibility() != View.VISIBLE) {
                this.setAlpha(0.0F);
                this.setVisibility(View.VISIBLE);
            }

            if (this.getAlpha() >= 1.0F) {
                this.setAlpha(1.0F);
            } else {
                this.setAlpha(Math.min(1.0F, duration));
            }
        } else {
            this.setAlpha(1.0F - duration);
            if (this.getAlpha() <= 0.05F) {
                this.setAlpha(0.0F);
                this.setBackground((Drawable)null);
                this.setVisibility(GONE);
            }
        }

    }

    private View getDisplayView(DisplayMode mode) {
        switch(mode) {
            case LOADING:
                return this.loading;
            case NO_DATA:
                return this.noData;
            case NO_NETWORK:
                return this.noNetwork;
            default:
                return null;
        }
    }

    private <T extends View> T f(int id) {
        return this.rootView.findViewById(id);
    }

    public void setOnTapListener(OnTapListener refresh,@NotNull Function0<Unit> function) {
        this.refresh = refresh;
        ((View)(this.btnEnable ? this.btnRefresh : this)).setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                if (BaseLoadingView.this.refreshEnable && BaseLoadingView.this.refreshEnableWithView && BaseLoadingView.this.refresh != null) {
                    BaseLoadingView.this.refresh.onTap();
                }
            }
        });
    }

    public interface BaseLoadingAnimatorListener {
        void onDurationChange(ValueAnimator var1, float var2, DisplayMode var3, OverLapMode var4);

        void onAnimEnd(Animator var1, DisplayMode var2, OverLapMode var3);
    }

    public static class BaseLoadingValueAnimator extends ValueAnimator {
        private DisplayMode curMode;
        private OverLapMode overLapMode;
        private float curDuration;
        private boolean isCancel;
        private BaseLoadingView.BaseLoadingAnimatorListener listener;

        private void start(DisplayMode mode, OverLapMode overLapMode) {
            if (this.isRunning()) {
                this.cancel();
            }

            this.curMode = mode;
            this.overLapMode = overLapMode;
            super.start();
        }

        public void cancel() {
            this.removeAllListeners();
            if (this.listener != null) {
                this.listener = null;
            }

            this.isCancel = true;
            super.cancel();
        }

        private BaseLoadingValueAnimator(BaseLoadingView.BaseLoadingAnimatorListener l) {
            this.listener = l;
            this.setFloatValues(new float[]{0.0F, 1.0F});
            this.addListener(new AnimatorListener() {
                public void onAnimationStart(Animator animation) {
                    if (BaseLoadingValueAnimator.this.curDuration != 0.0F) {
                        BaseLoadingValueAnimator.this.curDuration = 0.0F;
                    }

                }

                public void onAnimationEnd(Animator animation) {
                    BaseLoadingValueAnimator.this.curDuration = 0.0F;
                    if (!BaseLoadingValueAnimator.this.isCancel) {
                        if (BaseLoadingValueAnimator.this.listener != null) {
                            BaseLoadingValueAnimator.this.listener.onAnimEnd(animation, BaseLoadingValueAnimator.this.curMode, BaseLoadingValueAnimator.this.overLapMode);
                        }

                    }
                }

                public void onAnimationCancel(Animator animation) {
                    BaseLoadingValueAnimator.this.curDuration = 0.0F;
                }

                public void onAnimationRepeat(Animator animation) {
                    BaseLoadingValueAnimator.this.curDuration = 0.0F;
                }
            });
            this.addUpdateListener(new AnimatorUpdateListener() {
                public void onAnimationUpdate(ValueAnimator animation) {
                    if (!BaseLoadingValueAnimator.this.isCancel) {
                        if (BaseLoadingValueAnimator.this.listener != null) {
                            float duration = (Float)animation.getAnimatedValue();
                            float offset = duration - BaseLoadingValueAnimator.this.curDuration;
                            BaseLoadingValueAnimator.this.listener.onDurationChange(animation, offset, BaseLoadingValueAnimator.this.curMode, BaseLoadingValueAnimator.this.overLapMode);
                            BaseLoadingValueAnimator.this.curDuration = duration;
                        }

                    }
                }
            });
        }

        public void setAnimatorListener(BaseLoadingView.BaseLoadingAnimatorListener listener) {
            this.listener = listener;
        }
    }
}
