package com.example.learn_intent

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.RadioGroup

class MoveForResultActivity : AppCompatActivity() {

    private lateinit var btnchoose : Button
    private lateinit var rgNumber : RadioGroup

    companion object {
        const val EXTRA_SELECTED_VALUE = "extra_selected_value"
        const val RESULT_CODE = 110
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_move_for_result)

        btnchoose = findViewById(R.id.btn_choose)
        rgNumber = findViewById(R.id.rg_number)
    }
}