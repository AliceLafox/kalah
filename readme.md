# Kalah Game
by Alice Lafox (alice@lafox.net)

## To Play
start game by running in console:
        
        mvn spring-boot:run
then open 

        http://localhost:8080
           
Or run on another port, if 8080 is used:       
            
        mvn spring-boot:run -Dserver.port=8090
        
## Game API

       GET /api/v1/game/newGame  - creates new Game with default parameters (in our case SEEDS_COUNT=6)
        
       POST /api/v1/game/nextTurn  - accepts Game with selected house and returns new Game state
       
## What could be improved (TODO)

* Possibility to start game with 4 or 5 seeds in houses from request. Now game has fixed SEEDS_COUNT=6;
* Improve somehow game winner returning state: now it returns String with Player.name() or word "DRAW" in case of draw 
* It will be good to use GameDto for requests and then convert it to Game for internal usage
