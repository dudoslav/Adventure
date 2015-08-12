package sk.dudoslav.adventure.engine;

import org.lwjgl.BufferUtils;

import java.nio.FloatBuffer;

import static org.lwjgl.opengl.GL11.*;

/**
 * Created by dusan on 12.08.2015.
 */
public class Light {
    FloatBuffer ambientLightColor;
    FloatBuffer specularLight;
    FloatBuffer lightColor0;
    FloatBuffer lightPos0;

    private float x,y,z;
    private final int num;


    public Light(int num){
        this.num = num;
    }

    public void createDirectionalLight(){
        x = 0.5f;
        y = 1f;
        z = 0.5f;

        ambientLightColor = BufferUtils.createFloatBuffer(4).put(new float[]{0.1f, 0.1f, 0.1f, 1.0f});
        specularLight = BufferUtils.createFloatBuffer(4).put(new float[]{1.f, 1.f, 1.f, 1.f});
        lightColor0 = BufferUtils.createFloatBuffer(4).put(new float[]{0.8f, 0.8f, 0.8f, 1.f});
        lightPos0 = BufferUtils.createFloatBuffer(4).put(new float[]{x, y, z, 0.0f});

        ambientLightColor.flip();
        specularLight.flip();
        lightColor0.flip();
        lightPos0.flip();
    }

    public void enable(){
        glEnable(GL_LIGHT0+num);

        glLightfv(GL_LIGHT0, GL_AMBIENT, ambientLightColor);
        glLightfv(GL_LIGHT0, GL_SPECULAR, specularLight);
        glLightfv(GL_LIGHT0, GL_DIFFUSE, lightColor0);
        glLightfv(GL_LIGHT0, GL_POSITION, lightPos0);
    }

    public void updatePosDir(){
        glLightfv(GL_LIGHT0, GL_POSITION, lightPos0);
    }

    public void addPosDir(float x, float y, float z){
        this.x += x;
        this.y += y;
        this.z += z;
        lightPos0 = BufferUtils.createFloatBuffer(4).put(new float[]{this.x, this.y, this.z, 0.0f});
        lightPos0.flip();
    }
}
