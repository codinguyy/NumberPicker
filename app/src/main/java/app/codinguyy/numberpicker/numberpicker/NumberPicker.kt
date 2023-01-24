package app.codinguyy.numberpicker.numberpicker

import android.annotation.TargetApi
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.os.Build
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import android.widget.LinearLayout

class NumberPicker : FrameLayout, NumberScrollView.ScrollChangedListener {

    /**
     * Create the leftspacer, rightspacer
     * Add the linearlayout to the framelayout
     *
     * On the leftside the view starts with the leftwspacer, then comes the linearlayout and than the rightspacer
     * Calculate the sizes of the leftspacer and the rightspacer
     */

    val mNotchPath: Path by lazy {
        Path()
    }

    val notchPaint: Paint by lazy {
        Paint()
    }

    var mRightSpacer: View? = null
    var mLeftSpacer: View? = null
    var numberPickerContainer: LinearLayout? = null
    var numberPickerView: NumberPickerView? = null
    var scrollView: NumberScrollView? = null

    /**
     * Constructors
     */
    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context?, attrs: AttributeSet?) : super(context!!, attrs) {
        init()
    }

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context!!, attrs, defStyleAttr) {
        init()
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) : super(
        context,
        attrs,
        defStyleAttr,
        defStyleRes
    ) {
        init()
    }

    private fun init() {
        // Add all the children
        addChildViews()
        prepareNotchPoint()
    }

    private fun addChildViews() {
        scrollView = NumberScrollView(context, this)
        scrollView!!.isHorizontalScrollBarEnabled = false // Don't display the scrollbar
        numberPickerContainer = LinearLayout(context)

        // Add left spacing to the container
        mLeftSpacer = View(context)
        mLeftSpacer!!.setBackgroundColor(Color.WHITE)
        numberPickerContainer!!.addView(mLeftSpacer)

        // Add ruler to the container
        numberPickerView = NumberPickerView(context)
        numberPickerContainer!!.addView(numberPickerView)

        // Add right spacing to the container
        mRightSpacer = View(context)
        mRightSpacer!!.setBackgroundColor(Color.WHITE)
        numberPickerContainer!!.addView(mRightSpacer)

        // Add this container to the scroll view.
        scrollView!!.removeAllViews()
        scrollView!!.addView(numberPickerContainer)


        addView(scrollView)
    }

    override fun onScrollChanged() {

    }

    override fun onScrollPaused() {

    }

    /**\
     * Adjust the size of the spacer
     */
    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
        val width = width

        val leftParams = mLeftSpacer?.layoutParams
        leftParams?.width = width / 2
        mLeftSpacer?.layoutParams = leftParams

        val rightParams = mRightSpacer?.layoutParams
        rightParams?.width = width / 2
        mRightSpacer?.layoutParams = rightParams

        calculateNotchPath()
        invalidate()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        // Draw the top notch
        canvas.drawPath(mNotchPath, notchPaint)
    }

    fun prepareNotchPoint() {
        notchPaint.color = Color.BLACK
        notchPaint.strokeWidth = 10f
        notchPaint.style = Paint.Style.FILL_AND_STROKE
    }

    private fun calculateNotchPath() {
        mNotchPath.reset()
        mNotchPath.moveTo((width / 2 - 30).toFloat(), 0f)
        mNotchPath.lineTo((width / 2).toFloat(), 40f)
        mNotchPath.lineTo((width / 2 + 30).toFloat(), 0f)
    }
}
