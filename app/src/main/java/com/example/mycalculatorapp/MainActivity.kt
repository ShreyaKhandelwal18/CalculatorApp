package com.example.mycalculatorapp

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar



class MainActivity : AppCompatActivity() {
    private  var tvInput: TextView? = null
    var lastNumeric:Boolean=false
    var lastDot:Boolean= false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        tvInput=findViewById(R.id.tvInput)
    }
    fun onDigit(view:View){
        tvInput?.append((view as Button).text)
        lastNumeric=true
        lastDot=false
    }

    fun onClear(view: View){
        tvInput?.text=""
    }
    fun onDecimalPoint(view: View){
if(lastNumeric && !lastDot){
    tvInput?.append(".")
    lastNumeric=false
    lastDot=true
}
    }


    fun onOperator(view:View){
        tvInput?.text?.let {
            if(lastNumeric && !isOperatorAdded(it.toString())){
                tvInput?.append((view as Button).text)
                lastNumeric=false
                lastDot=false
            }
        }

    }

    private fun isOperatorAdded(value: String):Boolean{
        return if(value.startsWith("-")){
            false
        }else{
            value.contains("/")||
                    value.contains("*")||
                    value.contains("+")||
                    value.contains("-")
        }
    }
     fun onEqual(view: View){
         if (lastNumeric){
             var tvValue=tvInput?.text.toString()
             var prefix=""

             try {
                 if(tvValue.startsWith("-")){
                     prefix="-"

                     tvValue=tvValue.substring(1)
                 }
                 if(tvValue.contains("-")){
                     val splitValue=tvValue.split("-")
                     //eg 99-1
                     var one=splitValue[0] //99
                     var two=splitValue[1] //1

                     if(prefix.isNotEmpty()){
                         one = prefix+ one
                     }

                     var result=one.toDouble() - two.toDouble()
                     tvInput?.text = removeZeroAfterDot(result.toString())
                 }
                 else if(tvValue.contains("+")){
                     val splitValue=tvValue.split("+")
                     //eg 99-1
                     var one=splitValue[0] //99
                     var two=splitValue[1] //1

                     if(prefix.isNotEmpty()){
                         one = prefix+ one
                     }

                     var result=one.toDouble() + two.toDouble()
                     tvInput?.text = removeZeroAfterDot(result.toString())
                 }
                 else if(tvValue.contains("*")){
                     val splitValue=tvValue.split("*")
                     //eg 99-1
                     var one=splitValue[0] //99
                     var two=splitValue[1] //1

                     if(prefix.isNotEmpty()){
                         one = prefix+ one
                     }

                     var result=one.toDouble() * two.toDouble()
                     tvInput?.text = removeZeroAfterDot(result.toString())
                 }
                 else if(tvValue.contains("/")){
                     val splitValue=tvValue.split("/")
                     //eg 99-1
                     var one=splitValue[0] //99
                     var two=splitValue[1] //1

                     if(prefix.isNotEmpty()){
                         one = prefix+ one
                     }

                     var result=one.toDouble() / two.toDouble()
                     tvInput?.text = removeZeroAfterDot(result.toString())
                 }


             }catch (e:ArithmeticException){
                 e.printStackTrace()
             }
         }
     }
     private fun removeZeroAfterDot(result: String):String{
         var value=result
         if (result.contains("0"))
             value=result.substring(0,result.length-2)
         return value
     }

    }
