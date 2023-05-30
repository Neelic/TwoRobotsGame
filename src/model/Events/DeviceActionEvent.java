package model.Events;

import java.util.EventObject;

public class DeviceActionEvent extends EventObject {
    /**
     * Constructs a prototypical Event.
     *
     * @param source the object on which the Event initially occurred
     * @throws IllegalArgumentException if source is null
     */
    public DeviceActionEvent(Object source) {
        super(source);
    }
}
