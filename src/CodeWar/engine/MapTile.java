package CodeWar.engine;


public class MapTile
{
    int numIron;
    int numSilicon;
    boolean passable;
    //This value is null if no Robot is present on the tile
    RobotInfo robotInfoOnTile;
    //location of the tile
    Point point;

    public MapTile(int numIron, int numSilicon, boolean passable, RobotInfo robotInfoOnTile, Point mapLocation)
    {
        this.numIron = numIron;
        this.numSilicon = numSilicon;
        this.passable = passable;
        this.robotInfoOnTile = robotInfoOnTile;
        this.point = mapLocation;
    }

    public MapTile(MapTile other)
    {
        this.numIron = other.numIron;
        this.numSilicon = other.numSilicon;
        this.passable = other.passable;
        this.robotInfoOnTile = new RobotInfo(other.robotInfoOnTile);
        this.point = new Point(other.point);
    }

    public MapTile(int x, int y){
        this.numIron = 0;
        this.numSilicon = 0;
        this.passable = true;
        this.robotInfoOnTile = null;
        this.point = new Point(x, y);
    }
}
