package cypher;
/**
 * Implements the Rot13 cypher, rotating each letter by 13 alphabetic positions
 * 
 * @author              Ikechukwuka Ofili
 * @since               1.0
 * @version             1.0
 * @license.agreement   GNU General Public License 3.0 
 */
public class Rot13 extends Substitution {
    /**
     * Generates an instance of a cypher as a substitution cypher with a specific key
     * @since   1.0
     * */
    public Rot13() { super("nopqrstuvwxyzabcdefghijklm"); }
}