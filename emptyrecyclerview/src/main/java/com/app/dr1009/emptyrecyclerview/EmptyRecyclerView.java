package com.app.dr1009.emptyrecyclerview;

import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;

public class EmptyRecyclerView extends RecyclerView {

    @Nullable
    private View mEmptyView;

    public EmptyRecyclerView(@NonNull Context context) {
        super(context);
    }

    public EmptyRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public EmptyRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
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
}
