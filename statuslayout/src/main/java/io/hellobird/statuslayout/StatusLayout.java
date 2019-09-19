package io.hellobird.statuslayout;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/*******************************************************************
 * StatusLayout.java  2019-09-19
 * <P>
 * <br/>
 * <br/>
 * </p>
 *
 * @author:zhoupeng
 *
 ******************************************************************/
public class StatusLayout extends FrameLayout {


    public static final int STATUS_SUCCESS = 1;

    public static final int STATUS_LOADING = 2;

    public static final int STATUS_ERROR = 3;

    public static final int STATUS_EMPTY = 4;

    public static final int STATUS_OTHER = 5;

    /**
     * For status that not setting resource id
     */
    public static final int NULL_VIEW_ID = -1;

    /**
     * Store xml's custom attributes
     */
    private Attr mInitAttr = new Attr();

    /**
     * Current Status
     */
    private int mStatus;

    /**
     * Store views with status
     */
    private SparseArray<View> mStatusViewArray = new SparseArray<>();

    /**
     * Enable the animation while change status
     */
    private boolean mEnableAnim;

    /**
     * In animation resource id
     */
    private Animation mAnimIn;

    /**
     * Out animation resource id
     */
    private Animation mAnimOut;

    public StatusLayout(@NonNull Context context) {
        this(context, null);
    }

    public StatusLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public StatusLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        parseAttr(context, attrs);
    }

    /**
     * parse xml attribute
     *
     * @param context the context
     * @param attrs   AttributeSet
     */
    private void parseAttr(@NonNull Context context, @Nullable AttributeSet attrs) {
        if (attrs == null) {
            return;
        }
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.StatusLayout);
        mInitAttr.successId = typedArray.getResourceId(R.styleable.StatusLayout_success, NULL_VIEW_ID);
        mInitAttr.loadingId = typedArray.getResourceId(R.styleable.StatusLayout_loading, NULL_VIEW_ID);
        mInitAttr.errorId = typedArray.getResourceId(R.styleable.StatusLayout_error, NULL_VIEW_ID);
        mInitAttr.emptyId = typedArray.getResourceId(R.styleable.StatusLayout_empty, NULL_VIEW_ID);
        mInitAttr.otherId = typedArray.getResourceId(R.styleable.StatusLayout_other, NULL_VIEW_ID);
        mInitAttr.initStatus = typedArray.getInt(R.styleable.StatusLayout_init, STATUS_SUCCESS);

        // Get the animation attributes
        mEnableAnim = typedArray.getBoolean(R.styleable.StatusLayout_anim_enable, true);
        mAnimIn = AnimationUtils.loadAnimation(context, typedArray.getResourceId(R.styleable.StatusLayout_anim_in, R.anim.status_view_anim_in));
        mAnimOut = AnimationUtils.loadAnimation(context, typedArray.getResourceId(R.styleable.StatusLayout_anim_out, R.anim.status_view_anim_out));

        typedArray.recycle();
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        putStatusView(STATUS_SUCCESS, mInitAttr.successId);
        putStatusView(STATUS_LOADING, mInitAttr.loadingId);
        putStatusView(STATUS_ERROR, mInitAttr.errorId);
        putStatusView(STATUS_EMPTY, mInitAttr.emptyId);
        putStatusView(STATUS_OTHER, mInitAttr.otherId);

        // Set init status without animation
        setStatus(mInitAttr.initStatus, false);
    }

    /**
     * Put view of status to array
     *
     * @param status view of status
     * @param resId  view's resource id
     */
    public void putStatusView(int status, int resId) {
        View statusView = findViewById(resId);
        if (statusView == null) {
            return;
        }
        // hide view
        statusView.setVisibility(GONE);
        mStatusViewArray.put(status, statusView);
    }


    /**
     * Set current status, and show view of the status
     *
     * @param status target status
     */
    public void setStatus(int status) {
        setStatus(status, mEnableAnim);
    }

    /**
     * Set current status, and show view of the status
     *
     * @param status     target status
     * @param enableAnim enable animation
     */
    public void setStatus(int status, boolean enableAnim) {
        if (mStatus == status) {
            return;
        }
        View inView = mStatusViewArray.get(status);
        View outView = mStatusViewArray.get(mStatus);
        if (enableAnim) {
            setVisibilitySafe(inView, VISIBLE, mAnimIn);
            setVisibilitySafe(outView, GONE, mAnimOut);
        } else {
            setVisibilitySafe(inView, VISIBLE, null);
            setVisibilitySafe(outView, GONE, null);
        }
        mStatus = status;
    }

    private void setVisibilitySafe(@Nullable View view, int visibility, @Nullable Animation animation) {
        if (view != null) {
            view.setVisibility(visibility);
            if (animation != null) {
                view.startAnimation(animation);
            }
        }
    }

    /**
     * Set animation is enable
     *
     * @param enableAnimation true or false
     */
    public void setEnableAnimation(boolean enableAnimation) {
        this.mEnableAnim = enableAnimation;
    }

    /**
     * Set animation while change view visibility
     *
     * @param animIn  Animation of view visible
     * @param animOut Animation of view gone
     */
    public void setAnim(@Nullable Animation animIn, @Nullable Animation animOut) {
        this.mAnimIn = animIn;
        this.mAnimOut = animOut;
    }


    private class Attr {

        int successId;

        int loadingId;

        int errorId;

        int emptyId;

        int otherId;

        int initStatus = STATUS_SUCCESS;
    }
}
