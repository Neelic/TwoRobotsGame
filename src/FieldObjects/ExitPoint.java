package FieldObjects;

import FieldObjects.Robots.SmallRobot;
import Navigation.Field;
import Navigation.Position;
import events.GameOverActionEvent;
import events.GameOverActionListener;

import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class ExitPoint {
    private final Position _pos;
    public ExitPoint(Field field, Position pos) {
        setField(field);
        _pos = pos;
    }

    private Field _field;

    public Field field() {
        return _field;
    }

    public void setField(Field field) {
        if (_field != null && _field != field && field != null)
            _field.setExitPoint(null);

        this._field = field;

        if (field != null && field.exitPoint() != this)
            field.setExitPoint(this);
    }


    public boolean isSmallRobotInExitPoint(SmallRobot robot) {
        if (robot.position().equals(_pos)) {
            _field.setSmallRobot(null);
            smallRobotIsCatch();
            return true;
        }
        return false;
    }

    private final ArrayList<GameOverActionListener> _listeners = new ArrayList<>();
    protected void smallRobotIsCatch() {
        for (GameOverActionListener obj : _listeners) {
            obj.gameOverEvent(new GameOverActionEvent(this));
        }
    }

    public void addListener(GameOverActionListener listener) {
        if (!_listeners.contains(listener))
            _listeners.add(listener);
    }

    public void removeListener(GameOverActionListener listener) {
        _listeners.remove(listener);
    }
}
