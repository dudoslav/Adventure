package sk.dudoslav.adventure.engine;

import static org.lwjgl.glfw.GLFW.*;

/**
 * Created by dusan on 10.08.2015.
 */
public class Input {
    private boolean keys[] = new boolean[GLFW_KEY_LAST];
    double mouseX, mouseY;

    public double getMouseX() {
        return mouseX;
    }

    public double getMouseY() {
        return mouseY;
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
}
