package shelter;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

public class Shelter {
	private String name;
	private ArrayList<Animal> animals;
	private String filename;

	public Shelter(String name) {
		this.name = name;
		this.filename = "untitled.mass";
		animals = new ArrayList<>();
	}
	public Shelter(BufferedReader br) throws IOException{
		this(br.readLine());
		filename = br.readLine();
		int count = Integer.parseInt(br.readLine());
		for (int i = 0; i < count; ++i) {
			String family = br.readLine();
			if (family.equals("dog"))
				addAnimal(new Dog(br));
			if (family.equals("cat"))
				addAnimal(new Cat(br));
		}
	}
	public Animal getAnimal(int index) throws IllegalArgumentException {
		if (index < 0) throw new IllegalArgumentException("Index cannot be negative.\n");
		return animals.get(index);
	}
	public void save(BufferedWriter bw) throws IOException{
		bw.write(name + '\n' + filename + '\n' + numAnimals() + '\n');
		for (Animal a : animals)
			a.save(bw);
	}
	
	public int numAnimals() { return animals.size();}
	public String getFilename() { return filename;}
	public void addAnimal(Animal animal){ animals.add(animal);}
	public void setFilename(String filename) { this.filename = filename;}
	
	@Override
	public String toString(){
		StringBuilder toString = new StringBuilder();
		for (Animal c: animals) {
			toString.append(c.toString());
		}
		return toString.toString();
	}
}