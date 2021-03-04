package com.ninh.quizapp

object Constants {
    const val USER_NAME: String = "user_name"
    const val TOTAL_QUESTIONS: String = "total_question"
    const val CORRECT_ANSWERS: String = "correct_answers"

    fun getQuestions(): ArrayList<Question> {
        val questionsList = ArrayList<Question>()
        val q1 = Question(1, "Which stage is this star in?",
            R.drawable.brown_dwarf, "Brown Dwarf", "Nebula",
            "Red Giant", "Black Dwarf", 1
        )
        questionsList.add(q1)
        val q2 = Question(2, "Which stage is this star in?",
            R.drawable.red_giant, "Main Sequence Star", "Brown Dwarf",
            "Red Giant", "Protostar", 3
        )

        val q3 = Question(3, "Which stage is this star in?",
            R.drawable.neutron, "Nebula", "White Dwarf",
            "Black Hole", "Neutron Star", 4
        )
        val q4 = Question(4, "Which stage is this star in?",
            R.drawable.supernova, "Nebula", "Supernova",
            "Dust Cloud", "Neutron Star", 2
        )
        val q5 = Question(5, "Which stage is this star in?",
            R.drawable.white_dwarf, "Brown Dwarf", "Nebula",
            "Protostar", "White Dwarf", 4
        )
        val q6 = Question(6, "Which stage is this star in?",
            R.drawable.black_hole, "Brown Dwarf", "Supernova",
            "Black Hole", "Black Dwarf", 3
        )
        questionsList.add(q1)
        questionsList.add(q2)
        questionsList.add(q3)
        questionsList.add(q4)
        questionsList.add(q5)
        questionsList.add(q6)
        return questionsList
    }
}