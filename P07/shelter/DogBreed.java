package shelter;
public enum DogBreed {
	CESKY("cesky terrier"), PULI("puli"), POMERANIAN("pomeranian"), BEAGLE("beagle"), 
	POODLE("poodle"), DACHSHUND("dachshund"), SHIH_TZU("shih tzu"), G_DANE("great dane"), 
	S_HUSKY("siberian husky"), CHIHUAHUA("chihuahua"), PUG("pug"), BOXER("boxer"), 
	G_RETRIEVER("golden retriever"), COLLIE("collie"), SHIBA("shiba inu");

	private final String toString;
	private DogBreed(String x){ toString = x;} 

	@Override
	public String toString(){return toString;}
}