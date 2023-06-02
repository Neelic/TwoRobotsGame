package model.FieldObjects.Robots;

import model.Events.RobotStateChangeEvent;
import model.FieldObjects.Devices.Device;
import model.FieldObjects.Swamp;
import model.Navigation.Direction;
import model.Navigation.Field;
import model.Navigation.MiddlePosition;
import model.Navigation.Position;

public class SmallRobot extends Robot {
    @Override
    public void setField(Field field) {
        if (_field != null && _field != field && field != null)
            _field.setSmallRobot(null);

        this._field = field;

        if (field != null && field.smallRobot() != this)
            field.setSmallRobot(this);
    }
    public SmallRobot(Field field) {
        super(field);
        if (field != null)
            field.setSmallRobot(this);
    }

    @Override
    protected boolean canMove(Direction dir) {
        if (_field == null) {
            return false;
        }

        Position nextPosition = _position.nextPosition(dir);
        Device device = _field.deviceByPosition(nextPosition);
        Swamp swamp = field().swamp(nextPosition);

        return (swamp == null || swamp.canMoveRobot(this, device))
                && field().wall(new MiddlePosition(position(), dir)) == null;
    }

    public void moveTo(Direction dir) {
        if (!_position.hasNextPosition(dir)) {
            return;
        }

        if (countMissedMoves == 0) {
            putDevice(_position.nextPosition(dir), chancePutDevice);

            if (canMove(dir)) {
                Position nextPosition = _position.nextPosition(dir);
                _position = nextPosition;

                Device device = _field.deviceByPosition(nextPosition);
                if (device != null) {
                    countMissedMoves = device.startAction(this);

                    if (countMissedMoves != 0) {
                        robotStateChange(this, RobotStateChangeEvent.StateStatus.START_MISS_COUNT);
                    }
                }

                robotMoveAction(this, dir);
            }
        } else {
            countMissedMoves--;
            robotStateChange(this, RobotStateChangeEvent.StateStatus.DECREASE_MISS_COUNT);
        }
    }

    private final int chancePutDevice = 100;
//    public void puttingDevice(Direction dir) {
//        if (!_position.hasNextPosition(dir)) {
//            return;
//        }
//
//        putDevice(_position.nextPosition(dir), chancePutDevice);
//    }
}
