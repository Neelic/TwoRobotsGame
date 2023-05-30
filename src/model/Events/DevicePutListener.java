package model.Events;

import java.util.EventListener;

public interface DevicePutListener extends EventListener {
    public void deviceIsPut(DevicePutEvent event);
}
