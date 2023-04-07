import FieldObjects.Swamp;
import FieldObjects.Wall;
import Navigation.Direction;
import Navigation.Field;
import Navigation.MiddlePosition;
import Navigation.Position;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LabyrinthTest {
    ArrayList<Wall> expectedWalls = new ArrayList<>(
            List.<Wall>of(
                    new Wall(new MiddlePosition(new Position(1, 2), Direction.east())),
                    new Wall(new MiddlePosition(new Position(3, 4), Direction.south())),
                    new Wall(new MiddlePosition(new Position(1,1), Direction.south())),
                    new Wall(new MiddlePosition(new Position(1,1), Direction.west())),
                    new Wall(new MiddlePosition(new Position(10,10), Direction.north())),
                    new Wall(new MiddlePosition(new Position(10,10), Direction.east()))
            )
    );

    ArrayList<Swamp> expectedSwamps = new ArrayList<>(
            List.<Swamp>of(
                    new Swamp(new Position(3, 3)),
                    new Swamp(new Position(4, 4)),
                    new Swamp(new Position(10, 10)),
                    new Swamp(new Position(1,1)),
                    new Swamp(new Position(1,2))
            )
    );

    @Test
    void createFieldObjects() {
        Field field = new Field();
        Labyrinth labyrinth = new Labyrinth(field);

        labyrinth.createFieldObjects();

        assertArrayEquals(expectedSwamps.toArray(), field.swamps().toArray());
        assertArrayEquals(expectedWalls.toArray(), field.walls().toArray());
    }
}