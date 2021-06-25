package com.example.myarabicword

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

class MainActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.arabic_option).setOnClickListener(this)
        findViewById<Button>(R.id.french_option).setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.arabic_option -> {
                val intent = Intent(this, ArabicActivity::class.java)
                startActivity(intent)
            }
            R.id.french_option -> {
                val intent = Intent(this, FrenchActivity::class.java)
                startActivity(intent)
            }
        }
    }


}