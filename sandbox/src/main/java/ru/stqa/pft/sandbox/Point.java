package ru.stqa.pft.sandbox;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

public class Point {

    public double x1;
    public double y1;
    public double x2;
    public double y2;

    public Point(double x1, double y1, double x2, double y2) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }

    public double distance() {
        double d;
        double p1;
        double p2;

        p1 = pow((this.x2 - this.x1), 2.0);
        p2 = pow((this.y2 - this.y1), 2.0);
        d = sqrt(p1 + p2);
        return d;
    }

}
