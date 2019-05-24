package ru.stqa.java_studying_sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.java_studying.sandbox.Point;

public class PointTests {

    @Test
    public void testDistance1() {
        Point p1 = new Point(2, 3);
        Point p2 = new Point(2, 3);

        Assert.assertEquals(p1.distance(p2), 0.0);

    }

    @Test
    public void testDistance2() {

        Point p3 = new Point(1, 1);
        Point p4 = new Point(0, 0);

        Assert.assertEquals(p3.distance(p4), 1.4142135623730951);


    }
}
