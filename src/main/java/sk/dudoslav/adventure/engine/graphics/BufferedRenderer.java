package sk.dudoslav.adventure.engine.graphics;

import org.lwjgl.BufferUtils;

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

    FloatBuffer vertexBuffer = BufferUtils.createFloatBuffer(BUFFER_SIZE*VERTEX_DIMENSIONS);
    FloatBuffer normalBuffer = BufferUtils.createFloatBuffer(BUFFER_SIZE*VERTEX_DIMENSIONS);
    FloatBuffer textureCoordBuffer = BufferUtils.createFloatBuffer(BUFFER_SIZE*TEXT_COORD_DIMENSIONS);

    private int vertexBufferObjectHandle;
    private int normalBufferObjectHandle;
    private int textureCoordBufferObjectHandle;

    private int count = 0;
    private int end = 0;

    public BufferedRenderer(){
        vertexBufferObjectHandle = glGenBuffers();
        normalBufferObjectHandle = glGenBuffers();
        textureCoordBufferObjectHandle = glGenBuffers();
    }

    public void reset(){
        vertexBuffer.clear();
        normalBuffer.clear();
        textureCoordBuffer.clear();
        count = 0;
    }

    public void flipBuffers(){
        vertexBuffer.flip();
        normalBuffer.flip();
        textureCoordBuffer.flip();
        end = count;
    }

    public void uploadToGPU(){

        glBindBuffer(GL_ARRAY_BUFFER, vertexBufferObjectHandle);
        glBufferData(GL_ARRAY_BUFFER, vertexBuffer, GL_STATIC_DRAW);

        glBindBuffer(GL_ARRAY_BUFFER, normalBufferObjectHandle);
        glBufferData(GL_ARRAY_BUFFER, normalBuffer, GL_STATIC_DRAW);

        glBindBuffer(GL_ARRAY_BUFFER, textureCoordBufferObjectHandle);
        glBufferData(GL_ARRAY_BUFFER, textureCoordBuffer, GL_STATIC_DRAW);

        glBindBuffer(GL_ARRAY_BUFFER, 0);
    }

    public void draw(){
        glEnableClientState(GL_VERTEX_ARRAY);
        glEnableClientState(GL_NORMAL_ARRAY);
        glEnableClientState(GL_TEXTURE_COORD_ARRAY);

        glBindBuffer(GL_ARRAY_BUFFER, vertexBufferObjectHandle);
        glVertexPointer(VERTEX_DIMENSIONS, GL_FLOAT, 0, 0);

        glBindBuffer(GL_ARRAY_BUFFER, normalBufferObjectHandle);
        glNormalPointer(GL_FLOAT, 0, 0);
 
        glBindBuffer(GL_ARRAY_BUFFER, textureCoordBufferObjectHandle);
        glTexCoordPointer(TEXT_COORD_DIMENSIONS, GL_FLOAT, 0, 0);

        glDrawArrays(GL_TRIANGLES, 0, end * 3);

        glDisableClientState(GL_VERTEX_ARRAY);
        glDisableClientState(GL_NORMAL_ARRAY);
        glDisableClientState(GL_TEXTURE_COORD_ARRAY);

        glBindBuffer(GL_ARRAY_BUFFER, 0);
    }

    public void dispose(){
        glDeleteBuffers(vertexBufferObjectHandle);
        glDeleteBuffers(normalBufferObjectHandle);
        glDeleteBuffers(textureCoordBufferObjectHandle);
    }

    public void addVertex3f(float x, float y, float z){
        vertexBuffer.put(x).put(y).put(z);
        count++;
    }

    public void addNormal3f(float x, float y, float z){
        normalBuffer.put(x).put(y).put(z);
    }

    public void addTexCoord2f(float x, float y){
        textureCoordBuffer.put(x).put(y);
    }

}
