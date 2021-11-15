package main.bomberman.entities.character.enermy;

import main.bomberman.entities.character.Bomber;
import main.bomberman.entities.character.enermy.ai.AIMedium;

public class Doll extends Enemy{
    public Doll(Bomber b){
        bomber = b;
        brain = new AIMedium(this, b);

        setFrame("sprites\\doll_left", "sprites\\doll_left",
                "sprites\\doll_right", "sprites\\doll_right", 3);
        setAnimateDead("sprites\\doll_dead", 1);
    }
}
