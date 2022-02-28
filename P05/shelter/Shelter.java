package shelter;
import java.util.ArrayList;

public class Shelter {
	private String name;
	private ArrayList<Animal> animals;

	public Shelter(String name) {
		this.name = name;
		animals = new ArrayList<>();
	}

	public void addAnimal(Animal animal){animals.add(animal);}
	public int numAnimals() {return animals.size();}

	public Animal getAnimal(int index) throws IllegalArgumentException {
		if (index < 0) throw new IllegalArgumentException("Index cannot be negative.\n");
		return animals.get(index);
	}
	
	@Override
	public String toString(){
		StringBuilder toString = new StringBuilder();
		for (Animal c: animals) {
			toString.append(c.toString());
		}
		return toString.toString();
	}
}