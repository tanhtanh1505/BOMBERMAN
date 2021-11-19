package main.bomberman.entities.tile;

import main.bomberman.entities.Entity;

public class Grass extends Entity {
    public Grass(){
        setImg("sprites\\grass.png", 1);
    }
    public Grass(int x, int y) {
        setPosition(x, y);
        setImg("sprites\\grass.png", 1);
    }

}
