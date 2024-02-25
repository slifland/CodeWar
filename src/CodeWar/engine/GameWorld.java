package CodeWar.engine;


public class GameWorld
{
    protected Team teamA;
    protected Team teamB;
    protected int sizeX;
    protected int sizeY;
    protected MapTile[][] gameWorld;


    protected GameWorld(int sizeX, int sizeY)
    {
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        teamA = new Team(1);
        teamB = new Team(2);
        gameWorld = new MapTile[sizeY][sizeX];
        for(int i = 0; i < gameWorld.length; i++)
        {
            for(int j = 0; j < gameWorld[i].length;j++)
            {
                gameWorld[i][j] = new MapTile(0,0,true,null,
                        Point.indexToTile(i,j,this));
            }
        }

    }

    protected GameWorld(GameWorld other)
    {
        this.teamA = new Team(other.teamA);
        this.teamB = new Team(other.teamB);
        MapTile[][] gameWorld =  new MapTile[other.sizeY][other.sizeX];
        MapTile[][] otherGameWorld = other.getGameWorld();
        for(int i = 0; i < gameWorld.length; i++)
        {
            for(int j = 0; j < gameWorld[i].length;j++)
            {
                gameWorld[i][j] = new MapTile(otherGameWorld[i][j]);
            }
        }
        this.sizeX = other.sizeX;
        this.sizeY = other.sizeY;
        this.gameWorld = gameWorld;
    }

    protected MapTile[][] getGameWorld()
    {
        return gameWorld;
    }
}
