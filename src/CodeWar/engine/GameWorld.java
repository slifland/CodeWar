package CodeWar.engine;


import java.util.*;
import java.io.*;

public class GameWorld
{
    protected Team teamA;
    protected Team teamB;
    protected int sizeX;
    protected int sizeY;
    protected MapTile[][] gameWorld;
    protected Scanner input = new Scanner(System.in);


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

    protected GameWorld(String targetFile) throws IOException {
        try {
            String[] lines = readFile(targetFile);
            String[] tiles = lines[0].split("m");
            this.sizeX = Integer.parseInt(tiles[0]);
            this.sizeY = Integer.parseInt(tiles[1]);
            teamA = new Team(1);
            teamB = new Team(2);
            int index = 2;
            gameWorld = new MapTile[sizeY][sizeX];for(int i = 0; i < gameWorld.length; i++)
            {
                for(int j = 0; j < gameWorld[i].length;j++)
                {
                    Point point = Point.indexToTile(i,j,this);
                    if(lines[index].equals("0")){
                        gameWorld[i][j] = new MapTile(0,0,false,null,
                                point);
                    }
                    else if(lines[index].equals("1")){
                        if(lines[index+1].contains("a")){
                            gameWorld[i][j] = new MapTile(0,0,false, new RobotInfo(GameConstants.HQ, 1, point, true, this),
                                    point);
                        }
                        else{
                            gameWorld[i][j] = new MapTile(0,0,false, new RobotInfo(GameConstants.HQ, 2, point, true, this),
                                    point);
                        }
                    }
                    else if(lines[index].contains("i")){
                        gameWorld[i][j] = new MapTile(Integer.parseInt(lines[index].split("i")[0]),0,false, null,
                                point);
                    }
                    else if(lines[index].contains("s")){
                        gameWorld[i][j] = new MapTile(0,Integer.parseInt(lines[index].split("s")[0]),false, null,
                                point);
                    }
                    index++;
                }
            }
        }
        catch(FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void encodeMapToFile(String mapName) throws IOException {
        String toSend = "";
        toSend += sizeX;
        toSend += "m";
        toSend += sizeY;
        toSend += "m";
        gameWorld = new MapTile[sizeY][sizeX];for(int i = 0; i < gameWorld.length; i++)
        {
            for(int j = 0; j < gameWorld[i].length;j++)
            {
                if(!gameWorld[i][j].passable) toSend += "0";
                else if(gameWorld[i][j].robotInfoOnTile != null && gameWorld[i][j].robotInfoOnTile.robotType == GameConstants.HQ){
                    toSend += "1";
                    if(gameWorld[i][j].robotInfoOnTile.playerOwner == 1){
                        toSend += "a";
                    }
                    else{
                        toSend += "b";
                    }
                }
                else if(gameWorld[i][j].numIron != 0 || gameWorld[i][j].numSilicon != 0){
                    if(gameWorld[i][j].numIron != 0) toSend += "i" + gameWorld[i][j].numIron;
                    else toSend += "s" + gameWorld[i][j].numSilicon;
                }
                else toSend += "e";
                toSend += "m";
            }
        }
        toSend += "k";
        String[] str = {toSend};
        writeToFile(str, mapName);
    }

    protected MapTile[][] getGameWorld()
    {
        return gameWorld;
    }

    public static String[] readFile(String fileName)throws IOException
    {
        int size = getFileSize(fileName);		//holds the # of elements in the file
        String[] list = new String[size];		//a heap will not use index 0;
        Scanner input = new Scanner(new FileReader(fileName));
        int i=0;											//index for placement in the array
        String line;
        while (input.hasNextLine())				//while there is another line in the file
        {
            line=input.nextLine();					//read in the next Line in the file and store it in line
            list[i]= line;								//add the line into the array
            i++;											//advance the index of the array
        }
        input.close();
        return list;
    }
    public static void writeToFile(String[] array, String filename) throws IOException
    {
        System.setOut(new PrintStream(new FileOutputStream(filename)));
        for(int i = 0; i < array.length; i++)
            System.out.println(array[i]);
        System.out.flush();
        System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));
    }
    public static int getFileSize(String fileName)throws IOException
    {
        Scanner input = new Scanner(new FileReader(fileName));
        int size=0;
        while (input.hasNextLine())				//while there is another line in the file
        {
            size++;										//add to the size
            input.nextLine();							//go to the next line in the file
        }
        input.close();									//always close the files when you are done
        return size;
    }

}
