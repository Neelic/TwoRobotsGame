package events;

import java.util.EventListener;

public interface GameOverActionListener extends EventListener {
    void gameOverEvent(GameOverActionEvent event);
}
