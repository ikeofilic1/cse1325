package cypher;
/**
 * Specifies the interface to a cypher that encrpts and decrypts strings
 * 
 * @author              Ikechukwuka Ofili
 * @since               1.0
 * @version             1.0
 * @license.agreement   GNU General Public License 3.0 * 
 */
public interface Cypher {
     /**
     * Encrypt a string using the implemented cypher and key
     * 
     * @param unencrypted   String to be encrypted
     * @since 1.0
     * @return              The resulting encrypted string
     */
    public String encrypt(String unencrypted);
    /**
     * Decrypt a string using the implemented cypher and key
     * 
     * @param encrypted     String to be decrypted
     * @since 1.0
     * @return              The decrypted string
     */
    public String decrypt(String encrypted);
}