package com.example.quizapp

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.core.content.ContextCompat

class QuizQuestionsActivity : AppCompatActivity(), View.OnClickListener{
    // View.OnClickListener allows us to click items inside it

    // Global variable declaration
    private var mCurrentPosition: Int = 1
    private var mQuestionsList: ArrayList<Question>? = null
    private var mSelectedOptionPosition: Int = 0
    private var mUserName: String? = null
    private var mCorrectAnswers: Int = 0

    private var tvQuestion: TextView? = null
    private var ivImage: ImageView? = null
    private var progressBar: ProgressBar? = null
    private var tvProgress: TextView? = null

    private var tvOptionOne: TextView? = null
    private var tvOptionTwo: TextView? = null
    private var tvOptionThree: TextView? = null
    private var tvOptionFour: TextView? = null
    private var btnSubmit: Button? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_questions)

        mUserName = intent.getStringExtra(Constants.USER_NAME)
        // Gets USER_NAME from Constants, which was passed in
        // intent.putExtra() from MainActivity

        tvQuestion = findViewById(R.id.tv_question)
        ivImage = findViewById(R.id.iv_image)
        progressBar = findViewById(R.id.progress_bar)
        tvProgress = findViewById(R.id.tv_progress)
        tvOptionOne = findViewById(R.id.tv_option_one)
        tvOptionTwo = findViewById(R.id.tv_option_two)
        tvOptionThree = findViewById(R.id.tv_option_three)
        tvOptionFour = findViewById(R.id.tv_option_four)
        btnSubmit = findViewById(R.id.btn_submit)

        tvOptionOne?.setOnClickListener(this)
        tvOptionTwo?.setOnClickListener(this)
        tvOptionThree?.setOnClickListener(this)
        tvOptionFour?.setOnClickListener(this)
        // Sets OnClickListeners to all options so that when they are clicked, the onClick method
        // below is called. We are passing this class to setOnClickListener() and the OnClick method
        // is called

        btnSubmit?.setOnClickListener(this)

        mQuestionsList = Constants.getQuestions()
        // Creates an ArrayList which stores the ArrayList returned from Constants object

        //Log.i("QuestionsList size is ", "${mQuestionsList.size}")
        // Prints a given message in the log when onCreate function of this activity is called
        // To find the message, go to logcat and search for the tag, i -> information

        //for (i in questionsList)
        //    Log.e("Questions", i.question)      // e -> throws error, message is red in logcat

        setQuestion()
        defaultOptionsView()
    }

    private fun setQuestion() {

        defaultOptionsView()
        val question: Question = mQuestionsList!![mCurrentPosition - 1]
        // !! tells the compiler that the given variable is not null

        tvQuestion?.text = question.question
        ivImage?.setImageResource(question.image)
        progressBar?.progress = mCurrentPosition
        tvProgress?.text = "$mCurrentPosition/${progressBar?.max}"
        tvOptionOne?.text = question.optionOne
        tvOptionTwo?.text = question.optionTwo
        tvOptionThree?.text = question.optionThree
        tvOptionFour?.text = question.optionFour

        if (mCurrentPosition == mQuestionsList!!.size)
            btnSubmit?.text = "FINISH"
        else
            btnSubmit?.text = "SUBMIT"
    }


    private fun defaultOptionsView(){
        val options = ArrayList<TextView>()

        tvOptionOne?.let {
            options.add(0, it)
        }

        tvOptionTwo?.let {
            options.add(1, it)
        }

        tvOptionThree?.let {
            options.add(2, it)
        }

        tvOptionFour?.let {
            options.add(3, it)
        }

        for (option in options){
            option.setTextColor(Color.parseColor("#7A8089"))
            // sets the text color of all options to "#7A8089"

            option.typeface = Typeface.DEFAULT
            // typeface default makes the text of type default - it is basically textStyle
            // It removes the bold text style that occurs after selectedOptionsView() is called

            option.background = ContextCompat.getDrawable(this,
                R.drawable.default_option_border_bg)
            // Changes background to the one which is stored in default_option_border_bg.xml
            // R is res
        }
    }


    private fun selectedOptionsView(tv: TextView, selectedOptionNum: Int){
        defaultOptionsView()
        // This will set the options to default view, without the coloured boundary that it will
        // get after clicking on it

        mSelectedOptionPosition = selectedOptionNum

        tv.setTextColor(Color.parseColor("#FF6200EE"))
        // Sets the text color of textview as purple - only hexadecimal values of colors work
        tv.setTypeface(tv.typeface, Typeface.BOLD)
        // Sets the text of textview as bold

        tv.background = ContextCompat.getDrawable(this,
            R.drawable.selected_option_border_bg)

    }

    @SuppressLint("SetTextI18n")
    override fun onClick(view: View?) {
        when (view?.id){ // checks what the id of view of - basically sees which textview is clicked
            R.id.tv_option_one -> {
                tvOptionOne?.let {
                    selectedOptionsView(it, 1)
                }
            }

            R.id.tv_option_two -> {
                tvOptionTwo?.let {
                    selectedOptionsView(it, 2)
                }
            }

            R.id.tv_option_three -> {
                tvOptionThree?.let {
                    selectedOptionsView(it, 3)
                }
            }

            R.id.tv_option_four -> {
                tvOptionFour?.let {
                    selectedOptionsView(it, 4)
                }
            }

            R.id.btn_submit ->{
                if (mSelectedOptionPosition == 0) {
                    mCurrentPosition++      // Goes to next question for the first question

                    when{
                        mCurrentPosition <= mQuestionsList!!.size -> {
                            setQuestion()
                            // Questions are set as long as the current question does not exceed the
                            // total number of questions
                        }

                        else -> {
                            val intent = Intent(this, ResultActivity::class.java)
                            intent.putExtra(Constants.USER_NAME, mUserName)
                            intent.putExtra(Constants.TOTAL_QUESTIONS, mQuestionsList?.size)
                            intent.putExtra(Constants.CORRECT_ANSWERS, mCorrectAnswers)

                            startActivity(intent)
                            finish()
                        }
                    }
                }

                else{
                    val question = mQuestionsList?.get(mCurrentPosition - 1)
                    if (question!!.correctAnswer != mSelectedOptionPosition) {
                        answerView(mSelectedOptionPosition, R.drawable.wrong_answer_color)
                        // if the selected option is not the correct answer, option will be coloured red
                    } else{
                        mCorrectAnswers++
                    }
                    answerView(question.correctAnswer, R.drawable.correct_answer_color)
                    // Always colours the correct answer green

                    if (mCurrentPosition == mQuestionsList!!.size){
                        btnSubmit?.text = "FINISH"
                    }else{
                        btnSubmit?.text = "GO TO NEXT QUESTION"
                    }

                    mSelectedOptionPosition = 0
                }
            }
        }
    }

    private fun answerView(answer: Int, drawableView: Int){
        when (answer){
            1 -> {
                tvOptionOne?.background = ContextCompat.getDrawable(this,
                    drawableView)
                tvOptionOne?.setTextColor(Color.parseColor("#000000"))
                tvOptionOne?.setTypeface(tvOptionOne?.typeface, Typeface.BOLD_ITALIC)
            }

            2 -> {
                tvOptionTwo?.background = ContextCompat.getDrawable(this,
                    drawableView)
                tvOptionTwo?.setTextColor(Color.parseColor("#000000"))
                tvOptionTwo?.setTypeface(tvOptionTwo?.typeface, Typeface.BOLD_ITALIC)
            }

            3 -> {
                tvOptionThree?.background = ContextCompat.getDrawable(this,
                    drawableView)
                tvOptionThree?.setTextColor(Color.parseColor("#000000"))
                tvOptionThree?.setTypeface(tvOptionThree?.typeface, Typeface.BOLD_ITALIC)
            }

            4 -> {
                tvOptionFour?.background = ContextCompat.getDrawable(this,
                    drawableView)
                tvOptionFour?.setTextColor(Color.parseColor("#000000"))
                tvOptionFour?.setTypeface(tvOptionFour?.typeface, Typeface.BOLD_ITALIC)
            }
        }
    }

}