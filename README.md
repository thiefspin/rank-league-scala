# Score Board Calculator

![Build Status](https://github.com/thiefspin/rank-league-scala/workflows/Scala%20CI/badge.svg?branch=master)
## Overview
This command line application takes in a file with game results in the following format:

```
Lions 3, Snakes 3
Tarantulas 1, FC Awesome 0
Lions 1, FC Awesome 1
Tarantulas 3, Snakes 1
Lions 4, Grouches 0
```

and then produces the end scoreboard in the following format:

```
1. Tarantulas, 6 pts
2. Lions, 5 pts
3. FC Awesome, 1 pt
3. Snakes, 1 pt
5. Grouches, 0 pts
```

## Rules
* Winning team gets 3 points
* Losing team gets 0 points
* If the game is drawn both teams are awarded 1 point each

## Running the application
The application has defaults set in place for the input file and game rules.
If you are interested in changing these set the following ENV vars:
* `GAME_FILE_LOCATION` sets the path to the input file. Example: `~/my_files/game_input.txt`. Setting this an invalid path will break the application.
* `GAME_WINNING_POINTS` sets the points allocated to a winner. Example: 3
* `GAME_DRAWING_POINTS` sets the points allocated to for a draw. . Example: 1

### Commands
This expects that `sbt` and `java` is installed on your machine.
* `sbt clean compile run` Runs the application
* `sbt assembly` Will package the application jar file. (Can then be run with `java -jar <file.jar>`)
* `sbt clean compile coverage test` Runs the unit tests
* `sbt coverageReport` Will give you a unit test coverage report. (After running the unit tests with the coverage option)

## Game files
Have a look at `games_file_generator.py` if you want to generate random games files.
