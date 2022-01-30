import java.util.Scanner;

public class Polygon{

	private int sides;
	private double perimeter;


	public void addSide(double length){
		sides++;
		perimeter += length;
	}

	public int getSides() { return sides; }

	public double getPerimeter() { return perimeter; }



	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		Polygon myPolygon = new Polygon();
		double length;

		while (true){
			System.out.print("Side length (0 when done): ");
			length = in.nextDouble();
			
			if (length <= 0) 
				break;

			myPolygon.addSide(length);
		}

		System.out.printf("Perimeter of %d-sided polygon is %.3f\n", myPolygon.sides, myPolygon.perimeter);
	}
}