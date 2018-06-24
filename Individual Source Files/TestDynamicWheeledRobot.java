package robot;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

import java.awt.Color;

public class TestDynamicWheeledRobot {
    private MockPainter _painter;

    @Before
    public void setUp() {
        _painter = new MockPainter();
    }

    @Test
    public void testTopWall() {
        DynamicWheeledRobot robot = new DynamicWheeledRobot(10, 500, 0, -500, 300, 10, Color.red);
        robot.paint(_painter);
        robot.move(500, 500);
        robot.paint(_painter);
        assertEquals("(rectangle 10,500,300,10)(rectangle 10,0,300,10)", _painter.toString());
    }

    @Test
    public void testBottomWall() {
        DynamicWheeledRobot robot = new DynamicWheeledRobot(10, 0, 0, 500, 300, 10, Color.red);
        robot.paint(_painter);
        robot.move(500, 510);
        robot.paint(_painter);
        assertEquals("(rectangle 10,0,300,10)(rectangle 10,500,300,10)", _painter.toString());
    }

    @Test
    public void testLeftWall() {
        DynamicWheeledRobot robot = new DynamicWheeledRobot(250, 250, -250, 0, 300, 10, Color.red);
        robot.paint(_painter);
        robot.move(500, 500);
        robot.paint(_painter);
        assertEquals("(rectangle 250,250,300,10)Color has been set to: java.awt.Color[r=255,g=0,b=0](fillRect 0,250,300,10)", _painter.toString());
    }

    @Test
    public void testRightWall() {
        DynamicWheeledRobot robot = new DynamicWheeledRobot(250, 250, 250, 0, 300, 10, Color.red);
        robot.paint(_painter);
        robot.move(500, 500);
        robot.paint(_painter);
        assertEquals("(rectangle 250,250,300,10)Color has been set to: java.awt.Color[r=255,g=0,b=0](fillRect 200,250,300,10)", _painter.toString());

    }

    @Test
    public void testTopRightCorner() {
        DynamicWheeledRobot robot = new DynamicWheeledRobot(10, 0, 250, 0, 30, 30, Color.red);
        robot.paint(_painter);
        robot.move(500,500);
        robot.paint(_painter);
        robot.move(500,500);
        robot.paint(_painter);
        robot.move(500,500);
        robot.paint(_painter);
        assertEquals("(rectangle 10,0,30,30)(rectangle 260,0,30,30)Color has been set to: java.awt.Color[r=255,g=0,b=0](fillRect 470,0,30,30)(rectangle 220,0,30,30)", _painter.toString());
    }

    @Test
    public void testTopLeftCorner() {
        DynamicWheeledRobot robot = new DynamicWheeledRobot(490, 0, -250, 0, 30, 30, Color.red);
        robot.paint(_painter);
        robot.move(500,500);
        robot.paint(_painter);
        robot.move(500,500);
        robot.paint(_painter);
        robot.move(500,500);
        robot.paint(_painter);
        assertEquals("(rectangle 490,0,30,30)(rectangle 240,0,30,30)Color has been set to: java.awt.Color[r=255,g=0,b=0](fillRect 0,0,30,30)(rectangle 250,0,30,30)", _painter.toString());
    }

    @Test
    public void testBottomRightCorner() {
        DynamicWheeledRobot robot = new DynamicWheeledRobot(10, 500, 250, 0, 30, 30, Color.red);
        robot.paint(_painter);
        robot.move(500,500);
        robot.paint(_painter);
        robot.move(500,500);
        robot.paint(_painter);
        robot.move(500,500);
        robot.paint(_painter);
        assertEquals("(rectangle 10,500,30,30)(rectangle 260,470,30,30)Color has been set to: java.awt.Color[r=255,g=0,b=0](fillRect 470,470,30,30)(rectangle 220,470,30,30)", _painter.toString());
    }

    @Test
    public void testBottomLeftCorner() {
        DynamicWheeledRobot robot = new DynamicWheeledRobot(500, 500, -250, 0, 30, 30, Color.red);
        robot.paint(_painter);
        robot.move(500,500);
        robot.paint(_painter);
        robot.move(500,500);
        robot.paint(_painter);
        robot.move(500,500);
        robot.paint(_painter);
        assertEquals("(rectangle 500,500,30,30)(rectangle 250,470,30,30)Color has been set to: java.awt.Color[r=255,g=0,b=0](fillRect 0,470,30,30)(rectangle 250,470,30,30)", _painter.toString());
    }

}
