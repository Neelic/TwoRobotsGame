package FieldObjects.Robots;

import Navigation.*;
import Navigation.Algorithms.SillyAlgorithm;
import Navigation.Algorithms.SmartAlgorithm;
import events.GameOverActionEvent;
import events.GameOverActionListener;

import java.util.ArrayList;

public class BigRobot extends Robot {

    private SillyAlgorithm _sillyAlgorithm;
    public void setSillyAlgorithm(SillyAlgorithm alg) {
        if (_sillyAlgorithm != null && _sillyAlgorithm != alg && alg != null)
            _sillyAlgorithm.setBigRobot(null);

        _sillyAlgorithm = alg;

        if (alg != null && alg.bigRobot() != this)
            _sillyAlgorithm.setBigRobot(this);
    }
    public SillyAlgorithm sillyAlgorithm() {
        return _sillyAlgorithm;
    }
    private SmartAlgorithm _smartAlgorithm;
    public void setSmartAlgorithm(SmartAlgorithm alg) {
        if (_smartAlgorithm != null && _smartAlgorithm != alg && alg != null)
            _smartAlgorithm.setBigRobot(null);

        _smartAlgorithm = alg;

        if (alg != null && alg.bigRobot() != this)
            _smartAlgorithm.setBigRobot(this);
    }
    public SmartAlgorithm smartAlgorithm() {
        return _smartAlgorithm;
    }
    private final int _zoneHeight;
    private final int _zoneWidth;
    private int _countMissedMoves = 0;
    public BigRobot(Field field, int zoneHeight, int zoneWidth) {
        super(field);
        if (field != null)
            field.setBigRobot(this);
        _zoneHeight = zoneHeight;
        _zoneWidth = zoneWidth;
    }

    public boolean moveTo() {
        Direction dir = null;
        if (_countMissedMoves == 0) {
            SmallRobot target = field().smallRobot();

            if (target != null && target.position() != null) {
                if (targetInZone(target.position()))
                    dir = _smartAlgorithm.getNextDirection(target.position());
                else
                    dir = _sillyAlgorithm.getNextDirection(target.position());
            }

            if (dir != null && canMove(dir)) {
                _position = _position.nextPosition(dir);
            }
            //Если на текущей позиции есть болото, установить счетчик
            if (field().swamp(position()) != null)
                _countMissedMoves = 3;

            catchSmallRobot(target);
            return true;
        } else
            _countMissedMoves-=1;

        return false;
    }

    private void catchSmallRobot(SmallRobot robot) {
        if (_position != null && robot!=null && _position.equals(robot.position())) {
            field().setSmallRobot(null);
            smallRobotIsCatch();
        }
    }

    private boolean targetInZone(Position targetPosition) {
        PositionRange width = new PositionRange(position().col() - _zoneWidth / 2, position().col() + _zoneWidth / 2);
        PositionRange height = new PositionRange(position().row() - _zoneHeight / 2, position().row() + _zoneHeight / 2);
        return width.contains(targetPosition.col()) && height.contains(targetPosition.row());
    }

    @Override
    public void setField(Field field) {
        if (_field != null && _field != field && field != null)
            _field.setBigRobot(null);

        this._field = field;

        if (field != null && field.bigRobot() != this)
            field.setBigRobot(this);
    }

    @Override
    protected boolean canMove(Direction dir) {
        return position().hasNextPosition(dir) && field().wall(new MiddlePosition(position(), dir)) == null;
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
