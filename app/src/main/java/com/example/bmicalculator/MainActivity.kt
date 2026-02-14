package com.example.bmicalculator

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.SeekBar
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat


class
MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, 0)
            insets
        }

        val editWeight = findViewById<EditText>(R.id.edit_weight)
        val seekBar = findViewById<SeekBar>(R.id.seekbar_height)
        val buttonClear = findViewById<Button>(R.id.button_clear)
        val buttonCalculate = findViewById<Button>(R.id.button_calculate)
        val textHeightValue = findViewById<TextView>(R.id.text_height_value)
        val textResult = findViewById<TextView>(R.id.text_result)



        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(
                seekBar: SeekBar?,
                progress: Int,
                fromUser: Boolean
            ) {
                textHeightValue.text = "$progress cm"
                textHeightValue.visibility = View.VISIBLE

            }


            override fun onStartTrackingTouch(seekBar: SeekBar?) {}

            override fun onStopTrackingTouch(seekBar: SeekBar?) {}


        })
        buttonCalculate.setOnClickListener {
            try {
                val weigh = editWeight.text.toString().toDouble()
                val height = seekBar.progress.toDouble() / 100

                if (height > 0) {
                    val bmi = weigh / (height * height)

                    val category = when {
                        bmi < 18.5 -> "Underweight"
                        bmi in 18.5..24.9 -> "Normal weight (Good!)"git
                        bmi in 25.0..29.9 -> "Overweight"
                        else -> "Obesity"
                    }

                    textResult.text = String.format("Your BMI is %.2f.\n%s", bmi, category)
                    textResult.visibility = View.VISIBLE

                } else {
                    Toast.makeText(this, R.string.msg_valid_height, Toast.LENGTH_SHORT).show()
                }

            } catch (e: NumberFormatException) {
                Toast.makeText(this, R.string.msg_valid_weight, Toast.LENGTH_SHORT).show()
            }
        }

        buttonClear.setOnClickListener {
            editWeight.setText("")
            seekBar.progress = 0
            textHeightValue.visibility = View.GONE
            textResult.visibility = View.GONE
        }
    }
}
