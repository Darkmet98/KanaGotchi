package UI.pkgControllers;

import javafx.fxml.FXML;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Sphere;

public class SelectMiniGame_Controller extends Common_Controller {

    //Values
    @FXML
    Sphere sphere;

    /*
     * Initialize the FXML Controller
     */
    @FXML
    public void initialize() {
        //Add to the sphere the texture color
        AddSphereColor(sphere);
    }

    /*
    * Load the Math Minigame
     */
    @FXML
    public void StartMathGame() {
        PressedSound();
        VistaNavigator.loadVista(VistaNavigator.MINIGAME_MATH);
    }
    /*
    * Load the Catch the ball Minigame
     */
    @FXML
    public void StartCatchTheBallGame() {
        PressedSound();
        VistaNavigator.loadVista(VistaNavigator.MINIGAME_CATCHTHEBALL);
    }

    //Add to the sphere textures
    public static void AddSphereColor(Sphere sphereDrawed) {
        PhongMaterial material = new PhongMaterial();
        material.setDiffuseColor(Color.RED);
        material.setSpecularColor(Color.BLACK);
        sphereDrawed.setMaterial(material);
    }

}
