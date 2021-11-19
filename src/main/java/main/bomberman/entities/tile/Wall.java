package main.bomberman.entities.tile;

import main.bomberman.entities.Entity;

public class Wall extends Entity {
    public Wall(){
        setImg("sprites\\wall.png", 1);
    }
    public Wall(int x, int y) {
        setPosition(x, y);
        setImg("sprites\\wall.png", 1);
    }
}
