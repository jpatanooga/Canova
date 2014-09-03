package tv.floe.canova.formatters;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Base64;

/**
 * Created by mjk on 8/30/14.
 */
public class ImageLoaderOutputFormat extends BaseOutputFormat<BufferedImage> {

    public static final String DEFAULT_FORMAT = new String("png");
    public String getFormat(String imageName)
    {
        switch(imageName.toLowerCase())
        {
            case ".png": return "PNG";
            case ".gif": return "GIF";
            case ".tiff": return "TIFF";
            case ".jpg": return "JPG";
            case ".jpeg": return "JPEG";
        }

        return "PNG";
    }

    @Override
    public void write(BufferedImage bufferedImage, File file) throws IOException {
        FileOutputStream os = new FileOutputStream(file);
        ImageIO.write(bufferedImage, getFormat(file.getName()), os);
    }

    @Override
    public String write(BufferedImage bufferedImage) throws IOException {
        return write(bufferedImage, DEFAULT_FORMAT);
    }
    public String write(BufferedImage bufferedImage, String format) throws IOException {

        //ImageIO.write(bufferedImage, format, b);
        ByteArrayOutputStream b = new ByteArrayOutputStream();
        ImageIO.write(bufferedImage, format, Base64.getEncoder().wrap(b));
        return b.toString();
    }


    @Override
    public void write(BufferedImage bufferedImage, OutputStream os) throws IOException {
        ImageIO.write(bufferedImage, DEFAULT_FORMAT, os);
    }
}
