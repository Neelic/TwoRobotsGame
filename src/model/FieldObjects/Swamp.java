package model.FieldObjects;

import model.FieldObjects.Devices.Device;
import model.FieldObjects.Robots.BigRobot;
import model.FieldObjects.Robots.Robot;
import model.FieldObjects.Robots.SmallRobot;
import model.Navigation.Position;

public class Swamp {
    private final Position _pos;

    public Swamp(Position pos) {
        _pos = pos;
    }

    public Position position() {
        return _pos;
    }

    public boolean canMoveRobot(Robot robot, Device device) {
        if (robot instanceof SmallRobot) {
            if (device != null) {
                return device.canMoveOnSwamp() && device.position().equals(_pos);
            } else {
                return false;
            }
        } else {
            return robot instanceof BigRobot;
        }
    }

    @Override
    public boolean equals(Object other) {
        if (other instanceof Swamp) {
            return _pos.equals(((Swamp) other)._pos);
        }
        return false;
    }
}
