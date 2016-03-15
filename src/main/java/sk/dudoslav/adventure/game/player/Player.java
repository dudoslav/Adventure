package sk.dudoslav.adventure.game.player;

import sk.dudoslav.adventure.game.world.Zone;


/**
 * Created by dusan on 10.08.2015.
 */
public class Player {
    public static final float SPEED = 3f;

    float x = 0f;
    float y = 0f;
    float z = 0f;

    float rx = 0f;
    float ry = 0f;
    float rz = 0f;


    public float getRx() {
        return rx;
    }

    public float getRy() {
        return ry;
    }

    public float getRz() {
        return rz;
    }

    public float getX() {
        return -x;
    }

    public float getY() {
        return -y;
    }

    public float getZ() {
        return -z;
    }

    public int getZoneX(){
        return (int)(x / Zone.WIDTH);
    }

    public int getZoneY(){
        return (int)(z / Zone.HEIGHT);
    }


    public void move(float x, float y, float z){
        this.x += (float) (x * Math.cos(ry) - z * Math.sin(ry));
        this.z += (float) (z * Math.cos(ry) + x * Math.sin(ry));
        this.y += y;
    }

}
