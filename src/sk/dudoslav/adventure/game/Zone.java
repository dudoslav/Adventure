package sk.dudoslav.adventure.game;

/**
 * Created by dusan on 10.08.2015.
 */
public class Zone {

    public static final int WIDTH  = 256;
    public static final int HEIGHT = 256;

    public int getOffsetX() {
        return offsetX;
    }

    public int getOffsetY() {
        return offsetY;
    }

    private int offsetX,offsetY;

    private float ps[][] = new float[WIDTH][HEIGHT];

    public Zone(int x, int y){
        this.offsetX = x;
        this.offsetY = y;
    }

    public void setPointAt(int x, int y, float h){
        ps[x][y] = h;
    }

    public float getPointAt(int x, int y){
        return ps[x][y];
    }
}
