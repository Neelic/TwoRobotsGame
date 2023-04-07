package events;

import java.util.EventObject;

public class GameOverActionEvent extends EventObject {
    /**
     * Constructs a prototypical Event.
     *
     * @param source the object on which the Event initially occurred
     * @throws IllegalArgumentException if source is null
     */
    public GameOverActionEvent(Object source) {
        super(source);
    }
}
