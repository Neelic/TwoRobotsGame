package model.Navigation;

import static org.junit.jupiter.api.Assertions.*;

class PositionTest {

    @org.junit.jupiter.api.Test
    void nextPosition() {
        Position pos = new Position(1, 1);

        pos = pos.nextPosition(Direction.north());
        assertEquals(2, pos.row());
        assertEquals(1, pos.col());

        pos = pos.nextPosition(Direction.south());
        assertEquals(1, pos.row());
        assertEquals(1, pos.col());

        pos = pos.nextPosition(Direction.east());
        assertEquals(1, pos.row());
        assertEquals(2, pos.col());

        pos = pos.nextPosition(Direction.west());
        assertEquals(1, pos.row());
        assertEquals(1, pos.col());
    }
    @org.junit.jupiter.api.Test
    void nextPositionNotValid() {
        Position pos = new Position(10, 1);
        try {
            pos.nextPosition(Direction.north());
        } catch (RuntimeException ex) {
            assertEquals("Position not create", ex.getMessage());
        }

        pos = new Position(1,1);
        try {
            pos.nextPosition(Direction.south());
        } catch (RuntimeException ex) {
            assertEquals("Position not create", ex.getMessage());
        }

        //pos = new Position(1,1);
        try {
            pos.nextPosition(Direction.west());
        } catch (RuntimeException ex) {
            assertEquals("Position not create", ex.getMessage());
        }

        pos = new Position(1,10);
        try {
            pos.nextPosition(Direction.east());
        } catch (RuntimeException ex) {
            assertEquals("Position not create", ex.getMessage());
        }
    }
}