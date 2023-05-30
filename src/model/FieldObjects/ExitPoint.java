package model.FieldObjects;

import model.FieldObjects.Robots.SmallRobot;
import model.Navigation.Field;
import model.Navigation.Position;
import model.Events.GameOverActionEvent;
import model.Events.GameOverActionListener;
import model.Events.RobotMoveEvent;
import model.Events.RobotMoveListener;

import java.util.ArrayList;

public class ExitPoint {
    private final Position _pos;

    public Position position() {
        return _pos;
    }
    public ExitPoint(Field field, Position pos) {
        setField(field);
        _pos = pos;

        if (field.smallRobot() != null) {
            subscribeToSmallRobot(field.smallRobot());
        }
    }

    public void subscribeToSmallRobot(SmallRobot robot) {
        if (robot != null) {
            robot.addListener(listener);
        }
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


    public void isSmallRobotInExitPoint(SmallRobot robot) {
        if (robot.position().equals(_pos)) {
            smallRobotIsCatch();
        }
    }

    private final ArrayList<GameOverActionListener> _listeners = new ArrayList<>();
    protected void smallRobotIsCatch() {
        for (GameOverActionListener obj : _listeners) {
            obj.gameOverEvent(new GameOverActionEvent(this, _field.smallRobot()));
        }
    }

    public void addListener(GameOverActionListener listener) {
        if (!_listeners.contains(listener))
            _listeners.add(0, listener);
    }

    public void removeListener(GameOverActionListener listener) {
        _listeners.remove(listener);
    }

    private final RobotMoveListener listener = new RobotMoveList();

    private class RobotMoveList implements RobotMoveListener {
        @Override
        public void robotMove(RobotMoveEvent event) {
            if (event.getSource() instanceof SmallRobot) {
                isSmallRobotInExitPoint((SmallRobot) event.getSource());
            }
        }
    }
}
