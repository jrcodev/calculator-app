package com.jrcodev.calculator_app.calculator

import bsh.Interpreter

object CalculatorOutputPresenter {
    private var mmView: CalculatorOutputInterfaceView? = null
    private var mmCurrentEquation: String = ""
    private var mmCurrentOutcome: String = ""
    private val mmInterpreter = Interpreter()

    fun attach(view: CalculatorOutputInterfaceView) {
        mmView = view
        updateEquation()
        updateOutcome()
    }

    fun detach() {
        mmView = null
    }

    private fun isOperator(item: String): Boolean {
        return item == "+" || item == "-" || item == "/" || item == "*" || item == "%"
    }

    fun add(item: String) {
        val itemIsOperator = isOperator(item);

        if (mmCurrentEquation.isEmpty()){
            if(itemIsOperator){
                return
            }
        }

        if (itemIsOperator){
            val lastCharacter = mmCurrentEquation.getOrNull(mmCurrentEquation.lastIndex)
            lastCharacter?.let {
                if(isOperator(it.toString())){
                    return
                }
            }
        }


        mmCurrentEquation = mmCurrentEquation.plus(item)
        updateEquation()
        calculateOutcome()
        updateOutcome()
    }

    fun remove() {
        mmCurrentEquation = when {
            mmCurrentEquation.length > 1 ->
                mmCurrentEquation.substring(0, mmCurrentEquation.length - 1)
            else -> ""
        }

        updateEquation()
        calculateOutcome()
        updateOutcome()
    }

    fun solve() {
        if(mmCurrentOutcome.isNotEmpty()){
            mmCurrentEquation = mmCurrentOutcome
            mmCurrentOutcome = ""
        }
        updateEquation()
        updateOutcome()
    }

    fun clear() {
        mmCurrentOutcome = ""
        mmCurrentEquation = ""
        updateEquation()
        updateOutcome()
    }

    private fun calculateOutcome() {
        try {
            if(mmCurrentEquation.isNotEmpty()){
                mmInterpreter.eval("result = $mmCurrentEquation")
                val result = mmInterpreter.get("result")

                result?.let {
                    if(result is Int){
                        mmCurrentOutcome = result.toString()
                    }
                }
            }else{
                mmCurrentOutcome = ""
            }
        }catch (ex: Exception){
            mmCurrentOutcome = ""
        }
    }

    private fun updateEquation() = mmView?.setEquation(mmCurrentEquation)

    private fun updateOutcome() = mmView?.setOutcome(mmCurrentOutcome)
}