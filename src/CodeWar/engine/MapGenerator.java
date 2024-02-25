package CodeWar.engine;

public class MapGenerator
{
    public static void generateGameWorld(Runner runner)
    {
        runner.world = new GameWorld(GameConstants.MAP_SIZE, GameConstants.MAP_SIZE);
        runner.world.gameWorld = new MapTile[GameConstants.MAP_SIZE][GameConstants.MAP_SIZE];
        for(int i = 0; i < GameConstants.MAP_SIZE; i++)
        {
            for (int j = 0; j < GameConstants.MAP_SIZE; j++)
            {
                runner.world.gameWorld[i][j] = new MapTile(j, GameConstants.MAP_SIZE - i - 1);
            }
        }

        runner.world.gameWorld[0][0].robotInfoOnTile = new RobotInfo(GameConstants.HQ,1,
                new Point(0, GameConstants.MAP_SIZE - 1), false, runner.world);
        runner.world.gameWorld[29][29].robotInfoOnTile = new RobotInfo(GameConstants.HQ,2,
                new Point(29, 0), false, runner.world);
    }
}
