package tv.floe.canova.vector.factory;

import junit.framework.TestCase;
import org.junit.Test;
import tv.floe.canova.formatters.ImageLoaderInputFormat;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Created by mjk on 8/30/14.
 */
public class imageLoaderTest extends TestCase {
    @Test
    public void testConvertImageStream() {
        File input = new File("src/test/java/tv/floe/canova/vector/factory/Test.jpg");
        try {
            BufferedImage imageReal = ImageIO.read(input);
            ImageLoaderInputFormat i = new ImageLoaderInputFormat();
            BufferedImage imageTest = i.read(new FileInputStream(input));
            assertEquals(imageReal, imageTest);
        } catch (IOException e) {
            e.printStackTrace();
        }



    }
}
