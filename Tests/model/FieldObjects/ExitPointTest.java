package model.FieldObjects;

import model.FieldObjects.Robots.SmallRobot;
import model.Navigation.Direction;
import model.Navigation.Field;
import model.Navigation.Position;
import model.Events.GameOverActionEvent;
import model.Events.GameOverActionListener;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ExitPointTest {
    private boolean isCorrect = false;
    private class Listener implements GameOverActionListener {
        @Override
        public void gameOverEvent(GameOverActionEvent event) {
            isCorrect = true;
        }
    }
    @Test
    void isSmallRobotInExitPoint() {
        isCorrect = false;
        var field = new Field();
        var robot = new SmallRobot(field);
        var point = new ExitPoint(field, new Position(2,2));
        var listener = new Listener();
        point.addListener(listener);

        robot.setPosition(new Position(2,1));
        robot.moveTo(Direction.east());

        assertTrue(isCorrect);
    }
}