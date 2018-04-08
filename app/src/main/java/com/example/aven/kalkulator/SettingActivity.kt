package com.example.aven.kalkulator

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_setting.*

class SettingActivity : AppCompatActivity() {
    var kolory: ArrayList<String> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting)

        kolory.add("white")
        kolory.add("red")
        kolory.add("green")
        kolory.add("blue")
    }

    override fun finish() {
        val data = Intent()

        data.putExtra("Kolor", kolory[kolorListView.selectedId.toInt()])
        val nP = precisionEdit.text.toString().toInt()
        data.putExtra("Precyzja", nP)
        setResult(Activity.RESULT_OK)

        super.finish()
    }
}
