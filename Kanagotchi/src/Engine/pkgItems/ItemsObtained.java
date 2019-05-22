package Engine.pkgItems;

public class ItemsObtained {
    //Values
    private int Amount;
    private String Name;
    private int Health;
    private int Experience;

    /*
    * Initialize the class
     */
    public ItemsObtained(int amount, String name, int health, int experience) {
        setAmount(amount);
        setName(name);
        setHealth(health);
        setExperience(experience);
    }


    //Get set zone
    public int getAmount() {
        return Amount;
    }

    public void setAmount(int amount) {
        Amount = amount;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getHealth() {
        return Health;
    }

    public void setHealth(int health) {
        Health = health;
    }

    public int getExperience() {
        return Experience;
    }

    public void setExperience(int experience) {
        Experience = experience;
    }
}