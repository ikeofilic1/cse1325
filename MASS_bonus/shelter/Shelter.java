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

	private static final String cat = (new Cat()).family();
    private static final String dog = (new Dog()).family();
    private static final String bun  = (new Bunny()).family();

	public Shelter(String name) {
		if (name.length() == 0) 
			throw new IllegalArgumentException("Name cannot be empty\n");
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
		while(count-- > 0) {
            animals.add(readAnimal(br));
        }

        count = Integer.parseInt(br.readLine());
        while(count-- > 0) {
            clients.add(new Client(br));
        }

        /*count = Integer.parseInt(br.readLine());
        while(count-- > 0) {
            Animal a = readAnimal(br);
            int index = Integer.parseInt(br.readLine());
            adoptions.put(a, clients.get(index));
        }*/

		count = Integer.parseInt(br.readLine());
		while (count-- > 0) {
			Animal a = readAnimal(br);
			adoptions.put(a, new Client(br));
		}
	}	

	private Animal readAnimal(BufferedReader br) throws IOException {
        String f = br.readLine();
        if(f.equals(cat)) return new Cat(br);
        else if(f.equals(dog)) return new Dog(br);
        else if(f.equals(bun)) return new Bunny(br);
        else throw new IOException("Invalid family: " + f);
    }

	public void save(BufferedWriter bw) throws IOException{
		bw.write(name + '\n' + filename + '\n' + animals.size() + '\n');
		for (Animal a : animals) a.save(bw);

		bw.write("" + clients.size() + '\n');
		for (Client c : clients) c.save(bw);

		bw.write("" + adoptions.size() + '\n');
		for(Animal a : adoptions.keySet()) {
			a.save(bw);
			getAdoptedClient(a).save(bw);
		}
	}
	
	public void addAnimal(Animal animal) { 
		if (animals.contains(animal) || adoptions.containsKey(animal))
			throw new IllegalArgumentException("" + animal + "is already in the shelter");
		animals.add(animal);
	}

	public String getFilename() { return filename;}
	public void setFilename(String filename) { this.filename = filename;}
	public void addClient(Client client) { clients.add(client);}
	public Client getAdoptedClient(Animal animal) {	return adoptions.get(animal);}

	public Iterator<Animal> adoptedAnimalIterator() { return adoptions.keySet().iterator();}
	public ListIterator<Animal> animalListIterator() { return animals.listIterator();}
	public ListIterator<Client> clientListIterator() { return clients.listIterator();}

	public void adopt(Animal animal, Client client) {
		if (animals.isEmpty())
			throw new IllegalArgumentException("No animals available for adoption. Add a new animal first");
		if (clients.isEmpty())
			throw new IllegalArgumentException("Add a client to adopt animals");
		if(adoptions.containsKey(animal)) 
            throw new IllegalArgumentException("Already adopted: " + animal);
        
		if (animals.remove(animal) && client != null)
			adoptions.put(animal, client);
	}

	public boolean isEmpty() {
		return animals.isEmpty() && 
		clients.isEmpty() && adoptions.isEmpty();  
	}

	public String clientsToString() {
		StringBuilder toString = new StringBuilder();
		for (Client c: clients) {
			toString.append(c.toString() + "\n");
		}
		return toString.toString();
	}

	public String adoptionsToString() {
		StringBuilder toString = new StringBuilder();
		
		for(Animal a : adoptions.keySet()) {			
			toString.append(a.toString());
			toString.append(" to " + adoptions.get(a) + "\n");
		}
		return toString.toString();
	}
	
	@Override
	public String toString(){
		StringBuilder toString = new StringBuilder();
		for (Animal a: animals) {
			toString.append(a.toString() + "\n");
		}
		return toString.toString();
	}
}