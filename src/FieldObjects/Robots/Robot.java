package FieldObjects.Robots;

import Navigation.Direction;
import Navigation.Field;
import Navigation.Position;
import events.GameOverActionEvent;
import events.GameOverActionListener;
import events.RobotMoveEvent;
import events.RobotMoveListener;

import java.util.ArrayList;

abstract class Robot {
    protected Field _field;

    public abstract void setField(Field field);
    public Field field() {
        return _field;
    }

    public Robot(Field field) {
        _field = field;
    }
    protected Position _position;
    public Position position() {
        return _position;
    }

    public void  setPosition(Position pos) {
        _position = pos;
    }

    protected abstract boolean canMove(Direction dir);

    private final ArrayList<RobotMoveListener> _listeners = new ArrayList<>();
    protected void robotMove(Robot robot) {
        if (robot != null) {
            for (RobotMoveListener obj : _listeners) {
                obj.robotMove(new RobotMoveEvent(robot));
            }
        }
    }

    public void addListener(RobotMoveListener listener) {
        if (!_listeners.contains(listener))
            _listeners.add(listener);
    }

    public void removeListener(RobotMoveListener listener) {
        _listeners.remove(listener);
    }
}
