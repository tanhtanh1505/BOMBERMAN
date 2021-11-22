package main.bomberman.entities.character;

import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import main.bomberman.Input.Input;
import main.bomberman.board.BoardGame;
import main.bomberman.entities.Entity;
import main.bomberman.entities.bomb.Bomb;
import main.bomberman.entities.tile.Brick;
import main.bomberman.entities.tile.Wall;
import main.bomberman.graphics.AnimatedCharacter;
import main.bomberman.graphics.Sprite;
import main.bomberman.sound.Sound;

import java.util.ArrayList;

public class Bomber extends AnimatedCharacter {
    private ArrayList<Bomb> listBomb = new ArrayList<>();
    private Input input = new Input();
    private int timeToPlaceNextBomb = 0;
    private int numberBomb = 2;
    private int powerFlames = 0;
    private int selectCharacter = 1; //0: mac dinh
    private int healNumSpaceShip = 0;

    public Bomber(int select) {
        setScale(3);
        this.selectCharacter = select;
        framesMove = Sprite.getListImage("sprites\\jetter" + selectCharacter +".png", 4, 3, scale);
//        setFrame("sprites\\player_down_", "sprites\\player_left_",
//        "sprites\\player_right_", "sprites\\player_up_", 3);
        setAnimateDead("sprites\\player_dead", 3);
        setPosition(44, 40);
        alive = true;
    }

    public void readInput(){
        setVelocity(0,0);
        isRunning = false;
        if (input.up()){
            setStatusMove("UP");
        }
        else if (input.down()) {
            setStatusMove("DOWN");
        }
        else if (input.left()){
            setStatusMove("LEFT");
        }
        else if (input.right()){
            setStatusMove("RIGHT");
        }
        if(numberBomb > listBomb.size() && input.placeBomb() && timeToPlaceNextBomb < 0){
            listBomb.add(new  Bomb(this, powerFlames));
            timeToPlaceNextBomb = 20;
        }
    }

    @Override
    public void update(double time){
        readInput();
        timeToPlaceNextBomb--;

        super.update(time);

        if(listBomb.size() > 0) {
            for(int i = 0; i < listBomb.size(); i++) {
                Bomb bomb = listBomb.get(i);
                bomb.update();
                if (bomb.isDestroyed()) {
                    listBomb.remove(bomb);
                    i--;
                }
            }
        }
    }

    @Override
    public boolean canMove(int x, int y){
        x += width/3;
        y += height/3;

        Entity tren_trai = BoardGame.getEntityAt((x - width/4)/width, (y + height/5)/height);
        Entity tren_phai = BoardGame.getEntityAt((x + width/3)/width, (y + height/5)/height);
        Entity duoi_trai = BoardGame.getEntityAt((x - width/4)/width, (y+ 8*height/10)/height);
        Entity duoi_phai = BoardGame.getEntityAt((x + width/3)/width, (y+ 8*height/10)/height);

        if(tren_trai instanceof Wall || tren_phai instanceof Wall ||
                duoi_trai instanceof Wall || duoi_phai instanceof Wall){
            return false;
        }

        Brick brick = null;
        if(tren_trai instanceof Brick){
            brick = (Brick) tren_trai;
            if(!brick.isDestroyed()){
                return false;
            }
            else if(brick.isPortal()){
                if( BoardGame.getListEnemy().size() == 0) {
                    System.out.println("next level");
                    BoardGame.nextLevel();
                    return true;
                }
                else return false;
            }
            else if(brick.hasItem()){
                brick.setCollectedItem(powerUp(brick.getItem().getProperties()));
            }
        }
        if(tren_phai instanceof Brick){
            brick = (Brick) tren_phai;
            if(!brick.isDestroyed()){
                return false;
            }
            else if(brick.isPortal()){
                if( BoardGame.getListEnemy().size() == 0) {
                    System.out.println("next level");
                    BoardGame.nextLevel();
                    return true;
                }
                else return false;
            }
            else if(brick.hasItem()){
                brick.setCollectedItem(powerUp(brick.getItem().getProperties()));
            }
        }
        if(duoi_trai instanceof Brick){
            brick = (Brick) duoi_trai;
            if(!brick.isDestroyed()){
                return false;
            }
            else if(brick.isPortal()){
                if( BoardGame.getListEnemy().size() == 0) {
                    System.out.println("next level");
                    BoardGame.nextLevel();
                    return true;
                }
                else return false;
            }
            else if(brick.hasItem()){
                brick.setCollectedItem(powerUp(brick.getItem().getProperties()));
            }
        }
        if(duoi_phai instanceof Brick){
            brick = (Brick) duoi_phai;
            if(!brick.isDestroyed()){
                return false;
            }
            else if(brick.isPortal()){
                if( BoardGame.getListEnemy().size() == 0) {
                    System.out.println("next level");
                    BoardGame.nextLevel();
                    return true;
                }
                else return false;
            }
            else if(brick.hasItem()){
                brick.setCollectedItem(powerUp(brick.getItem().getProperties()));
            }
        }

        return true;
    }

    public boolean powerUp(double[] properties){
        //0: speed, 1:flame, 2:bomb 3:spaceShip
        this.speed *= properties[0];
        this.powerFlames += properties[1];
        this.numberBomb += properties[2];
        this.healNumSpaceShip = (int) properties[3];
        if(healNumSpaceShip > 0){
            framesMove = Sprite.getListImage("sprites\\specialMode" + selectCharacter + ".png", 4, 2, scale);
        }
        return true;
    }

    public void render(GraphicsContext gc, double time){
//        super.render(gc, time);

        if (alive) {
            if(isRunning) {
                gc.drawImage(getFrame(time, framesMove[statusMove]), positionX, positionY, 44, 55);
            }
            else gc.drawImage(framesMove[statusMove][0], positionX, positionY, 44, 55);
        } else if (lastTime < dead.length * 10) {
            gc.drawImage(dead[lastTime / 10], positionX, positionY);
            lastTime++;
        }

        if(listBomb.size() > 0) {
            for (Bomb bomb : listBomb) {
                bomb.render(gc, time);
            }
        }
    }

    public int getNumberBomb(){
        return numberBomb;
    }

    public int getPowerFlames(){
        return powerFlames;
    }

    @Override
    public Rectangle2D getBoundary() {
        return new Rectangle2D(positionX + width/5, positionY + height/5, width/2, height/2);
    }

    @Override
    public void kill(){
        healNumSpaceShip--;
        if(healNumSpaceShip == 0){
            framesMove = Sprite.getListImage("sprites\\jetter" + selectCharacter +".png", 4, 3, scale);
        }
        else if(healNumSpaceShip < 0) {
            Sound.playSound(Sound.bomberDie);
            BoardGame.setGameOver(true);
            alive = false;
        }
    }

    public int getSelectCharacter(){
        return selectCharacter;
    }
}
