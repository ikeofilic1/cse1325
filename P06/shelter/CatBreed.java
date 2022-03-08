package shelter;
public enum CatBreed {
	ABYSSINIAN("abyssinian"), CHARTREUX("chartreux"), RAGAMUFFIN("ragamuffin");

	private final String toString;
	private CatBreed(String x){ toString = x;} 

	@Override
	public String toString(){return toString;}
}