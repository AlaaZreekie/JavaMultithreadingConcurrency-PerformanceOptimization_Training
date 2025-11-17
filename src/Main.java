import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Main {

    public static final String SOURCE_FILE = "res/1.jpg";
    public static final String DESTINATION_FILE = "./out/2.jpg";

    public static void main(String[] args) throws IOException {
        BufferedImage originalImage = ImageIO.read(new File(SOURCE_FILE));
        BufferedImage resultImage = new BufferedImage(originalImage.getWidth(), originalImage.getHeight(), BufferedImage.TYPE_INT_RGB);


    }


    public static boolean isShadeOfGray(int red, int green, int blue) {
        return Math.abs(red - green) < 30 && Math.abs(red - blue) < 30 && Math.abs(green - blue) < 30;
    }

    public static int createRGBFromColor(int red, int green, int blue) {
        int rgb = 0;

        rgb |= red << 16;
        rgb |= green << 8;
        rgb |= blue;

        rgb |= 0xff000000;

        return rgb;
    }

    public static int getBlue(int rgb) {
        return rgb & 0x000000FF;
    }

    public static int getRed(int rgb) {
        return rgb & 0x00FF0000 >> 16;
    }

    public static int getGreen(int rgb) {
        return rgb & 0x0000FF00 >> 8;
    }
}