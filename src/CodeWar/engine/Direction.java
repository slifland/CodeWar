package CodeWar.engine;

public enum Direction {
    NORTH,
    NORTHEAST,
    EAST,
    SOUTHEAST,
    SOUTH,
    SOUTHWEST,
    WEST,
    NORTHWEST,
    NONE;

    public Direction rotateRight(){
        if(this == NONE) return NONE;
        return values()[ordinal() + 1];
    }
    public Direction rotateLeft(){
        if(this == NONE) return NONE;
        else if(this == NORTH) return NORTHWEST;
        return values()[ordinal() - 1];
    }
}
