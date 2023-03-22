package com.example.laboratorio02guerra

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.example.laboratorio02guerra.R
import java.text.Format

class MainActivity : AppCompatActivity() {

    private lateinit var weigthEditText:EditText
    private lateinit var heigthEditText: EditText
    private lateinit var actionCalculate: Button
    private lateinit var bmiText:TextView
    private lateinit var resulTextView: TextView
    private lateinit var infoTextView: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bind()
        SetListener()
    }
    private fun bind(){
        weigthEditText = findViewById(R.id.weigth_edit_text)
        heigthEditText = findViewById(R.id.heigth_edit_text)
        actionCalculate = findViewById(R.id.calculate_button)
        bmiText = findViewById(R.id.bmi_text_view)
        resulTextView = findViewById(R.id.result_text_view)
        infoTextView = findViewById(R.id.info_text_view)
    }

    private fun SetListener(){
        actionCalculate.setOnClickListener {

            val weigth = weigthEditText.text.toString()
            val heigth = heigthEditText.text.toString()

            if (!validateInput(heigth,weigth)){
                clearTextview()
                return@setOnClickListener
            }

            val bmi = calculateBMI(weigth.toFloat(),heigth.toFloat())
            val bmiTwoDigits = String.format("%.2f",bmi).toFloat()
            clearFocus()
            displayResult(bmiTwoDigits)
        }
    }
    private fun calculateBMI(Weigth:Float,Height:Float):Float {
        return Weigth/((Height/100)*(Height/100))
    }

    private fun displayResult(bmi: Float){
        bmiText.text = bmi.toString()
        infoTextView.text = "Normal range is 18.5-24.9"

        var informationResult = ""
        var color = 0
        when{
            bmi <18.50 ->{
                informationResult = "Underweigth"
                color = R.color.under_weight
            }
            bmi in 18.50..24.99 ->{
                informationResult = "Healthy"
                color = R.color.normal_weight
            }
            bmi in 25.00..29.99 ->{
                informationResult = "Overweigth"
                color = R.color.over_weight
            }
            bmi >30 ->{
                informationResult = "Obese"
                color = R.color.obese
            }
        }
        resulTextView.text = informationResult
        resulTextView.setTextColor(ContextCompat.getColor(this,color))
    }
    private fun validateInput (heigth: String?, weigth:String):Boolean{
        when{
            heigth.isNullOrEmpty()->{
                Toast.makeText(this, "Heigth is empty", Toast.LENGTH_SHORT).show()
                return false
            }
            weigth.isNullOrEmpty()->{
                Toast.makeText(this, "Wigth is empty", Toast.LENGTH_SHORT).show()
                return false
            }
        }
        return true
    }
    private fun clearFocus(){
        weigthEditText.clearFocus()
        heigthEditText.clearFocus()
    }
    private fun clearTextview (){
        resulTextView.text = ""
        infoTextView.text = ""
        bmiText.text = ""
    }
}