# tic-tac-toe
A tic-tac-toe game built with springboot
It has four endpoints which includes: 1. START GAME which creates the game with
a board of 3 by 3 two-dimensional array. it takes in Player as an argument
2. CONNECT GAME: This connects two player and also having the gameId.
3. CONNECT RANDOM: this connects to random and set the status of the game to IN_PROGRESS
4. GAMEPLAY: This method is called when the gameis in progress, it uses websocket to send
messages to the players.

At the end of the game a player is gotten and it returns the winner either Player X
or Player O.
