# Kalah Game
by Alice Lafox (alice@lafox.net)

### Demo game is running here:
    http://kalah.lafox.net

## To Play locally:
    
start game by running in console:
        
    mvn spring-boot:run
        
then open one of those urls:

    http://localhost:8080  - to play default 6 seeds game
    http://localhost:8080?seeds=4  - to play game with 4 seeds
    http://localhost:8080?seeds=5  - to play game with 5 seeds

                      
or run on another port, if 8080 is used:       
            
    mvn spring-boot:run -Dserver.port=8090
             
## Game API

    GET /api/v1/game/newGame  - creates new Game with default parameters (in our case SEEDS_COUNT=6)
      
    GET /api/v1/game/newGame?seeds=4  - creates new Game with given seeds count. Allowed values 4,5,6. 
                                        Any other seeds value here creates default 6 seeds game
        
    POST /api/v1/game/nextTurn  - accepts Game with selected house and returns new Game state
       
## What could be improved (TODO)

* game winner returning state: now it returns String with Player.name() or "A DRAW" in case of a draw 
* demo html can ask for seeds count before creating game 