package model.Events;

import java.util.EventListener;

public interface RobotMoveListener extends EventListener {
    public void robotMove(RobotMoveEvent event);
}
