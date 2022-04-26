package shelter;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

public class Client {
	private String name;
	private String phone;

	public Client(String name, String phone) throws IllegalArgumentException{
		if (name == null || name.equals("")) 
			throw new IllegalArgumentException("Name cannot be empty.\n");
		if (phone == null || phone.equals("")) 
			throw new IllegalArgumentException("Contact info cannot be left empty.\n");	
		this.name = name;
		this.phone = phone;
	}

	public Client(BufferedReader br) throws IOException {
		name = br.readLine();
		phone = br.readLine();
    }

    public void save(BufferedWriter bw) throws IOException {  
    	bw.write("" + name + '\n' + phone + '\n');
    }

    @Override
    public String toString(){
    	return name + " (" + phone + ")\n";
    }
}

//add regex for phone