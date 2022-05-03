package shelter;
public enum CatBreed {
	ABYSSINIAN("Abyssinian"), CHARTREUX("Chartreux"), RAGAMUFFIN("Ragamuffin"), 
	RAGDOLL("Ragdoll"), BRIT_SHORT("British Shorthair"), EX_SHORT("Exotic Shorthair"), 
	SPHYNX("Sphynx"), SIAMESE("Siamese"), REX("Cornish Rex"), BENGAL("Bengal"), 
	PERSIAN("Persian"), JAVANESE("Javanese"), BOMBAY("Bombay"), MIX("Mix-breed"), 
	BLUE("Russian Blue"), SAVANNAH("Savannah"), VAN("Turkish van");

	private final String toString;
	private CatBreed(String x){ toString = x;} 

	@Override
	public String toString(){return toString;}
}