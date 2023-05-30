package ui;

import javax.swing.*;
import java.awt.*;

public class EndGameWindowWidget {
    public static void playerWinWindow() {
        JFrame window = new JFrame();
        window.setPreferredSize(new Dimension(120, 60));
        JOptionPane.showMessageDialog(window, "You win!");
    }

    public static void playerLoseWindow() {
        JFrame window = new JFrame();
        window.setPreferredSize(new Dimension(120, 60));
        JOptionPane.showMessageDialog(window, "You lose!");
    }
}
