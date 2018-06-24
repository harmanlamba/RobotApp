package robot;

import java.awt.*;
import java.awt.image.ImageObserver;

/**
 * Implementation of the Painter interface that does not actually do any
 * painting. A MockPainter implementation responds to Painter requests by
 * logging simply logging them. The contents of a MockPainter object's
 * log can be retrieved by a call to toString() on the MockPainter.
 *
 * @author Craig Sutherland
 */
public class MockPainter implements Painter {
    // Internal log.
    private StringBuffer _log = new StringBuffer();

    /**
     * Returns the contents of this MockPainter's log.
     */
    public String toString() {
        return _log.toString();
    }

    /**
     * Logs the drawRect call.
     */
    public void drawRect(int x, int y, int width, int height) {
        _log.append("(rectangle " + x + "," + y + "," + width + "," + height + ")");
    }

    /**
     * Logs the drawOval call.
     */
    public void drawOval(int x, int y, int width, int height) {
        _log.append("(oval " + x + "," + y + "," + width + "," + height + ")");
    }

    /**
     * Logs the drawLine call.
     */
    public void drawLine(int x1, int y1, int x2, int y2) {
        _log.append("(line " + x1 + "," + y1 + "," + x2 + "," + y2 + ")");
    }

    @Override
    public void fillRect(int x, int y, int width, int height) {
        _log.append("(fillRect " + x + "," + y + "," + width + "," + height+")");
    }

    @Override
    public Color getColor() {
        _log.append("The color gotten is: " + this.getColor());
        return null;
    }

    @Override
    public void setColor(Color color) {
        _log.append("Color has been set to: "+ color);

    }

    @Override
    public void drawImage(Image img, int x, int y, int width, int height) {
        //Not yet implemented
    }

    @Override
    public void translate(int x, int y){
       // _log.append("New Origin has been set to:"+ x +"," +y);
    }

    @Override
    public void drawCenteredText(String str, int x, int y, int width, int height) {

    }


}