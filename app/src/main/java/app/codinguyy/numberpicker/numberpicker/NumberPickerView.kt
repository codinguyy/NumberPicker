package app.codinguyy.numberpicker.numberpicker

import android.annotation.TargetApi
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.os.Build
import android.util.AttributeSet
import android.view.View
import androidx.annotation.CheckResult
import androidx.annotation.ColorInt

/**
 * Class to design the TextView
 * How many TextViews?
 * Which design, paint? (size, color)
 * Drawing with canvas using a certain paint
 *
 */
class NumberPickerView : View {


    private var mViewHeight = 0
    /**
     * Attributes of the view
     */

    var minValue = 1
    var maxValue = 9
    var gapX = 10F
    var range = maxValue - minValue

    /**
     * Paint Other numbers
     */
    var textPaintOtherNumbers = Paint()

    @get:ColorInt
    @get:CheckResult
    var textColorOtherNumbers: Int = Color.GRAY
    var textSizeOtherNumbers = 50

    /**
     * Paint Mid number
     */
    var textPaintMidNumber = Paint()

    @get:ColorInt
    @get:CheckResult
    var textColorMidNumber: Int = Color.BLACK
    var textSizeMidNumber = 90

    constructor(context: Context?) : super(context) {
        refreshPaint()
    }

    constructor(
        context: Context?,
        attrs: AttributeSet?
    ) : super(context, attrs) {
        refreshPaint()
    }

    constructor(
        context: Context?,
        attrs: AttributeSet?,
        defStyleAttr: Int
    ) : super(context, attrs, defStyleAttr) {
        refreshPaint()
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    constructor(
        context: Context?,
        attrs: AttributeSet?,
        defStyleAttr: Int,
        defStyleRes: Int
    ) : super(context, attrs, defStyleAttr, defStyleRes) {
        refreshPaint()
    }

    fun refreshPaint() {
        textPaintOtherNumbers = Paint(Paint.ANTI_ALIAS_FLAG)
        textPaintOtherNumbers.color = textColorOtherNumbers
        textPaintOtherNumbers.textSize = textSizeOtherNumbers.toFloat()
        textPaintOtherNumbers.textAlign = Paint.Align.CENTER

        textPaintMidNumber = Paint(Paint.ANTI_ALIAS_FLAG)
        textPaintMidNumber.color = textColorMidNumber
        textPaintMidNumber.textSize = textSizeMidNumber.toFloat()
        textPaintMidNumber.textAlign = Paint.Align.CENTER

        invalidate()
        requestLayout()
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.let {
            for (i in 0..range) {
                gapX += 100F

                val midNumber = maxValue / 2
                if (i == midNumber) {
                    drawText(it, i + minValue, gapX, 150F, textPaintMidNumber)
                } else {
                    drawText(it, i + minValue, gapX, 250F, textPaintOtherNumbers)
                }
            }
        }
    }

    private fun drawText(canvas: Canvas, value: Int, gapX: Float, gapY: Float, textPaint: Paint) {
        canvas.drawText(value.toString(), gapX, gapY, textPaint)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        // Measure dimensions
        mViewHeight = MeasureSpec.getSize(heightMeasureSpec)
        val viewWidth = (maxValue - minValue - 1) * 200
        setMeasuredDimension(viewWidth, mViewHeight)
    }
}
