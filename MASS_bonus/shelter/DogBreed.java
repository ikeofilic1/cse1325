package shelter;
public enum DogBreed {
	CESKY("Cesky Terrier"), PULI("Puli"), POMERANIAN("Pomeranian"), BEAGLE("Beagle"), 
	POODLE("Poodle"), DACHSHUND("Dachshund"), SHIH_TZU("Shih Tzu"), G_DANE("Great Dane"), 
	S_HUSKY("Siberian Husky"), CHIHUAHUA("Chihuahua"), PUG("Pug"), BOXER("Boxer"), 
	G_RETRIEVER("Golden Retriever"), COLLIE("Collie"), SHIBA("Shiba Inu"), 
	SHEPHERD("Golden Shepherd"), MIX("Mix-breed");

	private final String toString;
	private DogBreed(String x){ toString = x;} 

	@Override
	public String toString(){return toString;}
}