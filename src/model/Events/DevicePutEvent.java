package model.Events;

import java.util.EventObject;

public class DevicePutEvent extends EventObject {
    /**
     * Constructs a prototypical Event.
     *
     * @param source the object on which the Event initially occurred
     * @throws IllegalArgumentException if source is null
     */
    public DevicePutEvent(Object source) {
        super(source);
    }
}