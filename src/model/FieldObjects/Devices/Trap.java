package model.FieldObjects.Devices;

import model.FieldObjects.Robots.BigRobot;
import model.FieldObjects.Robots.Robot;
import model.FieldObjects.Robots.SmallRobot;
import model.Navigation.Position;

public class Trap extends Device{
    @Override
    public boolean canAddToRobot(Robot robot) {
        return robot instanceof BigRobot;
    }

    @Override
    protected boolean canReplace(Position position) {
        if (_field == null) {
            return false;
        }

        return _field.swamp(position) == null && _field.deviceByTypeAndPosition(position, this.getClass()) == null;
    }

    private final static int COUNT_MISSED_MOVES = 1;

    @Override
    public int startAction(Robot robot) {
        if (_field != null && robot instanceof SmallRobot) {
            _field.deleteDevice(this);

            actionDeviceEvent();
            return COUNT_MISSED_MOVES;
        }

        return 0;
    }

    @Override
    public Device clone() {
        Trap trap = new Trap();
        trap.setField(_field);
        return trap;
    }

    @Override
    public boolean canMoveOnSwamp() {
        return false;
    }
}
