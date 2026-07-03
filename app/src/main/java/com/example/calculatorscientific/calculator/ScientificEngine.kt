package com.example.calculatorscientific.calculator

import kotlin.math.*

object ScientificEngine {

    fun squareRoot(value: Double): Double {
        return sqrt(value)
    }
    private var degreeMode = true

    fun toggleAngleMode() {
        degreeMode = !degreeMode
    }

    fun isDegreeMode(): Boolean {
        return degreeMode
    }

    fun square(value: Double): Double {
        return value * value
    }

    fun power(base: Double, exponent: Double): Double {
        return base.pow(exponent)
    }

    fun sin(value: Double): Double {

        val angle =
            if (degreeMode)
                Math.toRadians(value)
            else
                value

        return kotlin.math.sin(angle)
    }

    fun cos(value: Double): Double {

        val angle =
            if (degreeMode)
                Math.toRadians(value)
            else
                value

        return kotlin.math.cos(angle)
    }

    fun tan(value: Double): Double {

        val angle =
            if (degreeMode)
                Math.toRadians(value)
            else
                value

        return kotlin.math.tan(angle)
    }

    fun log(value: Double): Double {
        return log10(value)
    }

    fun ln(value: Double): Double {
        return kotlin.math.ln(value)
    }
    fun formatScientificResult(result: Double): String {
        return ResultFormatter.format(result)
    }

}