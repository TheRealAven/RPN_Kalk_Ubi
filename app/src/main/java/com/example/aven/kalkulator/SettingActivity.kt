package com.example.aven.kalkulator

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_setting.*

class SettingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting)
        if((intent.hasExtra("curCol"))&&(intent.hasExtra("curPrec"))) {
            val extras = intent.extras ?: return
            val curCol = extras.getString("curKol")
            kolorEdit.setText(curCol)
            val curP = extras.getInt("curPrec")
            precisionEdit.setText(curP)
        }

        potwierdzButton.setOnClickListener {
            finish()
        }
    }

    override fun finish() {
        val data = Intent()

        data.putExtra("Kolor", kolorEdit.text.toString())
        val nP = precisionEdit.text.toString().toInt()
        data.putExtra("Precyzja", nP)
        setResult(Activity.RESULT_OK, data)

        super.finish()
    }
}
