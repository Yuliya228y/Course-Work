package sample;

import java.util.ArrayList;
import java.util.List;

class Pet {
    private int cost;
    private int id;
    private String name;
    private String breed;
    List<Pet> petList = new ArrayList<>();

    public Pet(){
        cost = 100;
        name = "noname";
        breed = "nobreed";
    }

    public Pet(int cost, int id, String name, String breed){
        setCost(cost);
        setId(id);
        setName(name);
        setBreed(breed);
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public void add(Pet pet){
        petList.add(pet);
    }

    public void sold(Pet pet){
        petList.remove(pet.getId());
    }

}

public class Controller extends Pet{

}
