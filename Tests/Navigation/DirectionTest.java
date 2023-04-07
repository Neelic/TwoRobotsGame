package Navigation;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DirectionTest {

    @Test
    void clockwise() {
        Direction dir = Direction.north();

        Direction dirOther = dir.clockwise();
        assertEquals(Direction.east(), dirOther);
        assertEquals(Direction.north(), dir);

        dir = dirOther.clockwise();
        assertEquals(Direction.south(), dir);
        assertEquals(Direction.east(), dirOther);

        dirOther = dir.clockwise();
        assertEquals(Direction.west(), dirOther);
        assertEquals(Direction.south(), dir);

        dir = dirOther.clockwise();
        assertEquals(Direction.north(), dir);
        assertEquals(Direction.west(), dirOther);
    }

    @Test
    void anticlockwise() {
        Direction dir = Direction.north();

        dir = dir.anticlockwise();
        assertEquals(Direction.west(), dir);

        dir = dir.anticlockwise();
        assertEquals(Direction.south(), dir);

        dir = dir.anticlockwise();
        assertEquals(Direction.east(), dir);

        dir = dir.anticlockwise();
        assertEquals(Direction.north(), dir);
    }

    @Test
    void opposite() {
        Direction dir = Direction.north();

        dir = dir.opposite();
        assertEquals(Direction.south(), dir);

        dir = dir.opposite();
        assertEquals(Direction.north(), dir);
    }
}