package ru.stqa.pft.sandbox;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

public class Point {

    public double x;
    public double y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double distance(Point p2) {
        double d;
        double res1;
        double res2;

        res1 = pow((p2.x - this.x), 2.0);
        res2 = pow((p2.y - this.y), 2.0);
        d = sqrt(res1 + res2);
        return d;
    }

}
