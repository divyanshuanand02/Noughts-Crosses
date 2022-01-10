# tic-tac-toe-minimax
An implementation of Minimax AI Algorithm on Tic-Tac-Toe (or Noughts and Crosses) game.
<p align="center">
	![TicTac](https://user-images.githubusercontent.com/54618261/148750972-b9866992-3eca-423a-aa34-6d6f7da0b0b9.png)
</p>

## Introduction
Tic-Tac-Toe is a rather simply game with only 255,168 possible games that can be played. This number is trivial for today’s computers, that’s why Tic-Tac-Toe is considered to be a solved game which means the outcome can be predicted given any state. The game is so simple that we can generate the entire game tree without trouble, whereas in chess there are already 69,352,859,712,417 possible games that could’ve been played after just 10 moves.

## What is Minimax
Minimax is a type of adversarial search algorithm for generating and exploring game trees. It is mostly used to solve zero-sum games where one side’s gain is equivalent to other side’s loss, so adding all gains and subtracting all losses end up being zero.
Adversarial search differs from conventional searching algorithms by adding opponents into the mix. Minimax algorithm keeps playing the turns of both player and the opponent optimally to figure out the best possible move.

## How does it work?
Implementing minimax for Tic-Tac-Toe is simple. We will recursively generate the game tree by exploring all possible moves for each board state and upon reaching a terminal state, we will assign a value of 1 for winning, -1 for losing and 0 for draw. Then based on these terminal states, for each explored turn either maximizer or minimizer will pick the most appropriate move. Doing so will propagate these outcomes all the way up to the root of the tree.
