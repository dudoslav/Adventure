package sk.dudoslav.adventure.engine.graphics;

import org.lwjgl.BufferUtils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.nio.ByteBuffer;

/**
 * Created by dusan on 16.08.2015.
 */
public class Image {

    private final ByteBuffer pixels;
    private final int w,h;

    public Image(String type, String path) throws Exception{
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

        ByteBuffer bb = BufferUtils.createByteBuffer(bi.getWidth() * bi.getHeight() * 4).put(b);
        bb.flip();

        pixels = bb;
        w = bi.getWidth();
        h = bi.getHeight();
    }

    public ByteBuffer getPixels() {
        return pixels;
    }

    public int getWidth() {
        return w;
    }

    public int getHeight() {
        return h;
    }
}
