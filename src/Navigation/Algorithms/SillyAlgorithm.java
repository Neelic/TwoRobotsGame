package Navigation.Algorithms;

import FieldObjects.Robots.BigRobot;
import Navigation.Direction;
import Navigation.Field;
import Navigation.MiddlePosition;
import Navigation.Position;

public class SillyAlgorithm implements BehaviorAlgorithm {
    private final Field _field;

    public SillyAlgorithm(Field field) {
        _field = field;
    }
    private BigRobot _robot;

    public void setBigRobot(BigRobot robot) {
        if (_robot != null && _robot != robot)
            _robot.setSillyAlgorithm(null);

        _robot = robot;

        if (robot != null && robot.sillyAlgorithm() != this)
            _robot.setSillyAlgorithm(this);
    }

    public BigRobot bigRobot() {
        return _robot;
    }
    @Override
    public Direction getNextDirection(Position pos) {
        if (!pos.isValid())
            return null;

        Direction resultDir = null;
        if (pos.col() < _robot.position().col()) {
            resultDir = Direction.west();
        } else if (pos.col() > _robot.position().col()) {
            resultDir = Direction.east();
        } else if (pos.row() < _robot.position().row()) {
            resultDir = Direction.south();
        } else if (pos.row() > _robot.position().row()) {
            resultDir = Direction.north();
        }

        int i = 0;
        boolean canMove = _field.wall(new MiddlePosition(_robot.position(), resultDir)) == null
                && _robot.position().hasNextPosition(resultDir);

        while (resultDir != null && i < 4 && !canMove) {
            resultDir = resultDir.clockwise();
            if (_robot.position().hasNextPosition(resultDir)
                    && _field.wall(new MiddlePosition(_robot.position(), resultDir)) == null)
                canMove = true;
            i++;
        }

        if (canMove)
            return resultDir;
        else
            return null;
    }
}
