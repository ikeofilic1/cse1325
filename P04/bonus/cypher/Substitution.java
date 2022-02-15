package cypher;
import java.util.Arrays;

/**
 * Implements a simple substitution cypher
 * 
 * @author              Ikechukwuka Ofili
 * @since               1.0
 * @version             1.0
 * @license.agreement   GNU General Public License 3.0
 * 
 */

public class Substitution implements Cypher{
    protected char[] encryptKey = new char[26];
    protected char[] decryptKey = new char[26];

    /**
     * Creates an instance of a substitution cypher for the specified key
     * 
     * @param key   The key for the substitution cypher
     * @since       1.0 
     * */
    public Substitution(String key) {        
        key = key.toUpperCase();
        char[] temp = key.toCharArray();
        Arrays.sort(temp);

        if (key.length() != 26)
            throw new IllegalArgumentException("Invalid key: key must be 26 letters\n");

        if (!("ABCDEFGHIJKLMNOPQRSTUVWXYZ".equals(new String(temp)))) 
            throw new IllegalArgumentException("Invalid key: key must contain all letters of the alphabet\n");

        char k = 'A';
        encryptKey = key.toCharArray();
        for (char c : encryptKey)
            decryptKey[c - 'A'] = k++;

    }

    protected String substitute(char[] key, String unsubstituted) {
        char[] substituted = unsubstituted.toUpperCase().toCharArray();

        for (int i = 0; i < substituted.length; i++) {
            if (Character.isLetter(substituted[i]))
                substituted[i] = key[(substituted[i] - 'A')];

        }
        return new String(substituted);
    }
    /**
     * Encrypts a string using a substitution cypher and the specified key
     * 
     * @since               1.0
     * */
    public String encrypt(String unencrypted) {
        return substitute(encryptKey, unencrypted);
    }
    /**
     * Decrypts a string using a substitution cypher and the specified key
     * 
     * @since               1.0
     * */
    public String decrypt(String encrypted) {
        return substitute(decryptKey, encrypted);
    }
}