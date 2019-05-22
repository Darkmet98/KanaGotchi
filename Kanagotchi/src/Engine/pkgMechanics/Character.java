package Engine.pkgMechanics;

import Engine.pkgExceptions.ItemIsZero;
import Engine.pkgExceptions.ItemNotSelected;
import Engine.pkgItems.Items;

public class Character {

    /*
     * Status explanation
     *
     * 0 = angry        Health 1-24
     * 1 = bad          Health 25-44
     * 2 = normal       Health 45-64
     * 3 = happy        Health 65-84
     * 4 = Very happy   Health 85-100
     */

    //Values
    private Game GameValues;

    /*
    * Initialize the class
     */
    public Character(Game game) {
        GameValues = game;
    }

    /*
    * The character eat food
     */
    public void EatFood(Integer selected) throws ItemIsZero, ItemNotSelected {
        if(selected == -1) throw new ItemNotSelected("No has seleccionado ningún objeto.");
        if(GameValues.getItemsOwned().get(selected) == 0) throw new ItemIsZero("No tienes ese objeto.");
        //Decrease in one the item
        GameValues.getItemsOwned().replace(selected, GameValues.getItemsOwned().get(selected)-1);
        //Recover health
        Items i = (Items) GameValues.getItem().getItemList().get(selected);
        RecoveryHealth(i.getHealthRecovered());
        //Get some experience from the food
        GameValues.setExperience(i.getExperience()+GameValues.getExperience());
        LevelUp();
    }

    /*
    * Recover health
     */
    public void RecoveryHealth(int recovered) {
        GameValues.setHealth(GameValues.getHealth()+recovered);
        if (GameValues.getHealth() > 100) GameValues.setHealth(100);
    }

    /*
    * Level up the character
     */
    public void LevelUp() {
        if(GameValues.getExperience() > 100) {
            GameValues.setPlayerLevel(GameValues.getPlayerLevel()+1);
            GameValues.setExperience(0);
        }
    }
}
