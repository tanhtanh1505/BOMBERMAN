package main.bomberman.entities.character.enermy;

import main.bomberman.entities.character.Bomber;
import main.bomberman.entities.character.enermy.ai.AIMedium;

public class Oneal extends Enemy {
    public Oneal(Bomber b){
        bomber = b;
        brain = new AIMedium(this, b);

        setFrame("sprites\\oneal_left", "sprites\\oneal_left",
                "sprites\\oneal_right", "sprites\\oneal_right", 3);
        setAnimateDead("sprites\\oneal_dead", 1);
    }
}
