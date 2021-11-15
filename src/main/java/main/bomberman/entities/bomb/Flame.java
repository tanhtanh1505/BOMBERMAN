package main.bomberman.entities.bomb;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import main.bomberman.board.BoardGame;
import main.bomberman.entities.Entity;
import main.bomberman.entities.character.Bomber;
import main.bomberman.entities.character.enermy.Enemy;
import main.bomberman.entities.tile.Brick;
import main.bomberman.graphics.AnimatedImage;
import main.bomberman.graphics.Properties;
import main.bomberman.graphics.Sprite;

import java.util.ArrayList;

public class Flame extends AnimatedImage {
    private Image[] center;
    private Image[] toplast;
    private Image[] bottomlast;
    private Image[] leftlast;
    private Image[] rightlast;

    private Image[][] vertical;
    private Image[][] horizontal;

    private int showed = 20;//hien trong 20 ms
    private int length = 0;

    //private List<Message>

    public Flame(Bomber bomber, int x, int y, int length){
        duration = 0.2;
        positionX = x;
        positionY = y;
        this.length = length;
        loadFrame();
        calcDestroy(bomber);
    }

    private void calcDestroy(Bomber bomber){
        ArrayList<Enemy> listE = BoardGame.getListEnemy();
        int gocTren = (positionY - height*(length + 1))/height;
        int gocTrai = (positionX - width*(length + 1))/width;
        for(int i = gocTren; i < gocTren + 3 + length*2; i++){
            Entity e = BoardGame.getEntityAt(positionX/width, i);
            if(e instanceof Brick){
                ((Brick) e).setDestroyed(true);
            }
            for(Enemy enemy : listE){
                if((enemy.getPositionY() + height/2)/height == i && (enemy.getPositionX()+width/2)/width == gocTrai + 1 + length/2){
                    BoardGame.addMessage("+100", enemy.getPositionX(), enemy.getPositionY());
                    Properties.addScore();
                    enemy.kill();
                }
            }
            if(bomber.getPositionY()/height == i && bomber.getPositionX()/width == gocTrai + 1 + length/2){
                bomber.kill();
            }
        }
        for(int i = gocTrai; i < gocTrai + 3 + length*2; i++){
            Entity e = BoardGame.getEntityAt(i, positionY/height);
            if(e instanceof Brick){
                ((Brick) e).setDestroyed(true);
            }
            for(Enemy enemy : listE){
                if((enemy.getPositionX()+width/2)/width == i && (enemy.getPositionY()+height/2)/height == gocTren + 1 + length/2){
                    BoardGame.addMessage("+100", enemy.getPositionX(), enemy.getPositionY());
                    Properties.addScore();
                    enemy.kill();
                }
            }
            if(bomber.getPositionX()/width == i && bomber.getPositionY()/height == gocTren + 1 + length/2){
                bomber.kill();
            }
        }
    }

    public boolean isShowed(){
        return showed == 0;
    }

    public Image getFrame(Image[] im, double time){
        int index = (int)((time % (im.length * duration)) / duration);
        return im[index];
    }

    @Override
    public void render(GraphicsContext gc, double time) {
        if(--showed > 0) {
            gc.drawImage(getFrame(center, time), positionX, positionY);

            for(int i = 0; i < length; i++){
                gc.drawImage(getFrame(vertical[i], time), positionX, positionY - height*(i+1));
                gc.drawImage(getFrame(vertical[i], time), positionX, positionY + height*(i+1));
                gc.drawImage(getFrame(horizontal[i], time), positionX - width*(i+1), positionY);
                gc.drawImage(getFrame(horizontal[i], time), positionX + width*(i+1), positionY);
            }

            gc.drawImage(getFrame(toplast, time), positionX, positionY - height*(length + 1));
            gc.drawImage(getFrame(bottomlast, time), positionX, positionY + height*(length + 1));
            gc.drawImage(getFrame(leftlast, time), positionX - width*(length + 1), positionY);
            gc.drawImage(getFrame(rightlast, time), positionX + width*(length + 1), positionY);
        }
    }

    private void loadFrame() {
        center = Sprite.getListImage("sprites\\bomb_exploded", 3, 3);
        toplast = Sprite.getListImage("sprites\\explosion_vertical_top_last", 3, 3);
        bottomlast = Sprite.getListImage("sprites\\explosion_vertical_down_last", 3, 3);
        leftlast = Sprite.getListImage("sprites\\explosion_horizontal_left_last", 3, 3);
        rightlast = Sprite.getListImage("sprites\\explosion_horizontal_right_last", 3, 3);

        if(length > 0) {
            vertical = new Image[length][];
            horizontal = new Image[length][];
            for (int i = 0; i < length; i++) {
                vertical[i] = Sprite.getListImage("sprites\\explosion_vertical", 3, 3);
                horizontal[i] = Sprite.getListImage("sprites\\explosion_horizontal", 3, 3);
            }

        }

    }
}
