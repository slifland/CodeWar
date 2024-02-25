package CodeWar.Player;
import CodeWar.engine.Direction;
import CodeWar.engine.GameConstants;
import CodeWar.engine.MapTile;
import CodeWar.engine.RobotUser;

import java.util.ArrayList;
import java.util.Random;

public class Player2 extends Player
{
    public Player2(int id){
        super(id);
    }
    public void run(RobotUser user){
        int type = user.getRobotType();
        switch(type){
            case GameConstants.HQ:
                ArrayList<MapTile> adjacents = user.getNearbyMapTiles(2);
                for(MapTile mapTile : adjacents){
                    if(user.canSpawn(GameConstants.SUPERBOT, mapTile.getPoint())){
                        user.spawn(GameConstants.SUPERBOT, mapTile.getPoint());
                    }
                }
                break;
            case GameConstants.SCOUT:
                Random rand = new Random();
                Direction[] d = Direction.values();
                Direction dir = d[rand.nextInt(d.length - 1)];
                if(dir != Direction.NONE){
                    if(user.canMove(dir)) user.move(dir);
                }
                break;
        }
    }
    public int getID(){
        return 2;
    }
}
