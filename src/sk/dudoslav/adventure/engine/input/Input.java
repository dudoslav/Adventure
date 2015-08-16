package sk.dudoslav.adventure.engine.input;

import static org.lwjgl.glfw.GLFW.*;

/**
 * Created by dusan on 10.08.2015.
 */
public class Input {

    public class Mouse{
        double lx = 0, ly = 0;
        public double x = 0,y = 0;
        public double dx = 0,dy = 0;
    }

    private boolean keys[] = new boolean[GLFW_KEY_LAST];
    private Mouse mouse = new Mouse();

    public Mouse getMouse(){
        return mouse;
    }
    public Input(){
        for(int i = 0; i < GLFW_KEY_LAST; i++){
            keys[i] = false;
        }
    }

    public boolean isKeyDown(int key){
        return keys[key];
    }

    public void updateKey(int key, boolean state){
        keys[key] = state;
    }

    public void updateMouse(){
        mouse.dx = mouse.x - mouse.lx;
        mouse.dy = mouse.y - mouse.ly;

        mouse.lx = mouse.x;
        mouse.ly = mouse.y;
    }
}
