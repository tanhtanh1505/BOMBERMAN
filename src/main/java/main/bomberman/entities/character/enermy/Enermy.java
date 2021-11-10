package main.bomberman.entities.character.enermy;

import main.bomberman.entities.character.enermy.ai.AI;
import main.bomberman.graphics.AnimatedCharacter;

abstract public class Enermy extends AnimatedCharacter {
    protected AI brain;
    protected int MAX_STEPS = 50;
    protected int steps = 0;

    public Enermy(){
        setStatusMove("LEFT");
        speed = 80;
        duration = 0.1;
    }

    @Override
    public void update(double time){
        if(steps >= MAX_STEPS){
            calcMove();
        }

        if(canMove((int) (positionX + velocityX * time), (int)(positionY + velocityY * time))){
            steps++;
            positionX += velocityX * time;
            positionY += velocityY * time;
        }
        else{
            calcMove();
        }
    }

    public void calcMove() {
        steps = 0;
        setStatusMove(brain.calcDirection());
    }

}
