package CodeWar.Player;

import CodeWar.engine.*;
import CodeWar.engine.RobotUser;

import java.util.ArrayList;


public class Player1 extends Player
{
    public Player1(int id){
        super(id);
    }
    public void run(RobotUser user){
        int type = user.getRobotType();
        switch(type){
            case GameConstants.HQ:
                ArrayList<MapTile> adjacents = user.getNearbyMapTiles(2);
                for(MapTile mapTile : adjacents){
                    if(user.canSpawn(GameConstants.SCOUT, mapTile.getPoint())){
                        user.spawn(GameConstants.SCOUT, mapTile.getPoint());
                    }
                }
                break;
        }
    }
    public int getID(){
        return 1;
    }
}
