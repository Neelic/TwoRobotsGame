package FieldObjects.Robots.Algorithms;

import Navigation.Direction;
import Navigation.Position;

public interface BehaviorAlgorithm {
    public Direction getNextDirection(Position pos);
}
