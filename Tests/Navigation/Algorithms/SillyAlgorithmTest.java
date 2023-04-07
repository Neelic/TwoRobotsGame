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

class SillyAlgorithmTest {
    private final Field field = new Field();
    private final SmallRobot smallRobot = new SmallRobot(field);
    private final BigRobot bigRobot = new BigRobot(field,3,3);
    @Test
    void getNextDirectionHorizontal() {
        smallRobot.setPosition(new Position(1,1));
        bigRobot.setPosition(new Position(10,3));
        bigRobot.setSillyAlgorithm(new SillyAlgorithm(field));
        bigRobot.moveTo();

        assertEquals(new Position(10, 2), bigRobot.position());
    }

    @Test
    void getNextDirectionVertical() {
        smallRobot.setPosition(new Position(1,1));
        bigRobot.setPosition(new Position(10,1));
        bigRobot.setSillyAlgorithm(new SillyAlgorithm(field));
        bigRobot.moveTo();

        assertEquals(new Position(9, 1), bigRobot.position());
    }

    @Test
    void getNextDirectionVerticalWithWall() {
        smallRobot.setPosition(new Position(1,1));
        bigRobot.setPosition(new Position(10,1));
        bigRobot.setSillyAlgorithm(new SillyAlgorithm(field));
        field.addWall(new Wall(new MiddlePosition(new Position(10,1), Direction.south())));
        bigRobot.moveTo();

        assertEquals(new Position(10, 2), bigRobot.position());
    }

    @Test
    void getNextDirectionWithoutSmallRobotPos() {
        smallRobot.setPosition(null);
        bigRobot.setPosition(new Position(10,2));
        bigRobot.setSillyAlgorithm(new SillyAlgorithm(field));
        bigRobot.moveTo();

        assertEquals(new Position(10, 2), bigRobot.position());
    }

    @Test
    void getNextDirectionWithoutSmallRobot() {
        field.setSmallRobot(null);
        bigRobot.setPosition(new Position(10,2));
        bigRobot.setSillyAlgorithm(new SillyAlgorithm(field));
        bigRobot.moveTo();

        assertEquals(new Position(10, 2), bigRobot.position());
    }

    @Test
    void  bigRobotCantMove() {
        smallRobot.setPosition(new Position(1,1));
        bigRobot.setPosition(new Position(10,1));
        bigRobot.setSillyAlgorithm(new SillyAlgorithm(field));
        field.addWall(new Wall(new MiddlePosition(new Position(10,1), Direction.south())));
        field.addWall(new Wall(new MiddlePosition(new Position(10,1), Direction.north())));
        field.addWall(new Wall(new MiddlePosition(new Position(10,1), Direction.east())));
        field.addWall(new Wall(new MiddlePosition(new Position(10,1), Direction.west())));
        bigRobot.moveTo();

        assertEquals(new Position(10, 1), bigRobot.position());
    }
}