package CodeWar.engine;


public class MapTile
{
    protected int numIron;
    protected int numSilicon;
    protected boolean passable;
    //This value is null if no Robot is present on the tile
    protected RobotInfo robotInfoOnTile;
    //location of the tile
    protected Point point;

    protected MapTile(int numIron, int numSilicon, boolean passable, RobotInfo robotInfoOnTile, Point mapLocation)
    {
        this.numIron = numIron;
        this.numSilicon = numSilicon;
        this.passable = passable;
        this.robotInfoOnTile = robotInfoOnTile;
        this.point = mapLocation;
    }

    protected MapTile(MapTile other)
    {
        this.numIron = other.numIron;
        this.numSilicon = other.numSilicon;
        this.passable = other.passable;
        this.robotInfoOnTile = new RobotInfo(other.robotInfoOnTile);
        this.point = new Point(other.point);
    }

    protected MapTile(int x, int y){
        this.numIron = 0;
        this.numSilicon = 0;
        this.passable = true;
        this.robotInfoOnTile = null;
        this.point = new Point(x, y);
    }

    protected MapTile(int x, int y, boolean isPassable){
        this.numIron = 0;
        this.numSilicon = 0;
        this.passable = isPassable;
        this.robotInfoOnTile = null;
        this.point = new Point(x, y);
    }

    public Point getPoint(){
        return this.point;
    }

    public int getIron(){
        return this.numIron;
    }

    public int getSilicon(){
        return this.numSilicon;
    }

    public boolean getPassable(){
        return this.passable;
    }

    public RobotInfo getRobotInfoOnTile(){
        return this.robotInfoOnTile;
    }
}
