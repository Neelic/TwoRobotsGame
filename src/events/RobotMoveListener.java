package events;

import java.util.EventListener;

public interface RobotMoveListener extends EventListener {
    public void robotMove(RobotMoveEvent event);
}
