package shelter;
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

	public abstract String family();
	public abstract String breed();

	@Override
	public String toString(){
		return name+" ("+age+" year old "+gender+" "+breed()+" "+family()+").\n";
	}
}