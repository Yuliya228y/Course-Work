package Classes;

import java.util.ArrayList;
import java.util.List;

public class Shop {
    private int balance;
    private String shop;
    private Pet pet;
    public List<Pet> petList = new ArrayList<>();

    public Shop(){
        setBalance(10000);
        setShop("Shop");
    }


    public int getBalance() { return balance; }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public String getShop() {
        return shop;
    }

    public void setShop(String shop) {
        this.shop = shop;
    }
    public void Add(Pet pet) {
        petList.add(pet);
    }
    public void Remove(Pet pet) {
        petList.remove(pet);
    }

}

