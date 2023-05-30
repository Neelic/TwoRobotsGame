import model.Game;
import ui.Navigation.FieldWidget;
import ui.WidgetFactory;

import javax.swing.*;
import java.awt.*;

public class Main {
    private static Game _game;
    public static void main(String[] args) {
        _game = new Game();
        _game.startGame();
        SwingUtilities.invokeLater(GamePanel::new);
    }

    static class GamePanel extends JFrame {
        private final WidgetFactory _widgetFactory;

        public GamePanel() throws HeadlessException {
            setVisible(true);

            _widgetFactory = new WidgetFactory();

            JPanel content = (JPanel) this.getContentPane();
            content.add(new FieldWidget(_game.field(), _widgetFactory));

            pack();
            setResizable(false);
            setDefaultCloseOperation(EXIT_ON_CLOSE);
        }
    }
}
