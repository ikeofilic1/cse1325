import java.util.Scanner;
import java.util.ArrayList;

public class Polygon{

	private int sides = 0;
	private ArrayList<Double> lengths = new ArrayList<Double>();

	public void addSide(Double length){
		sides++;
		lengths.add(length);
	}

	public int getSides() { return sides; }

	public double getArea(double apothem) { return 0.5 * apothem * getPerimeter(); }

	public double getPerimeter() { 
		double perimeter = 0;

		for (Double length : lengths) { 
			perimeter += length; 
		}
		return perimeter; 
	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		Polygon myPolygon = new Polygon();
		Double length;

		while (true){
			System.out.print("Side length (0 when done): ");
			length = in.nextDouble();
			
			if (length <= 0) 
				break;

			myPolygon.addSide(length);
		}

		System.out.print("Apothem: ");
		double apothem = in.nextDouble();
		
		System.out.printf("Perimeter of %d-sided polygon is %.2f\n"+
			"Area is %.2f\n", 
			myPolygon.getSides(), 
			myPolygon.getPerimeter(),
			myPolygon.getArea(apothem));
	}
}

/*
Using ArrayLists is better since they allow for an array without a specific size. 
We need an array without a definite size since we dont know how many sides the user wants the polygon to be.
Since ArrayLists do not accept non-objects, I had to change the user input variable from a double type to a Double type in the main method.
This is the only change I made in the main method since Double and double are interchangeable in Java (most times).
*/