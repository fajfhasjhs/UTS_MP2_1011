package com.example.uts_mp2_1011

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_second)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.second)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val name = findViewById<EditText>(R.id.name)
        val age = findViewById<EditText>(R.id.age)
        val gender = findViewById<RadioGroup>(R.id.gender)

        val sharedPreferences = getSharedPreferences("UserPrefs",Context.MODE_PRIVATE)

        val sharedName = sharedPreferences.getString("SHARED_NAME","")
        val sharedAge = sharedPreferences.getString("SHARED_AGE", "")
        val sharedGender = sharedPreferences.getString("SHARED_GENDER","")

        name.setText(sharedName)
        age.setText(sharedAge)

        if (sharedGender == getString(R.string.man)){
            gender.check(R.id.man)
        }else if (sharedGender == getString(R.string.woman)){
            gender.check(R.id.woman)
        }


        findViewById<Button>(R.id.send).setOnClickListener {
            val genderId = gender.checkedRadioButtonId

            val nameText = name.text.toString()
            val ageText = age.text.toString()
            val ageInt = ageText.toIntOrNull()
            val genderText = when(genderId){
                R.id.man -> getString(R.string.man)
                R.id.woman -> getString(R.string.woman)
                else -> getString(R.string.error)
            }

            if (ageText.isEmpty()){
                Toast.makeText(this, "Isi dengan benar!", Toast.LENGTH_SHORT).show()
            }

            if (ageInt != null){
                if (nameText.isNotEmpty() && ageInt >= 1 && ageInt <= 100 && genderId != -1){
                    val edit = sharedPreferences.edit()
                    edit.putString("SHARED_NAME",nameText)
                    edit.putString("SHARED_AGE",ageText)
                    edit.putString("SHARED_GENDER",genderText)
                    edit.apply()


                    val intent = Intent (this, ThirdActivity::class.java).apply {
                        putExtra("NAMA", nameText)
                        putExtra("AGE", ageText)
                        putExtra("GENDER", genderText)
                    }
                    startActivity(intent)
                }else{
                    Toast.makeText(this, "Isi dengan benar!", Toast.LENGTH_SHORT).show()
                }

            }






        }

    }
}