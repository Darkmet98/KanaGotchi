package pkgTests;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pkgExceptions.InsufficientMoney;
import pkgExceptions.ItemIsZero;
import pkgMechanics.Game;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class TestInGame {

    private static Game testingame;

    @BeforeAll
    static void Initialize() {
        testingame= new Game();
    }

    @Test
    @DisplayName("Test new game works.")
    void TestNewGame() {
        testingame.NewGame();
        assertAll(
                //Check the health
                ()-> assertEquals(100, testingame.getHealth()),
                //Check the money
                ()-> assertEquals(100, testingame.getMoney()),
                //Check the Status
                ()-> assertEquals(4, testingame.getStatus()),
                //Check the list of items owned
                ()-> assertEquals(3, testingame.getItemsOwned().size()),
                //Check if the game have a item from the first save
                ()-> assertEquals(2, testingame.getItemsOwned().get(0))
        );
    }

    @Test
    @DisplayName("Buy a item")
    void TestBuy() throws InsufficientMoney {
        testingame.NewGame();
        testingame.Buy(1);
        assertAll(
                ()-> assertEquals(80, testingame.getMoney()),
                ()->assertEquals(3, testingame.getItemsOwned().get(1)),
                ()-> assertThrows(InsufficientMoney.class, ()-> {
                    testingame.Buy(1);
                    testingame.Buy(1);
                    testingame.Buy(1);
                    testingame.Buy(1);
                    testingame.Buy(1);
                })

        );
    }

    @Test
    @DisplayName("Test health")
    void TestHealth() {
        testingame.NewGame();
        testingame.DecreaseHealth();
        assertEquals(99, testingame.getHealth());
    }

    @Test
    @DisplayName("Test all status")
    void TestStatus() {
        testingame.NewGame();
        assertAll(
                () -> {
                    testingame.setHealth(50);
                    testingame.ChangeStatus();
                    assertEquals(2, testingame.getStatus());
                },

                () -> {
                    testingame.setHealth(80);
                    testingame.ChangeStatus();
                    assertEquals(3, testingame.getStatus());
                },
                () -> {
                    testingame.setHealth(30);
                    testingame.ChangeStatus();
                    assertEquals(1, testingame.getStatus());
                },
                () -> {
                    testingame.setHealth(5);
                    testingame.ChangeStatus();
                    assertEquals(0, testingame.getStatus());
                },
                () -> {
                    testingame.setHealth(95);
                    testingame.ChangeStatus();
                    assertEquals(4, testingame.getStatus());
                }
        );
    }

    @Test
    @DisplayName("Test recovery health")
    void TestRecoveryHealth() {
        testingame.NewGame();
        assertAll(
                ()-> {
                    testingame.setHealth(45);
                    testingame.RecoveryHealth(40);
                    assertEquals(85, testingame.getHealth());
                },
                ()-> {
                    testingame.RecoveryHealth(400);
                    assertEquals(100, testingame.getHealth());
                }
        );
    }

    @Test
    @DisplayName("Test eat food")
    void TestEatFood() throws ItemIsZero {
        testingame.NewGame();
        testingame.setHealth(20);
        testingame.EatFood(1);
        assertAll(
                ()-> assertEquals(60, testingame.getHealth()),
                ()-> assertEquals(1, testingame.getItemsOwned().get(1)),
                ()->  {
                    testingame.EatFood(1);
                    assertThrows(ItemIsZero.class, ()-> testingame.EatFood(1));
                },
                ()-> assertThrows(ItemIsZero.class, ()->testingame.EatFood(2))
        );
    }

    @Test
    @DisplayName("Test save game")
    void TestSaveGame() throws IOException {
        testingame.NewGame();
        testingame.setMoney(0x4565647);
        testingame.save();
        assertEquals(true, testingame.getSaveformat().getSaveFile().exists());
    }

    /*@Test
    @DisplayName("Test health time event.")
    void TestHealthEvent() throws InterruptedException {
        testingame.NewGame();
        //TimeUnit.MINUTES.sleep(2);
        //TimeUnit.MINUTES.wait(2);
        assertEquals(99, testingame.getHealth());
    }*/
}
