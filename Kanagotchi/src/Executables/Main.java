package Executables;

import UI.pkgControllers.Main_Controller;

public abstract class Main {
    public static void main(String[] args) {
        Main_Controller start = new Main_Controller();
        start.main();
    }
}
