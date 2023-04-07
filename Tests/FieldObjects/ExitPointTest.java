package FieldObjects;

import FieldObjects.Robots.SmallRobot;
import Navigation.Direction;
import Navigation.Field;
import Navigation.Position;
import events.GameOverActionEvent;
import events.GameOverActionListener;
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
        Field field = new Field();
        ExitPoint point = new ExitPoint(field, new Position(2,2));
        SmallRobot robot = new SmallRobot(field);
        Listener listener = new Listener();
        point.addListener(listener);

        robot.setPosition(new Position(2,1));
        robot.moveTo(Direction.east());

        assertTrue(isCorrect);
    }
}