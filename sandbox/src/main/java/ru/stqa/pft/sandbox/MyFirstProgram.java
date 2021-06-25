package ru.stqa.pft.sandbox;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

public class MyFirstProgram {

	public static void main(String[] args) {
		hello("world");
		hello("user");
		hello("Jakub");

		Square s = new Square(5);
		System.out.println("Powierzchnia kwadratu o boku " + s.l + " = " + s.area());

		Rectangle r = new Rectangle(4, 6);
		System.out.println("Powierzchnia prostokąta o bokach " + r.a + " i " + r.b + " = " + r.area());

		Point p = new Point(5, 3, 7, 2);
		System.out.println("Odległość między punktami wynosi: " + distance(p.x1, p.y1, p.x2, p.y2));
		System.out.println("Odległość między punktami wynosi: " + p.distance());
	}

	public static void hello(String somebody) {
		System.out.println("Hello, " + somebody + "!");
	}

	public static double distance(double x1, double y1, double x2, double y2) {
		double d;
		double p1;
		double p2;

		p1 = pow((x2 - x1), 2.0);
		p2 = pow((y2 - y1), 2.0);
		d = sqrt(p1 + p2);
		return d;
	}
}