package com.divyanshu.tictactoe

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import kotlin.random.Random
import kotlin.system.exitProcess

class GamePlayActivity2 : AppCompatActivity() {
    private val boardCells = Array(size = 3){ arrayOfNulls<ImageView>(size = 3)}

    var board = Board()
    var scx = 0
    var scy = 0
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_play2)
        loadboard()
    }

    private fun resetgame(){
        var reset = findViewById<Button>(R.id.Reset)
        var scoreX = findViewById<TextView>(R.id.ScoreX)
        var scoreY = findViewById<TextView>(R.id.ScoreY)
        reset.setOnClickListener{
            board = Board()
            scoreX.text = "Score X :- 0"
            scoreY.text = "Score Y :- 0"
            mapboardtoUI()
        }
    }

    private fun printscore(){
        var scoreX = findViewById<TextView>(R.id.ScoreX)
        var scoreY = findViewById<TextView>(R.id.ScoreY)
        scoreX.text = "Score X :- $scx"
        scoreY.text = "Score Y :- $scy"
    }
    private fun mapboardtoUI(){
        for(i in board.board.indices){
            for(j in board.board.indices){
                when(board.board[i][j]){
                    Board.PLAYER -> {
                        boardCells[i][j]?.setImageResource(R.drawable.circle)
                        boardCells[i][j]?.isEnabled = false
                    }
                    Board.ROBOT -> {
                        boardCells[i][j]?.setImageResource(R.drawable.cross)
                        boardCells[i][j]?.isEnabled = false
                    }
                    else -> {
                        boardCells[i][j]?.setImageResource(0)
                        boardCells[i][j]?.isEnabled = true
                    }
                }
            }
        }
    }
    private fun loadboard(){
        boardCells[0][0] = findViewById(R.id.buttonImage1)
        boardCells[0][0]?.setOnClickListener(CellClickListener(0,0))
        boardCells[0][1] = findViewById(R.id.buttonImage2)
        boardCells[0][1]?.setOnClickListener(CellClickListener(0,1))
        boardCells[0][2] = findViewById(R.id.buttonImage3)
        boardCells[0][2]?.setOnClickListener(CellClickListener(0,2))
        boardCells[1][0] = findViewById(R.id.buttonImage4)
        boardCells[1][0]?.setOnClickListener(CellClickListener(1,0))
        boardCells[1][1] = findViewById(R.id.buttonImage5)
        boardCells[1][1]?.setOnClickListener(CellClickListener(1,1))
        boardCells[1][2] = findViewById(R.id.buttonImage6)
        boardCells[1][2]?.setOnClickListener(CellClickListener(1,2))
        boardCells[2][0] = findViewById(R.id.buttonImage7)
        boardCells[2][0]?.setOnClickListener(CellClickListener(2,0))
        boardCells[2][1] = findViewById(R.id.buttonImage8)
        boardCells[2][1]?.setOnClickListener(CellClickListener(2,1))
        boardCells[2][2] = findViewById(R.id.buttonImage9)
        boardCells[2][2]?.setOnClickListener(CellClickListener(2,2))
    }

    inner class CellClickListener(private val i : Int , private val j : Int) : View.OnClickListener{
        override fun onClick(p0 : View?) {

            if (!board.isGameOver) {
                val cell = Move(i, j)
                board.placeMove(cell, Board.PLAYER)
                board.minimax(0 , Board.ROBOT)
                    val audio = MediaPlayer.create(this@GamePlayActivity2, R.raw.app_src_main_res_raw_poutch)
                    audio.start()
                    Handler().postDelayed(Runnable { audio.release() }, 500)
                board.robomove?.let {
                    board.placeMove(it , Board.ROBOT)
                }


                mapboardtoUI()
                if(board.hasRoboWon() == true){
                    val audio = MediaPlayer.create(this@GamePlayActivity2, R.raw.app_src_main_res_raw_success)
                    scx = scx + 1
                    printscore()
                    audio.start()
                    Handler().postDelayed(Runnable { audio.release() }, 4000)
                    val build = AlertDialog.Builder(this@GamePlayActivity2)
                    build.setTitle("Game Over")
                    build.setMessage("Player 1 Wins!!" + "\n\n" + "Do you want to play again")
                    build.setPositiveButton("Ok") {dialog, which ->
                        audio.release()
                        resetgame()
                    }
                    build.setNegativeButton("Exit") { dialog, which ->
                        audio.release()
                        exitProcess(1)
                    }
                    Handler().postDelayed(Runnable { build.show() }, 2000)


                }
                else if(board.hasPlayerWon() == true){
                    val audio = MediaPlayer.create(this@GamePlayActivity2, R.raw.app_src_main_res_raw_success)
                    scy = scy + 1
                    printscore()
                    audio.start()
                    Handler().postDelayed(Runnable { audio.release() }, 4000)
                    val build = AlertDialog.Builder(this@GamePlayActivity2)
                    build.setTitle("Game Over")
                    build.setMessage("Player 2 Wins!!" + "\n\n" + "Do you want to play again")
                    build.setPositiveButton("Ok") {dialog, which ->
                        audio.release()
                        resetgame()
                    }
                    build.setNegativeButton("Exit") { dialog, which ->
                        audio.release()
                        exitProcess(1)
                    }
                    Handler().postDelayed(Runnable { build.show() }, 2000)

                }
                else if(board.isGameOver){
                    val audio = MediaPlayer.create(this@GamePlayActivity2, R.raw.app_src_main_res_raw_success)
                    printscore()
                    audio.start()
                    Handler().postDelayed(Runnable { audio.release() }, 4000)
                    val build = AlertDialog.Builder(this@GamePlayActivity2)
                    build.setTitle("Game Over")
                    build.setMessage("Game Draw!!" + "\n\n" + "Do you want to play again")
                    build.setPositiveButton("Ok") {dialog, which ->
                        audio.release()
                        resetgame()
                    }
                    build.setNegativeButton("Exit") { dialog, which ->
                        audio.release()
                        exitProcess(1)
                    }
                    Handler().postDelayed(Runnable { build.show() }, 2000)

                }
            }
        }
    }
}