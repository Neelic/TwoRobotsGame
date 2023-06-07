package model.FieldObjects.Robots;

import model.FieldObjects.Devices.Pontoon;
import model.FieldObjects.Devices.Teleport;
import model.FieldObjects.Devices.Trap;
import model.FieldObjects.Swamp;
import model.FieldObjects.Wall;
import model.Navigation.Direction;
import model.Navigation.Field;
import model.Navigation.MiddlePosition;
import model.Navigation.Position;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

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

    @Test
    void moveToSwampAndRobotWithPontoon() {
        Field field = new Field();
        SmallRobot robot = new SmallRobot(field);
        robot.setPosition(new Position(1, 1));
        robot.addDevice(new Pontoon(), 1);
        field.addSwamp(new Swamp(new Position(2,1)));

        robot.moveTo(Direction.north());

        assertEquals(new Position(2, 1), robot.position());
    }

    @Test
    void moveToTrap() {
        Field field = new Field();
        SmallRobot robot = new SmallRobot(field);
        robot.setPosition(new Position(1, 1));
        Trap trap = new Trap();
        trap.setField(field);
        trap.putDevice(new Position(2,1));

        robot.moveTo(Direction.north());

        assertEquals(new Position(2, 1), robot.position());
        assertEquals(robot.countMissedMoves, 1);
    }

    @Test
    void moveToTeleportWithoutProperty() {
        Field field = new Field();
        SmallRobot robot = new SmallRobot(field);
        robot.setPosition(new Position(1, 1));
        Teleport teleport = new Teleport();
        teleport.setField(field);
        teleport.putDevice(new Position(2,1));

        robot.moveTo(Direction.north());

        assertEquals(new Position(2, 1), robot.position());
    }

    @Test
    void moveToTeleportWithProperty() {
        Field field = new Field();
        SmallRobot robot = new SmallRobot(field);
        robot.setPosition(new Position(1, 1));
        robot.addDevice(new Teleport(), 1);

        robot.moveTo(Direction.north());

        assertEquals(new Position(2, 10), robot.position());
    }
}