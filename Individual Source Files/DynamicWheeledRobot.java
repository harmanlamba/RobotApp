package robot;

import java.awt.Color;



public class DynamicWheeledRobot extends WheeledRobot {
    private Color _color;
    private boolean _isColored=false;


    public DynamicWheeledRobot() {
        super();
        _color = Color.black;

    }


    public DynamicWheeledRobot(int x, int y, int deltaX, int deltaY, Color color) {
        super(x, y, deltaX, deltaY);
        _color = color;
    }

    public DynamicWheeledRobot(int x, int y, int deltaX, int deltaY, int width, int height,  Color color) {
        super(x, y, deltaX, deltaY, width, height);
        _color = color;
    }


    public DynamicWheeledRobot(int x, int y, int deltaX, int deltaY, int width, int height, String text, Color color) {
        super(x, y, deltaX, deltaY, width, height,text);
        _color = color;
    }

    @Override
    public void doPaint(Painter painter) {
        //Calling Parent Class' method in order to avoid code duplication as it paints itself exactly as WheeledRobot.
        if (_hitHorizontalWall && !(_hitHorizontalWall && _hitVerticalWall)) {
            super.doPaint(painter);
            _hitHorizontalWall=false;
            _isColored=false;
        }else if(_isColored ||_hitVerticalWall || (_hitHorizontalWall && _hitVerticalWall)){
            painter.setColor(_color);
            painter.fillRect(_x,_y,_width,_height);
            painter.setColor(Color.BLACK);
            _hitVerticalWall=false;
            _hitHorizontalWall=false;
            _isColored=true;
        }else if(!_isColored){ //For the first iteration of the code.
            super.doPaint(painter);
        }

    }
}
