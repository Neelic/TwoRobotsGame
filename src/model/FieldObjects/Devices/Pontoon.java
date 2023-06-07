package model.FieldObjects.Devices;

import model.FieldObjects.Robots.Robot;
import model.Navigation.Position;
import org.jetbrains.annotations.NotNull;

public class Pontoon extends Device{
    private final static int COUNT_MISSED_MOVES = 0;

    @Override
    public boolean canAddToRobot(Robot robot) {
        return true;
    }

    @Override
    protected boolean canReplace(Position position) {
        if (_field == null) {
            return false;
        }

        return _field.swamp(position) != null && _field.deviceByPosition(position) == null;
    }

    @Override
    public int startAction(@NotNull Robot robot) {
        actionDeviceEvent();
        return COUNT_MISSED_MOVES;
    }

    @Override
    public Device clone() {
        var pontoon = new Pontoon();
        pontoon.setField(_field);
        return pontoon;
    }

    @Override
    public boolean canMoveOnSwamp() {
        return true;
    }
}
