package com.divyanshu.tictactoe

object Minimax {
    var player = "O"
    var opponent = "X"

    public fun findbestmove(board: Array<Array<String?>>) : Move{
            for(i in 0..2){
                for(j in 0..2){
                    System.out.print(board[i][j]+" ")
                }
                System.out.println()
            }
        var bestmove = Move(-1 , -1)
        var bestval : Int = -1000
        for(i in 0..2){
            for(j in 0..2){
                if(board[i][j].isNullOrEmpty()){
                    board[i][j] = player
                    var moveval : Int = minimax(board , 0 , false)
                    board[i][j] = ""
                    System.out.println(bestval)
                    System.out.println(moveval)
                    if(moveval > bestval){
                        bestmove.row = i
                        bestmove.col = j
                        bestval = moveval
                    }

                }
            }
        }

        return bestmove
    }

     private fun minimax(board: Array<Array<String?>>, depth: Int, isMax: Boolean): Int {
        var score: Int = evaluate(board)
        if (score == 10)
            return score
        if (score == -10)
            return score
        if (isMovesLeft(board) == false)
            return 0
        if (isMax) {
            var best: Int = -1000;
            for (i in 0..2) {
                for (j in 0..2) {
                    if (board[i][j].isNullOrEmpty()) {
                        board[i][j] = player
                        best = Math.max(
                            best, minimax(
                                board,
                                depth + 1, !isMax
                            )
                        )
                        board[i][j] = ""
                    }
                }
            }
            return best
        } else {
            var best = 1000
            for(i in 0..2){
                for(j in 0..2){
                    if(board[i][j].isNullOrEmpty()){
                        board[i][j]=opponent
                        best = Math.min(best, minimax(board,
                            depth + 1, !isMax))
                        board[i][j] = ""
                    }
                }
            }
            return best
        }
    }
    private fun isMovesLeft(board: Array<Array<String?>>): Boolean {
        for(i in 0..2){
            for(j in 0..2){
                if(board[i][j].isNullOrEmpty())
                    return true
            }
        }
        return false
    }

    private fun evaluate(b: Array<Array<String?>>): Int {

        for (row in 0..2) {
            if (b[row][0] === b[row][1] &&
                b[row][1] === b[row][2]
            ) {
                if (b[row][0] === player) return 10
                else if (b[row][0] === opponent)
                    return -10
            }
        }

        // Checking for Columns for X or O victory.

        // Checking for Columns for X or O victory.
        for (col in 0..2) {
            if (b[0][col] === b[1][col] &&
                b[1][col] === b[2][col]
            ) {
                if (b[0][col] === player)
                    return 10
                else if (b[0][col] === opponent)
                    return -10
            }
        }

        // Checking for Diagonals for X or O victory.

        // Checking for Diagonals for X or O victory.
        if (b[0][0] === b[1][1] && b[1][1] === b[2][2]) {
            if (b[0][0] === player)
                return 10
            else if (b[0][0] === opponent)
                return -10
        }

        if (b[0][2] === b[1][1] && b[1][1] === b[2][0]) {
            if (b[0][2] === player)
                return 10
            else if (b[0][2] === opponent) return -10
        }

        return 0
    }

}

