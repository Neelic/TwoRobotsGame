package model.Events;

import java.util.EventObject;

public class RobotStateChangeEvent extends EventObject {
    /**
     * Constructs a prototypical Event.
     *
     * @param source the object on which the Event initially occurred
     * @throws IllegalArgumentException if source is null
     */
    public RobotStateChangeEvent(Object source) {
        super(source);
    }
}
