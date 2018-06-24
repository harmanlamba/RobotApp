package robot;


import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class DynamicCarRobot extends Robot {
    private boolean _isBugatti = false;
    private boolean _isPagani = false;
    private boolean _isCar = false;
    private BufferedImage _car1;
    private BufferedImage _car2;
    private BufferedImage _car3;

    {
        try {
            _car1 = ImageIO.read(getClass().getResource("smallerlambo.png"));
            _car2 = ImageIO.read(getClass().getResource("bugatti.png"));
            _car3 = ImageIO.read(getClass().getResource("pagani.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public DynamicCarRobot() {
        super();
    }


    public DynamicCarRobot(int x, int y, int deltaX, int deltaY) {
        super(x, y, deltaX, deltaY);
    }


    public DynamicCarRobot(int x, int y, int deltaX, int deltaY, int width, int height, String text) {
        super(x, y, deltaX, deltaY, width, height,text);
    }

    @Override
    public void doPaint(Painter painter) {
        if ((!_hitVerticalWall && _isCar && _isBugatti) || _hitHorizontalWall) {
            painter.drawImage(_car2, _x, _y, _width, _height);
            _hitHorizontalWall = false;
            _isBugatti = true;
            _isPagani = false;

        } else if ((_isCar && !_hitHorizontalWall && _isPagani) || _hitVerticalWall ) {
            painter.drawImage(_car3, _x, _y, _width, _height);
            _hitVerticalWall = false;
            _isBugatti = false;
            _isPagani = true;

        } else {
            painter.drawImage(_car1, _x, _y, _width, _height);
            _isCar = true;

        }

    }
}
