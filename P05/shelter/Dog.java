package shelter;
public class Dog extends Animal {
	private DogBreed breed;

	public Dog(String name, Gender gender, int age, DogBreed breed){
		super(name, gender, age);
		this.breed = breed;
	}
	
	@Override
	public String family() { return "dog";}
	@Override
	public String breed() { return ""+breed;}
}				