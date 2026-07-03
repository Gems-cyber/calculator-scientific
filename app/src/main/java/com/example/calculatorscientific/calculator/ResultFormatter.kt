package com.example.calculatorscientific.calculator

import kotlin.math.abs
import java.text.DecimalFormat

object ResultFormatter {

    private val decimalFormat = DecimalFormat("#.########")

    fun format(value: Double): String {

        return if (abs(value % 1.0) < 0.000000001) {
            value.toLong().toString()
        } else {
            decimalFormat.format(value)
        }

    }

}