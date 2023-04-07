package Navigation.Algorithms;

import Navigation.Direction;
import Navigation.Field;
import Navigation.Position;

public interface BehaviorAlgorithm {
    public Direction getNextDirection(Position pos);
}
