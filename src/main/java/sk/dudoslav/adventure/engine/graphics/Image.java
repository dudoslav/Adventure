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
    private final int width, height;

    public Image(String type, String path) throws Exception{
        BufferedImage bufferedImage = ImageIO.read(new File(path));

        byte[] pixels = new byte[bufferedImage.getWidth()*bufferedImage.getHeight()*4];

        Color c;

        for(int y = 0; y < bufferedImage.getHeight(); y++)
            for(int x = 0; x < bufferedImage.getWidth(); x++){
                c = new Color(bufferedImage.getRGB(x,y));
                pixels[((x+y*bufferedImage.getWidth())*4)] = (byte) c.getRed();
                pixels[((x+y*bufferedImage.getWidth())*4)+1] = (byte) c.getGreen();
                pixels[((x+y*bufferedImage.getWidth())*4)+2] = (byte) c.getBlue();
                pixels[((x+y*bufferedImage.getWidth())*4)+3] = (byte) c.getAlpha();
            }

        ByteBuffer pixelBuffer = BufferUtils.createByteBuffer(bufferedImage.getWidth() * bufferedImage.getHeight() * 4).put(pixels);
        pixelBuffer.flip();

        this.pixels = pixelBuffer;
        width = bufferedImage.getWidth();
        height = bufferedImage.getHeight();
    }

    public ByteBuffer getPixels() {
        return pixels;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
