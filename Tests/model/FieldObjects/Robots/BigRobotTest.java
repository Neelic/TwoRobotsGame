package model.FieldObjects.Robots;

import model.FieldObjects.Devices.Pontoon;
import model.FieldObjects.Devices.Teleport;
import model.FieldObjects.Swamp;
import model.Navigation.Algorithms.SillyAlgorithm;
import model.Navigation.Field;
import model.Navigation.Position;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
    void moveToSwampWithPontoon() {
        smallRobot.setPosition(new Position(1,1));
        bigRobot.setPosition(new Position(3,1));
        bigRobot.setSillyAlgorithm(new SillyAlgorithm(field));
        bigRobot.addDevice(new Pontoon(), 1);
        bigRobot.setPutDeviceChance(100);
        field.addSwamp(new Swamp(new Position(2,1)));
        bigRobot.moveTo();

        assertEquals(new Position(2,1), bigRobot.position());
        assertEquals(0, bigRobot.countMissedMoves);
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
        assertEquals(2, bigRobot.countMissedMoves);

        bigRobot.setCountMissedMoves(0);
    }

    @Test
    void moveToTeleportWithProperty() {
        smallRobot.setPosition(new Position(1,1));
        bigRobot.setPosition(new Position(3,1));
        bigRobot.setSillyAlgorithm(new SillyAlgorithm(field));
        bigRobot.addDevice(new Teleport(), 1);
        bigRobot.setPutDeviceChance(100);
        bigRobot.moveTo();

        assertEquals(new Position(2,10), bigRobot.position());
    }

    @Test
    void moveToTeleportWithoutProperty() {
        smallRobot.setPosition(new Position(1,1));
        bigRobot.setPosition(new Position(3,1));
        bigRobot.setSillyAlgorithm(new SillyAlgorithm(field));
        Teleport teleport = new Teleport();
        teleport.setField(field);
        teleport.putDevice(new Position(2,1));
        bigRobot.moveTo();

        assertEquals(new Position(2,1), bigRobot.position());

        field.deleteDevice(teleport);
    }
}