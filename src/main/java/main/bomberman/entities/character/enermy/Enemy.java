package main.bomberman.entities.character.enermy;

import main.bomberman.entities.character.Bomber;
import main.bomberman.entities.character.enermy.ai.AI;
import main.bomberman.graphics.AnimatedCharacter;

abstract public class Enemy extends AnimatedCharacter {
    protected AI brain;
    protected int MAX_STEPS = 50;
    protected int steps = 0;
    protected Bomber bomber;

    public Enemy(){
        setStatusMove("LEFT");
        speed = 80;
        duration = 0.1;
    }

    @Override
    public void update(double time){
        if(!alive)
            return;

        if(this.intersects(bomber)){
            bomber.kill();
        }

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