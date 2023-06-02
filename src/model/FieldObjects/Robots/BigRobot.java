package model.FieldObjects.Robots;

import model.Events.GameOverActionEvent;
import model.Events.GameOverActionListener;
import model.Events.RobotStateChangeEvent;
import model.FieldObjects.Devices.Device;
import model.FieldObjects.Devices.Pontoon;
import model.Navigation.Algorithms.BehaviorAlgorithm;
import model.Navigation.Algorithms.SillyAlgorithm;
import model.Navigation.Algorithms.SmartAlgorithm;
import model.Navigation.Algorithms.TargetInZone;
import model.Navigation.Direction;
import model.Navigation.Field;
import model.Navigation.MiddlePosition;
import model.Navigation.Position;

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

    private final int _missedMoves = 3;

    private final int chancePutDevice = 40;

    private final TargetInZone zone;

    public BigRobot(Field field, int zoneHeight, int zoneWidth) {
        super(field);
        if (field != null)
            field.setBigRobot(this);
        zone = new TargetInZone(zoneHeight, zoneWidth);
    }

    public void moveTo() {
        Direction dir = null;
        if (countMissedMoves == 0) {
            SmallRobot target = field().smallRobot();

            if (target != null && target.position() != null) {
                BehaviorAlgorithm alg;
                if (zone.isTargetInSquareZone(_position, target.position())) {
                    alg = _smartAlgorithm;
                } else {
                    alg = _sillyAlgorithm;
                }

                dir = alg.getNextDirection(target.position(), _position);
            }

            if (dir != null && canMove(dir)) {
                Position nextPosition = _position.nextPosition(dir);
                putDevice(nextPosition, chancePutDevice);

                Device device = _field.deviceByPosition(nextPosition);

                if (_field.swamp(nextPosition) != null) {
                    if (!(device instanceof Pontoon)) {
                        countMissedMoves = _missedMoves;
                    }
                }

                if (device != null) {
                    countMissedMoves = device.startAction(this);
                }

                _position = _position.nextPosition(dir);
                robotMoveAction(this, dir);


                if (countMissedMoves > 0) {
                    robotStateChange(this, RobotStateChangeEvent.StateStatus.START_MISS_COUNT);
                }
            }

            catchSmallRobot(target);
        } else {
            countMissedMoves--;
            robotStateChange(this, RobotStateChangeEvent.StateStatus.DECREASE_MISS_COUNT);
        }
    }

    private void catchSmallRobot(SmallRobot robot) {
        if (_position != null && robot!=null && _position.equals(robot.position())) {
            smallRobotIsCatch();
        }
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
}
