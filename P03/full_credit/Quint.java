import java.util.Scanner;

public class Quint {
	public static void main(String[] args) {
		int tries = 0;
		WordList list = new WordList();
		Puzzle game = new Puzzle(list.getWord());
		Scanner in = new Scanner(System.in);
		String guess, result;


		System.out.println("=====");
		System.out.println("QUINT");
		System.out.println("=====");
		System.out.println("Guess a 5-letter word");
		System.out.print("guess ");

		while (!game.isSolved()) {
			guess = in.next();
			guess = guess.toUpperCase();

			result = game.compareGuess(guess);
			tries++;
			System.out.print(result + " ");
		}

		System.out.println("\nGuessed in " + tries + " tries.");

	}
}