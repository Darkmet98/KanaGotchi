package Engine.pkgMechanics;

import Engine.pkgExceptions.InsufficientMoney;
import Engine.pkgExceptions.ItemNotSelected;
import Engine.pkgItems.Items;

public class Shop {

    //Values
    private Game GameValues;

    /*
    * Initialize the class
     */
    public Shop(Game game) {
        GameValues = game;
    }

    /*
    * Buy a item
     */
    public void Buy(Integer item_selected) throws InsufficientMoney, ItemNotSelected {
        if(item_selected == -1) throw new ItemNotSelected("No has seleccionado ningún objeto.");
        //Get the item
        Items i = (Items) GameValues.getItem().getItemList().get(item_selected);

        //If the price are higher
        if(i.getPrice() > GameValues.getMoney()) throw new InsufficientMoney("No tienes el dinero suficiente para hacer esta compra");
        //Reduce the actual money
        GameValues.setMoney(GameValues.getMoney() - i.getPrice());
        //Add the new item
        GameValues.getItemsOwned().replace(item_selected, GameValues.getItemsOwned().get(item_selected)+1);
    }
}
