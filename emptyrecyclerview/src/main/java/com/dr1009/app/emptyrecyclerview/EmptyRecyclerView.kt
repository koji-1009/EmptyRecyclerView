package com.dr1009.app.emptyrecyclerview

import android.content.Context
import android.graphics.Canvas
import android.os.Build
import android.util.AttributeSet
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.LayoutRes
import androidx.annotation.StringRes
import androidx.annotation.StyleRes
import androidx.recyclerview.widget.RecyclerView
import com.app.dr1009.emptyrecyclerview.R

class EmptyRecyclerView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0,
) : RecyclerView(context, attrs, defStyle) {

    private var emptyView: View? = null

    init {
        val a = context.obtainStyledAttributes(attrs, R.styleable.EmptyRecyclerView, 0, 0)
        val resId = a.getResourceId(R.styleable.EmptyRecyclerView_emptyView, 0)
        a.recycle()

        setEmptyView(resId)
    }

    override fun onMeasure(widthSpec: Int, heightSpec: Int) {
        val view = emptyView
        if (view != null && adapter?.itemCount == 0) {
            view.measure(widthSpec, heightSpec)
            setMeasuredDimension(view.measuredWidth, view.measuredHeight)
            return
        }

        super.onMeasure(widthSpec, heightSpec)
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        super.onLayout(changed, l, t, r, b)
        emptyView?.layout(l, t, r, b)
    }

    override fun onDraw(c: Canvas) {
        super.onDraw(c)
        if (adapter?.itemCount == 0) {
            emptyView?.draw(c)
        }
    }

    /**
     * Set a view to show if the adapter is empty
     *
     * @param view [View]
     */
    @SuppressWarnings("unused")
    fun setEmptyView(view: View?) {
        emptyView = view

        invalidate()
    }

    /**
     * Set a view to show if the adapter is empty
     *
     * @param resId empty view's layout id
     */
    @SuppressWarnings("unused")
    fun setEmptyView(@LayoutRes resId: Int) {
        if (resId == 0) return

        val view = LayoutInflater.from(context).inflate(resId, null)
        setEmptyView(view)
    }

    /**
     * Create TextView from a string resource, Gravity, LayoutParams and TextAppearance resource,
     * add to EmptyRecyclerView to show if the adapter is empty.
     *
     * @param emptyMessageResId resource id of message to show
     * @param gravity           [android.view.Gravity]
     * @param params            [ViewGroup.LayoutParams]
     * @param textAppearanceResId resource id of TextAppearance. If null, show default appearance.
     */
    @Suppress("unused")
    fun setEmptyString(
        @StringRes emptyMessageResId: Int,
        gravity: Int = Gravity.CENTER,
        params: ViewGroup.LayoutParams? = null,
        @StyleRes textAppearanceResId: Int? = null,
    ) {
        setEmptyString(
            emptyMessage = context.getString(emptyMessageResId),
            gravity = gravity,
            params = params,
            textAppearanceResId = textAppearanceResId,
        )
    }

    /**
     * Create TextView from a string, Gravity, LayoutParams and TextAppearance resource,
     * add to EmptyRecyclerView to show if the adapter is empty.
     *
     * @param emptyMessage message to show
     * @param gravity      [android.view.Gravity]
     * @param params       [ViewGroup.LayoutParams]
     * @param textAppearanceResId resource id of TextAppearance. If null, show default appearance.
     */
    @SuppressWarnings("unused")
    fun setEmptyString(
        emptyMessage: String?,
        gravity: Int = Gravity.CENTER,
        params: ViewGroup.LayoutParams? = null,
        @StyleRes textAppearanceResId: Int? = null,
    ) {
        if (emptyMessage.isNullOrEmpty()) {
            setEmptyView(null)
            return
        }

        val textView = TextView(context).also { view ->
            view.text = emptyMessage
            view.gravity = gravity
            view.layoutParams = params ?: ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )

            if (textAppearanceResId != null) {
                // set TextAppearance
                if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
                    @Suppress("DEPRECATION")
                    view.setTextAppearance(context, textAppearanceResId)
                } else {
                    view.setTextAppearance(textAppearanceResId)
                }
            }
        }

        setEmptyView(textView)
    }
}
