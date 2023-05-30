package ui.FieldsObjects;

import ui.Imagine;
import ui.Navigation.Cells.CellWidget;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public abstract class FieldObjectWidget extends JPanel {
    public FieldObjectWidget() {
        setOpaque(false);
        setPreferredSize(new Dimension(CellWidget.CELL_SIZE, CellWidget.CELL_SIZE));
    }
    private BufferedImage _image;
    protected BufferedImage getImage() {
        return _image;
    }
    protected String pathnameImg;
    @Override
    protected void paintComponent(Graphics g) {
        BufferedImage image = null;
        try {
            image = ImageIO.read(new File(pathnameImg));
            image = Imagine.resizeImage(image, CellWidget.CELL_SIZE - 5, CellWidget.CELL_SIZE - 5);
            g.drawImage(image, 0, 0, null);
        } catch (IOException e) {
            e.printStackTrace();
        }

        setImage(image);
    }

    protected void setImage(BufferedImage image) {
        _image = image;
    }
}
