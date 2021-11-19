package main.bomberman.gui;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.stage.Stage;
import main.bomberman.Game;
import main.bomberman.sound.Sound;

public class SelectCharacter {
    private String namePlayer;

    public void setName(String name){
        namePlayer = name;
    }

    public void selectFirst(ActionEvent event) {
        Sound.playSound(Sound.placeBomb);
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        new Game(stage, namePlayer, 1);
    }

    public void selectSecond(ActionEvent event) {
        Sound.playSound(Sound.placeBomb);
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        new Game(stage, namePlayer, 2);
    }

    public void selectThird(ActionEvent event) {
        Sound.playSound(Sound.placeBomb);
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        new Game(stage, namePlayer, 3);
    }
}
