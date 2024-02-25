package CodeWar.engine;

public class Team
{
    public int team;
    private int numIron;
    private int numSilicon;

    public Team(int team)
    {
        this.team = team;
        this.numIron = 100;
        this.numSilicon = 0;
    }

    public Team(Team other)
    {
        this.team = other.team;
        this.numIron = other.numIron;
        this.numSilicon = other.numSilicon;
    }

    protected int getIron()
    {
        return numIron;
    }
    protected int getSilicon()
    {
        return numSilicon;
    }
    protected void setSilicon(int s)
    {
         numSilicon = s;
    }
    protected void setIron(int s)
    {
        numIron = s;
    }
}
