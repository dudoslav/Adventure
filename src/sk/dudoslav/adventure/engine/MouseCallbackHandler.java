package sk.dudoslav.adventure.engine;

import org.lwjgl.glfw.GLFWCursorPosCallback;

/**
 * Created by dusan on 10.08.2015.
 */
public class MouseCallbackHandler extends GLFWCursorPosCallback {

    private final Input i;

    public MouseCallbackHandler(Input i){
        this.i = i;
    }

    @Override
    public void invoke(long window, double xpos, double ypos) {
        i.mouseX = xpos;
        i.mouseY = ypos;
    }
}
