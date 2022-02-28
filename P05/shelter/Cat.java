package shelter;
public class Cat extends Animal {
	private CatBreed breed;

	public Cat(String name, Gender gender, int age, CatBreed breed){
		super(name, gender, age);
		this.breed = breed;
	}
	
	@Override
	public String family() { return "cat";}
	@Override
	public String breed() { return ""+breed;}
}				