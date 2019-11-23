package com.dr1009.app.emptyrecyclerview

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.*
import android.widget.TextView
import androidx.annotation.LayoutRes
import androidx.annotation.StyleRes
import androidx.recyclerview.widget.RecyclerView
import com.app.dr1009.emptyrecyclerview.R

class EmptyRecyclerView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
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
            val width = view.measuredWidth
            val height = view.measuredHeight
            setMeasuredDimension(width, height)
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
     * Create TextView from a string resource, Gravity, LayoutParams and Style resource,
     * add to EmptyRecyclerView to show if the adapter is empty.
     *
     * @param emptyMessageResId resource id of message to show
     * @param gravity           [android.view.Gravity]
     * @param params            [ViewGroup.LayoutParams]
     * @param styleRes          resource id of view style
     */
    @SuppressWarnings("unused")
    fun setEmptyString(
        emptyMessageResId: Int,
        gravity: Int = Gravity.CENTER,
        params: ViewGroup.LayoutParams? = null,
        @StyleRes styleRes: Int = 0
    ) {
        val message = if (emptyMessageResId > 0) {
            context.getString(emptyMessageResId)
        } else {
            null
        }

        setEmptyString(message, gravity, params, styleRes)
    }

    /**
     * Create TextView from a string, Gravity, LayoutParams and Style resource,
     * add to EmptyRecyclerView to show if the adapter is empty.
     *
     * @param emptyMessage message to show
     * @param gravity      [android.view.Gravity]
     * @param params       [ViewGroup.LayoutParams]
     * @param styleRes     resource id of view style
     */
    @SuppressWarnings("unused")
    fun setEmptyString(
        emptyMessage: String?,
        gravity: Int = Gravity.CENTER,
        params: ViewGroup.LayoutParams? = null,
        @StyleRes styleRes: Int = 0
    ) {
        if (emptyMessage.isNullOrEmpty()) {
            setEmptyView(null)
            return
        }

        val textView = TextView(ContextThemeWrapper(context, styleRes)).also {
            it.text = emptyMessage
            it.gravity = gravity
            it.layoutParams = params ?: ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
        }

        setEmptyView(textView)
    }
}
