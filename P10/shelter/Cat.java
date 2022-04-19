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
		this.age = age;
		this.gender = gender;
		this.name = name;
		this.breed = (CatBreed) breed;
	}
	@Override
	public String toString(){
		return super.toString() + (age < 1 ?"kitten":"cat") + ").\n";
	}
}				