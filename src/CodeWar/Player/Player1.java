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
        //Now, let's consider what to do with our scouts: we may want to explore randomly, but if we see resources or an enemy, we may as well mine/attack
        else if(type == GameConstants.SCOUT){
            ArrayList<MapTile> nearbyDeposits = user.getNearbyDeposits(GameConstants.VISION_RADIUS[GameConstants.SCOUT]);
            if(!nearbyEnemies.isEmpty()){
                //Since we know we can see at least one enemy, let's attack them
                if (user.canAttack(nearbyEnemies.get(0).getPosition())) {
                    user.attack(nearbyEnemies.get(0).getPosition());
                }
                //In addition, if we can see the enemy HQ, let's stay near it so we can keep attacking
                if(nearbyEnemies.get(0).getRobotType() == GameConstants.HQ) {
                    Direction dir = user.getPosition().directionTo(nearbyEnemies.get(0).getPosition());
                    return;
                    //Again, make sure you check you can take an action before taking it
//                    if (user.canMove(dir))
//                        user.move(dir);
                }
            }
            //Can't see any enemies, lets try to move towards any minerals we can see
            else if(!nearbyDeposits.isEmpty()){
                Direction dir = user.getPosition().directionTo(nearbyDeposits.get(0).getPoint());
                //Again, make sure you check you can take an action before taking it
                if (user.canMove(dir))
                    user.move(dir);
                //We can try and mine this deposit as well
                if(user.canMine(nearbyDeposits.get(0).getPoint())){
                    user.mine(nearbyDeposits.get(0).getPoint());
                }
            }
            //If we still haven't moved, we should just move randomly to explore the map
            if(user.getMoveCooldown() < 10){
                Random rand = new Random();
                Direction[] d = Direction.values();
                Direction dir = d[rand.nextInt(d.length - 1)];
                if (dir != Direction.NONE) {
                    if (user.canMove(dir)) user.move(dir);
                }
            }
        }
        //That's all! Clearly a very simple bot, with a basic foundation but lot's to build on. Consider exploring other kinds of units, and more advanced
        //strategies for fighting enemies or mining resources. Once you can beat this basic reference player, move on to our next level of reference opponent!
    }
    public int getID(){
        return 1;
    }
    public int otherTeam(){
        return 2;
    }
}
