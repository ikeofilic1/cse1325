package shelter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.ListIterator;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

public class Shelter {
	private String name;
	private String filename;
	private ArrayList<Animal> animals;
	private ArrayList<Client> clients;
	private HashMap<Animal,Client> adoptions;

	public Shelter(String name) {
		this.name = name;
		this.filename = "";
		animals = new ArrayList<>();
		clients = new ArrayList<>();
		adoptions = new HashMap<>();
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

		count = Integer.parseInt(br.readLine());
		for (int i = 0; i < count; ++i) {
			Animal a;
			String family = br.readLine();
			if (family.equals("dog"))
				a = new Dog(br);
			else if (family.equals("cat"))
				a = new Cat(br);
			else if (family.equals("bunny"))
				a = new Bunny(br);
			else a = null;

			adoptions.put(a, new Client(br));
		}
	}	
	
	public void save(BufferedWriter bw) throws IOException{
		bw.write(name + '\n' + filename + '\n' + animals.size() + '\n');
		for (Animal a : animals) a.save(bw);

		bw.write("" + clients.size() + '\n');
		for (Client c : clients) c.save(bw);

		bw.write("" + adoptions.size() + '\n');
		Iterator<Animal> it = adoptedAnimalIterator();
		while(it.hasNext()) {
			Animal a = it.next();
			Client c = getAdoptedClient(a);
			a.save(bw);
			c.save(bw);
		}
	}
	
	public void addAnimal(Animal animal) { animals.add(animal);}
	public void addClient(Client client) { clients.add(client);}

	public String getFilename() { return filename;}
	public void setFilename(String filename) { this.filename = filename;}
	public Client getAdoptedClient(Animal animal) {	return adoptions.get(animal);}

	public Iterator<Animal> adoptedAnimalIterator() { return adoptions.keySet().iterator();}
	public ListIterator<Animal> animalListIterator() { return animals.listIterator();}
	public ListIterator<Client> clientListIterator() { return clients.listIterator();}

	public void adopt(Animal animal, Client client) {
		if (animals.remove(animal) && client != null)
			adoptions.put(animal, client);
	}

	public String clientsToString() {
		StringBuilder toString = new StringBuilder();
		for (Client c: clients) {
			toString.append(c.toString());
		}
		return toString.toString();
	}

	public String adoptionsToString() {
		StringBuilder toString = new StringBuilder();
		Iterator<Animal> it = adoptedAnimalIterator();

		while(it.hasNext()) {
			Animal a = it.next();
			Client c = getAdoptedClient(a);
			
			toString.append(a.toString());
			toString.deleteCharAt(toString.length() - 1);
			toString.append(" to " + c.toString());
		}
		return toString.toString();
	}
	
	@Override
	public String toString(){
		StringBuilder toString = new StringBuilder();
		for (Animal c: animals) {
			toString.append(c.toString());
		}
		return toString.toString();
	}
}