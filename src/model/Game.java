package model;

import model.Events.GameOverActionEvent;
import model.Events.GameOverActionListener;
import model.Navigation.Field;

public class Game {
    private Field _field;
    public void startGame() {
        _field = new Field();
        Labyrinth labyrinth = new Labyrinth(_field);
        labyrinth.createFieldObjects();

        _field.exitPoint().addListener(_listener);
        _field.bigRobot().addListener(_listener);
    }

    public Field field() {
        return  _field;
    }

    private void endGame() {
        System.exit(0);
    }

    private final GameOverList _listener = new GameOverList();

    private class GameOverList implements GameOverActionListener {
        @Override
        public void gameOverEvent(GameOverActionEvent event) {
            _field.setSmallRobot(null);
            endGame();
        }
    }
}
