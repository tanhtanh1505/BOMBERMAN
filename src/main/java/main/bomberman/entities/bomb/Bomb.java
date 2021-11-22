package main.bomberman.entities.bomb;

import javafx.scene.canvas.GraphicsContext;
import main.bomberman.entities.character.Bomber;
import main.bomberman.graphics.AnimatedImage;
import main.bomberman.graphics.Sprite;
import main.bomberman.sound.Sound;

public class Bomb extends AnimatedImage {
    private boolean isExplored = false;
    private int timeToExplode = 120; //2s
    private Flame flame;
    private boolean isDestroyed = false;
    private int powerFlames = 0;
    private Bomber bomber;

    public Bomb(Bomber bomber, int length){
        Sound.playSound(Sound.placeBomb);
        this.bomber = bomber;
        duration = 0.2;
        int x = ((bomber.getPositionX() + bomber.getWidth() / 2)/bomber.getWidth())*bomber.getWidth();
        int y = ((bomber.getPositionY() + 2*bomber.getHeight() / 3)/ bomber.getHeight())* bomber.getHeight();
        setPosition(x, y);
        powerFlames = length;
        setFrame(Sprite.getListImage("sprites\\bomb_", 3, scale, false));
    }

    public void update(){
        if(timeToExplode > 0)
            timeToExplode--;
        else {
            if(!isExplored) {
                isExplored = true;
                flame = new Flame(bomber, positionX, positionY, powerFlames);
            }
        }
    }

    public boolean isDestroyed(){
        return isDestroyed;
    }

    @Override
    public void render(GraphicsContext gc, double time){
        if(isExplored){
            if(!isDestroyed) {
                flame.render(gc, time);

                if(flame.isShowed())
                    isDestroyed = true;
            }
        }
        else{
            super.render(gc, time);
        }
    }

}
