package model.Navigation.Algorithms;

import model.FieldObjects.Robots.BigRobot;
import model.Navigation.Direction;
import model.Navigation.Field;
import model.Navigation.MiddlePosition;
import model.Navigation.Position;

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
    public Direction getNextDirection(Position targetPosition, Position currentPosition) {
        if (!targetPosition.isValid())
            return null;

        Direction resultDir = null;
        if (targetPosition.col() < currentPosition.col()) {
            resultDir = Direction.west();
        } else if (targetPosition.col() > currentPosition.col()) {
            resultDir = Direction.east();
        } else if (targetPosition.row() < currentPosition.row()) {
            resultDir = Direction.south();
        } else if (targetPosition.row() > currentPosition.row()) {
            resultDir = Direction.north();
        }

        int i = 0;
        boolean canMove = _field.wall(new MiddlePosition(currentPosition, resultDir)) == null
                && currentPosition.hasNextPosition(resultDir);

        while (resultDir != null && i < 4 && !canMove) {
            resultDir = resultDir.clockwise();
            if (currentPosition.hasNextPosition(resultDir)
                    && _field.wall(new MiddlePosition(currentPosition, resultDir)) == null)
                canMove = true;
            i++;
        }

        if (canMove)
            return resultDir;
        else
            return null;
    }
}
