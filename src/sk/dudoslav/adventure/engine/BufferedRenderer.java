package sk.dudoslav.adventure.engine;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL30;

import java.nio.FloatBuffer;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;

/**
 * Created by dusan on 10.08.2015.
 */
public class BufferedRenderer {
    public static final int VERTEX_DIMENSIONS = 3;
    public static final int TEXT_COORD_DIMENSIONS = 2;
    public static final int BUFFER_SIZE = 0x2000000;

    FloatBuffer v = BufferUtils.createFloatBuffer(BUFFER_SIZE*VERTEX_DIMENSIONS);
    FloatBuffer n = BufferUtils.createFloatBuffer(BUFFER_SIZE*VERTEX_DIMENSIONS);
    FloatBuffer t = BufferUtils.createFloatBuffer(BUFFER_SIZE*TEXT_COORD_DIMENSIONS);

    private int vbo;
    private int nbo;
    private int tbo;

    private int count = 0;

    public BufferedRenderer(){
        vbo = glGenBuffers();
        nbo = glGenBuffers();
        tbo = glGenBuffers();
    }

    public void reset(){
        v.clear();
        n.clear();
        t.clear();
        count = 0;
    }

    public void uploadToGPU(){
        v.flip();
        glBindBuffer(GL_ARRAY_BUFFER, vbo);
        glBufferData(GL_ARRAY_BUFFER, v, GL_STATIC_DRAW);

        n.flip();
        glBindBuffer(GL_ARRAY_BUFFER, nbo);
        glBufferData(GL_ARRAY_BUFFER, n, GL_STATIC_DRAW);

        t.flip();
        glBindBuffer(GL_ARRAY_BUFFER, tbo);
        glBufferData(GL_ARRAY_BUFFER, t, GL_STATIC_DRAW);

        glBindBuffer(GL_ARRAY_BUFFER, 0);
    }

    public void draw(){
        glEnableClientState(GL_VERTEX_ARRAY);
        glEnableClientState(GL_NORMAL_ARRAY);
        glEnableClientState(GL_TEXTURE_COORD_ARRAY);

        glBindBuffer(GL_ARRAY_BUFFER, vbo);
        glVertexPointer(VERTEX_DIMENSIONS, GL_FLOAT, 0, 0);

        glBindBuffer(GL_ARRAY_BUFFER, nbo);
        glNormalPointer(GL_FLOAT, 0, 0);

        glBindBuffer(GL_ARRAY_BUFFER, tbo);
        glTexCoordPointer(TEXT_COORD_DIMENSIONS, GL_FLOAT, 0, 0);

        glDrawArrays(GL_TRIANGLES, 0, count * 3);

        glDisableClientState(GL_VERTEX_ARRAY);
        glDisableClientState(GL_NORMAL_ARRAY);
        glDisableClientState(GL_TEXTURE_COORD_ARRAY);

        glBindBuffer(GL_ARRAY_BUFFER, 0);
    }

    public void dispose(){
        glDeleteBuffers(vbo);
        glDeleteBuffers(nbo);
        glDeleteBuffers(tbo);
    }

    public void addVertex3f(float x, float y, float z){
        v.put(x).put(y).put(z);
        count++;
    }

    public void addNormal3f(float x, float y, float z){
        n.put(x).put(y).put(z);
    }

    public void addTexCoord2f(float x, float y){
        t.put(x).put(y);
    }
}
