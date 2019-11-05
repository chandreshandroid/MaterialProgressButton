package com.chand.progressbutton

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ValueAnimator
import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.util.TypedValue
import android.widget.FrameLayout

import android.widget.ProgressBar
import androidx.core.content.ContextCompat


import com.google.android.material.button.MaterialButton
import androidx.core.content.res.ResourcesCompat

import android.opengl.ETC1.getHeight
import android.view.*
import android.opengl.ETC1.getHeight
import androidx.core.os.HandlerCompat.postDelayed

import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import androidx.swiperefreshlayout.widget.CircularProgressDrawable


class ProgressButton @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0, defStyleRes: Int = 0
) : FrameLayout(context, attrs, defStyleAttr, defStyleRes) {


    private var mCircleView: CircleImageView? = null
    var mProgress: CircularProgressDrawable? = null

    private var contentLoadingProgressBar: ProgressBar? = null
    var progressButton: MaterialButton? = null

    var isStartAnim = false
    private var initialWidth: Int = 0
    private var finalWidth: Int = 0
    var progressText: String? = null
        set(value) {
            field = value


            if (!value.isNullOrEmpty()) {
                progressButton?.text = value

            }


        }

    var textWidth: Int = -1

    var progressBGColor: Int = Color.WHITE
        set(value) {
            field = value

            mCircleView?.setBackgroundColor(value)
        }
    var progressColor: Int=Color.BLACK
        set(value) {
            field = value
            if (style == Style.circleBar)
                mProgress?.setColorSchemeColors(value)
            else
                contentLoadingProgressBar?.indeterminateTintList = ColorStateList.valueOf(value)
        }
    var bgColor: Int = -1
        set(value) {
            field = value

            progressButton?.setBackgroundColor(value)
        }

    var isArrowVisible: Boolean = true
        set(value) {
            field = value
            mProgress?.arrowEnabled = value
        }


    var textColor = -1
        set(value) {
            field = value

            progressButton?.setTextColor(value)
        }
    var backgroundTint = -1
        set(value) {
            field = value
            progressButton?.backgroundTintList = ColorStateList.valueOf(value)
        }
    var iconTint = Color.BLACK
        set(value) {

            field = value

            progressButton?.iconTint = ColorStateList.valueOf(value)
        }
    var btn_elevation = 0f
        set(value) {
            field = value

            progressButton?.elevation = value
        }
    var icon: Drawable? = null
        set(value) {
            if (value != null) {
                field = value

                progressButton?.icon = value
            }
        }
    var iconSize = -1
        set(value) {
            if (value > -1) {
                field = value
                progressButton?.iconSize = value
            }
        }
    var textSize = -1f
        set(value) {
            if (value > -1) {
                field = value
                progressButton?.setTextSize(TypedValue.COMPLEX_UNIT_PX, value)
            }
        }
    var iconPadding = 0
        set(value) {

            field = value
            progressButton?.iconPadding = value

        }
    var backgroundTintMode: Display.Mode? = null
    var capsText = false
        set(value) {
            field = value
            progressButton?.isAllCaps = value
        }
    var iconGravity = 0
        set(value) {
            field = value
            progressButton?.iconGravity = value
        }
    var strokeColor = -1
        set(value) {

            field = value
            progressButton?.strokeColor = ColorStateList.valueOf(value)

        }
    var strokeWidth = -1
        set(value) {

            field = value
            progressButton?.strokeWidth = value

        }
    var rippleColor = -1
        set(value) {

            field = value
            progressButton?.rippleColor = ColorStateList.valueOf(value)

        }
    var cornerRadius = -1
        set(value) {

            field = value
            progressButton?.cornerRadius = value

        }
    var font: String? = null
        set(value) {
            if (!value.isNullOrEmpty()) {


                field = value

                var id = if (value.indexOf(".") >= 0)
                    context.resources.getIdentifier(
                        value.toString().substring(0, value.toString().indexOf(".")),
                        "font",
                        context.packageName
                    )
                else
                    context.resources.getIdentifier(value, "font", context.packageName)

                if (id != 0 && !isInEditMode) {


                    val typeface = ResourcesCompat.getFont(context, id)

                    if (typeface != null)
                        progressButton?.typeface = typeface

                }
            }

        }


    internal var animationListener: AnimationListener? = null

    internal var animator: ValueAnimator? = null
    internal var yourListener: View.OnClickListener? = null

    var leftPadding = 0

    var topPadding = 0
    var rightPadding = 0
    var bottomPadding = 0

    private var style = Style.circleBar

    private var layoutDirection = LayoutDirection.ltr
        set(value) {
            field = value
            if(value==LayoutDirection.ltr)
            {
                progressButton?.layoutDirection=View.LAYOUT_DIRECTION_LTR
            }
            else
            {
                progressButton?.layoutDirection=View.LAYOUT_DIRECTION_RTL
            }
        }


    init {
        createButtonView()
        createCircleProgressView()
        createContentLoadingProgressBar()


        var pa = context.obtainStyledAttributes(
            attrs,
            R.styleable.ProgressButton, 0, 0
        )

        this.style = Style.values()[pa.getInt(R.styleable.ProgressButton_p_style, 0)]
        this.layoutDirection = LayoutDirection.values()[pa.getInt(R.styleable.ProgressButton_p_layoutDirection, 0)]


        this.progressBGColor = pa.getColor(R.styleable.ProgressButton_p_progressBGColor, Color.WHITE)
        this.progressColor = pa.getColor(R.styleable.ProgressButton_p_progressColor, Color.BLACK)
        this.isArrowVisible = pa.getBoolean(R.styleable.ProgressButton_p_arrowVisible, true)

        this.bgColor = pa.getColor(R.styleable.ProgressButton_p_bgColor, ContextCompat.getColor(context, R.color.colorSecondary))
        this.progressText = pa.getString(R.styleable.ProgressButton_p_text)


        this.textColor = pa.getColor(R.styleable.ProgressButton_p_textColor, Color.BLACK)
        this.backgroundTint = pa.getColor(
            R.styleable.ProgressButton_p_backgroundTint,
          ContextCompat.getColor(context,R.color.colorSecondaryVariant)
        )
        this.iconTint = pa.getColor(R.styleable.ProgressButton_p_iconTint, Color.WHITE)
        this.strokeColor = pa.getColor(
            R.styleable.ProgressButton_p_strokeColor,
          ContextCompat.getColor(context,android.R.color.transparent)
        )
        this.rippleColor = pa.getColor(
            R.styleable.ProgressButton_p_rippleColor,
            ContextCompat.getColor(context,R.color.ripple_material_light)
        )
//            backgroundTintMode=pa.get(R.styleable.ProgressButton_p_backgroundTintMode,0)
        this.btn_elevation = pa.getFloat(R.styleable.ProgressButton_p_elevation, 0f)
        this.iconSize = pa.getDimensionPixelSize(R.styleable.ProgressButton_p_iconSize, -1)
        this.textSize = pa.getDimensionPixelSize(R.styleable.ProgressButton_p_textSize, -1).toFloat()
        this.strokeWidth = pa.getDimensionPixelSize(R.styleable.ProgressButton_p_strokeWidth, -1)
        this.iconPadding = pa.getDimensionPixelSize(R.styleable.ProgressButton_p_iconPadding, -1)
        this.cornerRadius = pa.getDimensionPixelSize(R.styleable.ProgressButton_p_cornerRadius, -1)
        this.icon = pa.getDrawable(R.styleable.ProgressButton_p_icon)
        this.capsText = pa.getBoolean(R.styleable.ProgressButton_p_capsText, false)
        this.iconGravity = pa.getInt(R.styleable.ProgressButton_p_iconGravity, MaterialButton.ICON_GRAVITY_START)
        this.font = pa.getString(R.styleable.ProgressButton_p_fontFamily)

        pa.recycle()




        yourListener = View.OnClickListener {
            avoidDoubleClicks(it)
            if(it.isClickable )
            performClick()
        }

        progressButton?.setOnClickListener(yourListener)
        this.post {

            setSizeView()
        }
        progressButton?.post {
            initialWidth = progressButton?.width!!

        }
        mCircleView?.post {
            mCircleView?.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED)
            finalWidth = mCircleView?.width!!
        }
        if (!progressText.isNullOrEmpty()) {
            val textPaint = progressButton?.paint
            textWidth = textPaint?.measureText(progressText)?.toInt()!!
        }


    }

   override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val parentWidth = MeasureSpec.getSize(widthMeasureSpec)

        super.onMeasure(widthMeasureSpec, heightMeasureSpec)



        this.postDelayed ({
       if(initialWidth==0)
           initialWidth =parentWidth

        },50)

    }



    fun setOnAnimationListener(animationListener: AnimationListener) {
        this.animationListener = animationListener
    }


    private fun setSizeView() {

       this.post{


        leftPadding = paddingLeft
        rightPadding = paddingRight
        topPadding = paddingTop
        bottomPadding = paddingBottom


        setPadding(0, 0, 0, 0)

        val barParams = FrameLayout.LayoutParams((width).toInt(), (height).toInt())


        barParams.gravity = Gravity.CENTER

        progressButton?.layoutParams = barParams

        progressButton?.setPadding(leftPadding, topPadding, rightPadding, bottomPadding)


        val barParams1 = FrameLayout.LayoutParams((height * 0.80).toInt(), (height * 0.80).toInt())
        barParams1.gravity = Gravity.CENTER
        mCircleView?.layoutParams = barParams1
        contentLoadingProgressBar?.layoutParams = barParams1

        mCircleView?.setPadding(leftPadding, topPadding, rightPadding, bottomPadding)
        contentLoadingProgressBar?.setPadding(leftPadding, topPadding, rightPadding, bottomPadding)


        if (height > dp2px(75.0f))
            mProgress?.setStyle(CircularProgressDrawable.LARGE)

        }
        addOnLayoutChangeListener(object:View.OnLayoutChangeListener{
            override fun onLayoutChange(
                v: View?,
                left: Int,
                top: Int,
                right: Int,
                bottom: Int,
                oldLeft: Int,
                oldTop: Int,
                oldRight: Int,
                oldBottom: Int
            ) {
                // Preventing extra work because method will be called many times.
                if(height == (bottom - top))
                    return;


                  setSizeView()

            }
        } )

    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)

            setSizeView()


    }

    private fun createCircleProgressView() {
        mCircleView = CircleImageView(context, progressBGColor)

            mProgress = CircularProgressDrawable(context)
            mProgress?.setColorSchemeColors(progressColor)
            mProgress?.setStyle(CircularProgressDrawable.DEFAULT)
            mCircleView?.setImageDrawable(mProgress)




        mCircleView?.visibility = View.GONE

        val barParams =
            FrameLayout.LayoutParams(dp2px(40f), dp2px(40f))
        barParams.gravity = Gravity.CENTER
        mCircleView?.layoutParams = barParams
        addView(mCircleView)

    }

    private fun createContentLoadingProgressBar() {
        contentLoadingProgressBar = ProgressBar(context,null,android.R.attr.progressBarStyleLarge)


        contentLoadingProgressBar?.isIndeterminate = true
        contentLoadingProgressBar?.indeterminateTintList = ColorStateList.valueOf(progressColor)

        contentLoadingProgressBar?.visibility=View.GONE

        val barParams =
            FrameLayout.LayoutParams(dp2px(40f), dp2px(40f))
        barParams.gravity = Gravity.CENTER
        contentLoadingProgressBar?.layoutParams = barParams
        addView(contentLoadingProgressBar)

    }

    private fun createButtonView() {


        val contextWrapper = ContextThemeWrapper(context, R.style.ProgressTheme)
        progressButton = MaterialButton(contextWrapper,null,R.style.Widget_MaterialComponents_Button_OutlinedButton)
        progressButton?.gravity = Gravity.CENTER_VERTICAL or Gravity.CENTER_HORIZONTAL



        val barParams =
            FrameLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT)
        barParams.gravity = Gravity.CENTER
        progressButton?.layoutParams = barParams
        addView(progressButton)
    }

    fun startAnimation() {

        isStartAnim = true
        isEnabled = false
        animationListener?.startAnimationListener()

        widthAnimator(progressButton!!, initialWidth, 0).start()


    }

    fun endAnimation() {
        isStartAnim = false
        reset()
        mCircleView?.visibility = View.GONE
        contentLoadingProgressBar?.visibility=View.GONE
        isEnabled = true
        progressButton?.visibility = View.VISIBLE

        widthAnimator(progressButton!!, 0, initialWidth).start()
        animationListener?.endAnimationListener()

    }


    internal fun widthAnimator(view: View, initial: Int, final: Int) =
        ValueAnimator.ofInt(initial, final).apply {
            addUpdateListener { animation ->
                if (animation.animatedValue as Int <= textWidth + dp2px(25f) && (view as MaterialButton).text.isNotEmpty()) {
                    view.text = ""
                } else if (animation.animatedValue as Int >= textWidth + dp2px(25f) && !(view as MaterialButton).text.isNotEmpty()) {
                    if(progressText!=null)
                    view.text = progressText
                }

                view.updateWidth(animation.animatedValue as Int)
            }
            addListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    // done
                    if (initialWidth > final) {
                        progressButton?.visibility = View.GONE
                        progressbarAnim()


                    }


                }
            })

            duration = 400

        }

    fun progressbarAnim() {
        if (animator != null) {
            animator!!.removeAllUpdateListeners()
            animator!!.cancel()
        }
        if (style == Style.circleBar)
        {

            mCircleView?.visibility = View.VISIBLE



            animator = ValueAnimator.ofInt(0, 255)
            animator!!.duration = 200
            animator?.addUpdateListener(ValueAnimator.AnimatorUpdateListener { valueAnimator ->

                mCircleView?.alpha = valueAnimator.animatedValue as Int / 255f


            })
            animator?.addListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    // done
                    mProgress?.start()


                }
            })
            animator?.start()
        } else {
            contentLoadingProgressBar?.visibility=View.VISIBLE
           // contentLoadingProgressBar?.show()
        }
    }

    internal fun heightAnimator(view: View, initial: Int, final: Int) =
        ValueAnimator.ofInt(initial, final).apply {
            addUpdateListener { animation ->
                view.updateHeight(animation.animatedValue as Int)
            }
        }

    internal fun View.updateWidth(width: Int) {
        val layoutParams = this.layoutParams
        layoutParams.width = width
        this.layoutParams = layoutParams
    }

    internal fun View.updateHeight(height: Int) {
        val layoutParams = this.layoutParams
        layoutParams.height = height
        this.layoutParams = layoutParams
    }

    internal fun reset() {
        mCircleView?.clearAnimation()
        mProgress?.stop()
        mProgress?.arrowEnabled = isArrowVisible

    }

    fun px2dp(px: Float): Float {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX, px, this.resources.displayMetrics)
    }

    fun dp2px(dp: Float): Int {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp.toFloat(), this.resources.displayMetrics)
            .toInt()
    }

    private enum class Style {
        circleBar, progressBar, back, shrieked
    }

   private enum class LayoutDirection {
        ltr, rtl
    }

    fun avoidDoubleClicks(view: View) {
        val DELAY_IN_MS: Long = 900
        if (!view.isClickable) {
            return
        }
        view.isClickable = false
        view.postDelayed({ view.isClickable = true }, DELAY_IN_MS)
    }

    interface AnimationListener {
        fun startAnimationListener()
        fun endAnimationListener()
    }


}