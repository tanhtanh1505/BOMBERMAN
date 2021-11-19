package main.bomberman.entities.bomb;

import javafx.scene.canvas.GraphicsContext;

import java.util.ArrayList;

public class FlameSegment {
    private int w = 48;
    private int h = 48;
    private ArrayList<Segment> up;
    private ArrayList<Segment> down;
    private ArrayList<Segment> left;
    private ArrayList<Segment> right;
    private Segment centre;

    public FlameSegment(int x, int y, int up, int down, int left, int right){
        if(up >= 1) {
            for (int i = 1; i < up; i++) {
                Segment segment = new Segment(x, y - i * h, "sprites\\explosion_vertical");
                this.up.add(segment);
            }
            this.up.add(new Segment(x, y - up * h, "sprites\\explosion_vertical_top_last"));
        }
        if(down >= 1){
            for (int i = 1; i < down; i++) {
                Segment segment = new Segment(x, y + i * h, "sprites\\explosion_vertical");
                this.down.add(segment);
            }
            this.down.add(new Segment(x, y - down * h, "sprites\\explosion_vertical_down_last"));
        }
        if(left >= 1){
            for (int i = 1; i < left; i++) {
                Segment segment = new Segment(x - i*w, y, "sprites\\explosion_horizontal");
                this.left.add(segment);
            }
            this.left.add(new Segment(x - left*w, y, "sprites\\explosion_horizontal_left_last"));
        }
        if(right >= 1){
            for (int i = 1; i < right; i++) {
                Segment segment = new Segment(x + i*w, y, "sprites\\explosion_horizontal");
                this.right.add(segment);
            }
            this.right.add(new Segment(x + right*w, y, "sprites\\explosion_horizontal_right_last"));
        }

        centre = new Segment(x, y, "sprites\\bomb_exploded");
    }

    public void render(GraphicsContext gc, double time){
        centre.render(gc, time);
        for(Segment segment : up){
            segment.render(gc, time);
        }
        for(Segment segment : down){
            segment.render(gc, time);
        }
        for(Segment segment : left){
            segment.render(gc, time);
        }
        for(Segment segment : right){
            segment.render(gc, time);
        }
    }
}
