package model.Events;

import model.Navigation.Position;

import java.util.EventObject;

public class RobotMoveEvent extends EventObject {
    /**
     *
     * @param source the object on which the Event initially occurred
     * @param oldPosition old position
     * @throws IllegalArgumentException if source is null
     */
    public RobotMoveEvent(Object source, Position oldPosition) {
        super(source);
        _oldPosition = oldPosition;
    }
    private final Position _oldPosition;
    public Position getOldPosition() {
        return _oldPosition;
    }
}
