package model.Navigation.Algorithms;

import model.Navigation.Direction;
import model.Navigation.Position;

public interface BehaviorAlgorithm {
    Direction getNextDirection(Position targetPosition, Position currentPosition);
}
