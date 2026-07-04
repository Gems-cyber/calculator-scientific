package com.example.calculatorscientific.calculator

import kotlin.math.pow
import kotlin.math.sqrt

object ExpressionEvaluator {

    fun evaluate(expression: String): Double {

        val values = Stack<Double>()
        val operators = Stack<Char>()
        val functions = Stack<String>()

        var i = 0

        while (i < expression.length) {

            val ch = expression[i]

            when {

                ch == ' ' -> {
                    i++
                }

                ch.isLetter() -> {

                    val name = StringBuilder()

                    while (
                        i < expression.length &&
                        expression[i].isLetter()
                    ) {
                        name.append(expression[i])
                        i++
                    }

                    functions.push(name.toString())
                }

                ch == '(' -> {
                    operators.push(ch)
                    i++
                }

                (ch.isDigit() || ch == '.') ||
                        (
                                ch == '-' &&
                                        (
                                                i == 0 ||
                                                        expression[i - 1] == '(' ||
                                                        isOperator(expression[i - 1])
                                                )
                                ) -> {

                    val number = StringBuilder()

                    if (ch == '-') {
                        number.append('-')
                        i++
                    }

                    while (
                        i < expression.length &&
                        (expression[i].isDigit() || expression[i] == '.')
                    ) {
                        number.append(expression[i])
                        i++
                    }

                    values.push(number.toString().toDouble())
                }

                ch == ')' -> {

                    while (
                        operators.isNotEmpty() &&
                        operators.peek() != '('
                    ) {

                        val b = values.pop()
                        val a = values.pop()

                        values.push(
                            applyOperator(
                                operators.pop(),
                                a,
                                b
                            )
                        )
                    }

                    if (operators.isNotEmpty()) {
                        operators.pop()
                    }

                    if (functions.isNotEmpty()) {

                        val value = values.pop()

                        values.push(
                            applyFunction(
                                functions.pop(),
                                value
                            )
                        )
                    }

                    i++
                }

                isOperator(ch) -> {

                    while (
                        operators.isNotEmpty() &&
                        (
                                precedence(operators.peek()) > precedence(ch) ||
                                        (
                                                precedence(operators.peek()) == precedence(ch)
                                                        && ch != '^'
                                                )
                                )
                    ) {

                        val b = values.pop()
                        val a = values.pop()

                        values.push(
                            applyOperator(
                                operators.pop(),
                                a,
                                b
                            )
                        )
                    }

                    operators.push(ch)
                    i++
                }

                else -> {
                    throw IllegalArgumentException("Invalid character")
                }
            }
        }

        while (operators.isNotEmpty()) {

            val b = values.pop()
            val a = values.pop()

            values.push(
                applyOperator(
                    operators.pop(),
                    a,
                    b
                )
            )
        }

        return values.pop()
    }

    private fun applyFunction(name: String, value: Double): Double {

        return when (name.lowercase()) {

            "sin" -> ScientificEngine.sin(value)

            "cos" -> ScientificEngine.cos(value)

            "tan" -> ScientificEngine.tan(value)

            "log" -> ScientificEngine.log(value)

            "ln" -> ScientificEngine.ln(value)

            "sqrt" -> sqrt(value)

            else -> throw IllegalArgumentException("Unknown function")
        }
    }

    private fun isOperator(ch: Char): Boolean {
        return ch == '+' ||
                ch == '-' ||
                ch == '*' ||
                ch == '/' ||
                ch == '^'
    }

    private fun precedence(op: Char): Int {

        return when (op) {
            '+', '-' -> 1
            '*', '/' -> 2
            '^' -> 3
            else -> 0
        }
    }

    private fun applyOperator(
        op: Char,
        a: Double,
        b: Double
    ): Double {

        return when (op) {

            '+' -> a + b

            '-' -> a - b

            '*' -> a * b

            '/' -> {
                if (b == 0.0)
                    throw ArithmeticException()
                a / b
            }

            '^' -> a.pow(b)

            else -> throw IllegalArgumentException("Invalid operator")
        }
    }

    private class Stack<T> {

        private val list = mutableListOf<T>()

        fun push(item: T) {
            list.add(item)
        }

        fun pop(): T {

            if (list.isEmpty()) {
                throw IllegalArgumentException("Invalid Expression")
            }

            return list.removeAt(list.lastIndex)
        }

        fun peek(): T {

            if (list.isEmpty()) {
                throw IllegalArgumentException("Invalid Expression")
            }

            return list.last()
        }

        fun isNotEmpty(): Boolean {
            return list.isNotEmpty()
        }

        fun isEmpty(): Boolean {
            return list.isEmpty()
        }

        fun size(): Int {
            return list.size
        }
    }
}