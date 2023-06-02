package model.Events;

import java.util.EventObject;

public class RobotStateChangeEvent extends EventObject {
    /**
     * Constructs a prototypical Event.
     *
     * @param source the object on which the Event initially occurred
     * @throws IllegalArgumentException if source is null
     */
    public RobotStateChangeEvent(Object source, StateStatus status) {
        super(source);
        _status = status;
    }

    private final StateStatus _status;

    public StateStatus getStatus() {
        return _status;
    }

    public enum StateStatus {
        START_MISS_COUNT,
        DECREASE_MISS_COUNT
    }
}
