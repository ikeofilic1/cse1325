import shelter.*;

public class Mass {
	public static void main(String[] args) {
		Gender[] genders = Gender.values();
		DogBreed[] dbreeds = DogBreed.values();
		CatBreed[] cbreeds = CatBreed.values();
		Shelter udc = new Shelter("UTA Dogs & Cats");
		
		Animal animal;
		int coin = 0, count = 7;
		for (int i = 1; i <= count; ++i) {
			String dogName = "Dog_"+i, catName = "Cat_"+i;
			Gender gender = genders[(int)(Math.random()*genders.length)];

			coin = (int)(Math.random() + 0.57);
			if (coin == 0) {
				int age = (int)(12*Math.random());
				DogBreed breed = dbreeds[(int)(dbreeds.length*Math.random())];
				animal = new Dog(dogName, gender, age, breed);
			}
			else {
				int age = (int)(16*Math.random());
				CatBreed breed = cbreeds[(int)(cbreeds.length*Math.random())];
				animal = new Cat(catName, gender, age, breed);
			}

			udc.addAnimal(animal);
		}

		System.out.print(udc);		
	}
}