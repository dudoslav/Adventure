package sk.dudoslav.adventure.engine.graphics;

import java.nio.ByteBuffer;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL13.*;
import static org.lwjgl.opengl.GL30.glGenerateMipmap;

/**
 * Created by dusan on 14.08.2015.
 */
public class TextureManager {

    private int textures[];

    public TextureManager(int num){
        textures = new int[num];
        for(int i = 0; i < num; i++){
            textures[i] = glGenTextures();
        }
    }

    public void activateTexture(int num){
        glActiveTexture(GL_TEXTURE0+num);
    }

    public void bindTexture(int num){
        glBindTexture(GL_TEXTURE_2D, textures[num]);
    }

    public void loadTexture(Image image){
        glTexParameteri( GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_LINEAR_MIPMAP_LINEAR );
        glTexParameteri( GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_LINEAR_MIPMAP_LINEAR );
        glTexParameteri( GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL_REPEAT);
        glTexParameteri( GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_REPEAT);

        glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA8, image.getWidth(), image.getHeight(), 0, GL_RGBA,
                GL_UNSIGNED_BYTE, image.getPixels());

        glGenerateMipmap(GL_TEXTURE_2D);
    }

}
