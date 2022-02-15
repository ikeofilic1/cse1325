import java.util.Scanner;
import cypher.Rot13;
import cypher.Substitution;
import cypher.Cypher;

public class Crypto {
    public static void main(String[] args) {
        String key = "";
        Cypher cypher = null;

        if (args.length > 1){
            System.err.println("Type \"java Crypto -h\" for usage");
            System.exit(-1);
        }
        if (args.length == 0){
            key = randomKey();
            System.out.println("Key = " + key);
        } 
        else{
           switch (args[0]) {
            case "-h":
                System.out.println("Usage: java Crypto [key | rot13]");
                System.exit(0);
                break;

            case "rot13":
                cypher = new Rot13();
                break;

            default:
                key = args[0];
                break;
            }            
        }
        if (cypher == null) {
            try {
                cypher = new Substitution(key);
            } 
            catch (IllegalArgumentException e){
                System.err.print(e.getMessage());
                System.exit(-1);
            }
        }

        boolean badOption;
        Scanner in = new Scanner(System.in);
        while(true) {
            do {
                badOption = false;
                System.out.print("\n(e)ncrypt, (d)ecrypt, or (q)uit? ");
                char c = in.next().charAt(0);
                in.nextLine();
                switch (c) {
                    case 'e':
                        System.out.println("Enter text to encrypt");
                        System.out.println(cypher.encrypt(in.nextLine()));
                        break;                       
    
                    case 'd':
                        System.out.println("Enter text to decrypt");
                        System.out.println(cypher.decrypt(in.nextLine()));
                        break;
    
                    case 'q':
                        System.exit(0);
                        break;
    
                    default:
                        badOption = true;
                        System.err.println("Not an option, try again!!");
                        break;    
                }
    
            } while (badOption);
        }
    }
    public static String randomKey() {
        char[] alphabet = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};        
        char[] key = new char[26];

        int i = 0;
        while (i < 26){
            int index = (int) (26 * Math.random());
            if (alphabet[index] == ' ') continue;
            key[i] = alphabet[index];
            alphabet[index] = ' ';
            i++;
        }

        return new String(key);
    }    
}