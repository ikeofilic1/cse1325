import java.util.Scanner;

public class Crypto {

    public static void main(String[] args) {
        String key = "";
        Substitution algorithm;

        if (args.length > 1){
            System.err.print("Type \"java Crypto -h\" for usage");
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
                break;

            case "rot13":
               // algorithm = new Rot13();
                //getAnswer(Substitution algo){algo.encrypt()}
                break;

            default:
                key = args[0];
                break;
            }            
        }
        
        boolean badOption;
        algorithm = new Substitution(key);        
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
                        System.out.println(algorithm.encrypt(in.nextLine()));
                        break;
    
                    case 'd':
                        System.out.println("Enter text to decrypt");
                        System.out.println(algorithm.decrypt(in.nextLine()));
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