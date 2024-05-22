**Snakes & Ladders**

**Problem Statement**

You have to design & implement a Snakes and Ladders game that supports the following
functionality:

**Requirements:
Mandatory Requirements:**

You have to take a configuration (can be a yml/json file) with the following parameters.
 Number of players: N
 Board Size: BS (BS x BS)
 Number of Snakes: S
 Number of Ladders: L
 Number of Dies: D
 Movement Strategy: MS

Note: Movement strategy is either SUM (sum of numbers on dies), MAX (max of numbers on
dies), MIN (min of number on dies).
You will be given a sample input to populate the board. Post which the game has to be
simulated among N players.

**Rules:**
Snake always takes you to the cell where its tail is, and has to be a number less than
where you are at currently.
Ladder takes you up (strictly).
If a player (A) comes to a cell where another player (B) is placed already, the previously
placed player (B) has to start again from 1.
Optional Extensions:
 Using the configuration you have to generate a random valid board & devise proper
rules for placing objects on the board.
Write unit tests to validate all implemented functionality and their edge cases.
Addition of special objects:
Crocodile, which takes you exactly 5 steps back.
Mine which holds you for 2 turns.

**Expectations:**

Implement a driver program which drives all above rules, and gives turns to players in a
round robin fashion to roll dies.
Proper logging of all events, e.g rolling dies, movement to new cell, encountering
snakes/ladders etc.
Code has to handle all edge cases
Code should be modular enough to add further extensions or rules, without much
changes.
A manual override must exist for the interviewer to verify the edge cases/ to write unit
tests. The program should take the following as input:
Starting location of each player.
The D die values that each player rolled in a turn.

