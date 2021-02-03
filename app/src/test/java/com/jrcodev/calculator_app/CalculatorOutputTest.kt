package com.jrcodev.calculator_app

import com.jrcodev.calculator_app.calculator.CalculatorOutputInterfaceView
import com.jrcodev.calculator_app.calculator.CalculatorOutputPresenter
import org.junit.Test
import org.mockito.BDDMockito.then
import org.mockito.Mockito

class CalculatorOutputTest {
    private val mmPresenter = CalculatorOutputPresenter
    private val mmMockView = Mockito.mock(CalculatorOutputInterfaceView::class.java)

    @Test
    fun `1 plus 1 is 2`() {
        mmPresenter.attach(mmMockView)
        mmPresenter.add("1")
        then(mmMockView).should().setEquation("1")
        mmPresenter.add("+")
        then(mmMockView).should().setEquation("1+")
        mmPresenter.add("1")
        then(mmMockView).should().setEquation("1+1")
        then(mmMockView).should().setOutcome("2")






    }
}