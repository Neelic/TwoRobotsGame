package model.Events;

import model.Navigation.Direction;

import java.util.EventObject;

public class RobotMoveEvent extends EventObject {
    /**
     *
     * @param source the object on which the Event initially occurred
     * @param direction move direction
     * @throws IllegalArgumentException if source is null
     */
    public RobotMoveEvent(Object source, Direction direction) {
        super(source);
        _dir = direction;
    }
    private final Direction _dir;
    public Direction getDirection() {
        return _dir;
    }
}
