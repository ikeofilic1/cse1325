import java.util.Scanner;

public class Quint {

	public enum ColorCode {
		RED_BACKGROUND   ("\u001B[41m"),
		GREEN_BACKGROUND ("\u001B[42m"),
		YELLOW_BACKGROUND("\u001B[43m"),
		GREY_BACKGROUND  ("\u001B[100m"),
		RESET            ("\u001B[0m");

		private final String ansiCode;

		ColorCode(String ansiCode) { this.ansiCode = ansiCode; }

		@Override
		public String toString() { return ansiCode; }
	}

	public static void main(String[] args) {
		int tries = 0;
		WordList list = new WordList();
		Puzzle game = new Puzzle(list.getWord());
		Scanner in = new Scanner(System.in);
		String guess = ".....", result = ".....";
		ColorCode color = ColorCode.RESET;
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
					System.err.print("Try again:\n\t");
					isValidWord = false;
				}
			} while(!isValidWord);

			tries++;
			for (int i = 0; i < result.length(); i++) {
				char ch = result.charAt(i);

				if (ch == '.')
					color = ColorCode.GREY_BACKGROUND;
				if (Character.isUpperCase(ch))
					color = ColorCode.GREEN_BACKGROUND;
				if (Character.isLowerCase(ch))
					color = ColorCode.YELLOW_BACKGROUND;

				System.out.print(color + Character.toString(guess.charAt(i)) + ColorCode.RESET);			
			}

			System.out.print(" ");
		}

		System.out.println("\nGuessed in " + tries + " tries.");

	}
}
