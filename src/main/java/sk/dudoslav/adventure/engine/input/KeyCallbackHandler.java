package sk.dudoslav.adventure.engine.input;

import org.lwjgl.glfw.GLFWKeyCallback;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.glfw.GLFW.GLFW_RELEASE;
import static org.lwjgl.opengl.GL11.GL_TRUE;

/**
 * Created by dusan on 10.08.2015.
 */
public class KeyCallbackHandler extends GLFWKeyCallback {

    private final Input input;

    public KeyCallbackHandler(Input i){
        this.input = i;
    }

    @Override
    public void invoke(long window, int key, int scancode, int action, int mods) {
        if (key == GLFW_KEY_ESCAPE && action == GLFW_RELEASE)
            glfwSetWindowShouldClose(window, GL_TRUE);

        switch (action){
            case GLFW_PRESS:
                input.updateKey(key, true);
                break;
            case GLFW_RELEASE:
                input.updateKey(key, false);
                break;
        }
    }
}
