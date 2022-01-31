import java.util.Scanner;

public class Polygon{

	private int sides;
	private double[] lengths = new double[0];

	public void addSide(double length){
		sides++;
		double[] temp = new double[sides];
		System.arraycopy(lengths, 0, temp, 0, sides - 1);

		lengths = temp;
		lengths[sides - 1] = length;
	}

	public int getSides() { return sides; }

	public double getArea(double apothem) { return 0.5 * apothem * getPerimeter(); }

	public double getPerimeter() { 
		double perimeter = 0;
		for (int i = 0; i < lengths.length; i++) {
			perimeter += lengths[i]; 
		}

		return perimeter; 
	}

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

		System.out.print("Apothem: ");
		double apothem = in.nextDouble();
		
		System.out.printf("Perimeter of %d-sided polygon is %.2f\n"+
			"Area is %.2f\n", 
			myPolygon.getSides(), 
			myPolygon.getPerimeter(),
			myPolygon.getArea(apothem));
	}
}