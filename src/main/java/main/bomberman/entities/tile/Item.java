package main.bomberman.entities.tile;

import main.bomberman.entities.Entity;
import main.bomberman.sound.Sound;

public class Item extends Entity {
    private double speed = 1.0;
    private int flames = 0;
    private int bomb = 0;

    public Item(String name){
        if(name.equals("speed")) {
            setImg("sprites\\powerup_speed.png");
            speed = 1.2;
        }
        else if(name.equals("flames")) {
            setImg("sprites\\powerup_flames.png");
            flames = 1;
        }
        else if(name.equals("bomb")) {
            setImg("sprites\\powerup_bombs.png");
            bomb = 1;
        }
        else if(name.equals("portal")){
            setImg("sprites\\portal.png");
        }
    }

    public double getSpeed(){
        return speed;
    }

    public int getFlames(){
        return flames;
    }

    public int getBomb(){
        return bomb;
    }

    public double[] getProperties(){
        Sound.playSound(Sound.itemCollected);
        return new double[]{speed, flames, bomb};
    }

}
