package Navigation.Algorithms;

import FieldObjects.Robots.BigRobot;
import FieldObjects.Robots.SmallRobot;
import FieldObjects.Wall;
import Navigation.Direction;
import Navigation.Field;
import Navigation.MiddlePosition;
import Navigation.Position;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SmartAlgorithmTest {

    private final Field field = new Field();
    private final SmallRobot smallRobot = new SmallRobot(field);
    private final BigRobot bigRobot = new BigRobot(field,3,3);
    @Test
    void getNextDirectionHorizontal() {
        smallRobot.setPosition(new Position(1,1));
        bigRobot.setPosition(new Position(1,2));
        bigRobot.setSmartAlgorithm(new SmartAlgorithm(field));
        bigRobot.moveTo();

        assertEquals(new Position(1, 1), bigRobot.position());
    }

    @Test
    void getNextDirectionVertical() {
        smallRobot.setPosition(new Position(1,1));
        bigRobot.setPosition(new Position(2,1));
        bigRobot.setSmartAlgorithm(new SmartAlgorithm(field));
        bigRobot.moveTo();

        assertEquals(new Position(1, 1), bigRobot.position());
    }

    @Test
    void getNextDirectionVerticalWithWall() {
        smallRobot.setPosition(new Position(1,1));
        bigRobot.setPosition(new Position(2,1));
        bigRobot.setSmartAlgorithm(new SmartAlgorithm(field));
        field.addWall(new Wall(new MiddlePosition(new Position(1,1), Direction.north())));
        bigRobot.moveTo();

        assertEquals(new Position(2, 2), bigRobot.position());
    }

    @Test
    void getNextDirectionWithoutSmallRobotPos() {
        smallRobot.setPosition(null);
        bigRobot.setPosition(new Position(10,2));
        bigRobot.setSmartAlgorithm(new SmartAlgorithm(field));
        bigRobot.moveTo();

        assertEquals(new Position(10, 2), bigRobot.position());
    }

    @Test
    void getNextDirectionWithoutSmallRobot() {
        field.setSmallRobot(null);
        bigRobot.setPosition(new Position(10,2));
        bigRobot.setSmartAlgorithm(new SmartAlgorithm(field));
        bigRobot.moveTo();

        assertEquals(new Position(10, 2), bigRobot.position());
    }
}