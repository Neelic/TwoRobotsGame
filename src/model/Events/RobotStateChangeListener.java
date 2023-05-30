package model.Events;

import java.util.EventListener;

public interface RobotStateChangeListener extends EventListener {
    public void robotStateChange(RobotStateChangeEvent event);
}
