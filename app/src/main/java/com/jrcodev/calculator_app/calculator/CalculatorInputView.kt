package com.jrcodev.calculator_app.calculator

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.RelativeLayout
import com.jrcodev.calculator_app.R
import kotlinx.android.synthetic.main.view_calculator_input.view.*

class CalculatorInputView(context: Context, attrs: AttributeSet?) : RelativeLayout(context, attrs) {
    init {
        LayoutInflater.from(context).inflate(R.layout.view_calculator_input, this, true)

        attrs?.run {
            val typedArray = context.obtainStyledAttributes(attrs, R.styleable.CalculatorInputView)
            val textResource = typedArray.getString(R.styleable.CalculatorInputView_item_text)
            val iconResource = typedArray.getResourceId(R.styleable.CalculatorInputView_item_icon, -1)

            when {
                iconResource != -1 -> {
                    input_element_text.visibility = GONE
                    input_element_image.apply {
                        visibility = VISIBLE
                        setImageResource(iconResource)
                    }
                }
                !textResource.isNullOrEmpty() -> {
                    input_element_image.visibility = GONE
                    input_element_text.apply {
                        visibility = VISIBLE
                        text = textResource
                    }
                }
                else -> {
                    input_element_text.visibility = GONE
                    input_element_image.visibility = GONE
                }
            }

            typedArray.recycle()
        }
    }

    override fun setOnClickListener(l: OnClickListener?) {
        super.setOnClickListener(l)
        input_element_click.setOnClickListener(l)
    }
}