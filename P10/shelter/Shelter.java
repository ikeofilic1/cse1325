package shelter;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

public class Shelter {
	private String name;
	private String filename;
	private ArrayList<Animal> animals;
	private ArrayList<Client> clients;

	public Shelter(String name) {
		this.name = name;
		this.filename = "";
		animals = new ArrayList<>();
		clients = new ArrayList<>();
	}
	public Shelter(BufferedReader br) throws IOException{
		this(br.readLine());
		filename = br.readLine();
		int count = Integer.parseInt(br.readLine());
		for (int i = 0; i < count; ++i) {
			String family = br.readLine();
			if (family.equals("dog"))
				addAnimal(new Dog(br));
			if (family.equals("cat"))
				addAnimal(new Cat(br));
			if (family.equals("bunny"))
				addAnimal(new Bunny(br));
		}
		count = Integer.parseInt(br.readLine());
		for (int i = 0; i < count; ++i) {
			addClient(new Client(br));
		}
	}

	public String clientsToString() {
		StringBuilder toString = new StringBuilder();
		for (Client c: clients) {
			toString.append(c.toString());
		}
		return toString.toString();
	}

	public Animal getAnimal(int index) throws IllegalArgumentException {
		if (index < 0) throw new IllegalArgumentException("Index cannot be negative.\n");
		return animals.get(index);
	}
	public void save(BufferedWriter bw) throws IOException{
		bw.write(name + '\n' + filename + '\n' + animals.size() + '\n');
		for (Animal a : animals) a.save(bw);

		bw.write("" + clients.size() + '\n');
		for (Client c : clients) c.save(bw);
	}
	
	public void addAnimal(Animal animal) { animals.add(animal);}
	public void addClient(Client client) { clients.add(client);}
	public String getFilename() { return filename;}
	public void setFilename(String filename) { this.filename = filename;}
	
	@Override
	public String toString(){
		StringBuilder toString = new StringBuilder();
		for (Animal c: animals) {
			toString.append(c.toString());
		}
		return toString.toString();
	}
}