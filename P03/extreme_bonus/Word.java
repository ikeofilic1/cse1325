public class Word{
	
	private char[] letters =  new char[5];

	public Word(String word){
		for(int i = 0; i < letters.length; i++)
			letters[i] = word.charAt(i);
	}

	public char charAt(int position){
		return letters[position];
	}

	public void setCharAt(char c, int position){
		letters[position] = c;
	} 


	@Override
	public String toString(){
		String array = new String(letters);
		return array;
	}

	public static void main(String[] args) {
		Word jay = new Word("quint");
		System.out.print(jay);
	}
}
