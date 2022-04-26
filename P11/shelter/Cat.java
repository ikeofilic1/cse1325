package shelter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

public class Cat extends Animal {
	private CatBreed breed;

	public Cat(String name, Gender gender, int age, CatBreed breed){
		super(name, gender, age);
		this.breed = breed;
	}
	public Cat(BufferedReader br) throws IOException{
		super(br);
		this.breed = CatBreed.valueOf(br.readLine());
	}
	public Cat() {}
	
	@Override
	public String family() { return "cat";}
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
		this.breed = (CatBreed) breed;
	}
	@Override
	public String toString(){
		return super.toString() + (age < 1 ?"kitten":"cat") + ")\n";
	}
	@Override
	public boolean equals(Object obj) {
		if (! super.equals(obj)) return false;
		Cat check = (Cat) obj;
		return breed == check.breed;
	}
	@Override
	public int hashCode() {
		int hash = 3;
		hash = 37 * hash + name.hashCode();
		hash = 37 * hash + breed.hashCode();

		return hash;
	}
}				