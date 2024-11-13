package com.example.uts_mp2_1011

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ThirdActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_third)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.third)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val name = findViewById<TextView>(R.id.name)
        val age = findViewById<TextView>(R.id.age)
        val gender = findViewById<TextView>(R.id.gender)

        val nameText = intent.getStringExtra("NAMA")
        val ageText = intent.getStringExtra("AGE")
        val genderText = intent.getStringExtra("GENDER")

        name.text = nameText
        age.text = ageText
        gender.text = genderText


        findViewById<Button>(R.id.home).setOnClickListener {
            val intent = Intent (this, MainActivity::class.java)
            startActivity(intent)
        }



    }
}