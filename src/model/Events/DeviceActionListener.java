package model.Events;

import java.util.EventListener;

public interface DeviceActionListener extends EventListener {

    public void startDeviceAction(DeviceActionEvent event);
}
