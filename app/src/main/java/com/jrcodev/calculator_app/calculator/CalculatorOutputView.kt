package com.jrcodev.calculator_app.calculator

import android.content.Context
import android.util.AttributeSet
import android.view.Gravity
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.jrcodev.calculator_app.R
import kotlinx.android.synthetic.main.view_calculator_output.view.*

class CalculatorOutputView(context: Context, attrs: AttributeSet?) : LinearLayout(context, attrs), CalculatorOutputInterfaceView {
    init {
        orientation = VERTICAL
        gravity = Gravity.CENTER_VERTICAL
        LayoutInflater.from(context).inflate(R.layout.view_calculator_output, this, true)
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        CalculatorOutputPresenter.attach(this)
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        CalculatorOutputPresenter.detach()
    }

    override fun setEquation(equation: String) {
        calculator_input_equation.text = equation
    }

    override fun setOutcome(outcome: String) {
        calculator_input_outcome.text = outcome
    }

    fun addItem(item: String){
        CalculatorOutputPresenter.add(item)
    }
    fun removeItem(){
        CalculatorOutputPresenter.remove()
    }
    fun solve(){
        CalculatorOutputPresenter.solve()
    }
    fun clear(){
        CalculatorOutputPresenter.clear()
    }
}