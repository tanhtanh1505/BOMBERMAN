package main.bomberman.entities.bomb;

import main.bomberman.graphics.AnimatedImage;
import main.bomberman.graphics.Sprite;

public class Segment extends AnimatedImage {
    public Segment(int x, int y, String img){
        setPosition(x, y);
        setFrame(Sprite.getListImage(img, 3, 3));
    }
}
