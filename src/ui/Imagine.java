package ui;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Imagine {
    public final static Color SWAMP_COLOR = new Color(157, 181, 126);
    public final static Color CELL_COLOR = new Color(122, 122, 122);
    public final static Color BACKGROUND_COLOR = new Color(217, 217, 217);
    public static final Color WALL_COLOR = new Color(217, 52, 0);
    public static BufferedImage resizeImage(BufferedImage img, Integer width, Integer height) {
        Image tmpImg = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

        Graphics2D g = bufferedImage.createGraphics();
        g.drawImage(tmpImg, 0, 0, null);
        g.dispose();

        return bufferedImage;
    }
}
