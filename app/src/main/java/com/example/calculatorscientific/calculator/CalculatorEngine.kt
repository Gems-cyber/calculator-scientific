package com.example.calculatorscientific.calculator

class CalculatorEngine {

    private var currentInput = ""

    fun append(value: String): String {

        if (currentInput == "0") {
            currentInput = value
        } else {
            currentInput += value
        }

        return currentInput
    }



    fun clear(): String {
        currentInput = "0"
        return currentInput
    }
    fun delete(): String {

        if (currentInput.isNotEmpty()) {

            currentInput =
                currentInput.dropLast(1)

        }

        return if (currentInput.isEmpty())
            "0"
        else
            currentInput

    }

    fun toggleSign(): String {

        val number = currentInput.toDoubleOrNull() ?: return currentInput

        val result = -number

        currentInput = if (result % 1 == 0.0)
            result.toLong().toString()
        else
            result.toString()

        return currentInput
    }
    fun reciprocal(): String {

        val number = currentInput.toDoubleOrNull() ?: return error()
        if (number == 0.0) {
            return error()
        }

        val result = 1 / number

        currentInput = if (result % 1 == 0.0)
            result.toLong().toString()
        else
            result.toString()

        return currentInput
    }
    fun factorial(): String {

        val number = currentInput.toIntOrNull() ?: return error()
        if (number < 0) {
            return error()
        }

        var result = 1L

        for (i in 1..number) {
            result *= i
        }

        currentInput = result.toString()

        return currentInput
    }

        fun setOperator(op: String): String {

            if (currentInput.isNotEmpty()) {

                val last = currentInput.last()

                if (last == '+' ||
                    last == '-' ||
                    last == '*' ||
                    last == '/' ||
                    last == '^'
                ) {
                    currentInput =
                        currentInput.dropLast(1)
                }

            }

            currentInput += op

            return currentInput
        }

        fun calculate(): String {

            return try {

                val result =
                    ExpressionEvaluator.evaluate(currentInput)

                currentInput = formatResult(result)

                currentInput

            } catch (e: Exception) {

                error()

            }

        }


    fun squareRoot(): String {

        val number = if (currentInput.isEmpty()) {
            0.0
        } else {
            currentInput.toDoubleOrNull() ?: return error()

        }

        if (number < 0)
            return error()

        val result = kotlin.math.sqrt(number)

        currentInput = formatResult(result)
        return currentInput
    }
    fun square(): String {

        val number = if (currentInput.isEmpty()) {
            0.0
        } else {
            currentInput.toDoubleOrNull() ?: return error()
        }

        val result = number * number

        currentInput = formatResult(result)
        return currentInput
    }
    fun pi(): String {

        currentInput = Math.PI.toString()

        return currentInput
    }
    fun e(): String {
        currentInput = Math.E.toString()
        return currentInput
    }

    fun sin(): String {

        val number = currentInput.toDoubleOrNull() ?: return error()

        val result = ScientificEngine.sin(number)

        currentInput = formatResult(result)
        return currentInput
    }
    fun cos(): String {

        val number = currentInput.toDoubleOrNull() ?: return error()

        val result = ScientificEngine.cos(number)

        currentInput = formatResult(result)
        return currentInput
    }
    fun tan(): String {

        val number = currentInput.toDoubleOrNull() ?: return error()

        val result = ScientificEngine.tan(number)

        currentInput = formatResult(result)
        return currentInput
    }
    fun log(): String {

        val number = currentInput.toDoubleOrNull() ?: return error()

        if (number <= 0)
            return error()

        val result = ScientificEngine.log(number)

        currentInput = formatResult(result)
        return currentInput
    }
    fun ln(): String {

        val number = currentInput.toDoubleOrNull() ?: return error()

        if (number <= 0)
            return error()

        val result = ScientificEngine.ln(number)

        currentInput = formatResult(result)
        return currentInput
    }
    fun percent(): String {

        val number = currentInput.toDoubleOrNull() ?: return error()

        val result = number / 100

        currentInput = formatResult(result)
        return currentInput
    }
    fun openParenthesis(): String {
        currentInput += "("
        return currentInput
    }

    fun closeParenthesis(): String {
        currentInput += ")"
        return currentInput
    }


    private fun formatResult(result: Double): String {
        return if (result % 1.0 == 0.0) {
            result.toLong().toString()
        } else {
            result.toString()
        }
    }
private fun error(): String {

    currentInput = ""

    return "Error"

}
    fun appendFunction(name: String): String {

        currentInput += "$name("

        return currentInput
    }

    fun appendConstant(value: String): String {

        currentInput += value

        return currentInput
    }
    fun getCurrentInput(): String {
        return currentInput
    }
}

