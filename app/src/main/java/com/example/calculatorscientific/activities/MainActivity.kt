package com.example.calculatorscientific.activities

import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.calculatorscientific.calculator.CalculatorEngine
import com.example.calculatorscientific.calculator.ScientificEngine
import com.example.calculatorscientific.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val calculatorEngine = CalculatorEngine()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupNumberButtons()
        setupOperatorButtons()
        setupScientificButtons()
        setupControlButtons()
        setupWindowInsets()

        binding.txtExpression.text = ""
        binding.txtDisplay.text = "0"

        binding.btnDegRad.text =
            if (ScientificEngine.isDegreeMode()) "DEG" else "RAD"
    }

    private fun setupWindowInsets() {

        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { view, insets ->

            val bars = insets.getInsets(WindowInsetsCompat.Type.systemBars())

            view.setPadding(
                bars.left,
                bars.top,
                bars.right,
                bars.bottom
            )

            insets
        }
    }

    private fun updateExpression(expression: String) {
        binding.txtExpression.text = expression
    }

    private fun updateResult(result: String) {
        binding.txtDisplay.text = result
    }

    private fun setupNumberButton(button: Button, value: String) {

        button.setOnClickListener {

            val expression = calculatorEngine.append(value)

            updateExpression(expression)

        }
    }

    private fun setupNumberButtons() {

        setupNumberButton(binding.btn0, "0")
        setupNumberButton(binding.btn1, "1")
        setupNumberButton(binding.btn2, "2")
        setupNumberButton(binding.btn3, "3")
        setupNumberButton(binding.btn4, "4")
        setupNumberButton(binding.btn5, "5")
        setupNumberButton(binding.btn6, "6")
        setupNumberButton(binding.btn7, "7")
        setupNumberButton(binding.btn8, "8")
        setupNumberButton(binding.btn9, "9")
    }

    private fun setupOperatorButtons() {

        binding.btnPlus.setOnClickListener {

            updateExpression(calculatorEngine.setOperator("+"))

        }

        binding.btnMinus.setOnClickListener {

            updateExpression(calculatorEngine.setOperator("-"))

        }

        binding.btnMultiply.setOnClickListener {

            updateExpression(calculatorEngine.setOperator("*"))

        }

        binding.btnDivide.setOnClickListener {

            updateExpression(calculatorEngine.setOperator("/"))

        }

        binding.btnEquals.setOnClickListener {

            val result = calculatorEngine.calculate()

            updateResult(result)

            updateExpression(calculatorEngine.getCurrentInput())
        }
    }

    private fun setupControlButtons() {

        binding.btnClear.setOnClickListener {

            calculatorEngine.clear()

            updateExpression("")
            updateResult("0")
        }

        binding.btnDelete.setOnClickListener {

            updateExpression(calculatorEngine.delete())

        }

        binding.btnDecimal.setOnClickListener {

            updateExpression(calculatorEngine.append("."))

        }

        binding.btnPlusMinus.setOnClickListener {

            updateResult(calculatorEngine.toggleSign())

            updateExpression(calculatorEngine.getCurrentInput())

        }
    }

    private fun setupScientificButtons() {

        binding.btnSqrt.setOnClickListener {

            val result = calculatorEngine.squareRoot()

            updateResult(result)
            updateExpression(calculatorEngine.getCurrentInput())
        }

        binding.btnSquare.setOnClickListener {

            val result = calculatorEngine.square()

            updateResult(result)

            updateExpression(calculatorEngine.getCurrentInput())

        }

        binding.btnPower.setOnClickListener {

            updateExpression(calculatorEngine.setOperator("^"))

        }

        binding.btnPi.setOnClickListener {

            updateExpression(
                calculatorEngine.appendConstant(Math.PI.toString())
            )

        }

        binding.btnE.setOnClickListener {

            updateExpression(
                calculatorEngine.appendConstant(Math.E.toString())
            )

        }

        binding.btnSin.setOnClickListener {

            val result = calculatorEngine.sin()

            updateResult(result)
            updateExpression(calculatorEngine.getCurrentInput())
        }

        binding.btnCos.setOnClickListener {

            val result = calculatorEngine.cos()

            updateResult(result)
            updateExpression(calculatorEngine.getCurrentInput())
        }

        binding.btnTan.setOnClickListener {

            val result = calculatorEngine.tan()

            updateResult(result)
            updateExpression(calculatorEngine.getCurrentInput())
        }

        binding.btnLog.setOnClickListener {

            val result = calculatorEngine.log()

            updateResult(result)
            updateExpression(calculatorEngine.getCurrentInput())
        }

        binding.btnLn.setOnClickListener {

            val result = calculatorEngine.ln()

            updateResult(result)
            updateExpression(calculatorEngine.getCurrentInput())
        }

        binding.btnReciprocal.setOnClickListener {

            val result = calculatorEngine.reciprocal()

            updateResult(result)

            updateExpression(calculatorEngine.getCurrentInput())

        }

        binding.btnFactorial.setOnClickListener {

            val result = calculatorEngine.factorial()

            updateResult(result)

            updateExpression(calculatorEngine.getCurrentInput())

        }

        binding.btnOpenBracket.setOnClickListener {

            updateExpression(calculatorEngine.openParenthesis())

        }

        binding.btnCloseBracket.setOnClickListener {

            updateExpression(calculatorEngine.closeParenthesis())

        }

        binding.btnDegRad.setOnClickListener {

            ScientificEngine.toggleAngleMode()

            binding.btnDegRad.text =
                if (ScientificEngine.isDegreeMode()) "DEG" else "RAD"
        }
    }
}