package Engine.pkgMechanics;

import Engine.pkgExceptions.ItemIsZero;
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

    private Game GameValues;
    public Character(Game game) {
        GameValues = game;
    }
    //When the character eat food
    public void EatFood(int selected) throws ItemIsZero {
        if(GameValues.getItemsOwned().get(selected) == 0) throw new ItemIsZero("Actualmente no tienes ese objeto");
        //Se reduce en uno su cantidad
        GameValues.getItemsOwned().replace(selected, GameValues.getItemsOwned().get(selected)-1);
        //Se obtiene lo que cura y se cura el personaje
        Items i = (Items) GameValues.getItem().getItemList().get(selected);
        RecoveryHealth(i.getHealthRecovered());
        GameValues.setExperience(i.getExperience()+GameValues.getExperience());
        LevelUp();
    }

    public void RecoveryHealth(int recovered) {
        GameValues.setHealth(GameValues.getHealth()+recovered);
        if (GameValues.getHealth() > 100) GameValues.setHealth(100);
    }

    public void LevelUp() {
        if(GameValues.getExperience() > 100) {
            GameValues.setPlayerLevel(GameValues.getPlayerLevel()+1);
            GameValues.setExperience(0);
        }
    }
}
