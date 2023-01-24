package app.codinguyy.numberpicker.numberpicker

import android.content.Context
import android.widget.HorizontalScrollView

class NumberScrollView(context: Context, listener: ScrollChangedListener) : HorizontalScrollView(context) {
    var listener: ScrollChangedListener? = listener

    /**
     * context: Context, val listener: ScrollChangedListener
     */

    override fun onScrollChanged(l: Int, t: Int, oldl: Int, oldt: Int) {
        super.onScrollChanged(l, t, oldl, oldt)
        listener?.onScrollChanged()
    }

    interface ScrollChangedListener {
        fun onScrollChanged()
        fun onScrollPaused()
    }
}
