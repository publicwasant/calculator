package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Button
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    private var tv: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tv = findViewById<TextView>(R.id.tv)
    }

    fun onDigits(v: View) {
        val dig = (v as Button).text

        tv?.text?.let {
            if (it.isEmpty() && dig == "0")
                return

            tv?.append(dig)
        }
    }

    fun onOperators(v: View) {
        val op = (v as Button).text

        tv?.text?.let {
            val str = it.toString()
            var result = str
            var operatorInd = getOperator(str)

            if (operatorInd != -1) {
                result = calculate(str, str[operatorInd].toString())
            }

            tv?.text = result

            if (result.isEmpty() && op == "-") {
                tv?.text = "-"
            } else if (result.isNotEmpty() && !"+-*/.".contains(result[result.length-1])) {
                tv?.append(op)
            }
        }
    }

    fun onDot(v: View) {
        tv?.text?.let {
            if (it.isEmpty()) {
                tv?.text = "0."
            } else if (it.isNotEmpty() && !"+-*/.".contains(it[it.length-1])) {
                for (i in it.indices) {
                    if (it[it.length-i-1] == '.') return
                    if ("+-*/".contains(it[it.length-i-1])) break
                }

                tv?.append(".")
            }
        }
    }

    fun onClr(v: View) {
        tv?.text = ""
    }

    fun onEql(v: View) {
        tv?.text?.let {
            val str = it.toString()
            var result = str
            var operatorInd = getOperator(str)

            if (operatorInd != -1) {
                result = calculate(str, str[operatorInd].toString())
            }

            tv?.text = result
        }
    }

    private fun getOperator(prefix: String): Int {
        if (prefix.isEmpty())
            return -1

        for (i in prefix.indices) {
            if (i == 0 && prefix[i] == '-')
                continue

            if ("+-*/".contains(prefix[i]))
                return i
        }


        return -1
    }

    private fun calculate(prefix: String, operator: String): String {
        val operands = prefix.split(operator)

        if (operands.size != 2)
            return prefix

        var a: Double = 0.0
        var b: Double = 0.0

        try {
            a = operands[0].toDouble()
            b = operands[1].toDouble()
        } catch (e: Exception) {
            return prefix
        }

        return when (operator) {
            "+" -> {
                "${a + b}"
            }
            "-" -> {
                "${a - b}"
            }
            "*" -> {
                "${a * b}"
            }
            "/" -> {
                "${b / b}"
            }
            else -> {
                prefix
            }
        }
    }
}