package pkgMechanics;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Items {

    //Values
    private String Name;
    private int Price;
    private int HealthRecovered;
    private String Image;
    private static List ItemList;

    public Items() {
        LoadItems();
    }

    public Items(String name, int price, int healthRecovered, String image) {
        setName(name);
        setPrice(price);
        setHealthRecovered(healthRecovered);
        setImage(image);
    }

    //http://www.java67.com/2015/08/how-to-load-data-from-csv-file-in-java.html

    public void LoadItems() {
        setItemList(readItemsFromCSV());
    }

    public List<Items> readItemsFromCSV() {
        List<Items> temp = new ArrayList<>();

        try (BufferedReader br = Files.newBufferedReader(Paths.get("/home/darkmet/IdeaProjects/KanaGotchi/Kanagotchi/resources/Media/Data/Items.csv"),
                StandardCharsets.UTF_8)) {

            // Read the first line from the text file.
            String line = br.readLine();

            // Loop until all lines are read.
            while (line != null) {

                /*
                * Use string.split to load a string array with the values from
                * each line of
                * the file, using a comma as the delimiter.
                */
                String[] attributes = line.split(",");

                //Load the item
                Items item = loadItem(attributes);

                // Adding the item into ArrayList.
                temp.add(item);

                // Read next line before looping
                // if end of file reached, line would be null.
                line = br.readLine();
            }

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        return temp;
    }

    private static Items loadItem(String[] metadata) {
        String name = metadata[0];
        int price = Integer.parseInt(metadata[1]);
        int health = Integer.parseInt(metadata[2]);
        String image = "/Media/Images/Icons/"+metadata[3];

        // create and return the item from the metadata
        return new Items(name, price, health, image);
    }

    //Get Set Zone
    public String getName() { return Name; }
    public void setName(String name) { Name = name; }
    public int getPrice() { return Price; }
    public void setPrice(int price) { Price = price; }
    public int getHealthRecovered() { return HealthRecovered; }
    public void setHealthRecovered(int healthRecovered) { HealthRecovered = healthRecovered; }
    public String getImage() { return Image; }
    public void setImage(String image) { Image = image; }
    public List getItemList() { return ItemList; }
    public void setItemList(List itemList) { ItemList = itemList; }
}
