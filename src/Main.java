
import processing.core.*;

public class Main extends PApplet {

    @Override
    public void setup() {
        size(640, 480);
    }

    @Override
    public void draw() {
        background(0);
        rectMode(CENTER);
        fill(0xFFFFAA99);
        rect(mouseX, mouseY, 100, 100);
    }

    // main method to launch this Processing sketch from computer
    public static void main(String[] args) {
        PApplet.main(new String[]{"Main"});
    }
}