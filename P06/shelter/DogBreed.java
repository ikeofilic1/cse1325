package shelter;
public enum DogBreed {
	CESKY("cesky terrier"), PULI("puli"), POMERANIAN("pomeranian");

	private final String toString;
	private DogBreed(String x){ toString = x;} 

	@Override
	public String toString(){return toString;}
}