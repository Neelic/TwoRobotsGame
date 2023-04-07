import FieldObjects.ExitPoint;
import FieldObjects.Robots.BigRobot;
import FieldObjects.Robots.SmallRobot;
import FieldObjects.Swamp;
import FieldObjects.Wall;
import Navigation.Algorithms.SillyAlgorithm;
import Navigation.Algorithms.SmartAlgorithm;
import Navigation.Direction;
import Navigation.Field;
import Navigation.MiddlePosition;
import Navigation.Position;

import java.util.ArrayList;
import java.util.List;

public class Labyrinth {
    private Field _field;

    public Labyrinth(Field field) {
        _field = field;
    }

    private final ArrayList<Wall> _insertWalls = new ArrayList<>(
            List.<Wall>of(
                    new Wall(new MiddlePosition(new Position(1, 2), Direction.east())),
                    new Wall(new MiddlePosition(new Position(3, 4), Direction.south())),
                    new Wall(new MiddlePosition(new Position(1,1), Direction.south())),
                    new Wall(new MiddlePosition(new Position(1,1), Direction.west())),
                    new Wall(new MiddlePosition(new Position(10,10), Direction.north())),
                    new Wall(new MiddlePosition(new Position(10,10), Direction.east()))
            )
    );

    private final ArrayList<Swamp> _insertSwamps = new ArrayList<>(
            List.<Swamp>of(
                    new Swamp(new Position(3, 3)),
                    new Swamp(new Position(4, 4)),
                    new Swamp(new Position(10, 10)),
                    new Swamp(new Position(1,1)),
                    new Swamp(new Position(1,2))
            )
    );

    public void createFieldObjects() {
        for (Wall obj : _insertWalls) {
            _field.addWall(obj);
        }

        for (Swamp obj : _insertSwamps) {
            _field.addSwamp(obj);
        }

        BigRobot robot = new BigRobot(_field, 3, 3);
        robot.setSillyAlgorithm(new SillyAlgorithm(_field));
        robot.setSmartAlgorithm(new SmartAlgorithm(_field));
        _field.setBigRobot(robot);

        SmallRobot robotS = new SmallRobot(_field);
        robotS.setPosition(new Position(1,1));
        _field.setSmallRobot(robotS);

        ExitPoint point = new ExitPoint(_field, new Position(10,10));
        _field.setExitPoint(point);

        _field = null;
    }
}
