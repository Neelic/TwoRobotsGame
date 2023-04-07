import FieldObjects.ExitPoint;
import Navigation.Direction;
import Navigation.Field;
import events.GameOverActionEvent;
import events.GameOverActionListener;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.concurrent.CountDownLatch;

public class Game implements GameOverActionListener, KeyListener {
    private boolean _isPlayerWin = false;
    private Field field;
    public void startGame() throws InterruptedException {
        field = new Field();
        Labyrinth labyrinth = new Labyrinth(field);
        labyrinth.createFieldObjects();
        boolean waitingToSet = true;

        field.exitPoint().addListener(this);
        field.bigRobot().addListener(this);

        while (field.smallRobot() != null) {
            waitForMoveButton();
            field.bigRobot().moveTo();
        }
    }

    public void moveSmallRobot(Direction dir) {
    }

    public void endGame() {
    }

    //TODO переделать ожидание кнопки
    private void waitForMoveButton() throws InterruptedException {
        final CountDownLatch latch = new CountDownLatch(1);
        KeyEventDispatcher dispatcher = new KeyEventDispatcher() {
            // Anonymous class invoked from EDT
            public boolean dispatchKeyEvent(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_W || e.getKeyCode() == KeyEvent.VK_S
                        || e.getKeyCode() == KeyEvent.VK_A || e.getKeyCode() == KeyEvent.VK_D)
                    latch.countDown();
                return false;
            }
        };
        KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(dispatcher);
        latch.await();  // current thread waits here until countDown() is called
        KeyboardFocusManager.getCurrentKeyboardFocusManager().removeKeyEventDispatcher(dispatcher);
    }

    @Override
    public void gameOverEvent(GameOverActionEvent event) {
        if (event.getSource() instanceof ExitPoint)
            _isPlayerWin = true;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {}

    @Override
    public void keyReleased(KeyEvent e) {}
}
