package sk.dudoslav.adventure.game;

import sk.dudoslav.adventure.engine.Input;

import static org.lwjgl.glfw.GLFW.*;

/**
 * Created by dusan on 10.08.2015.
 */
public class Player {
    public static final float SPEED = 0.5f;

    private float x = 256f;
    private float y = 64f;
    private float z = 256f;

    private float rx = 0f;
    private float ry = 0f;
    private float rz = 0f;

    private double lx = 0f;
    private double ly = 0f;

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

    public void update(Input i){
        if(i.isKeyDown(GLFW_KEY_W)) move(0,0,-SPEED);
        if(i.isKeyDown(GLFW_KEY_S)) move(0,0,SPEED);
        if(i.isKeyDown(GLFW_KEY_A)) move(-SPEED,0,0);
        if(i.isKeyDown(GLFW_KEY_D)) move(SPEED,0,0);
        if(i.isKeyDown(GLFW_KEY_E)) y += SPEED;
        if(i.isKeyDown(GLFW_KEY_Q)) y -= SPEED;

        ry += (i.getMouseX()-lx)/100;
        rx += (i.getMouseY()-ly)/100;

        lx = i.getMouseX();
        ly = i.getMouseY();
    }

    private void move(float x, float y, float z){
        this.x += (float) (x * Math.cos(ry) - z * Math.sin(ry));
        this.z += (float) (z * Math.cos(ry) + x * Math.sin(ry));
    }

}
