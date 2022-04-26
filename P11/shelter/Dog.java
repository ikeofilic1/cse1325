package shelter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

public class Dog extends Animal {
	private DogBreed breed;

	public Dog(String name, Gender gender, int age, DogBreed breed){
		super(name, gender, age);
		this.breed = breed;
	}
	public Dog(BufferedReader br) throws IOException{
		super(br);
		this.breed = DogBreed.valueOf(br.readLine());
	}
	public Dog() {}
	
	@Override
	public String family() { return "dog";}
	@Override
	public String breed() { return ""+breed;}
	@Override
	public void save(BufferedWriter bw) throws IOException{
		super.save(bw);
		bw.write("" + breed.name() + '\n');
	}
	@Override
	public void create(Object breed, String name, Gender gender, int age) {
		if (age < 0) throw new IllegalArgumentException("Age cannot be negative.\n");
		if (name.equals("")) throw new IllegalArgumentException("Name cannot be empty.\n");
		this.age = age;
		this.gender = gender;
		this.name = name;
		this.breed = (DogBreed) breed;
	}
	@Override
	public String toString(){
		return super.toString() + (age < 2 ?"pup":"dog") + ")\n";
	}
	@Override
	public boolean equals(Object obj) {
		if (! super.equals(obj)) return false;
		Dog check = (Dog) obj;
		return breed == check.breed;
	}
	@Override
	public int hashCode() {
		int hash = 19;
		hash = 37 * hash + name.hashCode();
		hash = 37 * hash + breed.hashCode();

		return hash;
	}
}