package shelter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

public abstract class Animal {
	protected Gender gender;
	protected int age;
	protected String name;

	public Animal(String name, Gender gender, int age) throws IllegalArgumentException {
		if (age < 0) throw new IllegalArgumentException("Age cannot be negative\n");
		if (name.equals("")) throw new IllegalArgumentException("Name cannot be empty\n");
		this.age = age;
		this.gender = gender;
		this.name = name;
	}
	public Animal(BufferedReader br) throws IOException{		
		name = br.readLine();
		gender = Gender.valueOf(br.readLine());
		age = Integer.parseInt(br.readLine());
	}
	public Animal() {}

	public abstract String family();
	public abstract String breed();
	public abstract void create(Object breed, String name, Gender gender, int age);

	public void save(BufferedWriter bw) throws IOException {
		bw.write("" + family() + '\n' + name + '\n' + gender + '\n' + age + '\n');
	}

	@Override
	public String toString() {
		return name +" ("+ age +" year old "+gender+" "+breed()+" ";
	}

	@Override
	public boolean equals(Object obj) {
		Animal check = (Animal) obj;
		return name.equals(check.name) && 
		age == check.age && gender == check.gender; 
	}
}

//move tostring breed to subclass
