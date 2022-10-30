package com.example.quizapp

object Constants {

    const val USER_NAME: String = "user_name"
    const val TOTAL_QUESTIONS: String = "total_questions"
    const val CORRECT_ANSWERS: String = "correct_answers"
    // It is a good practice to use variables in Constants object when
    // passing values from one activity to another

    // Member function which stores all questions in form of array list and returns it
    fun getQuestions(): ArrayList<Question>{
        val questionsList = ArrayList<Question>()

        val que1 = Question(1, R.drawable.ic_flag_of_argentina,
        "Which country does this flag belong to?",
            "Argentina", "Austria", "Brazil", "Armenia",
        1)
        questionsList.add(que1)

        val que2 = Question(2, R.drawable.ic_flag_of_australia,
            "Which country does this flag belong to?",
            "South America", "England", "New Zealand",
            "Australia", 4)
        questionsList.add(que2)

        val que3 = Question(3, R.drawable.ic_flag_of_belgium,
            "Which country does this flag belong to?",
            "Germany", "Belgium", "Denmark", "Fiji",
            2)
        questionsList.add(que3)

        val que4 = Question(4, R.drawable.ic_flag_of_brazil,
            "Which country does this flag belong to?",
            "Brazil", "Mexico", "Canada", "Malaysia",
            1)
        questionsList.add(que4)

        val que5 = Question(5, R.drawable.ic_flag_of_denmark,
            "Which country does this flag belong to?",
            "Norway", "Switzerland", "Denmark", "Sweden",
            3)
        questionsList.add(que5)

        val que6 = Question(6, R.drawable.ic_flag_of_fiji,
            "Which country does this flag belong to?",
            "USA", "Australia", "England", "Fiji",
            4)
        questionsList.add(que6)

        val que7 = Question(7, R.drawable.ic_flag_of_germany,
            "Which country does this flag belong to?",
            "Belgium", "Austria", "Germany", "Spain",
            3)
        questionsList.add(que7)

        val que8 = Question(1, R.drawable.ic_flag_of_india,
            "Which country does this flag belong to?",
            "India", "Pakistan", "Iran", "China",
            1)
        questionsList.add(que8)

        val que9 = Question(1, R.drawable.ic_flag_of_kuwait,
            "Which country does this flag belong to?",
            "Saudi Arabia", "Kuwait", "Iraq", "Egypt",
            2)
        questionsList.add(que9)

        val que10 = Question(1, R.drawable.ic_flag_of_new_zealand,
            "Which country does this flag belong to?",
            "Australia", "South Africa", "Spain", "New Zealand",
            4)
        questionsList.add(que10)


        return questionsList
    }
}