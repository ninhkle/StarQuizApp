package com.ninh.quizapp

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.core.content.ContextCompat

class QuizQuestionActivity : AppCompatActivity(), View.OnClickListener {
    private var mCurrentPosition: Int = 1
    private var mQuestionsList: ArrayList<Question>? = null
    private var mSelectedOptionPosition : Int = 0
    private var mCorrectAnswers : Int = 0
    private var mUserName: String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_question)

        mUserName = intent.getStringExtra(Constants.USER_NAME)

        mQuestionsList = Constants.getQuestions()
        setQuestion()
        val op1 = findViewById<TextView>(R.id.tv_option_one)
        val op2 = findViewById<TextView>(R.id.tv_option_two)
        val op3 = findViewById<TextView>(R.id.tv_option_three)
        val op4 = findViewById<TextView>(R.id.tv_option_four)
        op1.setOnClickListener(this)
        op2.setOnClickListener(this)
        op3.setOnClickListener(this)
        op4.setOnClickListener(this)

        val btnSub = findViewById<Button>(R.id.btn_submit)
        btnSub.setOnClickListener(this)
    }
    private fun setQuestion() {
        val progressBar = findViewById<ProgressBar>(R.id.progressBar)
        val tv_question = findViewById<TextView>(R.id.tv_question)
        val iv_image = findViewById<ImageView>(R.id.iv_image)
        val tv_progress = findViewById<TextView>(R.id.tv_progress)
        val op1 = findViewById<TextView>(R.id.tv_option_one)
        val op2 = findViewById<TextView>(R.id.tv_option_two)
        val op3 = findViewById<TextView>(R.id.tv_option_three)
        val op4 = findViewById<TextView>(R.id.tv_option_four)
        val btn_submit = findViewById<Button>(R.id.btn_submit)

        defaultOptionsView()
        if(mCurrentPosition == mQuestionsList!!.size - 1) {
            btn_submit.text = "FINISH"
        } else {
            btn_submit.text = "SUBMIT"
        }

        progressBar.progress = mCurrentPosition
        val question: Question? = mQuestionsList!!.get(mCurrentPosition)
        tv_progress.text = "${mCurrentPosition}/${progressBar.max}"
        tv_question.text = question!!.question
        iv_image.setImageResource(question.image)
        op1.text = question.optionOne
        op2.text = question.optionTwo
        op3.text = question.optionThree
        op4.text = question.optionFour
    }
    private fun defaultOptionsView(){
        val options = ArrayList<TextView>()
        options.add(findViewById<TextView>(R.id.tv_option_one))
        options.add(findViewById<TextView>(R.id.tv_option_two))
        options.add(findViewById<TextView>(R.id.tv_option_three))
        options.add(findViewById<TextView>(R.id.tv_option_four))

        for (option in options) {
            option.setTextColor(Color.parseColor("#7A8089"))
            option.typeface = Typeface.DEFAULT
            option.background = ContextCompat.getDrawable(this,
                R.drawable.default_option_border_bg)

        }
    }

    override fun onClick(v: View?) {
        val btn_submit = findViewById<Button>(R.id.btn_submit)
        when (v?.id) {
            R.id.tv_option_one -> selectedOptionView(v as TextView, 1)
            R.id.tv_option_two -> selectedOptionView(v as TextView, 2)
            R.id.tv_option_three -> selectedOptionView(v as TextView, 3)
            R.id.tv_option_four -> selectedOptionView(v as TextView, 4)
            R.id.btn_submit -> {
                if(mSelectedOptionPosition == 0) {
                    mCurrentPosition++
                    when {
                        mCurrentPosition <= mQuestionsList!!.size -1 -> {
                            setQuestion()
                        }
                        else -> {
                            val intent = Intent(this, ResultActivity::class.java)
                            intent.putExtra(Constants.USER_NAME, mUserName)
                            intent.putExtra(Constants.CORRECT_ANSWERS, mCorrectAnswers)
                            intent.putExtra((Constants.TOTAL_QUESTIONS), mQuestionsList!!.size -1)
                            startActivity(intent)
                            finish()
                        }
                    }
                } else {
                    val question = mQuestionsList?.get(mCurrentPosition)
                    if(question!!.correctAnswer != mSelectedOptionPosition) {
                        answerView(mSelectedOptionPosition, R.drawable.wrong_option_border_bg)
                    } else {
                        mCorrectAnswers++
                    }
                    answerView(question.correctAnswer, R.drawable.correct_option_border_bg)

                    if(mCurrentPosition == mQuestionsList!!.size - 1) {
                        btn_submit.text = "FINISH"
                    } else {
                        btn_submit.text = "GO TO NEXT QUESTION"
                    }
                    mSelectedOptionPosition = 0
                }
            }
        }
    }
    private fun answerView(answer: Int, drawableView: Int) {
        val op1 = findViewById<TextView>(R.id.tv_option_one)
        val op2 = findViewById<TextView>(R.id.tv_option_two)
        val op3 = findViewById<TextView>(R.id.tv_option_three)
        val op4 = findViewById<TextView>(R.id.tv_option_four)
        when(answer) {
            1 -> op1.background = ContextCompat.getDrawable(this, drawableView)
            2 -> op2.background = ContextCompat.getDrawable(this, drawableView)
            3 -> op3.background = ContextCompat.getDrawable(this, drawableView)
            4 -> op4.background = ContextCompat.getDrawable(this, drawableView)

        }
    }
    private fun selectedOptionView(tv: TextView, SelectedOptionNum: Int) {
        defaultOptionsView()
        mSelectedOptionPosition = SelectedOptionNum
        tv.setTextColor(Color.parseColor("#363A43"))
        tv.setTypeface(tv.typeface, Typeface.BOLD)
        tv.background = ContextCompat.getDrawable(this,
            R.drawable.selected_option_border_bg)
    }

}