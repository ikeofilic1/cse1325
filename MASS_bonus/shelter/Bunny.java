package shelter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

public class Bunny extends Animal{
    private BunnyBreed breed;

    public Bunny(String name, Gender gender, int age, BunnyBreed breed){
        super(name, gender, age);
        this.breed = breed;
    }
    public Bunny(BufferedReader br) throws IOException{
        super(br);
        this.breed = BunnyBreed.valueOf(br.readLine());
    }
    public Bunny() {}
    
    @Override
    public String family() { return "bunny";}
    @Override
    public String breed() { return ""+breed;}
    @Override
    public void save(BufferedWriter bw) throws IOException{
        super.save(bw);
        bw.write(breed.name() + '\n');
    }
    @Override
    public void create(Object breed, String name, Gender gender, int age) {
        if (age < 0) throw new IllegalArgumentException("Age cannot be negative.\n");
        if (name.equals("")) throw new IllegalArgumentException("Name cannot be empty.\n");
        this.age = age;
        this.gender = gender;
        this.name = name;
        this.breed = (BunnyBreed) breed;
    }
    @Override
    public String toString(){
        return super.toString() + " " + breed + " " + "bunny" + ")";
    }
    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || this.getClass() != obj.getClass()) return false;
        
        Bunny check = (Bunny) obj;
        return name.equals(check.name) && age == check.age 
            && gender == check.gender && breed == check.breed;
    }
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + name.hashCode();
        hash = 37 * hash + breed.hashCode();

        return hash;
    }
}