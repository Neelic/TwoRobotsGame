package model.Navigation;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PositionRangeTest {

    @Test
    void length() {
        PositionRange range = new PositionRange(1,5);
        assertEquals(5, range.length());
    }

    @Test
    void isValidRange() {
        assertTrue(PositionRange.isValidRange(2,4));
        assertFalse(PositionRange.isValidRange(4,2));
    }

    @Test
    void contains() {
        PositionRange range = new PositionRange(1,5);

        assertTrue(range.contains(4));
        assertTrue(range.contains(1));
        assertTrue(range.contains(5));

        assertFalse(range.contains(6));
        assertFalse(range.contains(0));
    }
}