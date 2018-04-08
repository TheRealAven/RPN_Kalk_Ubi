package com.example.aven.kalkulator

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import java.math.BigDecimal
import kotlinx.android.synthetic.main.activity_main.*
import android.view.MenuInflater
import android.preference.PreferenceManager
import android.content.SharedPreferences
import java.math.RoundingMode


class MainActivity : AppCompatActivity() {
    val kalkulator: Kalkulator = Kalkulator()
    var actualNumber: BigDecimal = BigDecimal(0)
    var actualNumberStringBuffer = ""
    var isFraction: Boolean = false
    val REQUEST_CODE = 10000
    var selectedPrecision: Int = 10
    var currentColor: String = "white"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        refreshStackView()

        //Button listners
        numberButton0.setOnClickListener{
            addToCurrentNumber(0)
            refreshStackView()
        }
        numberButton1.setOnClickListener{
            addToCurrentNumber(1)
            refreshStackView()
        }
        numberButton2.setOnClickListener{
            addToCurrentNumber(2)
            refreshStackView()
        }
        numberButton3.setOnClickListener{
            addToCurrentNumber(3)
            refreshStackView()
        }
        numberButton4.setOnClickListener{
            addToCurrentNumber(4)
            refreshStackView()
        }
        numberButton5.setOnClickListener{
            addToCurrentNumber(5)
            refreshStackView()
        }
        numberButton6.setOnClickListener{
            addToCurrentNumber(6)
            refreshStackView()
        }
        numberButton7.setOnClickListener{
            addToCurrentNumber(7)
            refreshStackView()
        }
        numberButton8.setOnClickListener{
            addToCurrentNumber(8)
            refreshStackView()
        }
        numberButton9.setOnClickListener{
            addToCurrentNumber(9)
            refreshStackView()
        }
        enterButton.setOnClickListener{
            kalkulator.addToStack(actualNumber)
            resetActualNumbers()
            refreshStackView()
        }
        swapButton.setOnClickListener{
            kalkulator.swapTop()
            refreshStackView()
        }
        dropButton.setOnClickListener{
            kalkulator.clearStackTop()
            refreshStackView()
        }
        clearButton.setOnClickListener{
            resetActualNumbers()
            refreshStackView()
        }
        allClearButton.setOnClickListener{
            kalkulator.clearStack()
            resetActualNumbers()
            refreshStackView()
        }
        addButton.setOnClickListener{
            kalkulator.addTop2()
            refreshStackView()
        }
        subtractButton.setOnClickListener{
            kalkulator.subtractTop2()
            refreshStackView()
        }
        multiplyButton.setOnClickListener{
            kalkulator.multiplyTop2()
            refreshStackView()
        }
        divButton.setOnClickListener{
            kalkulator.divideTop2()
            refreshStackView()
        }
        powButton.setOnClickListener{
            kalkulator.powTop()
            refreshStackView()
        }
        sqrtButton.setOnClickListener{
            kalkulator.sqrtTop()
            refreshStackView()
        }
        signButton.setOnClickListener{
            kalkulator.changeTopSign()
            refreshStackView()
        }
        fractionButton.setOnClickListener{
            isFraction = true
            actualNumberStringBuffer+="."
        }
        backspaceButton.setOnClickListener {
            actualNumberStringBuffer = actualNumberStringBuffer.dropLast(1)
            if(actualNumberStringBuffer.last()=='.'){
                actualNumberStringBuffer = actualNumberStringBuffer.dropLast(1)
                isFraction=false
            }
            actualNumber = BigDecimal(actualNumberStringBuffer)
            refreshStackView()
        }
    }

    fun loadOptions(color: String, prec: Int){
        try {
            stackEdit.setBackgroundColor(Color.parseColor(color))
            currentColor=color
            selectedPrecision = prec
            kalkulator.setPrecision(prec)
        }catch (e: IllegalArgumentException){
            e.printStackTrace()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if((requestCode==REQUEST_CODE)&&(resultCode==Activity.RESULT_OK)){
            if(data!=null){
                if((data.hasExtra("Kolor"))&&(data.hasExtra("Precyzja"))){
                    val nKolor = data.extras.getString("Kolor")
                    val nPrec = data.extras.getInt("Precyzja")
                    loadOptions(nKolor,nPrec)
                }
            }
        }

        super.onActivityResult(requestCode, resultCode, data)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_settings, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if(item?.getItemId()==R.id.settings){
            val i= Intent(this, SettingActivity::class.java)
            i.putExtra("curKol", currentColor)
            i.putExtra("curPrec", selectedPrecision)
            startActivityForResult(i, REQUEST_CODE)
            return true
        }else{
            return false
        }
    }

    fun refreshStackView(){
        val stackValues = kalkulator.getTop4Numbers()
        val text = """
            4: ${stackValues[3]}
            3: ${stackValues[2]}
            2: ${stackValues[1]}
            1: ${stackValues[0]}
            new number: $actualNumber
            """
        stackEdit.setText(text)
    }

    fun resetActualNumbers(){
        isFraction = false
        actualNumberStringBuffer = ""
        actualNumber = BigDecimal(0)
    }

    fun addToCurrentNumber(nextDigit: Int) {
        actualNumberStringBuffer += "$nextDigit"
        actualNumber = BigDecimal(actualNumberStringBuffer)
        actualNumber.setScale(selectedPrecision, RoundingMode.HALF_EVEN)
    }
}
