public class Puzzle {
	private Word solution;
	private boolean solved;	
	public boolean isSolved() { return solved; }

	public Puzzle(String solution) { 
		this.solution = new Word(solution);
		System.out.println("DEBUG " + solution); 
		solved = false;
	}

	public String compareGuess(String guess) {
	    // For actual and check, letters will be replaced with spaces when found
	    Word actual = new Word(solution.toString()); // Solution to the puzzle
	    Word check = new Word(guess);                // Guess at the solution
	    // For result, uppercase for correct, lower if in wrong position, '.' wrong
	    Word result = new Word(".....");             // The result to be returned

	    // Check for correct letter in correct position
	    for(int i=0; i<5; ++i) {
	        if(check.charAt(i) == actual.charAt(i)) {  // Correct letter!
	            result.setCharAt(Character.toUpperCase(solution.charAt(i)), i);
	            check.setCharAt(' ', i);
	            actual.setCharAt(' ', i);
	        }
	    }
	    
	    // Determine if guess was right
	    solved = (check.toString().equals("     "));
	    
	    // Check for correct letter in incorrect position
	    for(int i=0; i<5; ++i) {  // iterate through actual and result
	        for(int j=0; j<5; ++j) { // iterate through check (the guess)
	            if(check.charAt(j) == ' ') continue; // This letter isn't available anymore
	            if(check.charAt(j) == actual.charAt(i)) {  // Correct letter, wrong position
	                result.setCharAt(Character.toLowerCase(solution.charAt(i)), j);
	                check.setCharAt(' ', j);
	                actual.setCharAt(' ', i);
	                continue;
	            }
	        }
	    }
	    
	    return result.toString();
	}

}