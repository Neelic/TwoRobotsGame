package FieldObjects.Robots;

import Navigation.Direction;
import Navigation.Field;
import Navigation.MiddlePosition;

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
        return position().hasNextPosition(dir)
                && field().swamp(position().nextPosition(dir)) == null
                && field().wall(new MiddlePosition(position(), dir)) == null;
    }

    public boolean moveTo(Direction dir) {
        if (canMove(dir)) {
            _position = position().nextPosition(dir);
            _field.exitPoint().isSmallRobotInExitPoint(this);
            return true;
        }
        return false;
    }
}
