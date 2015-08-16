package sk.dudoslav.adventure.engine.input;

import static org.lwjgl.glfw.GLFW.*;

/**
 * Created by dusan on 10.08.2015.
 */
public class InputWrapper {
    private KeyCallbackHandler kch;
    private MouseCallbackHandler mch;
    private Input i = new Input();

    public InputWrapper(long w){
        kch = new KeyCallbackHandler(i);
        mch = new MouseCallbackHandler(i);
        glfwSetKeyCallback(w, kch);
        glfwSetCursorPosCallback(w, mch);
    }

    public Input getInput(){
        return i;
    }
}
