package sk.dudoslav.adventure.game;

/**
 * Created by dusan on 12.08.2015.
 */
public class VisibleZone {
    private int wtrd;

    private Zone zones[][];

    public VisibleZone(int wtrd){
        this.wtrd = wtrd;
        zones = new Zone[wtrd][wtrd];
    }

    public float getPointAt(int x, int y){
        if(x >= 0 && x < wtrd*(Zone.WIDTH-1) && y >= 0 && y < wtrd*(Zone.HEIGHT-1))
            return zones[x / (Zone.WIDTH-1)][y / (Zone.HEIGHT-1)].getPointAt(x % (Zone.WIDTH-1),y % (Zone.HEIGHT-1));
        return 0;
    }

    public void addZone(int x, int y, Zone zone){
        zones[x][y] = zone;
    }

    public Zone getZone(int x, int y){
        return zones[x][y];
    }

    public int getWtrd() {
        return wtrd;
    }

}
