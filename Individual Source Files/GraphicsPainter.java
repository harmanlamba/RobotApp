package robot;



import java.awt.*;
import java.awt.image.ImageObserver;


/**
 * Implementation of the Painter interface that delegates drawing to a
 * java.awt.Graphics object.
 * 
 * @author Craig Sutherland
 * 
 */
public class GraphicsPainter implements Painter {
	// Delegate object.
	private Graphics _g;

	/**
	 * Creates a GraphicsPainter object and sets its Graphics delegate.
	 */
	public GraphicsPainter(Graphics g) {
		this._g = g;
	}

	/**
	 * @see robot.Painter.drawRect
	 */
	public void drawRect(int x, int y, int width, int height) {
		_g.drawRect(x, y, width, height);
	}

	/**
	 * @see robot.Painter.drawOval
	 */
	public void drawOval(int x, int y, int width, int height) {
		_g.drawOval(x, y, width, height);
	}

	/**
	 * @see bounce.Painter.drawLine
	 */
	public void drawLine(int x1, int y1, int x2, int y2) {
		_g.drawLine(x1, y1, x2, y2);
	}

    @Override
    public void fillRect(int x, int y, int width, int height) {
	    _g.getColor();
        _g.fillRect(x,y,width,height);
    }

    @Override
    public Color getColor() {
       return _g.getColor();
    }

    @Override
    public void setColor(Color color) {
	    _g.setColor(color);

    }

	@Override
	public void drawImage(Image img, int x, int y, int width, int height) {
		_g.drawImage(img,x,y,width,height,null);
	}

	@Override
	public void translate(int x, int y){
		_g.translate(x,y);
	}

	@Override
	public void drawCenteredText(String str, int x, int y, int width, int height) {
	    FontMetrics metrics = _g.getFontMetrics();
	    int x2=x + (width-metrics.stringWidth(str))/2;
	    int y2=y+(((height-metrics.getHeight()))/2)+metrics.getAscent();

	    int ascent= metrics.getAscent();
	    int descent=metrics.getDescent();

	  /*  if(ascent>descent){
	        y2=y2+ ((ascent-descent)/2);
        }else if(ascent < descent){
            y2=y2+((ascent+descent)/2);
        } */


		_g.drawString(str,x2,y2);
	}
}
