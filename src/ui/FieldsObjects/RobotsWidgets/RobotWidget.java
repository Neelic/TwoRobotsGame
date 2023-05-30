package ui.FieldsObjects.RobotsWidgets;

import model.Events.RobotStateChangeEvent;
import model.Events.RobotStateChangeListener;
import model.FieldObjects.Robots.BigRobot;
import model.FieldObjects.Robots.Robot;
import ui.FieldsObjects.FieldObjectWidget;
import ui.Navigation.Cells.CellWidget;

import javax.swing.*;
import java.awt.*;

public abstract class RobotWidget extends FieldObjectWidget {

    public RobotWidget(Robot robot) {
        RobotStateListener listenerRobotState = new RobotStateListener();
        robot.addListener(listenerRobotState);
    }

    private void showStringMissCount(int count) {
        removeAll();
        if (count != 0) {
            JLabel p = new JLabel(String.valueOf(count));
            p.setLocation(0, -5);
            p.setForeground(Color.ORANGE);
            p.setSize(new Dimension(CellWidget.CELL_SIZE, 20));
            add(p);
        }
    }

    private class RobotStateListener implements RobotStateChangeListener {
        @Override
        public void robotStateChange(RobotStateChangeEvent event) {
            BigRobot source = (BigRobot) event.getSource();
            showStringMissCount(source.missedMoves());
        }
    }
}
