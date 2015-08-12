package sk.dudoslav.adventure.engine;

import org.lwjgl.BufferUtils;

import java.nio.FloatBuffer;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;

/**
 * Created by dusan on 10.08.2015.
 */
public class BufferedRenderer {
    public static final int VERTEX_DIMENSIONS = 3;
    public static final int BUFFER_SIZE = 0x2000000*2;

    FloatBuffer v = BufferUtils.createFloatBuffer(BUFFER_SIZE);
    FloatBuffer n = BufferUtils.createFloatBuffer(BUFFER_SIZE);
    FloatBuffer c = BufferUtils.createFloatBuffer(BUFFER_SIZE);

    private int vbo;
    private int nbo;
    private int cbo;

    private int count = 0;

    public BufferedRenderer(){
        vbo = glGenBuffers();
        nbo = glGenBuffers();
        cbo = glGenBuffers();
    }

    public void reset(){
        v.clear();
        n.clear();
        c.clear();
        count = 0;
    }

    public void uploadToGPU(){
        v.flip();
        glBindBuffer(GL_ARRAY_BUFFER, vbo);
        glBufferData(GL_ARRAY_BUFFER, v, GL_STATIC_DRAW);

        n.flip();
        glBindBuffer(GL_ARRAY_BUFFER, nbo);
        glBufferData(GL_ARRAY_BUFFER, n, GL_STATIC_DRAW);

        c.flip();
        glBindBuffer(GL_ARRAY_BUFFER, cbo);
        glBufferData(GL_ARRAY_BUFFER, c, GL_STATIC_DRAW);

        glBindBuffer(GL_ARRAY_BUFFER, 0);
    }

    public void draw(){
        glEnableClientState(GL_VERTEX_ARRAY);
        glEnableClientState(GL_NORMAL_ARRAY);
        glEnableClientState(GL_COLOR_ARRAY);

        glBindBuffer(GL_ARRAY_BUFFER, vbo);
        glVertexPointer(VERTEX_DIMENSIONS, GL_FLOAT, 0, 0);

        glBindBuffer(GL_ARRAY_BUFFER, nbo);
        glNormalPointer(GL_FLOAT, 0, 0);

        glBindBuffer(GL_ARRAY_BUFFER, cbo);
        glColorPointer(3, GL_FLOAT, 0, 0);

        glDrawArrays(GL_LINES, 0, count * 3);

        glDisableClientState(GL_VERTEX_ARRAY);
        glDisableClientState(GL_NORMAL_ARRAY);
        glDisableClientState(GL_COLOR_ARRAY);

        glBindBuffer(GL_ARRAY_BUFFER, 0);
    }

    public void dispose(){
        glDeleteBuffers(vbo);
        glDeleteBuffers(nbo);
        glDeleteBuffers(cbo);
    }

    public void addVertex3f(float x, float y, float z){
        v.put(x).put(y).put(z);
        count++;
    }

    public void addNormal3f(float x, float y, float z){
        n.put(x).put(y).put(z);
    }

    public void addColor3f(float r, float g, float b){
        c.put(r).put(g).put(b);
    }
}
