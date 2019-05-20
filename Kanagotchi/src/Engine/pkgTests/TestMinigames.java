package Engine.pkgTests;

import Engine.pkgExceptions.OperationResultIsNull;
import Engine.pkgMechanics.Game;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static junit.framework.Assert.assertEquals;

public class TestMinigames {

    private static Game testingame;

    @BeforeAll
    static void Initialize() {
        testingame= new Game();
    }

    @Test
    @DisplayName("Test Math game punctuation")
    public void TestMathGame() throws OperationResultIsNull {
        testingame.NewGame(false);
        testingame.Maths.StartGame();
        System.out.println("La primera línea es:" + testingame.Maths.getFirstLine());
        System.out.println("La segunda línea es:" + testingame.Maths.getSecondLine());
        System.out.println("El resultado es:" + testingame.Maths.getResult());
        System.out.println("El tipo de operación es:" + testingame.Maths.getType());
        testingame.Maths.CheckResult((long)50);
        assertEquals(2, testingame.Maths.CommonValues.getLife().intValue());
    }

    @Test
    @DisplayName("Test Math game end ")
    public void TestMathGameEndGame() throws OperationResultIsNull {
        testingame.NewGame(false);
        testingame.Maths.StartGame();
        for(int i = 0; i < 80; i++) testingame.Maths.CheckResult(testingame.Maths.getResult());
        System.out.println(testingame.Maths.CommonValues.getMaxPunctuation());
        for(int i = 0; i < 3; i++) testingame.Maths.CheckResult((long)0);
        assertEquals(80, testingame.getMaxPunctuationMath().intValue());
    }
}
