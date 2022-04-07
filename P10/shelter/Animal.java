package shelter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

public abstract class Animal {
	private Gender gender;
	private int age;
	private String name;

	public Animal(String name, Gender gender, int age) throws IllegalArgumentException {
		if (age < 0) throw new IllegalArgumentException("Age cannot be negative.\n");
		this.age = age;
		this.gender = gender;
		this.name = name;
	}
	public Animal(BufferedReader br) throws IOException{		
		name = br.readLine();
		gender = Gender.valueOf(br.readLine());
		age = Integer.parseInt(br.readLine());
	}

	public abstract String family();
	public abstract String breed();
	public String getName() {return name;}
	public int getAge() {return age;}

	public void save(BufferedWriter bw) throws IOException {
		bw.write("" + family() + '\n' + name + '\n' + gender + '\n' + age + '\n');
	}
	@Override
	public String toString(){
		return name+" ("+((age > 0)?age:"< 1")+" year old "+gender+" "+breed()+" ";
	}
}