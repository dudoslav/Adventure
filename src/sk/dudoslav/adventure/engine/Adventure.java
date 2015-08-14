package sk.dudoslav.adventure.engine;

import org.lwjgl.Sys;
import org.lwjgl.glfw.*;
import org.lwjgl.opengl.*;

import java.io.File;
import java.nio.ByteBuffer;

import static org.lwjgl.glfw.Callbacks.*;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryUtil.*;

/**
 * Created by dusan on 09.08.2015.
 */
public class Adventure {
    private GLFWErrorCallback errorCallback;

    private GameStatesManager gsm;
    private InputWrapper iw;
    private AdventureContainer ac;

    private int WIDTH;
    private int HEIGHT;

    public void run() {
        System.out.println("Hello LWJGL " + Sys.getVersion() + "!"); //TODO: neco s tym spravit!

        try {
            init();
            loop();

            gsm.dispose();
            glfwDestroyWindow(ac.getWindow());
        } catch (Exception e) {
            System.out.println("Something went wrong! -> "+e.getMessage());
            e.printStackTrace();
        } finally {
            glfwTerminate();
            errorCallback.release();
        }
    }

    private void init() {
        AdventureProperties ap;
        long window;

        ap  = new AdventureProperties(new File("settings.prop"));
        try{
            ap.load();
        } catch (Exception E) {
            System.out.println("ERROR: Failed to open settings file! -> "+E.getMessage());
        }

        WIDTH  = ap.getWidth();
        HEIGHT = ap.getHeight();

        glfwSetErrorCallback(errorCallback = errorCallbackPrint(System.err));

        if ( glfwInit() != GL11.GL_TRUE )
            throw new IllegalStateException("Unable to initialize GLFW");

        glfwDefaultWindowHints();
        glfwWindowHint(GLFW_VISIBLE, GL_FALSE);
        glfwWindowHint(GLFW_RESIZABLE, GL_TRUE);

        window = glfwCreateWindow(WIDTH, HEIGHT, "Adventure", NULL, NULL);
        if ( window == NULL )
            throw new RuntimeException("Failed to create the GLFW window");

        // Setup a key callback. It will be called every time a key is pressed, repeated or released.
        iw = new InputWrapper(window);
        ByteBuffer vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());

        glfwSetWindowPos(
                window,
                (GLFWvidmode.width(vidmode) - WIDTH) / 2,
                (GLFWvidmode.height(vidmode) - HEIGHT) / 2
        );

        glfwMakeContextCurrent(window);

        //glfwSwapInterval(1); //TODO: toto je vsync - treba dorobit s propertiesamy.

        glfwShowWindow(window);
        glfwSetInputMode(window, GLFW_CURSOR, GLFW_CURSOR_DISABLED); //TODO: Neskor prenest do in game stateu.

        Resources r = new Resources();
        r.loadResources();

        ac = new AdventureContainer(ap,iw.getInput(),r,window);
    }

    private void loop() {
        GLContext.createFromCurrent();
        glClearColor(0.3f, 0.92f, 0.92f, 0.0f);
        glEnable(GL_DEPTH_TEST);

        gsm = new GameStatesManager(ac);

        while ( glfwWindowShouldClose(ac.getWindow()) == GL_FALSE ) {
            glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT); // clear the framebuffer
            glViewport(0, 0, WIDTH, HEIGHT);
            gsm.tick(iw.getInput());
            glfwSwapBuffers(ac.getWindow());
            glfwPollEvents();
        }
    }

}
