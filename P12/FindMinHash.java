import java.util.ArrayList;
import java.util.Objects;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FindMinHash {
    // This nested class is the "struct" for which we search for the 
    //    smallest possible hashCode() 
    class WordWrapper {
        public WordWrapper() {
            word = "default";
            value = Integer.MAX_VALUE;
        }
        public WordWrapper(int word0, int word1) {
            word = wordList.get(word0) + " " + wordList.get(word1);
            value = word0 * word0 - 3 * word1 + 17;
        }
        @Override
        public int hashCode() {
            return Math.abs(Objects.hash(word, value)); // Not a normal hashCode!
        }
        public final String word;
        public final int value;
    }

    public void findMinHashSlice(int numThreads, long maxHashes) {        
        Thread[] threads = new Thread[numThreads];

        long start1 = 0;
        long step1 = maxHashes/numThreads;

        for (int i = 0; i < numThreads; ++i) {
            if (i == numThreads - 1) step1 = maxHashes - start1;
            final long start = start1;
            final long step = step1 - 1;
            threads[i] = new Thread(() -> search(start, step));
            threads[i].start();
            start1 += step1;          
        }

        for(Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                System.out.println("Interrupted Exception");
            }
        }

        System.out.println("Best word \"" + bestWord.word + "\" has hashCode " 
                         + String.format("%,d", bestWord.hashCode()));
    }

    public void findMinHashPool(int numThreads, long maxHashes) {
        Thread[] threads = new Thread[numThreads];

        for (int i = 0; i < numThreads; ++i) {
            threads[i] = new Thread(() -> {
                while (true) {
                    long start = getPacket(maxHashes);
                    if (start == -1) break;
                    long step = (maxHashes < start + 100) ? 
                                maxHashes - start - 1: 99;
                    this.search(start, step);
                }                
            });

            threads[i].start();       
        }

        for(Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                System.out.println("Interrupted Exception");
            }
        }

        System.out.println("Best word \"" + bestWord.word + "\" has hashCode " 
                         + String.format("%,d", bestWord.hashCode()));

    }

    public synchronized long getPacket(final long maxHashes) {
        index += 100;
        if (index >= maxHashes) return -1;

        return index;
    }
    
    // This constructor loads the word list needed by class WordWrapper
    public FindMinHash(String filename) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String word;
            while((word = br.readLine()) != null) wordList.add(word);
        } catch(Exception e) {
            throw new IOException("Unable to load word list from " + filename);
        }
    }
    
    // This method searches for smaller hashCodes from index start to start+number
    public void search(long start, long number) {
        System.out.printf("%-11s (ID %3s) searching %,15d to %,15d\n",
           Thread.currentThread().getName(), Thread.currentThread().getId(),
           start, start + number);
        int word0 = (int) (start % wordList.size());
        int word1 = (int) ((start / wordList.size()) % wordList.size());
        
        int bestHashCode = Integer.MAX_VALUE;
        while(number-- > 0) {
            WordWrapper candidate = new WordWrapper(word0, word1);
            if(candidate.hashCode() < bestHashCode) {
                bestHashCode = setBestWord(candidate);
            }
            if(++word0 >= wordList.size()) {
                word0 = 0;
                ++word1;
            }
        }
    }
    
    // Be sure to protect this method from thread interference!
    public synchronized int setBestWord(WordWrapper candidate) {
        if(candidate.hashCode() < bestWord.hashCode()) bestWord = candidate;
        return bestWord.hashCode();
    }
    
    // Main provides the user interface. The majority of changes will be made
    //   to this method.
    public static void main(String[] args) {
        if(2 > args.length || args.length > 3) {
            System.err.println("usage: java FindMinHash <#strings> <#threads> [filename]");
            System.exit(-1);
        }

        final long maxHashes = Long.parseLong(args[0]);
        final int numThreads = Integer.parseInt(args[1]);

        FindMinHash f = null;

        // Include a program argument here for number of threads (numThreads)

        try {
            f = new FindMinHash((args.length > 2) ? args[2] : "all-words.txt");
        } catch(IOException e) {
            System.err.println(e.getMessage());
            System.exit(-2);            
        }

        //f.findMinHashPool(numThreads, maxHashes);
        f.findMinHashSlice(numThreads, maxHashes);      
    }
    private long index = -100l; 
    private WordWrapper bestWord = new WordWrapper();
    private final ArrayList<String> wordList= new ArrayList<>();
}
