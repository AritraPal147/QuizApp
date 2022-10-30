package com.example.quizapp

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import org.w3c.dom.Text
import java.lang.invoke.ConstantCallSite

class ResultActivity : AppCompatActivity() {
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val tvName: TextView = findViewById(R.id.tv_name)
        val tvScore: TextView = findViewById(R.id.tv_score)
        val btnFinish: Button = findViewById(R.id.btn_finish)

        val correctAnswers: Int = intent.getIntExtra(Constants.CORRECT_ANSWERS, 0)
        val totalQuestions: Int = intent.getIntExtra(Constants.TOTAL_QUESTIONS, 0)

        tvName.text = intent.getStringExtra(Constants.USER_NAME)
        tvScore.text = "Your score is $correctAnswers / $totalQuestions"

        btnFinish.setOnClickListener(){
            startActivity(Intent(this, MainActivity::class.java))
        }
    }
}