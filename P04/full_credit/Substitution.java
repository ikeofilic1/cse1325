import java.util.Arrays;

public class Substitution implements Cypher{
    protected char[] encryptKey = new char[26];
    protected char[] decryptKey = new char[26];

    public Substitution(String key) {
        if (key.length() != 26)
            throw new IllegalArgumentException("Invalid key: key must be 26 letters");
        
        key = key.toUpperCase();
        char[] temp = key.toCharArray();
        Arrays.sort(temp);

        if (!("ABCDEFGHIJKLMNOPQRSTUVWXYZ".equals(new String(temp)))) 
            throw new IllegalArgumentException("Invalid key: key must contain all letters of the alphabet");

        encryptKey = key.toCharArray();
        System.out.print(key);

        decryptKey = encrypt(key).toCharArray();
        System.out.println(" " + new String(decryptKey));
    }

    protected String substitute(char[] key, String unsubstituted) {
        char[] substituted = unsubstituted.toUpperCase().toCharArray();

        for (int i = 0; i < substituted.length; i++) {
            if (Character.isLetter(substituted[i]))
                substituted[i] = key[(substituted[i] - 'A')];

        }
        return new String(substituted);
    }

    public String encrypt(String unencrypted) {
        return substitute(encryptKey, unencrypted);
    }

    public String decrypt(String encrypted) {
        return substitute(decryptKey, encrypted);
    }
}