package model.FieldObjects.Devices;

import model.FieldObjects.Robots.BigRobot;
import model.FieldObjects.Robots.SmallRobot;
import model.FieldObjects.Swamp;
import model.Navigation.Field;
import model.Navigation.Position;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TeleportTest {

    @Test
    void canAddToRobot() {
        Teleport teleport = new Teleport();
        assertTrue(teleport.canAddToRobot(new SmallRobot(new Field())));
        assertTrue(teleport.canAddToRobot(new BigRobot(new Field(), 1,1)));
    }

    @Test
    void canReplaceOnCommonPositionNotBorder() {
        Teleport teleport = new Teleport();
        Field field = new Field();
        teleport.setField(field);

        teleport.putDevice(new Position(3,3));
        assertNull(field.deviceByPosition(new Position(3, 3)));
    }

    @Test
    void canReplaceOnCommonPosition() {
        Teleport teleport = new Teleport();
        Field field = new Field();
        teleport.setField(field);

        teleport.putDevice(new Position(1,1));
        assertEquals(teleport, field.deviceByPosition(new Position(1,1)));
    }

    @Test
    void canReplaceOnSwamp() {
        Teleport teleport = new Teleport();
        Field field = new Field();
        field.addSwamp(new Swamp(new Position(1,1)));
        teleport.setField(field);

        teleport.putDevice(new Position(1,1));
        assertEquals(teleport, field.deviceByPosition(new Position(1,1)));
    }
}