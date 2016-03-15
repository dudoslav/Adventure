package sk.dudoslav.adventure.engine.input;

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
        Input.Mouse mouse = i.getMouse();

        mouse.lx = mouse.x;
        mouse.ly = mouse.y;

        mouse.x = xpos;
        mouse.y = ypos;
    }
}
