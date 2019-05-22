package Engine.pkgTests;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import Engine.pkgItems.Items;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestItems {

    private static Items testitems;

    @BeforeAll
    static void Initialize() {
        testitems= new Items();
    }

    @Test
    @DisplayName("Test create list items")
    void TestListItems() {
        testitems.LoadItems();
        assertEquals(3, testitems.getItemList().size());
    }

    @Test
    @DisplayName("Test get name from the second position from the list")
    void TestNameListItems() {
        testitems.LoadItems();
        Items i = (Items) testitems.getItemList().get(1);
        assertEquals("Sopa", i.getName());
    }

}
