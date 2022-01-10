package com.divyanshu.tictactoe

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

var oneplayer : Boolean = false
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var player1 = findViewById<Button>(R.id.btnsinleplayer)
        var player2 = findViewById<Button>(R.id.btnmultiplayer)

        player1.setOnClickListener {
            oneplayer = true
            startActivity(Intent(this , GamePlayActivity2::class.java))
        }

        player2.setOnClickListener {
            oneplayer = false
            startActivity(Intent(this , GamePlayActivity::class.java))
        }
    }
}