package sk.dudoslav.adventure.engine.input;

import static org.lwjgl.glfw.GLFW.*;

/**
 * Created by dusan on 10.08.2015.
 */
public class InputWrapper {
    private KeyCallbackHandler keyCallbackHandler;
    private MouseCallbackHandler mouseCallbackHandler;
    private Input input = new Input();

    public InputWrapper(long windowHandle){
        keyCallbackHandler = new KeyCallbackHandler(input);
        mouseCallbackHandler = new MouseCallbackHandler(input);
        glfwSetKeyCallback(windowHandle, keyCallbackHandler);
        glfwSetCursorPosCallback(windowHandle, mouseCallbackHandler);
    }

    public Input getInput(){
        return input;
    }
}
