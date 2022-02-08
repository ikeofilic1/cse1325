import java.util.Scanner;

public class Quint {


	public static void main(String[] args) {
		int tries = 0;
		WordList list = new WordList();
		Puzzle game = new Puzzle(list.getWord());
		Scanner in = new Scanner(System.in);
		String guess = ".....", result = ".....";
		boolean isValidWord = false;

		System.out.println("=========");
		System.out.println("Q U I N T");
		System.out.println("=========");
		System.out.println("Guess a 5-letter word");
		System.out.print("guess ");

		while (!game.isSolved()) {
			do {
				try {
					guess = in.next();
					guess = guess.toUpperCase();
					result = game.compareGuess(guess);
					isValidWord = true;
				}
				catch (IllegalArgumentException e) {
					System.err.print("\t" + e.getMessage());
					System.err.print("Try again:\n     ");
					isValidWord = false;
				}
			} while(!isValidWord);

			tries++;			
			System.out.print(result + " ");
		}

		System.out.println("\nGuessed in " + tries + " tries.");

	}
}
