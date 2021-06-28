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

		Point p1 = new Point(5, 3);
		Point p2 = new Point(4, 6);
		System.out.println("Odległość między punktami wynosi: " + distance(p1, p2));
		System.out.println("Odległość między punktami wynosi: " + p1.distance(p2));
	}

	public static void hello(String somebody) {
		System.out.println("Hello, " + somebody + "!");
	}

	public static double distance(Point p1, Point p2) {
		double d;
		double res1;
		double res2;

		res1 = pow((p2.x - p1.x), 2.0);
		res2 = pow((p2.y - p1.y), 2.0);
		d = sqrt(res1 + res2);
		return d;
	}
}