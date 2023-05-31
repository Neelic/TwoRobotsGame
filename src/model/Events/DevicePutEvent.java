package model.Events;

import model.Navigation.Position;

import java.util.EventObject;

public class DevicePutEvent extends EventObject {

    private final Position _position;
    /**
     * Constructs a prototypical Event.
     *
     * @param source the object on which the Event initially occurred
     * @throws IllegalArgumentException if source is null
     */
    public DevicePutEvent(Object source, Position position) {
        super(source);
        _position = position;
    }

    public Position getPosition() {
        return _position;
    }
}
