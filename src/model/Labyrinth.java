package model;

import model.FieldObjects.Devices.Pontoon;
import model.FieldObjects.Devices.Teleport;
import model.FieldObjects.Devices.Trap;
import model.FieldObjects.ExitPoint;
import model.FieldObjects.Robots.BigRobot;
import model.FieldObjects.Robots.SmallRobot;
import model.FieldObjects.Swamp;
import model.FieldObjects.Wall;
import model.Navigation.Algorithms.SillyAlgorithm;
import model.Navigation.Algorithms.SmartAlgorithm;
import model.Navigation.Direction;
import model.Navigation.Field;
import model.Navigation.MiddlePosition;
import model.Navigation.Position;

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
                    new Wall(new MiddlePosition(new Position(1,1), Direction.west()))
            )
    );

    private final ArrayList<Swamp> _insertSwamps = new ArrayList<>(
            List.<Swamp>of(
                    new Swamp(new Position(3, 3)),
                    new Swamp(new Position(4, 4)),
                    new Swamp(new Position(10, 9)),
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

        BigRobot robotB = new BigRobot(_field, 3, 3);
        robotB.setSillyAlgorithm(new SillyAlgorithm(_field));
        robotB.setSmartAlgorithm(new SmartAlgorithm(_field));
        robotB.setPosition(new Position(5,5));
        _field.setBigRobot(robotB);

        SmallRobot robotS = new SmallRobot(_field);
        robotS.setPosition(new Position(2,2));
        _field.setSmallRobot(robotS);

        ExitPoint point = new ExitPoint(_field, new Position(10,10));
        _field.setExitPoint(point);

        robotS.addDevice(new Pontoon(), 5);
        robotS.addDevice(new Teleport(), 1);

        robotB.addDevice(new Pontoon(), 5);
        robotB.addDevice(new Trap(), 3);
        robotB.addDevice(new Teleport(), 1);

        _field = null;
    }
}
