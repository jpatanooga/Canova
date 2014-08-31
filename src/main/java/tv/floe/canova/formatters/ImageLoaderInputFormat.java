package tv.floe.canova.formatters;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Base64;

/**
 * Created by mjk on 8/30/14.
 */
public class ImageLoaderInputFormat extends BaseInputFormat<BufferedImage> {
    private static final String COMMA = new String(",");
    private static final String NEWLINE = new String("\n");
    @Override
    public BufferedImage read(InputStream is, String delim) throws IOException {
        BufferedImage bi = ImageIO.read(is);

        return bi;
    }

    public BufferedImage read(InputStream is) throws IOException {
        return read(is, COMMA);
    }

    public BufferedImage read(InputStream is, int height, int width) throws IOException {

        /*
        BufferedImage bi = read(is, COMMA);
        bi.
        bi.getScaledInstance(height, width, 0);
        */
        return read(is, COMMA);
    }
    @Override
    public BufferedImage read(String str, String delim) throws IOException {
        return ImageIO.read(new ByteArrayInputStream(Base64.getDecoder().decode(str)));
    }

    public BufferedImage read(String is) throws IOException {
        return this.read(is, COMMA);
    }

    @Override
    public BufferedImage read(File file, String delim) throws IOException {
        return read(new BufferedInputStream( new FileInputStream(file)), COMMA);
    }

    public BufferedImage read(File file) throws IOException {
        return this.read(file, COMMA);
    }
}
