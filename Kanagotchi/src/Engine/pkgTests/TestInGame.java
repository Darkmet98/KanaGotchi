package Engine.pkgTests;

import Engine.pkgExceptions.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import Engine.pkgMechanics.Game;

import java.io.IOException;

import static junit.framework.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

class TestInGame {

    private static Game testingame;

    @BeforeAll
    static void Initialize() {
        testingame= new Game();
    }

    @Test
    @DisplayName("Test new game works.")
    void TestNewGame() {
        testingame.NewGame(true);
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
                ()-> assertEquals(2, testingame.getItemsOwned().get(0).intValue())
        );
    }

    @Test
    @DisplayName("Shop a item")
    void TestBuy() throws InsufficientMoney, ItemNotSelected {
        testingame.NewGame(true);
        testingame.Buy(1);
        assertAll(
                ()-> assertEquals(85, testingame.getMoney()),
                ()->assertEquals(3, testingame.getItemsOwned().get(1).intValue()),
                ()-> assertThrows(InsufficientMoney.class, ()-> {
                    for(int i = 0; i < 15; i++) testingame.Buy(1);
                })

        );
    }

    @Test
    @DisplayName("Test health")
    void TestHealth() {
        testingame.NewGame(true);
        testingame.DecreaseHealth();
        assertEquals(99, testingame.getHealth());
    }

    @Test
    @DisplayName("Test all status")
    void TestStatus() {
        testingame.NewGame(true);
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
        testingame.NewGame(true);
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
    void TestEatFood() throws ItemIsZero, ItemNotSelected {
        testingame.NewGame(true);
        testingame.setHealth(20);
        testingame.EatFood(1);
        assertAll(
                ()-> assertEquals(50, testingame.getHealth()),
                ()-> assertEquals(1, testingame.getItemsOwned().get(1).intValue()),
                ()->  {
                    testingame.EatFood(1);
                    assertThrows(ItemIsZero.class, ()-> testingame.EatFood(1));
                },
                ()-> assertThrows(ItemIsZero.class, ()->testingame.EatFood(2))
        );
    }

    @Test
    @DisplayName("Test load game")
    void TestLoadGame() throws IOException, InsufficientMoney, BadHeaderSave, SaveFileDoesntExists, ItemNotSelected {
        testingame.NewGame(true);
        testingame.setCharacterSelected(1);
        testingame.setMaxPunctuationMath((long)40);
        testingame.setHealth(40);
        testingame.setStatus(2);
        testingame.Buy(1);
        testingame.setMoney(0x4565647);
        testingame.setExperience(60);
        testingame.setPlayerLevel(15);
        testingame.save();
        testingame.NewGame(true);
        testingame.load();
        assertAll(
                ()-> assertEquals(40, testingame.getHealth()),
                ()-> assertEquals(2, testingame.getStatus()),
                ()-> assertEquals(0x4565647, testingame.getMoney()),
                ()-> assertEquals(2, testingame.getItemsOwned().get(0).intValue()),
                ()-> assertEquals(3, testingame.getItemsOwned().get(1).intValue()),
                ()-> assertEquals(0, testingame.getItemsOwned().get(2).intValue()),
                ()-> assertEquals(60, testingame.getExperience()),
                ()-> assertEquals(15, testingame.getPlayerLevel()),
                ()-> assertEquals(1, testingame.getCharacterSelected()),
                ()-> assertEquals(40, testingame.getMaxPunctuationMath().intValue())
        );
    }
}
