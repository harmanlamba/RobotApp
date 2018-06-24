package robot;

public class TrackedRobot extends Robot {

    public TrackedRobot() {
        super();
    }


    public TrackedRobot(int x, int y, int deltaX, int deltaY) {
        super(x, y, deltaX, deltaY);
    }


    public TrackedRobot(int x, int y, int deltaX, int deltaY, int width, int height) {
        super(x, y, deltaX, deltaY, width, height);
    }

    public TrackedRobot(int x, int y, int deltaX, int deltaY, int width, int height, String text) {
        super(x, y, deltaX, deltaY, width, height,text);
    }

    @Override
    public void doPaint(Painter painter) {
        if (_width < 40) {
            painter.drawLine(_x,_y+(_height/2),_x + _width / 2, _y);
            painter.drawLine(_x + _width / 2, _y, _width + _x, _y + (_height / 2));
            painter.drawLine(_width + _x, _y + (_height / 2), _x + _width / 2, _height + _y);
            painter.drawLine(_x + _width / 2, _height + _y,_x,_y+(_height/2));
        } else {
            painter.drawLine(_x, (_height / 2) + _y, _x + 20, _y);
            painter.drawLine(_x + 20, _y, _width + _x - 20, _y);
            painter.drawLine(_width + _x - 20, _y, _width + _x, (_height / 2) + _y);
            painter.drawLine(_width + _x, (_height / 2) + _y, _width - 20 + _x, _height + _y);
            painter.drawLine(_width - 20 + _x, _height + _y, _x + 20, _height + _y);
            painter.drawLine(_x + 20, _height + _y, _x, (_height / 2) + _y);


        }

    }


}
