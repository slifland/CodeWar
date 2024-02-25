package CodeWar.engine;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

public class MapGenerator
{
    static Random rng = new Random(89);
    public static void generateGameWorld(Runner runner)
    {
        int mapSymmetry;
        float randFloat = rng.nextFloat();
        if(randFloat > 0.666f)
        {
            mapSymmetry = 0;
        }
        else if(randFloat > 0.333f)
        {
            mapSymmetry = 1;
        }
        else
        {
            mapSymmetry = 2;
        }

        //0 - rotational, 1 - horizontal, 2 - vertical

        List<Point> alreadyBeen = new ArrayList<>();
        while(alreadyBeen.size() < GameConstants.NUM_OBSTACLES)
        {
            Point randomPoint = generateRandomPoint();
            if(!alreadyBeen.contains(randomPoint))
            {
                Point symmetryPoint;
                switch(mapSymmetry)
                {
                    case 0:
                        symmetryPoint = new Point(randomPoint.y, randomPoint.x);
                        alreadyBeen.add(symmetryPoint);
                        break;
                    case 1:
                        symmetryPoint = new Point(randomPoint.x, GameConstants.MAP_SIZE - randomPoint.y - 1);
                        alreadyBeen.add(symmetryPoint);
                        break;
                    case 2:
                        symmetryPoint = new Point(GameConstants.MAP_SIZE - randomPoint.x - 1, randomPoint.y);
                        alreadyBeen.add(symmetryPoint);
                        break;
                }
                alreadyBeen.add(randomPoint);
            }
        }


        runner.world = new GameWorld(GameConstants.MAP_SIZE, GameConstants.MAP_SIZE);
        runner.world.gameWorld = new MapTile[GameConstants.MAP_SIZE][GameConstants.MAP_SIZE];
        for(int i = 0; i < GameConstants.MAP_SIZE; i++)
        {
            for (int j = 0; j < GameConstants.MAP_SIZE; j++)
            {
                if(alreadyBeen.contains(new Point(i,j)))
                {
                    runner.world.gameWorld[i][j] = new MapTile(j, GameConstants.MAP_SIZE - i - 1, false);
                }
                else
                {
                    runner.world.gameWorld[i][j] = new MapTile(j, GameConstants.MAP_SIZE - i - 1, true);
                }
            }
        }

        int numWells = rng.nextInt(GameConstants.MAX_WELLS) + 1;

        List<Point> alreadyBeenWells = new ArrayList<>();

        while (alreadyBeenWells.size() < numWells * 2)
        {
            Point randomPoint = generateRandomPoint();
            if(!alreadyBeenWells.contains(randomPoint))
            {
                Point symmetryPoint;
                switch (mapSymmetry)
                {
                    case 0:
                        symmetryPoint = new Point(randomPoint.y, randomPoint.x);
                        runner.world.gameWorld[symmetryPoint.x][symmetryPoint.y].numIron = GameConstants.WELL_IRON;
                        runner.world.gameWorld[symmetryPoint.x][symmetryPoint.y].passable = true;
                        alreadyBeenWells.add(symmetryPoint);
                        break;
                    case 1:
                        symmetryPoint = new Point(randomPoint.x, GameConstants.MAP_SIZE - randomPoint.y - 1);
                        runner.world.gameWorld[symmetryPoint.x][symmetryPoint.y].numIron = GameConstants.WELL_IRON;
                        runner.world.gameWorld[symmetryPoint.x][symmetryPoint.y].passable = true;
                        alreadyBeenWells.add(symmetryPoint);
                        break;
                    case 2:
                        symmetryPoint = new Point(GameConstants.MAP_SIZE - randomPoint.x - 1, randomPoint.y);
                        runner.world.gameWorld[symmetryPoint.x][symmetryPoint.y].numIron = GameConstants.WELL_IRON;
                        runner.world.gameWorld[symmetryPoint.x][symmetryPoint.y].passable = true;
                        alreadyBeenWells.add(symmetryPoint);
                        break;
                }
                runner.world.gameWorld[randomPoint.x][randomPoint.y].numIron = GameConstants.WELL_IRON;
                alreadyBeenWells.add(randomPoint);
            }
        }


        alreadyBeenWells = new ArrayList<>();

        while (alreadyBeenWells.size() < numWells * 2)
        {
            Point randomPoint = generateRandomPoint();
            if(!alreadyBeenWells.contains(randomPoint) && runner.world.gameWorld[randomPoint.x][randomPoint.y].numIron == 0)
            {
                Point symmetryPoint;
                switch (mapSymmetry)
                {
                    case 0:
                        symmetryPoint = new Point(randomPoint.y, randomPoint.x);
                        runner.world.gameWorld[symmetryPoint.x][symmetryPoint.y].numSilicon = GameConstants.WELL_SILICON;
                        runner.world.gameWorld[symmetryPoint.x][symmetryPoint.y].passable = true;
                        alreadyBeenWells.add(symmetryPoint);
                        break;
                    case 1:
                        symmetryPoint = new Point(randomPoint.x, GameConstants.MAP_SIZE - randomPoint.y - 1);
                        runner.world.gameWorld[symmetryPoint.x][symmetryPoint.y].numSilicon = GameConstants.WELL_SILICON;
                        runner.world.gameWorld[symmetryPoint.x][symmetryPoint.y].passable = true;
                        alreadyBeenWells.add(symmetryPoint);
                        break;
                    case 2:
                        symmetryPoint = new Point(GameConstants.MAP_SIZE - randomPoint.x - 1, randomPoint.y);
                        runner.world.gameWorld[symmetryPoint.x][symmetryPoint.y].numSilicon = GameConstants.WELL_SILICON;
                        runner.world.gameWorld[symmetryPoint.x][symmetryPoint.y].passable = true;
                        alreadyBeenWells.add(symmetryPoint);
                        break;
                }
                runner.world.gameWorld[randomPoint.x][randomPoint.y].numSilicon = GameConstants.WELL_SILICON;
                alreadyBeenWells.add(randomPoint);
            }
        }

        int numHeadquarters = rng.nextInt(GameConstants.MAX_HQS) + 1;

        List<Point> alreadyBeenHQs = new ArrayList<>();

        while (alreadyBeenHQs.size() < numHeadquarters * 2)
        {
            Point randomPoint = generateRandomPoint();
            if(!alreadyBeen.contains(randomPoint) && runner.world.gameWorld[randomPoint.x][randomPoint.y].numSilicon == 0
                    && runner.world.gameWorld[randomPoint.x][randomPoint.y].numIron == 0)
            {
                Point symmetryPoint;
                switch (mapSymmetry)
                {
                    case 0:
                        symmetryPoint = new Point(randomPoint.y, randomPoint.x);
                        runner.world.gameWorld[symmetryPoint.x][symmetryPoint.y].robotInfoOnTile = new RobotInfo(GameConstants.HQ, 2,
                                new Point(symmetryPoint.x, GameConstants.MAP_SIZE - symmetryPoint.y - 1), false, runner.world);
                        runner.world.gameWorld[symmetryPoint.x][symmetryPoint.y].passable = true;
                        alreadyBeenHQs.add(symmetryPoint);
                        break;
                    case 1:
                        symmetryPoint = new Point(randomPoint.x, GameConstants.MAP_SIZE - randomPoint.y - 1);
                        runner.world.gameWorld[symmetryPoint.x][symmetryPoint.y].robotInfoOnTile = new RobotInfo(GameConstants.HQ, 2,
                                new Point(symmetryPoint.x, GameConstants.MAP_SIZE - symmetryPoint.y - 1), false, runner.world);
                        runner.world.gameWorld[symmetryPoint.x][symmetryPoint.y].passable = true;
                        alreadyBeenHQs.add(symmetryPoint);
                        break;
                    case 2:
                        symmetryPoint = new Point(GameConstants.MAP_SIZE - randomPoint.x - 1, randomPoint.y);
                        runner.world.gameWorld[symmetryPoint.x][symmetryPoint.y].robotInfoOnTile = new RobotInfo(GameConstants.HQ, 2,
                                new Point(symmetryPoint.x, GameConstants.MAP_SIZE - symmetryPoint.y - 1), false, runner.world);
                        runner.world.gameWorld[symmetryPoint.x][symmetryPoint.y].passable = true;
                        alreadyBeenHQs.add(symmetryPoint);
                        break;
                }
                runner.world.gameWorld[randomPoint.x][randomPoint.y].robotInfoOnTile = new RobotInfo(GameConstants.HQ, 1,
                        new Point(randomPoint.x, GameConstants.MAP_SIZE - 1 - randomPoint.y), false, runner.world);
                alreadyBeenHQs.add(randomPoint);
            }
        }



        /*runner.world.gameWorld[0][0].robotInfoOnTile = new RobotInfo(GameConstants.HQ,1,
                new Point(0, GameConstants.MAP_SIZE - 1), false, runner.world);
        runner.world.gameWorld[29][29].robotInfoOnTile = new RobotInfo(GameConstants.HQ,2,
                new Point(29, 0), false, runner.world);
        runner.world.gameWorld[25][25].numIron = 10;*/
    }

    private static Point generateRandomPoint()
    {
        return new Point(rng.nextInt(GameConstants.MAP_SIZE), rng.nextInt(GameConstants.MAP_SIZE));
    }
}
