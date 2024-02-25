package CodeWar.engine;

public class GameConstants
{
    public static final int SCOUT = 0;
    public static final int INFANTRY = 1;
    public static final int MINER = 2;
    public static final int SUPERBOT = 3;
    public static final int HQ = 4;
    public static final int CITADEL = 5;


    public static final int[] ATTACK = {6, 10, 2, 14, 0, 5};
    public static final int[] IRON_COST = {10, 20, 20, 20, Integer.MAX_VALUE, 50};
    public static final int[] SILICON_COST = {0, 5, 0, 15, Integer.MAX_VALUE, 0};
    public static final int[] HEALTH = {80, 100, 80, 120, 500, 200};
    public static final int[] COOLDOWN_MOVE = {10, 20, 20, 25, 0, 0};
    public static final int[] COOLDOWN_ATTACK = {25, 15, 25, 15, 20};
    public static final int[] COOLDOWN_MINE = {20, 20, 5, 20, 0, 0};
    public static final int[] VISION_RADIUS = {20, 20, 20, 20, 20, 20};
    public static final int BUILD_COOLDOWN = 15;


    public static final int MAX_TURN_COUNT = 2000;
    public static final int MINE_AMOUNT = 5;

    public static final int MAP_SIZE = 30;



}
