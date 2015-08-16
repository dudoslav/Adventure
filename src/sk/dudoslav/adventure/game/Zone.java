package sk.dudoslav.adventure.game;

/**
 * Created by dusan on 10.08.2015.
 */
public class Zone {

    public static final int WIDTH  = 128;
    public static final int HEIGHT = 128;

    public int getOffsetX() {
        return offsetX;
    }

    public int getOffsetY() {
        return offsetY;
    }

    private int offsetX,offsetY;
    private int q = 1;

    private float ps[][] = new float[WIDTH][HEIGHT];

    public Zone(int x, int y){
        this.offsetX = x;
        this.offsetY = y;
    }

    public int getQ() {
        return q;
    }

    public void setQ(int q) {
        this.q = q;
    }

    public void setPointAt(int x, int y, float h){
        ps[x][y] = h;
    }

    public float getPointAt(int x, int y){
        if(x >= 0 && x < WIDTH && y >= 0 && y < HEIGHT) return ps[x][y];
        return 0;
    }
}
