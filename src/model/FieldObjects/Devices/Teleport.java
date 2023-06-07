package model.FieldObjects.Devices;

import model.FieldObjects.Robots.Robot;
import model.Navigation.Position;

public class Teleport extends Device{
    private Robot _propertyRobot;

    public void setPropertyRobot(Robot propertyRobot) {
        this._propertyRobot = propertyRobot;
    }

    @Override
    public boolean canAddToRobot(Robot robot) {
        return true;
    }

    @Override
    protected boolean canReplace(Position position) {
        if (_field == null) {
            return false;
        }

        return _field.deviceByPosition(position) == null && position.isBorderPosition();
    }

    @Override
    public int startAction(Robot robot) {
        if (_field != null && robot != null && robot.position().equals(position())
                && _propertyRobot != null && _propertyRobot.equals(robot)) {
            robot.setPosition(robot.position().getMirrorPositionRelativeToVertical());
        }
        return 0;
    }

    @Override
    public Device clone() {
        Device teleport = new Teleport();
        teleport.setField(_field);
        return teleport;
    }

    @Override
    public boolean canMoveOnSwamp() {
        return false;
    }
}
