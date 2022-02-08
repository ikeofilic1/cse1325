import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Word{		
	private char[] letters =  new char[5];
	Pattern pattern = Pattern.compile("([A-Z]+|[.]+|[ ]+)+");
	Matcher m;

	public Word(String word){
		if (word.length() != 5) 
			throw new IllegalArgumentException("Not 5 letters. ");

		m = pattern.matcher(word);
		if (!m.matches()) 
			throw new IllegalArgumentException("A character in the word \"" + word + "\" is not a letter. ");

		for(int i = 0; i < letters.length; i++)
			letters[i] = word.charAt(i);
	}

	public char charAt(int position){
		if (position < 0 || position > 4)  
			throw new IllegalArgumentException("Invalid index. ");
		return letters[position];
	}

	public void setCharAt(char c, int position){
		if (position < 0 || position > 4) 
			throw new IllegalArgumentException("Invalid index. ");

		if (c != '.' && c != ' ' && !(Character.isLetter(c))) 
			throw new IllegalArgumentException("Cannot set invalid char '" + Character.toString(c) + "'. ");

		letters[position] = c;
	} 


	@Override
	public String toString(){ return new String(letters); }	
}

//I couldn't make the data validation for setCharAt to check for only uppercase since we purposely 
//set the char in result to a lowercase of itself (to show a letter in the word but not in the right position)
