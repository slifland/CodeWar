package CodeWar.engine;

public class GameConstants
{
    public static final int SCOUT = 0;
    public static final int INFANTRY = 1;
    public static final int MINER = 2;
    public static final int SUPERBOT = 3;
    public static final int HQ = 4;
    public static final int CITADEL = 5;

    //accessing any of these arrays at the value listed above will return a robots relevant value/skill
    //note - 0 indicates a lack of capability, and max_value costs indicates an inability to build new headquarters
    public static final int[] ATTACK = {7, 14, 3, 21, 0, 8};
    public static final int[] IRON_COST = {10, 20, 20, 20, Integer.MAX_VALUE, 50};
    public static final int[] SILICON_COST = {0, 5, 0, 15, Integer.MAX_VALUE, 0};
    public static final int[] HEALTH = {40, 60, 40, 80, 200, 100};
    public static final int[] COOLDOWN_MOVE = {10, 20, 15, 25, 0, 0};
    public static final int[] COOLDOWN_ATTACK = {25, 15, 25, 15, 20};
    public static final int[] COOLDOWN_MINE = {25, 25, 10, 25, 0, 0};
    public static final int[] VISION_RADIUS = {25, 20, 20, 20, 20, 20};
    public static final int BUILD_COOLDOWN = 15;


    public static final int MAX_TURN_COUNT = 2000;
    public static final int MINE_AMOUNT = 5;

    public static final int MAP_SIZE = 30;

    public static final int NUM_OBSTACLES = 100;
    public static final int MAX_HQS = 3;
    public static final int MAX_WELLS = 3;
    public static final int WELL_IRON = 40;
    public static final int WELL_SILICON = 40;
    public static final int ACTION_FAILURE_PENALTY = 40;



}
