package main.bomberman.graphics;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import main.bomberman.entities.character.Bomber;
import main.bomberman.level.LoadLevel;

public class Properties{
    //W: 240
    private int positionX;
    private Image image;
    private Image avatar;
    private Image gameOver;
    private Bomber bomber;

    private static int score;

    public Properties(Bomber bomber){
        positionX = LoadLevel.get_width()*48;
        image = new Image(GetImage.get("sprites\\properties.png"));
        avatar = new Image(GetImage.get("sprites\\player_down_0.png"));
        avatar = getAvatar(avatar, 12);
        gameOver = new Image(GetImage.get("sprites\\gameover.png"));
        this.bomber = bomber;
    }

    private Image getAvatar(Image image, int scale){
        int W = (int) image.getWidth();
        int H = (int) image.getHeight()/2;
        WritableImage outputImage = new WritableImage(W*scale, H*scale);
        PixelReader reader = image.getPixelReader();
        PixelWriter writer = outputImage.getPixelWriter();
        Color oldColor = image.getPixelReader().getColor(W - 1,H - 1);
        int ob=(int) oldColor.getBlue()*255;
        int or=(int) oldColor.getRed()*255;
        int og=(int) oldColor.getGreen()*255;

        for (int y = 0; y < H; y++) {
            for (int x = 0; x < W; x++) {
                int argb = reader.getArgb(x, y);
                int a = (argb >> 24) & 0xFF;
                int r = (argb >> 16) & 0xFF;
                int g = (argb >>  8) & 0xFF;
                int b =  argb        & 0xFF;
                if (g==og && r==or && b==ob) {
                    a = 0;
                }

                argb = (a << 24) | (r << 16) | (g << 8) | b;
                for(int e = 0; e < scale; e++)
                    for (int f = 0; f < scale; f++)
                        writer.setArgb(x*scale + e, y*scale + f, argb);
            }
        }
        return outputImage;
    }

    public void render(GraphicsContext gc){
        gc.drawImage(image, positionX, 0);
        gc.drawImage(avatar, positionX + 50, 180);

        gc.setFont(new Font("Algerian", 40));
        gc.fillText("LEVEL " + LoadLevel.get_level(), positionX + 45, 60);
        gc.setFont(new Font("Algerian", 28));
        gc.fillText("SCORE: " + score, positionX + 50, 400);
        gc.setFont(new Font("Algerian", 30));
        gc.fillText(":  " + bomber.getNumberBomb(), positionX + 110, 493);
        gc.fillText(":  " + (bomber.getPowerFlames()*2 + 3), positionX + 110, 550);
        gc.fillText(":  " + (int)bomber.getSpeed(), positionX + 110, 607);
        gc.setFont(new Font("Algerian", 20));
        gc.fillText("PRESS P TO PAUSE", positionX + 33, 675);
    }

    public static void addScore(){
        score += 100;
    }

    public void renderGameOver(GraphicsContext gc){
        gc.drawImage(gameOver, 327, 200);
    }
}
