package sk.dudoslav.adventure.game;

/**
 * Created by dusan on 10.08.2015.
 */
public class Zone {
    public static final class Key{
        public int x,y;
    }

    public static final int WIDTH  = 512;
    public static final int HEIGHT = 512;

    private float ps[][] = new float[WIDTH][HEIGHT];

    public void setPointAt(int x, int y, float h){
        ps[x][y] = h;
    }

    public float getPointAt(int x, int y){
        return ps[x][y];
    }
}
