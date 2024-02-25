package CodeWar.Player;

import CodeWar.engine.*;
import CodeWar.engine.RobotUser;

import java.util.ArrayList;
import java.util.Random;


public class Player1 extends Player
{
    public Player1(int id){
        super(id);
    }
    //Reference Player Run Method
    public void run(RobotUser user){

        //First, we begin by getting the robots type. By following a different course of action for each robot according to its strengths and weaknesses, we can maximize the utility of our horde of robots.
        //User is the object through which our robots can interact with the game, and it contains a robot which contains a type which we uncover below
        int type = user.getRobotType();

        //We may also want to gather some information which will always be important, such as the amount of enemies which we can see, and sensing all
        //other team robots in our vision radius will do so
        ArrayList<RobotInfo> nearbyEnemies = user.getNearbyRobots(GameConstants.VISION_RADIUS[user.getRobotType()], otherTeam());

        //From here, we can decide what to do based on what type of robot our method earlier returned.
        //Headquarters should try to produce ally units to explore and fight the enemy, while our scout units will skirmish and search for resources
        if(type == GameConstants.HQ){
            //We can use a method which returns nearby Map Tiles within a certain radius to obtain all of the potential spawning locations for our new unit
            ArrayList<MapTile> adjacents = user.getNearbyMapTiles(2);
            for(int i = 0; i < adjacents.size(); i++){
                //always call "canX" before attempting to call an action or movement, or risk having movement and action
                //cooldowns increased by 40 for failing to properly check your actions
                if(user.canSpawn(GameConstants.SCOUT, adjacents.get(i).getPoint())){
                    user.spawn(GameConstants.SCOUT, adjacents.get(i).getPoint());
                }
            }
        }
        else if(type == GameConstants.SCOUT){

        }
    }
    public int getID(){
        return 1;
    }
    public int otherTeam(){
        return 2;
    }
}
