package model.FieldObjects;

import model.FieldObjects.Devices.Pontoon;
import model.FieldObjects.Devices.Trap;
import model.FieldObjects.Robots.BigRobot;
import model.FieldObjects.Robots.SmallRobot;
import model.Navigation.Field;
import model.Navigation.Position;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SwampTest {

    @Test
    void canMoveRobot() {
        var swamp = new Swamp(new Position(1, 1));
        var field = new Field();
        field.addSwamp(swamp);
        var smallRob = new SmallRobot(field);
        var bigRob = new BigRobot(field, 1,1);

        assertFalse(swamp.canMoveRobot(smallRob, null));
        assertTrue(swamp.canMoveRobot(bigRob, null));

        var pontoon = new Pontoon();
        pontoon.setField(field);
        pontoon.putDevice(new Position(1,1));

        assertTrue(swamp.canMoveRobot(smallRob, pontoon));
        assertTrue(swamp.canMoveRobot(bigRob, pontoon));

        field.deleteDevice(pontoon);
        var trap = new Trap();
        trap.setField(field);
        trap.putDevice(new Position(1,1));

        assertFalse(swamp.canMoveRobot(smallRob, trap));
        assertTrue(swamp.canMoveRobot(bigRob, trap));
    }

    @Test
    void testEquals() {
        var swamp_1 = new Swamp(new Position(1,1));
        var swamp_2 = new Swamp(new Position(1,1));
        var swamp_3 = new Swamp(new Position(1,2));

        assertEquals(swamp_1, swamp_2);
        assertNotEquals(swamp_1, swamp_3);
        assertNotEquals(swamp_2, swamp_3);
    }
}