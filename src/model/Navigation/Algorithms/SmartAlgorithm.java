package model.Navigation.Algorithms;

import model.FieldObjects.Robots.BigRobot;
import model.Navigation.Direction;
import model.Navigation.Field;
import model.Navigation.MiddlePosition;
import model.Navigation.Position;

import java.util.ArrayList;
import java.util.Arrays;

//Использует Алгоритм Ли, чтобы найти ближайший путь к маленькому роботу
public class SmartAlgorithm implements BehaviorAlgorithm {

    private final Field _field;
    private final int[][] map;
    private final ArrayList<Position> _path = new ArrayList<>();

    /**
     * Использует Алгоритм Ли, чтобы найти ближайший путь к маленькому роботу
     * @param field
     */
    public SmartAlgorithm(Field field) {
        _field = field;
        map = new int[field.height()][field.width()];
        //clearPath();
    }
    @Override
    public Direction getNextDirection(Position targetPosition, Position currentPosition) {
        boolean isHasPath = fillMap(currentPosition);
        
        if (!isHasPath)
            return null;
        
        if (_path.size() > 0) {
            boolean hasNext = false;
            int i = 0;

            for (;i < _path.size() && !hasNext; i++) {
                if (_path.get(i).equals(currentPosition))
                    hasNext = true;
            }
            i--;
            if (hasNext && i > 0) {
                if (currentPosition.row() != _field.height() && _path.get(i - 1).equals(
                        new Position(currentPosition.row() + 1, currentPosition.col())))
                    return Direction.north();
                else if (currentPosition.row() != 1 && _path.get(i - 1).equals(
                        new Position(currentPosition.row() - 1, currentPosition.col())))
                    return Direction.south();
                else if (currentPosition.col()!= _field.width() && _path.get(i - 1).equals(
                        new Position(currentPosition.row(), currentPosition.col() + 1)))
                    return Direction.east();
                else if (currentPosition.col() != 1 && _path.get(i - 1).equals(
                        new Position(currentPosition.row(), currentPosition.col() - 1)))
                    return Direction.west();
            } else {
                //clearPath();
            }
        }
        return null;
    }

    private boolean fillMap(Position currentCell) {
        clearPath();
        map[currentCell.row() - 1][currentCell.col() - 1] = 0;
        for (int k = 0; k < Math.max(_field.height(), _field.width()); k++) {
            for (int i = 0; i < _field.height(); i++) {
                for (int j = 0; j < _field.width(); j++) {
                    createWave(new Position(i + 1, j + 1));
                }
            }
        }
        return createPath();
    }

    private boolean createPath() {
        if(_field.smallRobot() != null) {
            Position pos = _field.smallRobot().position();

            while (map[pos.row() - 1][pos.col() - 1] != 0) {
                _path.add(pos);

                if (pos.row() != 1 && canNext(pos, Direction.south()) &&
                        map[pos.row() - 2][pos.col() - 1] < map[pos.row() - 1][pos.col() - 1]) {
                    pos = new Position(pos.row() - 1, pos.col());
                } else if (pos.col() != 1 && canNext(pos, Direction.west()) &&
                        map[pos.row() - 1][pos.col() - 2] < map[pos.row() - 1][pos.col() - 1]) {
                    pos = new Position(pos.row(), pos.col() - 1);
                } else if (pos.row() != _field.height() && canNext(pos, Direction.north()) &&
                        map[pos.row()][pos.col() - 1] < map[pos.row() - 1][pos.col() - 1]) {
                    pos = new Position(pos.row() + 1, pos.col());
                } else if (pos.col() != _field.width() && canNext(pos, Direction.east()) &&
                        map[pos.row() - 1][pos.col()] < map[pos.row() - 1][pos.col() - 1]) {
                    pos = new Position(pos.row(), pos.col() + 1);
                }
            }

            if (map[pos.row() - 1][pos.col() - 1] == 0) {
                _path.add(pos);
                return true;
            }
        }
        return false;
    }

    private void createWave(Position cell) {
        int row = cell.row();
        int column = cell.col();
        if (isValid(cell) && map[row - 1][column - 1] != -1) {
            int currentCost = map[row - 1][column - 1];
            if (canNext(cell, Direction.south()) && map[row - 2][column - 1] == -1) {
                map[row - 2][column - 1] = currentCost + 1;
            }
            if (canNext(cell, Direction.north()) && map[row][column - 1] == -1) {
                map[row][column - 1] = currentCost + 1;
            }
            if (canNext(cell, Direction.west()) && map[row - 1][column - 2] == -1) {
                map[row - 1][column - 2] = currentCost + 1;
            }
            if (canNext(cell, Direction.east()) && map[row - 1][column] == -1) {
                map[row - 1][column] = currentCost + 1;
            }
        }
    }

    private void clearPath() {
        _path.clear();

        for (int i = 0; i < _field.height(); i++) {
            Arrays.fill(map[i], -1);
        }
    }

    private boolean canNext(Position cell, Direction dir) {
        return cell.hasNextPosition(dir) && _field.wall(new MiddlePosition(cell, dir)) == null;
    }

    private boolean isValid(Position cell) {
        int row = cell.row();
        int col = cell.col();
        return !((row != 10 && map[row][col - 1] != -1) && (row != 1 && map[row - 2][col - 1] != -1) &&
                (col != 10 && map[row - 1][col] != -1) && (col != 1 && map[row - 1][col - 2] != -1));
    }

    private BigRobot _robot;

    public void setBigRobot(BigRobot robot) {
        if (_robot != null && _robot != robot)
            _robot.setSmartAlgorithm(null);

        _robot = robot;

        if (robot != null && robot.smartAlgorithm() != this)
            _robot.setSmartAlgorithm(this);
    }

    public BigRobot bigRobot() {
        return _robot;
    }
}
