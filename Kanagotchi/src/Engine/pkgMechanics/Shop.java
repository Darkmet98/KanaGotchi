package Engine.pkgMechanics;

import Engine.pkgExceptions.InsufficientMoney;
import Engine.pkgItems.Items;

public class Shop {

    private Game GameValues;

    public Shop(Game game) {
        GameValues = game;
    }

    //When you buy a item
    public void Buy(int item_selected) throws InsufficientMoney {
        Items i = (Items) GameValues.getItem().getItemList().get(item_selected);

        //If the price are higher
        if(i.getPrice() > GameValues.getMoney()) throw new InsufficientMoney("No tienes el dinero suficiente para hacer esta compra");
        GameValues.setMoney(GameValues.getMoney() - i.getPrice());
        GameValues.getItemsOwned().replace(item_selected, GameValues.getItemsOwned().get(item_selected)+1);
    }
}
