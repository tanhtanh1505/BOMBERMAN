package main.bomberman.entities.character.enermy;

import main.bomberman.entities.character.Bomber;
import main.bomberman.entities.character.enermy.ai.AILow;

public class Balloon extends Enermy {
    public Balloon(Bomber b){
        brain = new AILow();

        setFrame("sprites\\balloom_left", "sprites\\balloom_left",
                "sprites\\balloom_right", "sprites\\balloom_right", 3);

        setPosition(576, 48);
    }

}
