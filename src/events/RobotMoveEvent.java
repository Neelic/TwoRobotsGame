package events;

import java.util.EventObject;

public class RobotMoveEvent extends EventObject {
    /**
     * Constructs a prototypical Event.
     *
     * @param source the object on which the Event initially occurred
     * @throws IllegalArgumentException if source is null
     */
    public RobotMoveEvent(Object source) {
        super(source);
    }
}
