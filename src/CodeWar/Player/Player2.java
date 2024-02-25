package CodeWar.Player;
import CodeWar.engine.*;

import java.util.ArrayList;
import java.util.Random;

public class Player2 extends Player
{
    public Player2(int id){
        super(id);
    }
    public void run(RobotUser user){
        return;
        /*int type = user.getRobotType();
        switch(type){
            case GameConstants.HQ:
                ArrayList<MapTile> adjacents = user.getNearbyMapTiles(2);
                for(MapTile mapTile : adjacents){
                    if(user.canSpawn(GameConstants.MINER, mapTile.getPoint()) && user.getIron() > 70){
                        user.spawn(GameConstants.MINER, mapTile.getPoint());
                    }
                }
                break;
            case GameConstants.SCOUT: {
                Random rand = new Random();
                Direction[] d = Direction.values();
                Direction dir = d[rand.nextInt(d.length - 1)];
                if (dir != Direction.NONE) {
                    if (user.canMove(dir)) user.move(dir);
                }
                ArrayList<RobotInfo> nearbyEnemies = user.getNearbyRobots(GameConstants.VISION_RADIUS[user.getRobotType()], getID());
                for (RobotInfo enemy : nearbyEnemies) {
                    if (user.canAttack(enemy.getPosition())) {
                        user.attack(enemy.getPosition());
                    }
                }
                break;
            }
            case GameConstants.MINER:
                ArrayList<MapTile> nearbyIron = user.getNearbyIronDeposits(GameConstants.VISION_RADIUS[(user.getRobotType())]);
                if(nearbyIron.size() > 0){
                    if(user.canMove(user.getPosition().directionTo(nearbyIron.get(0).getPoint()))){
                        user.move(user.getPosition().directionTo(nearbyIron.get(0).getPoint()));
                        if(user.canMine(nearbyIron.get(0).getPoint())){
                            System.out.println("hi");
                            user.mine(nearbyIron.get(0).getPoint());
                        }
                        break;
                    }
                }
                Random rand = new Random();
                Direction[] d = Direction.values();
                Direction dir = d[rand.nextInt(d.length - 1)];
                if (dir != Direction.NONE) {
                    if (user.canMove(dir)) user.move(dir);
                    if(user.canBuildCitadel(user.getPosition().pointInDirection(dir))){
                        user.buildCitadel(user.getPosition().pointInDirection(dir));
                    }
                }
//                ArrayList<RobotInfo> nearbyEnemies = user.getNearbyRobots(GameConstants.VISION_RADIUS[user.getRobotType()], 1);
//                for (RobotInfo enemy : nearbyEnemies) {
//                    if (user.canAttack(enemy.getPosition())) {
//                        user.attack(enemy.getPosition());
//                    }
//                }
                break;
        }
        /*

         */
    }
    public int getID(){
        return 2;
    }
    public int otherTeam(){
        return 1;
    }
}
