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
        AddSphereColor();
    }

    @FXML
    public void StartMathGame() {
        VistaNavigator.loadVista(VistaNavigator.MINIGAME_MATH);
    }

    private void AddSphereColor() {
        PhongMaterial material = new PhongMaterial();
        material.setDiffuseColor(Color.RED);
        material.setSpecularColor(Color.BLACK);
        sphere.setMaterial(material);
    }

}
