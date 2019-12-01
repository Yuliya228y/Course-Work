package Classes;

import java.util.ArrayList;
import java.util.List;

public class Shop {
    private int balance;
    private String shop;
    private int ID;

    public Shop(){
        setBalance(10000);
        setShop("Shop");
    }

    public Shop(int ID, int Balance, String name){
        setID(ID);
        setBalance(Balance);
        setShop(name);
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

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }
}

