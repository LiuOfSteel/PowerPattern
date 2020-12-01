package guioptimiser;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

class fitness {
    public fitness() {
    }

    public static float loadPixelsFromImage(File file) throws IOException {
        float redSum = 0L;
        float greenSum = 0L;
        float blueSum = 0L;
        BufferedImage image = ImageIO.read(file);
        Color[][] colors = new Color[image.getWidth()][image.getHeight()];

        for(int x = 0; x < image.getWidth(); ++x) {
            for(int y = 0; y < image.getHeight(); ++y) {
                colors[x][y] = new Color(image.getRGB(x, y));
                int clr = image.getRGB(x, y);
                int red = (clr & 16711680) >> 16;
                int green = (clr & '\uff00') >> 8;
                int blue = clr & 255;
                redSum += (long)red;
                greenSum += (long)green;
                blueSum += (long)blue;
            }
        }

        float sum = (redSum * 131L + greenSum * 142L + blueSum * 241L + (long)(181 * image.getHeight() * image.getWidth())) / 940032000L;
        System.out.println("Notice the sum of this image is: " + sum);
        return sum;
    }

    public static void main(String[] args) throws IOException {
        loadPixelsFromImage(new File("C://Users//84464//Desktop//sbse//头像//pic05.jpg"));
        loadPixelsFromImage(new File("C://Users//84464//Desktop//sbse//头像//pic04.jpg"));
        loadPixelsFromImage(new File("C://Users//84464//Desktop//sbse//头像//pic03.jpg"));
    }
}
