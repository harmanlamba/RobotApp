package robot;


import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Abstract superclass to represent the general concept of a Robot. This class
 * defines state common to all special kinds of Robot instances and implements
 * a common movement algorithm. Robot subclasses must override method paint()
 * to handle robot-specific painting.
 *
 * @author Craig Sutherland
 */
public abstract class Robot {
    // === Constants for default values. ===
    protected static final int DEFAULT_X_POS = 0;

    protected static final int DEFAULT_Y_POS = 0;

    protected static final int DEFAULT_DELTA_X = 5;

    protected static final int DEFAULT_DELTA_Y = 5;

    protected static final int DEFAULT_HEIGHT = 35;

    protected static final int DEFAULT_WIDTH = 25;
    // ===

    // === Instance variables, accessible by subclasses.
    protected int _x;

    protected int _y;

    protected int _deltaX;

    protected int _deltaY;

    protected int _width;

    protected int _height;

    protected boolean _hitVerticalWall;

    protected boolean _hitHorizontalWall;

    protected CarrierRobot _parentCarrierRobot;

    protected String _innerText;


    // ===

    /**
     * Creates a Robot object with default values for instance variables.
     */
    public Robot() {
        this(DEFAULT_X_POS, DEFAULT_Y_POS, DEFAULT_DELTA_X, DEFAULT_DELTA_Y, DEFAULT_WIDTH, DEFAULT_HEIGHT,null);
    }

    /**
     * Creates a Robot object with a specified x and y position.
     */
    public Robot(int x, int y) {
        this(x, y, DEFAULT_DELTA_X, DEFAULT_DELTA_Y, DEFAULT_WIDTH, DEFAULT_HEIGHT,null);
    }

    /**
     * Creates a Robot instance with specified x, y, deltaX and deltaY values.
     * The Robot object is created with a default width and height.
     */
    public Robot(int x, int y, int deltaX, int deltaY) {
        this(x, y, deltaX, deltaY, DEFAULT_WIDTH, DEFAULT_HEIGHT, null);
    }

    public Robot(int x, int y, int deltaX, int deltaY, int width, int height){
        this(x,y,deltaX,deltaY,width,height,null);
    }

    /**
     * Creates a Robot instance with specified x, y, deltaX, deltaY, width and
     * height values.
     */
    public Robot(int x, int y, int deltaX, int deltaY, int width, int height, String text) {
        _x = x;
        _y = y;
        _deltaX = deltaX;
        _deltaY = deltaY;
        _width = width;
        _height = height;
        _innerText=text;
    }

    /**
     * Moves this Robot object within the specified bounds. On hitting a
     * boundary the Robot instance bounces off and back into the two-
     * dimensional world.
     *
     * @param width  width of two-dimensional world.
     * @param height height of two-dimensional world.
     */
    public void move(int width, int height) {
        int nextX = _x + _deltaX;
        int nextY = _y + _deltaY;

        if (nextX <= 0) {
            nextX = 0;
            _deltaX = -_deltaX;
            _hitVerticalWall=true;

        } else if (nextX + _width >= width) {
            nextX = width - _width;
            _deltaX = -_deltaX;
            _hitVerticalWall=true;

        }

        if (nextY <= 0) {
            nextY = 0;
            _deltaY = -_deltaY;
            _hitHorizontalWall=true;

        } else if (nextY + _height >= height) {
            nextY = height - _height;
            _deltaY = -_deltaY;
            _hitHorizontalWall=true;
        }

        _x = nextX;
        _y = nextY;
    }


    public CarrierRobot parent(){
        return _parentCarrierRobot;
    }

    public List<Robot> path(){
        List<Robot> pathList= new ArrayList<Robot>();
        Robot tempParent=this.parent();

        while(tempParent!=null){
            pathList.add(tempParent);
            tempParent=tempParent.parent();
        }
        Collections.reverse(pathList);
        pathList.add(this);

        return pathList;
    }

    /**
     * Method to be implemented by concrete subclasses to handle subclass
     * specific painting.
     *
     * @param painter the Painter object used for drawing.
     */
    public final void paint(Painter painter){
        this.doPaint(painter);
        if(_innerText != null && !_innerText.isEmpty()){
            painter.setColor(Color.black);
            painter.drawCenteredText(_innerText,_x,_y,_width,_height);
        }
    }

    /**
     * Returns this Robot object's x position.
     */
    public int x() {
        return _x;
    }

    /**
     * Returns this Robot object's y position.
     */
    public int y() {
        return _y;
    }

    /**
     * Returns this Robot object's speed and direction.
     */
    public int deltaX() {
        return _deltaX;
    }

    /**
     * Returns this Robot object's speed and direction.
     */
    public int deltaY() {
        return _deltaY;
    }

    /**
     * Returns this Robot's width.
     */
    public int width() {
        return _width;
    }

    /**
     * Returns this Robot's height.
     */
    public int height() {
        return _height;
    }

    /**
     * Returns a String whose value is the fully qualified name of this class
     * of object. E.g., when called on a WheeledRobot instance, this method
     * will return "robot.WheeledRobot".
     */
    public String toString() {
        return getClass().getName();
    }

    public void linkParentCarrierRobot(CarrierRobot parentCarrierRobot){
        _parentCarrierRobot=parentCarrierRobot;
    }

    public void unLinkParentCarrierRobot(){
        _parentCarrierRobot=null;
    }

    public void setInnerText(String str){
        this._innerText=str;
    }

    protected abstract void doPaint(Painter painter);

    public String text() {
        return _innerText;
    }
}
