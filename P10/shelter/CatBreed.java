package shelter;
public enum CatBreed {
	ABYSSINIAN("abyssinian"), CHARTREUX("chartreux"), RAGAMUFFIN("ragamuffin"), 
	RAGDOLL("ragdoll"), BRIT_SHORT("british shorthair"), EX_SHORT("exotic shorthair"), 
	SPHYNX("sphynx"), SIAMESE("siamese"), REX("cornish rex"), BENGAL("bengal"), 
	PERSIAN("persian"), JAVANESE("javanese"), BOMBAY("bombay"), MIX("mixed breed"), 
	BLUE("russian blue"), SAVANNAH("savannah"), VAN("turkish van");

	private final String toString;
	private CatBreed(String x){ toString = x;} 

	@Override
	public String toString(){return toString;}
}