package sk.dudoslav.adventure.engine;

import org.lwjgl.BufferUtils;

import java.nio.FloatBuffer;

import static org.lwjgl.opengl.GL11.*;

/**
 * Created by dusan on 14.08.2015.
 */
public class Material {

    FloatBuffer ambient;
    FloatBuffer specular;
    FloatBuffer diffuse;
    FloatBuffer emission;

    public Material(){

        ambient = BufferUtils.createFloatBuffer(4).put(new float[]{0.2f, 0.2f, 0.2f, 1.0f});
        specular = BufferUtils.createFloatBuffer(4).put(new float[]{0.8f, 0.8f, 0.8f, 1.0f});
        diffuse = BufferUtils.createFloatBuffer(4).put(new float[]{0.0f, 0.0f, 0.0f, 1.0f});
        emission = BufferUtils.createFloatBuffer(4).put(new float[]{0.0f, 0.0f, 0.0f, 1.0f});

        ambient.flip();
        specular.flip();
        diffuse.flip();
        emission.flip();

    }

    public void apply(){
        glMaterialfv( GL_FRONT_AND_BACK, GL_AMBIENT, ambient);
        glMaterialfv( GL_FRONT_AND_BACK, GL_DIFFUSE, diffuse);
        glMaterialfv( GL_FRONT_AND_BACK, GL_SPECULAR, specular);
        glMaterialfv( GL_FRONT_AND_BACK, GL_EMISSION, emission);
    }
}
