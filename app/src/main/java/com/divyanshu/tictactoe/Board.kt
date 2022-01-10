package com.divyanshu.tictactoe

import android.media.MediaPlayer
import android.widget.ImageView

class Board {
    companion object{
        const val PLAYER = "O"
        const val ROBOT = "X"
    }
    val board = Array(size = 3){ arrayOfNulls<String>(size = 3)}

    val isGameOver: Boolean
        get() = hasRoboWon() || hasPlayerWon() || availableCells.isEmpty()

    fun hasPlayerWon() : Boolean{
        if (board[0][0] == board[1][1] &&
            board[0][0] == board[2][2] &&
            board[0][0] == PLAYER ||
            board[0][2] == board[1][1] &&
            board[0][2] == board[2][0] &&
            board[0][2] == PLAYER
        ) {
            return true
        }

        for (i in board.indices) {
            if (
                board[i][0] == board[i][1] &&
                board[i][0] == board[i][2] &&
                board[i][0] == PLAYER ||
                board[0][i] == board[1][i] &&
                board[0][i] == board[2][i] &&
                board[0][i] == PLAYER
            ) {
                return true
            }
        }

        return false
    }

    fun hasRoboWon() : Boolean{
        if (board[0][0] == board[1][1] &&
            board[0][0] == board[2][2] &&
            board[0][0] == ROBOT ||
            board[0][2] == board[1][1] &&
            board[0][2] == board[2][0] &&
            board[0][2] == ROBOT
        ) {
            return true
        }

        for (i in board.indices) {
            if (
                board[i][0] == board[i][1] &&
                board[i][0] == board[i][2] &&
                board[i][0] == ROBOT||
                board[0][i] == board[1][i] &&
                board[0][i] == board[2][i] &&
                board[0][i] == ROBOT
            ) {
                return true
            }
        }

        return false
    }
    val availableCells : List<Move>
    get(){
        val cells = mutableListOf<Move>()
        for(i in board.indices){
            for(j in board.indices){
                if(board[i][j].isNullOrEmpty()){
                    cells.add(Move(i,j))
                }
            }
        }
        return cells
    }
    var robomove : Move? = null
    fun minimax(depth : Int , player: String) : Int{
        if(hasRoboWon())
            return +1
        if(hasPlayerWon())
            return -1
        if(availableCells.isEmpty())
            return 0
        var min = Integer.MAX_VALUE
        var max = Integer.MIN_VALUE
        for(i in availableCells.indices){
            val cells = availableCells[i]
            if(player == ROBOT){
                placeMove(cells, ROBOT)
                val currscore = minimax(depth+1 , PLAYER)
                max = Math.max(currscore, max)
                if(currscore >= 0){
                    if(depth == 0)
                        robomove = cells
                }
                if(currscore == 1){
                    board[cells.row][cells.col] = ""
                    break
                }
                if(i == availableCells.size - 1 && max < 0){
                    if(depth == 0) robomove = cells
                }
            }
            else if(player == PLAYER){
                placeMove(cells, PLAYER)
                val currscore = minimax(depth+1 , ROBOT)
                min = Math.min(currscore,min)
                if(min == -1){
                    board[cells.row][cells.col] = ""
                    break
                }
            }
            board[cells.row][cells.col] = ""
        }
        return if(player == ROBOT) max else min
    }
    fun placeMove(move : Move , player : String){
      //  audio.start()
        board[move.row][move.col] = player
    }
}