package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PointTests {

    @Test
    public void pointDistance() {
        Point p1 = new Point(2, 4);
        Point p2 = new Point(4, 6);
        Point p3 = new Point(2, 2);
        Point p4 = new Point(1, 7);
        Assert.assertEquals(p1.distance(p2), 2.8284271247461903);
        Assert.assertEquals(p3.distance(p1), 2.0);
        Assert.assertEquals(p2.distance(p3), 4.47213595499958);
        Assert.assertEquals(p3.distance(p4), 5.0990195135927845);
    }
}
