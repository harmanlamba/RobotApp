package robot;
import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class TestTrackedRobot {
    private MockPainter _painter;

    @Before
    public void setUp() {
        _painter = new MockPainter();
    }

    @Test
    public void testSmallRobot(){
        TrackedRobot robot= new TrackedRobot(100,100,1,1,30,30);
        robot.paint(_painter);
        assertEquals("(line 100,115,115,100)(line 115,100,130,115)(line 130,115,115,130)(line 115,130,100,115)",_painter.toString());
    }

    @Test
    public void testRegularRobot(){
        TrackedRobot robot= new TrackedRobot(100,100,1,1,80,80);
        robot.paint(_painter);
        assertEquals("(line 100,140,120,100)(line 120,100,160,100)(line 160,100,180,140)(line 180,140,160,180)(line 160,180,120,180)(line 120,180,100,140)",_painter.toString());
    }




}
