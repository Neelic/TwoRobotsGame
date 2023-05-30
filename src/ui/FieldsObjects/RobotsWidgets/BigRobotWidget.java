package ui.FieldsObjects.RobotsWidgets;

import model.Events.RobotMoveEvent;
import model.Events.RobotMoveListener;
import model.FieldObjects.Robots.BigRobot;
import model.FieldObjects.Robots.SmallRobot;
import org.jetbrains.annotations.NotNull;
import ui.Navigation.Cells.CellWidget;

import java.awt.*;

public class BigRobotWidget extends RobotWidget {
    private final BigRobot _robot;
    public BigRobotWidget(BigRobot robot) {
        super(robot);
        pathnameImg = "bigRobot.png";
        _robot = robot;
        setPreferredSize(new Dimension(CellWidget.CELL_SIZE - 5, CellWidget.CELL_SIZE - 5));
    }

    private final SmallRobotList _listenerSmallRobot = new SmallRobotList();

    public void addMoveListener(@NotNull SmallRobot robot) {
        robot.addListener(_listenerSmallRobot);
    }

    private class SmallRobotList implements RobotMoveListener {
        @Override
        public void robotMove(RobotMoveEvent event) {
            if (event.getSource() instanceof SmallRobot) {
                _robot.moveTo();
            }
        }
    }
}
