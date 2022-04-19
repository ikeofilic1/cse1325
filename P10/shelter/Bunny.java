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
        bw.write("" + breed.name() + '\n');
    }
    @Override
    public void create(Object breed, String name, Gender gender, int age) {
        this.age = age;
        this.gender = gender;
        this.name = name;
        this.breed = (BunnyBreed) breed;
    }
    @Override
    public String toString(){
        return super.toString() + "bunny" + ").\n";
    }
}