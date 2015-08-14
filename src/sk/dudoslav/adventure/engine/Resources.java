package sk.dudoslav.adventure.engine;

import org.lwjgl.BufferUtils;

import javax.imageio.ImageIO;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;

import static org.lwjgl.opengl.GL11.*;

/**
 * Created by dusan on 14.08.2015.
 */
public class Resources {
    private ByteBuffer rock, grass, dirt_grass;

    private ByteBuffer loadImageToBuffer(String type, String path) throws Exception{
        BufferedImage bi = ImageIO.read(new File(path));

        byte[] b = new byte[bi.getWidth()*bi.getHeight()*4];

        Color c;

        for(int y = 0; y < bi.getHeight(); y++)
            for(int x = 0; x < bi.getWidth(); x++){
                c = new Color(bi.getRGB(x,y));
                b[((x+y*bi.getWidth())*4)] = (byte) c.getRed();
                b[((x+y*bi.getWidth())*4)+1] = (byte) c.getGreen();
                b[((x+y*bi.getWidth())*4)+2] = (byte) c.getBlue();
                b[((x+y*bi.getWidth())*4)+3] = (byte) c.getAlpha();
            }

        ByteBuffer bb = BufferUtils.createByteBuffer(bi.getWidth()*bi.getHeight()*4).put(b);
        bb.flip();
        return bb;
    }

    public void loadResources(){
        try {
            rock = loadImageToBuffer("jpg","res/tex/rock.jpg");
            grass = loadImageToBuffer("jpg","res/tex/grass.jpg");
            dirt_grass = loadImageToBuffer("jpg","res/tex/dirt_grass.jpg");
        } catch (Exception e) {
            System.out.println("Failed to load resources!");
            e.printStackTrace();
        }
    }

    public ByteBuffer getRock() {
        return rock;
    }

    public ByteBuffer getGrass() {
        return grass;
    }

    public ByteBuffer getDirt_grass() {
        return dirt_grass;
    }
}
