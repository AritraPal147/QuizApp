package com.example.quizapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var btnStart : Button = findViewById(R.id.btnStart)
        var editTextView : EditText = findViewById(R.id.editTextView)

        btnStart.setOnClickListener{
            if (editTextView.text.isEmpty()){
                Toast.makeText(this, "Please Enter Name", Toast.LENGTH_SHORT).show()
                // Toast with "Please Enter Name" shown when the edit Text View is empty
            }

            else{
                val intent = Intent(this, QuizQuestionsActivity::class.java)
                // A Variable intent is created which will change the activity
                // from this (MainActivity) to QuizQuestionsActivity

                intent.putExtra(Constants.USER_NAME, editTextView.text.toString())
                /* putExtra() allows us to pass extra things within the intent so that
                 when the switching happens, the extra things can be used within the other
                 Activity to which intent switches to */

                startActivity(intent)
                // Starts the Activity to which the intent switches to

                finish()
                /* Terminates the Previous Activity when app switches to other activity.
                 In this case, it will terminate MainActivity and Switch to QuizQuestionsActivity
                 if Main Activity is not terminated, then pressing the back button from a question
                 will return us to the first screen, i.e., the Main Activity */
            }
        }
    }



}