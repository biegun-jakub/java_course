package ru.stqa.pft.sandbox;

public class MyFirstProgram {

	public static void main(String[] args) {
		hello("world");
		hello("user");
		hello("Jakub");

		Square s = new Square(5);
		System.out.println("Powierzchnia kwadratu o boku " + s.l + " = " + s.area());

		Rectangle r = new Rectangle(4, 6);
		System.out.println("Powierzchnia prostokąta o bokach " + r.a + " i " + r.b + " = " + r.area());
	}

	public static void hello(String somebody) {
		System.out.println("Hello, " + somebody + "!");
	}

}