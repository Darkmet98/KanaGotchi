package UI.pkgControllers;

import javafx.fxml.FXML;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Sphere;

public class SelectMiniGame_Controller extends Common_Controller {

    @FXML
    Sphere sphere;

    @FXML
    public void initialize() {
        AddSphereColor(sphere);
    }

    @FXML
    public void StartMathGame() {
        VistaNavigator.loadVista(VistaNavigator.MINIGAME_MATH);
    }
    @FXML
    public void StartCatchTheBallGame() {
        VistaNavigator.loadVista(VistaNavigator.MINIGAME_CATCHTHEBALL);
    }

    public static void AddSphereColor(Sphere sphereDrawed) {
        PhongMaterial material = new PhongMaterial();
        material.setDiffuseColor(Color.RED);
        material.setSpecularColor(Color.BLACK);
        sphereDrawed.setMaterial(material);
    }

}
