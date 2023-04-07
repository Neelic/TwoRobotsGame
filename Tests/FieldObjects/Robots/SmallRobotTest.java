package FieldObjects.Robots;

import FieldObjects.Swamp;
import FieldObjects.Wall;
import Navigation.Direction;
import Navigation.Field;
import Navigation.MiddlePosition;
import Navigation.Position;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SmallRobotTest {

    @Test
    void moveTo() {
        Field field = new Field();
        SmallRobot robot = new SmallRobot(field);
        robot.setPosition(new Position(1,1));

        robot.moveTo(Direction.north());

        assertEquals(new Position(2,1), robot.position());

        robot.moveTo(Direction.west());

        assertEquals(new Position(2,1), robot.position());
    }

    @Test
    void moveToSwamp() {
        Field field = new Field();
        SmallRobot robot = new SmallRobot(field);
        robot.setPosition(new Position(1, 1));
        field.addSwamp(new Swamp(new Position(2,1)));

        robot.moveTo(Direction.north());

        assertEquals(new Position(1, 1), robot.position());
    }

    @Test
    void moveToWall() {
        Field field = new Field();
        SmallRobot robot = new SmallRobot(field);
        robot.setPosition(new Position(1, 1));
        field.addWall(new Wall(new MiddlePosition(new Position(1,1), Direction.north())));

        robot.moveTo(Direction.north());

        assertEquals(new Position(1, 1), robot.position());
    }
}