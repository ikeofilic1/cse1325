import shelter.*;
public class Mass {
	public static void main(String[] args) {
		Dog jay = new Dog("Jay", Gender.valueOf("male"), 4, DogBreed.POMERANIAN);
		System.out.print(jay);
	}
}