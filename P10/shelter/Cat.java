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
	public String toString(){
		return super.toString() + (super.getAge() < 1 ?"kitten":"cat") + ").\n";
	}
}				