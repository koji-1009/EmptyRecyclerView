package com.app.dr1009.emptyrecyclerview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

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
     * Sets the view to show if the adapter is empty
     *
     * @param emptyView {@link View}
     */
    public void setEmptyView(@Nullable View emptyView) {
        mEmptyView = emptyView;
    }

    /**
     * Sets the view to show if the adapter is empty
     *
     * @param resId empty view's layout id
     */
    public void setEmptyView(@LayoutRes int resId) {
        if (resId == 0) {
            return;
        }

        mEmptyView = LayoutInflater.from(getContext()).inflate(resId, null);
    }
}
