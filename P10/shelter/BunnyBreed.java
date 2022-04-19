package shelter;
public enum BunnyBreed {
    MIN_LION("miniature lion lop"), VELVET("velveteen lop"), L_HEAD("lionhead"),
    AMER_FUZZ("american fuzzy"), HOLL("holland lop"), FLEM("flemish giant"), MINI("mini rex"),
    C_GIANT("checkered giant"), BRIT("britannia petite"), BLANC("blanc de hotot");

    private final String toString;
    private BunnyBreed(String x){ toString = x;} 

    @Override
    public String toString(){return toString;}
}