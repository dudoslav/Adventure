package sk.dudoslav.adventure.engine.input;

import org.lwjgl.glfw.GLFWCursorPosCallback;

/**
 * Created by dusan on 10.08.2015.
 */
public class MouseCallbackHandler extends GLFWCursorPosCallback {

    private final Input input;

    public MouseCallbackHandler(Input i){
        this.input = i;
    }

    @Override
    public void invoke(long windowHandle, double xpos, double ypos) {
        Input.Mouse mouse = input.getMouse();

        mouse.lx = mouse.x;
        mouse.ly = mouse.y;

        mouse.x = xpos;
        mouse.y = ypos;
    }
}
