public class Crypto {

    public static void main(String[] args) {
        String key;

        if (args.length > 1)
            System.err.print("Use ") 
    }

    public string randomKey() {
        char[] alphabet = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};        
        char[] key = new char[26];

        for (int i = 0; i < 26; i++)
            key[i] = alphabet[(int) (26 * Math.random())];

        return new String(key);
    }
    
    
}