package com.divyanshu.tictactoe

import android.graphics.Color
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import kotlin.system.exitProcess




var playerTurn = true
var board = Array(size= 3){ arrayOfNulls<String>(size = 3)}
class GamePlayActivity : AppCompatActivity() {
    lateinit var btn1: Button
    lateinit var btn2: Button
    lateinit var btn3: Button
    lateinit var btn4: Button
    lateinit var btn5: Button
    lateinit var btn6: Button
    lateinit var btn7: Button
    lateinit var btn8: Button
    lateinit var btn9: Button
    lateinit var resetbtn: Button
    lateinit var player1cnt: TextView
    lateinit var player2cnt: TextView
    var player1count = 0
    var player2count = 0
    var player1 = ArrayList<Int>()
    var player2 = ArrayList<Int>()
    var emptyCells = ArrayList<Int>()
    var activeuser = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_play)
        btn1 = findViewById(R.id.btnbox0)
        btn2 = findViewById(R.id.btnbox1)
        btn3 = findViewById(R.id.btnbox2)
        btn4 = findViewById(R.id.btnbox3)
        btn5 = findViewById(R.id.btnbox4)
        btn6 = findViewById(R.id.btnbox5)
        btn7 = findViewById(R.id.btnbox6)
        btn8 = findViewById(R.id.btnbox7)
        btn9 = findViewById(R.id.btnbox8)
        player1cnt = findViewById(R.id.tvplayer1count)
        player2cnt = findViewById(R.id.tvplayer2count)
        resetbtn = findViewById(R.id.btnreset)

        resetbtn.setOnClickListener {
            resetgame()
        }

    }

    private fun resetgame() {
        player1.clear()
        player2.clear()
        emptyCells.clear()
        for(i in 0..2){
            for(j in 0..2){
                board[i][j] = ""
            }
        }
        activeuser = 1
        for (i in 1..9) {
            var ButtonSelected: Button?
            ButtonSelected = when (i) {
                1 -> btn1
                2 -> btn2
                3 -> btn3
                4 -> btn4
                5 -> btn5
                6 -> btn6
                7 -> btn7
                8 -> btn8
                9 -> btn9
                else -> {
                    btn1
                }
            }
            ButtonSelected.isEnabled = true
            ButtonSelected.text = ""
            player1cnt.text = "Player1 : $player1count"
            player2cnt.text = "Player2 : $player2count"

        }
    }

    fun buttonClick(view: android.view.View) {
        if (playerTurn) {
            val but = view as Button
            var cellID = 0
            when (but.id) {
                R.id.btnbox0 -> cellID = 1
                R.id.btnbox1 -> cellID = 2
                R.id.btnbox2 -> cellID = 3
                R.id.btnbox3 -> cellID = 4
                R.id.btnbox4 -> cellID = 5
                R.id.btnbox5 -> cellID = 6
                R.id.btnbox6 -> cellID = 7
                R.id.btnbox7 -> cellID = 8
                R.id.btnbox8 -> cellID = 9
            }
            playerTurn = false
            Handler().postDelayed(Runnable { playerTurn = true }, 600)
            playNow(but, cellID)
        }
    }

    private fun playNow(buttonSelected: Button, currCell: Int) {
        val audio = MediaPlayer.create(this, R.raw.app_src_main_res_raw_poutch)
        if (activeuser == 1) {
            buttonSelected.text = "X"
            var row = detectrow(buttonSelected)
            var col = detectcol(buttonSelected)
            board[row][col] = "X"
            buttonSelected.setTextColor(Color.parseColor("#EC0C0C"))
            player1.add(currCell)
            emptyCells.add(currCell)
            audio.start()
            buttonSelected.isEnabled = false
            Handler().postDelayed(Runnable { audio.release() }, 200)
            val CheckWinner = checkWinner()
            if (CheckWinner == 1)
                Handler().postDelayed(Runnable { resetgame() }, 2000)
            else if (oneplayer) {
                Handler().postDelayed(Runnable { robot() }, 500)
            } else
                activeuser = 2
        } else {
            buttonSelected.text = "O"
            var row = detectrow(buttonSelected)
            val col = detectcol(buttonSelected)
            board[row][col] = "O"
            audio.start()
            buttonSelected.setTextColor(Color.parseColor("#D22BB804"))
            activeuser = 1
            player2.add(currCell)
            emptyCells.add(currCell)
            Handler().postDelayed(Runnable { audio.release() }, 200)
            buttonSelected.isEnabled = false
            val CheckWinner = checkWinner()
            if (CheckWinner == 1)
                Handler().postDelayed(Runnable { resetgame() }, 5000)

        }
    }

    private fun detectrow(buttonSelected: Button): Int {
        if(buttonSelected == btn1)
            return 0
        else if(buttonSelected == btn2)
            return 0
        else if(buttonSelected == btn3)
            return 0
        else if(buttonSelected == btn4)
            return 1
        else if(buttonSelected == btn5)
            return 1
        else if(buttonSelected == btn6)
            return 1
        else if(buttonSelected == btn7)
            return 2
        else if(buttonSelected == btn8)
            return 2
        else
            return 2
    }

    private fun detectcol(buttonSelected: Button): Int {
        if(buttonSelected == btn1)
            return 0
        else if(buttonSelected == btn2)
            return 1
        else if(buttonSelected == btn3)
            return 2
        else if(buttonSelected == btn4)
            return 0
        else if(buttonSelected == btn5)
            return 1
        else if(buttonSelected == btn6)
            return 2
        else if(buttonSelected == btn7)
            return 0
        else if(buttonSelected == btn8)
            return 1
        else
            return 2
    }

    private fun robot() {
       var x : Move = Minimax.findbestmove(board)
        val r = x.row
        val c = x.col
        System.out.println(r)
        System.out.println(c)
//        board[r][c] = "O"
        lateinit var buttonSelected : Button
        var rnd : Int = 0

        if(r == 0 && c == 0) {
            buttonSelected = btn1
            rnd = 1

        }

        else if(r == 0 && c == 1) {
            buttonSelected = btn2
            rnd = 2

        }
        else if(r == 0 && c == 2) {
            buttonSelected = btn3
            rnd = 3
        }
        else if(r == 1 && c == 0) {
            buttonSelected = btn4
            rnd = 4
        }
        else if(r == 1 && c == 1) {
            buttonSelected = btn5
            rnd = 5
        }
        else if(r == 1 && c == 2) {
            buttonSelected = btn6
            rnd = 6
        }
        else if(r == 2 && c == 0) {
            buttonSelected = btn7
            rnd = 7
        }
        else if(r == 2 && c == 1) {
            buttonSelected = btn8
            rnd = 8
        }
        else if(r == 2 && c == 3) {
            buttonSelected = btn9
            rnd = 9
        }
//        else{
//           // buttonSelected = btn1
//        //    rnd = 1
//        }
            emptyCells.add(rnd)
//            val audio = MediaPlayer.create(this, R.raw.app_src_main_res_raw_poutch)
//            audio.start()
//            Handler().postDelayed(Runnable { audio.release() }, 500)
            buttonSelected.text = "O"
            buttonSelected.setTextColor(Color.parseColor("#D22BB804"))
            player2.add(rnd)
            buttonSelected.isEnabled = false
            var checkwinner = checkWinner()
            if(checkwinner == 1){
                Handler().postDelayed(Runnable { resetgame() }, 4000)
            }
        }


    private fun checkWinner(): Int {
        val audio = MediaPlayer.create(this, R.raw.app_src_main_res_raw_success)
        if ((player1.contains(1) && player1.contains(2) && player1.contains(3) ) || (player1.contains(1) && player1.contains(4) && player1.contains(7))||
            (player1.contains(3) && player1.contains(6) && player1.contains(9)) || (player1.contains(7) && player1.contains(8) && player1.contains(9))||
            (player1.contains(4)&&player1.contains(5)&&player1.contains(6)) || (player1.contains(1)&&player1.contains(5) && player1.contains(9))||
            player1.contains(3)&&player1.contains(5)&&player1.contains(7) || (player1.contains(2)&&player1.contains(5) && player1.contains(8)))
         {
            player1count += 1
            buttonDisable()
            audio.start()
            disableReset()
            Handler().postDelayed(Runnable { audio.release() }, 4000)
            val build = AlertDialog.Builder(this)
            build.setTitle("Game Over")
            build.setMessage("Player 1 Wins!!" + "\n\n" + "Do you want to play again")
            build.setPositiveButton("Ok") { dialog, which ->
                resetgame()
                audio.release()
            }
            build.setNegativeButton("Exit") { dialog, which ->
                audio.release()
                exitProcess(1)

            }
            Handler().postDelayed(Runnable { build.show() }, 2000)
            return 1

        } else if ((player2.contains(1) && player2.contains(2) && player2.contains(3) ) || (player2.contains(1) && player2.contains(4) && player2.contains(7))||
            (player2.contains(3) && player2.contains(6) && player2.contains(9)) || (player2.contains(7) && player2.contains(8) && player2.contains(9))||
            (player2.contains(4)&&player2.contains(5)&&player2.contains(6)) || (player2.contains(1)&&player2.contains(5) && player2.contains(9))||
            player2.contains(3)&&player2.contains(5)&&player2.contains(7) || (player2.contains(2)&&player2.contains(5) && player2.contains(8))
        ) {
            player2count += 1
            audio.start()
            buttonDisable()
            disableReset()
            Handler().postDelayed(Runnable { audio.release() }, 4000)
            val build = AlertDialog.Builder(this)
            build.setTitle("Game Over")
            build.setMessage("Player 2 Wins!!" + "\n\n" + "Do you want to play again")
            build.setPositiveButton("Ok") { dialog, which ->
                resetgame()
                audio.release()
            }
            build.setNegativeButton("Exit") { dialog, which ->
                audio.release()
                exitProcess(1)
            }
            Handler().postDelayed(Runnable { build.show() }, 2000)
            return 1
        } else if (emptyCells.contains(1) && emptyCells.contains(2) && emptyCells.contains(3) && emptyCells.contains(4) && emptyCells.contains(5) && emptyCells.contains(6) && emptyCells.contains(7) &&
            emptyCells.contains(8) && emptyCells.contains(9)
        ) {

            val build = AlertDialog.Builder(this)
            build.setTitle("Game Draw")
            build.setMessage("Nobody Wins" + "\n\n" + "Do you want to play again")
            build.setPositiveButton("Ok") { dialog, which ->
                resetgame()
            }
            build.setNegativeButton("Exit") { dialog, which ->
                exitProcess(1)
            }
            build.show()
            return 1

        }
        return 0
    }

    private fun disableReset() {
        resetbtn.isEnabled = false
        Handler().postDelayed(Runnable { resetbtn.isEnabled = true }, 2200)

    }

    private fun buttonDisable() {
        player1.clear()
        player2.clear()
        emptyCells.clear()
        for(i in 0..2){
            for(j in 0..2){
                board[i][j]=""
            }
        }
        activeuser = 1
        for (i in 1..9) {
            var buttonselected: Button?
            buttonselected = when (i) {
                1 -> btn1
                2 -> btn2
                3 -> btn3
                4 -> btn4
                5 -> btn5
                6 -> btn6
                7 -> btn7
                8 -> btn8
                9 -> btn9
                else -> {
                    btn1
                }
            }
            buttonselected.isEnabled = true
            buttonselected.text = ""
            player1cnt.text = "Player1 : $player1count"
            player2cnt.text = "Player1 : $player2count"
        }
    }

}