package CodeWar.engine;

public class Point
{
    public int x;
    public int y;

    public Point(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    public Point(Point other)
    {
        this.x = other.x;
        this.y = other.y;
    }

    public Direction directionTo(Point other)
    {
        int dx = other.x - this.x;
        int dy = other.y - this.y;

        if(dx == 0 && dy > 0)
        {
            return Direction.NORTH;
        }
        else if (dx > 0 && dy > 0)
        {
            return Direction.NORTHEAST;
        }
        else if (dx > 0 && dy == 0)
        {
            return Direction.EAST;
        }
        else if (dx > 0 && dy < 0)
        {
            return Direction.SOUTHEAST;
        }
        else if (dx == 0 && dy < 0)
        {
            return Direction.SOUTH;
        }
        else if (dx < 0 && dy < 0)
        {
            return Direction.SOUTHWEST;
        }
        else if (dx < 0 && dy == 0)
        {
            return Direction.WEST;
        }
        else if (dx < 0 && dy > 0)
        {
            return Direction.NORTHWEST;
        }
        return Direction.NONE;
    }

    //returns the point one square in the direction from point origin
    public Point pointInDirection(Direction direction){
        return switch (direction) {
            case NORTH -> new Point(x, y + 1);
            case NORTHEAST -> new Point(x + 1, y + 1);
            case EAST -> new Point(x + 1, y);
            case SOUTHEAST -> new Point(x + 1, y - 1);
            case SOUTH -> new Point(x, y - 1);
            case SOUTHWEST -> new Point(x - 1, y - 1);
            case WEST -> new Point(x - 1, y);
            case NORTHWEST -> new Point(x - 1, y + 1);
            default -> this;
        };
    }
    public MapTile pointAsMapTile(GameWorld gw){
        return gw.gameWorld[x][gw.sizeY - y - 1];
    }
    public static Point indexToTile(int row, int col, GameWorld gw)
    {
        return new Point(col, gw.sizeY - row - 1);
    }

    public int distanceSquaredTo(Point p) {
        return (int)Math.pow(Math.sqrt((x - p.x) * (x - p.x) + (y - p.y) * (y - p.y)), 2);
    }

    public boolean isAdjacent(Point p){
        if(x == p.x){
            return Math.abs(p.y-y) <= 1;
        }
        if (y == p.y){
            return Math.abs(p.x-x) <= 1;
        }
        return Math.abs(p.x-x) + Math.abs(p.y-y) <= 2;
    }
}
