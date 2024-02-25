package CodeWar.engine;

public class GameConstants
{
    static final int SCOUT = 0;
    static final int INFANTRY = 1;
    static final int MINER = 2;
    static final int SUPERBOT = 3;
    static final int HQ = 4;


    static final int[] ATTACK = {3, 5, 1, 7, 0};
    static final int[] IRON_COST = {10, 20, 20, 20, Integer.MAX_VALUE};
    static final int[] SILICON_COST = {0, 5, 0, 10, Integer.MAX_VALUE};
    static final int[] HEALTH = {80, 100, 80, 120, 500};
    static final int[] COOLDOWN_MOVE = {10, 20, 20, 25, 0};
    static final int[] COOLDOWN_ATTACK = {20, 15, 25, 15, 0};
    static final int[] COOLDOWN_MINE = {20, 20, 5, 20, 0};
    static final int[] VISION_RADIUS = {20, 20, 20, 20, 20};


    static final int MAX_TURN_COUNT = 2000;
    static final int MINE_AMOUNT = 5;



}
