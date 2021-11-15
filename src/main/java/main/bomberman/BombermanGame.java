package main.bomberman;

import javafx.application.Application;
import javafx.stage.Stage;

public class BombermanGame extends Application {
    private final int WIDTH = 1344;
    private final int HEIGHT = 720;

    @Override
    public void start(Stage theStage) {
        new Game(theStage, WIDTH, HEIGHT);
        theStage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}
