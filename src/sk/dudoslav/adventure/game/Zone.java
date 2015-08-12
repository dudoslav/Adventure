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
    private float r,g,b;

    private float ps[][] = new float[WIDTH][HEIGHT];

    public Zone(int x, int y){
        this.offsetX = x;
        this.offsetY = y;
    }

    public void setColor(float r, float g, float b){
        this.r = r;
        this.g = g;
        this.b = b;
    }

    public float getR() {
        return r;
    }

    public float getG() {
        return g;
    }

    public float getB() {
        return b;
    }

    public void setPointAt(int x, int y, float h){
        ps[x][y] = h;
    }

    public float getPointAt(int x, int y){
        if(x >= 0 && x < WIDTH && y >= 0 && y < HEIGHT) return ps[x][y];
        return 0;
    }
}
