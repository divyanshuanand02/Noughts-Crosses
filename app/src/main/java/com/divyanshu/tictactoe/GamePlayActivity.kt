package com.divyanshu.tictactoe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class GamePlayActivity : AppCompatActivity() {
    lateinit var btn0 : Button
    lateinit var btn1 : Button
    lateinit var btn2 : Button
    lateinit var btn3 : Button
    lateinit var btn4 : Button
    lateinit var btn5 : Button
    lateinit var btn6 : Button
    lateinit var btn7 : Button
    lateinit var btn8 : Button
    lateinit var resetbtn : Button
    lateinit var player1cnt : TextView
    lateinit var player2cnt : TextView
    var player1count = 0
    var player2count = 0
    var player1 = ArrayList<Int>()
    var player2 = ArrayList<Int>()
    var emptyCells = ArrayList<Int>()
    var activeuser = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_play)
        btn0 = findViewById(R.id.btnbox0)
        btn1 = findViewById(R.id.btnbox1)
        btn2 = findViewById(R.id.btnbox2)
        btn3 = findViewById(R.id.btnbox3)
        btn4 = findViewById(R.id.btnbox4)
        btn5 = findViewById(R.id.btnbox5)
        btn6 = findViewById(R.id.btnbox6)
        btn7 = findViewById(R.id.btnbox7)
        btn8 = findViewById(R.id.btnbox8)
        player1cnt = findViewById(R.id.tvplayer1count)
        player2cnt = findViewById(R.id.tvplayer2count)
        resetbtn = findViewById(R.id.btnreset)

        resetbtn.setOnClickListener {
            resetgame()
        }

    }

    private fun resetgame() {
        TODO("Not yet implemented")
    }

    fun buttonClick(view: android.view.View) {}
}