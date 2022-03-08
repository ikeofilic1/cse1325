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
	@Override
	public String toString(){
		return super.toString() + (super.getAge() < 2 ?"pup":"dog") + ").\n";
	}
}