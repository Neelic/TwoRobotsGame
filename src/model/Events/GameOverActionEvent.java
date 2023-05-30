package model.Events;

import model.FieldObjects.Robots.SmallRobot;

import java.util.EventObject;

public class GameOverActionEvent extends EventObject {
    /**
     * Constructs a prototypical Event.
     *
     * @param source the object on which the Event initially occurred
     * @throws IllegalArgumentException if source is null
     */
    public GameOverActionEvent(Object source, SmallRobot robot) {
        super(source);
        this.robot = robot;
    }
    private final SmallRobot robot;
    public SmallRobot getRobot() {
        return robot;
    }
}
