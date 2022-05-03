package shelter;
public enum BunnyBreed {
    MIN_LION("miniature Lion Lop"), VELVET("Velveteen Lop"), L_HEAD("Lionhead"),
    AMER_FUZZ("American Fuzzy"), HOLL("Holland Lop"), FLEM("Flemish Giant"), MINI("Mini Rex"),
    C_GIANT("Checkered Giant"), BRIT("Britannia Petite"), BLANC("Blanc de Hotot");

    private final String toString;
    private BunnyBreed(String x){ toString = x;} 

    @Override
    public String toString(){return toString;}
}