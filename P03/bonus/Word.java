import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Word{		
	private char[] letters =  new char[5];
	
	public void validateChar(char c){
		Pattern pattern = Pattern.compile("[[A-Z]?[.]?[ ]?]");
		Matcher m = pattern.matcher(Character.toString((Character.toUpperCase(c))));
		if (!m.matches()) 
			throw new IllegalArgumentException("Invalid character: \"" +
			 Character.toString(c) + "\". ");
	}

	public void validatePos(int position) {		
		if (position < 0 || position > 4)  
			throw new IllegalArgumentException("Invalid index. ");
	}

	public Word(String word){
		if (word.length() != 5) 
			throw new IllegalArgumentException("Not 5 letters. ");
		
		for(int i = 0; i < 5; i++)
			validateChar(word.charAt(i));

		letters = word.toUpperCase().toCharArray();
	}

	public char charAt(int position){
		validatePos(position);
		return letters[position];
	}

	public void setCharAt(char c, int position){
		validatePos(position);
		validateChar(c);
		letters[position] = c;
	} 

	@Override
	public String toString(){ return new String(letters); }	
}
