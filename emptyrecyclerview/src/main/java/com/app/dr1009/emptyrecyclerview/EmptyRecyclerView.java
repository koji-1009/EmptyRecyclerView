package com.app.dr1009.emptyrecyclerview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.annotation.StyleRes;
import android.support.v7.view.ContextThemeWrapper;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class EmptyRecyclerView extends RecyclerView {

    @Nullable
    private View mEmptyView;

    public EmptyRecyclerView(@NonNull Context context) {
        this(context, null);
    }

    public EmptyRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public EmptyRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.EmptyRecyclerView, 0, 0);
        int resId = a.getResourceId(R.styleable.EmptyRecyclerView_emptyView, 0);
        setEmptyView(resId);
        a.recycle();
    }

    @Override
    protected void onMeasure(int widthSpec, int heightSpec) {
        if (mEmptyView != null && getAdapter() != null && getAdapter().getItemCount() == 0) {
            mEmptyView.measure(widthSpec, heightSpec);
            final int width = mEmptyView.getMeasuredWidth();
            final int height = mEmptyView.getMeasuredHeight();

            setMeasuredDimension(width, height);
        } else {
            super.onMeasure(widthSpec, heightSpec);
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);

        if (mEmptyView != null) {
            mEmptyView.layout(l, t, r, b);
        }
    }

    @Override
    public void onDraw(Canvas c) {
        super.onDraw(c);

        if (mEmptyView != null && getAdapter() != null && getAdapter().getItemCount() == 0) {
            mEmptyView.draw(c);
        }
    }

    /**
     * Set a view to show if the adapter is empty
     *
     * @param emptyView {@link View}
     */
    public void setEmptyView(@Nullable View emptyView) {
        mEmptyView = emptyView;
    }

    /**
     * Set a view to show if the adapter is empty
     *
     * @param resId empty view's layout id
     */
    public void setEmptyView(@LayoutRes int resId) {
        if (resId == 0) {
            return;
        }

        mEmptyView = LayoutInflater.from(getContext()).inflate(resId, null);
    }

    /**
     * Create TextView from a string resource and Gravity,
     * add to EmptyRecyclerView to show if the adapter is empty.
     *
     * @param emptyMessageResId resource id of message to show
     * @param gravity           {@link android.view.Gravity}
     */
    public void setEmptyString(@StringRes int emptyMessageResId, int gravity) {
        setEmptyString(emptyMessageResId, gravity, null);
    }

    /**
     * Create TextView from a string resource and Gravity and LayoutParams,
     * add to EmptyRecyclerView to show if the adapter is empty.
     *
     * @param emptyMessageResId resource id of message to show
     * @param gravity           {@link android.view.Gravity}
     * @param params            {@link ViewGroup.LayoutParams}
     */
    public void setEmptyString(@StringRes int emptyMessageResId, int gravity, @Nullable ViewGroup.LayoutParams params) {
        setEmptyString(emptyMessageResId, gravity, params, 0);
    }

    /**
     * Create TextView from a string resource and Gravity and Style resource,
     * add to EmptyRecyclerView to show if the adapter is empty.
     *
     * @param emptyMessageResId resource id of message to show
     * @param gravity           {@link android.view.Gravity}
     * @param styleRes          resource id of view style
     */
    public void setEmptyString(@StringRes int emptyMessageResId, int gravity, @StyleRes int styleRes) {
        setEmptyString(emptyMessageResId, gravity, null, styleRes);
    }

    /**
     * Create TextView from a string resource, Gravity, LayoutParams and Style resource,
     * add to EmptyRecyclerView to show if the adapter is empty.
     *
     * @param emptyMessageResId resource id of message to show
     * @param gravity           {@link android.view.Gravity}
     * @param params            {@link ViewGroup.LayoutParams}
     * @param styleRes          resource id of view style
     */
    public void setEmptyString(@StringRes int emptyMessageResId, int gravity, @Nullable ViewGroup.LayoutParams params, @StyleRes int styleRes) {
        setEmptyString(getContext().getString(emptyMessageResId), gravity, params, styleRes);
    }

    /**
     * Create TextView from a string and Gravity,
     * add to EmptyRecyclerView to show if the adapter is empty.
     *
     * @param emptyMessage message to show
     * @param gravity      {@link android.view.Gravity}
     */
    public void setEmptyString(@Nullable String emptyMessage, int gravity) {
        setEmptyString(emptyMessage, gravity, null);
    }

    /**
     * Create TextView from a string and Gravity and LayoutParams,
     * add to EmptyRecyclerView to show if the adapter is empty.
     *
     * @param emptyMessage message to show
     * @param gravity      {@link android.view.Gravity}
     * @param params       {@link ViewGroup.LayoutParams}
     */
    public void setEmptyString(@Nullable String emptyMessage, int gravity, @Nullable ViewGroup.LayoutParams params) {
        setEmptyString(emptyMessage, gravity, params, 0);
    }

    /**
     * Create TextView from a string and Gravity and Style resource,
     * add to EmptyRecyclerView to show if the adapter is empty.
     *
     * @param emptyMessage message to show
     * @param gravity      {@link android.view.Gravity}
     * @param styleRes     resource id of view style
     */
    public void setEmptyString(@Nullable String emptyMessage, int gravity, @StyleRes int styleRes) {
        setEmptyString(emptyMessage, gravity, null, styleRes);
    }

    /**
     * Create TextView from a string, Gravity, LayoutParams and Style resource,
     * add to EmptyRecyclerView to show if the adapter is empty.
     *
     * @param emptyMessage message to show
     * @param gravity      {@link android.view.Gravity}
     * @param params       {@link ViewGroup.LayoutParams}
     * @param styleRes     resource id of view style
     */
    public void setEmptyString(@Nullable String emptyMessage, int gravity, @Nullable ViewGroup.LayoutParams params, @StyleRes int styleRes) {
        TextView textView = new TextView(new ContextThemeWrapper(getContext(), styleRes));
        textView.setText(emptyMessage);
        textView.setGravity(gravity);
        if (params != null) {
            textView.setLayoutParams(params);
        } else {
            textView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        }

        mEmptyView = textView;
    }
}
