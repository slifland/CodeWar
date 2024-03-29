package CodeWar.engine;

import CodeWar.Player.*;
import CodeWar.engine.*;

import java.util.ArrayList;
import java.util.List;

public class Runner
{
    //Client client;
    //constants

    //current turn number
    protected int turn;

    GameWorld world;
    protected List<GameWorld> pastTurns;

    protected int champion = 0;



    //list of all of the robots
    ArrayList<RobotPlayer> robotPlayers;
    //robots created during the turn
    ArrayList<RobotPlayer> toAdd;
    //robots who died during the turn
    ArrayList<RobotPlayer> toRemove;

    //instantiates a runner object
    protected Runner(){
        MapGenerator.generateGameWorld(this);
        pastTurns = new ArrayList<>();
        robotPlayers = new ArrayList<>();
        for(int i = 0; i < world.sizeX; i++){
            for(int k = 0; k < world.sizeY; k++){
                if(world.gameWorld[i][k].robotInfoOnTile != null && world.gameWorld[i][k].robotInfoOnTile.robotType == GameConstants.HQ){
                    switch(world.gameWorld[i][k].robotInfoOnTile.playerOwner){
                        case 1:
                            robotPlayers.add(new RobotPlayer(world.gameWorld[i][k].robotInfoOnTile, new Player1(1)));
                            break;
                        case 2:
                            robotPlayers.add(new RobotPlayer(world.gameWorld[i][k].robotInfoOnTile, new Player2(2)));
                            break;
                    }
                }
            }
        }
        toAdd = new ArrayList<>();
        toRemove = new ArrayList<>();
        //client = new Client(this);
    }

    //increments one turn
    protected void update(){
        //System.out.println(robotPlayers.size());
        for(RobotPlayer robotPlayer : robotPlayers){
            if(robotPlayer.alive()){
                RobotUser tempRobotUser = new RobotUser(robotPlayer.getRobot(), world);
                try {
                    robotPlayer.run(tempRobotUser);
                    robotPlayer.getRobot().cooldownAction -= 10;
                    robotPlayer.getRobot().cooldownAction = Math.max(robotPlayer.getRobot().cooldownAction, 0);
                    robotPlayer.getRobot().cooldownMove -= 10;
                    robotPlayer.getRobot().cooldownMove = Math.max(robotPlayer.getRobot().cooldownMove, 0);
                }
                catch(Exception e){
                    e.printStackTrace();
                    toRemove.add(robotPlayer);
                }
                if(tempRobotUser.spawned != null){
                    switch(tempRobotUser.spawned.playerOwner){
                        case 1:
                            toAdd.add(new RobotPlayer(tempRobotUser.spawned, new Player1(1)));
                            break;
                        case 2:
                            toAdd.add(new RobotPlayer(tempRobotUser.spawned, new Player2(2)));
                            break;
                        default:
                            System.out.println("Something is wrong");
                            break;
                    }
                    tempRobotUser.spawned = null;
                }
            }
            else{
                toRemove.add(robotPlayer);
            }
        }
        //these should already be removed from the map
        for(RobotPlayer robotPlayer : toRemove){
            robotPlayers.remove(robotPlayer);
            if(robotPlayer.getRobot().robotType == GameConstants.HQ){
                int toElim = robotPlayer.getRobot().playerOwner;
                for(RobotPlayer rP : robotPlayers){
                    if(rP.getRobot().robotType == GameConstants.HQ && rP.getRobot().playerOwner == toElim){
                        champion = -1;
                        break;
                    }
                }
                if(champion == -1) champion = 0;
                else champion = (toElim == 1) ? 2 : 1;
            }
        }
        //these should already be added to the map
        robotPlayers.addAll(toAdd);
        toAdd.clear();
        toRemove.clear();
        pastTurns.add(new GameWorld(world));
        turn++;
    }

    //returns whether the current game is active
    protected boolean active(){
        return champion == 0 && turn < GameConstants.MAX_TURN_COUNT;
    }

    public int getSize(){
        return world.sizeX;
    }
}

//pairs a robot and a player, so that each robot can have its own instance of player 1 or player 2
class RobotPlayer{
    private RobotInfo robot;
    private Player player;
    public RobotPlayer(RobotInfo r, Player p){
        robot = r;
        player = p;
    }
    protected boolean alive(){
        return robot.health > 0;
    }
    protected RobotInfo getRobot(){
        return robot;
    }
    protected int getPlayerID(){
        return player.getID();
    }
    protected void run(RobotUser r){
        player.run(r);
    }

}
