package Navigation;

import FieldObjects.ExitPoint;
import FieldObjects.Robots.BigRobot;
import FieldObjects.Robots.SmallRobot;
import FieldObjects.Swamp;
import FieldObjects.Wall;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FieldTest {

    @Test
    void setSmallRobot() {
        Field field = new Field();
        SmallRobot sRobot = new SmallRobot(field);

        assertEquals(sRobot,field.smallRobot());

        field.setSmallRobot(null);

        assertNull(field.smallRobot());
        assertNull(sRobot.field());

        field.setSmallRobot(sRobot);

        assertEquals(sRobot.field(),field);
    }

    @Test
    void setBigRobot() {
        Field field = new Field();
        BigRobot robot = new BigRobot(field,3,3);

        assertEquals(robot,field.bigRobot());

        field.setBigRobot(null);

        assertNull(field.bigRobot());
        assertNull(robot.field());

        field.setBigRobot(robot);

        assertEquals(field, robot.field());
    }

    @Test
    void addSwamp() {
        Field field = new Field();
        Swamp swamp = new Swamp(new Position(1, 1));

        field.addSwamp(swamp);

        assertEquals(swamp, field.swamp(new Position(1,1)));

        boolean isErrorMessage = false;
        try {
            field.addSwamp(new Swamp(new Position(1,1)));
        } catch (RuntimeException ignored) {
            if ("Can't add object".equals(ignored.getMessage()))
                isErrorMessage = true;
        }

        assertTrue(isErrorMessage);
    }

    @Test
    void addWall() {
        Field field = new Field();
        Wall wall = new Wall(new MiddlePosition(new Position(1,1), Direction.west()));
        
        field.addWall(wall);
        
        assertEquals(wall, field.wall(new MiddlePosition(new Position(1,1), Direction.west())));
        
        boolean isErrorMessage = false;
        try {
            field.addWall(new Wall(new MiddlePosition(new Position(1,1), Direction.west())));
        } catch (RuntimeException ignored) {
            if ("Can't add object".equals(ignored.getMessage()))
                isErrorMessage = true;
        }

        assertTrue(isErrorMessage);
    }

    @Test
    void setExitPoint() {
        Field field = new Field();
        ExitPoint point = new ExitPoint(field, new Position(2,2));

        assertEquals(field, point.field());

        field.setExitPoint(null);

        assertNull(field.exitPoint());
        assertNull(point.field());
    }
}