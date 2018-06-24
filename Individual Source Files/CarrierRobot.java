package robot;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class CarrierRobot extends Robot {
    protected List<Robot> _innerRobots = new ArrayList<Robot>();


    public CarrierRobot() {
        super();
    }


    public CarrierRobot(int x, int y) {
        super(x, y);
    }

    public CarrierRobot(int x, int y, int deltaX, int deltaY) {
        super(x, y, deltaX, deltaY);
    }


    public CarrierRobot(int x, int y, int deltaX, int deltaY, int width, int height) {
        super(x, y, deltaX, deltaY, width, height);
    }

    public CarrierRobot(int x, int y, int deltaX, int deltaY, int width, int height, String text) {
        super(x, y, deltaX, deltaY, width, height,text);
    }


    @Override
    public void move(int width, int height) {
        //For the CarrierRobot
        super.move(width, height);
        for (Robot counter : _innerRobots) {
            Robot temp = counter.parent();
            counter.move(temp._width , temp._height);
        }

    }

    @Override
    public void doPaint(Painter painter) {
        //Actual Carrier Robot
        painter.drawRect(_x, _y, _width, _height);
        //Drawing the children
        if (_innerRobots.size() > 0) {
            for (Robot counter : _innerRobots) {
                painter.translate(this._x, this._y);
                counter.paint(painter);
                painter.translate(-this._x,-this._y);

            }
        }

    }

    void add(Robot robot) throws IllegalArgumentException {
        if (_innerRobots.size() <= 0) {
            _innerRobots.add(robot);
            robot.linkParentCarrierRobot(this);
        } else {
            for (Robot counter : _innerRobots) {
                if ((robot._width + robot._x > this._width) || (robot._height + robot._y > this._height)) {
                    throw new IllegalArgumentException();
                } else if (!(counter.equals(robot)) && !(checkParentship(this, robot))) {
                    _innerRobots.add(robot);
                    robot.linkParentCarrierRobot(this);
                    break;
                } else if (checkParentship(this, robot)) {
                    throw new IllegalArgumentException();
                }
            }
        }

    }

    public boolean checkParentship(Robot parent, Robot child) {
        Robot tempParent = child.parent();
        while (!(tempParent == null)) {
            if (parent.equals(tempParent)) {
                return true;
            }
            tempParent = tempParent.parent();
        }
        return false;
    }


    void remove(Robot robot) {
        for (Robot counter : _innerRobots) {
            if (counter.equals(robot)) {
                _innerRobots.remove(robot);
                robot.unLinkParentCarrierRobot();
                break;
            }
        }

    }

    public Robot robotAt(int index) throws IndexOutOfBoundsException {

        for (Robot counter : _innerRobots) {
            if (index < 0 || index >= _innerRobots.size()) {
                throw new IndexOutOfBoundsException();
            } else {
                return _innerRobots.get(index);
            }

        }

        return null;
    }

    public int robotCount() {
        return _innerRobots.size();
    }


    public int indexOf(Robot robot) {
        return _innerRobots.indexOf(robot);
    }

    public boolean contains(Robot robot) {

        if (robot._parentCarrierRobot == this) {
            return true;
        } else {
            return false;
        }
    }
}

