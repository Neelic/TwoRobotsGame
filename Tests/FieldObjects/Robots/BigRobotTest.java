package FieldObjects.Robots;

import FieldObjects.Swamp;
import Navigation.Algorithms.SillyAlgorithm;
import Navigation.Algorithms.SmartAlgorithm;
import Navigation.Field;
import Navigation.Position;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BigRobotTest {
    Field field = new Field();
    BigRobot bigRobot = new BigRobot(field, 3,3);
    SmallRobot smallRobot = new SmallRobot(field);

    @Test
    void moveTo() {
        smallRobot.setPosition(new Position(1,1));
        bigRobot.setPosition(new Position(3,1));
        bigRobot.setSillyAlgorithm(new SillyAlgorithm(field));
        bigRobot.moveTo();

        assertEquals(new Position(2,1), bigRobot.position());
    }

    @Test
    void moveToSwamp() {
        smallRobot.setPosition(new Position(1,1));
        bigRobot.setPosition(new Position(3,1));
        bigRobot.setSillyAlgorithm(new SillyAlgorithm(field));
        field.addSwamp(new Swamp(new Position(2,1)));
        bigRobot.moveTo();
        bigRobot.moveTo();

        assertEquals(new Position(2,1), bigRobot.position());
    }
}