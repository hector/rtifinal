package rtifinal;

import processing.core.*;
import rtifinal.instruments.*;
import rtifinal.graphics.*;

public class Main extends PApplet {

  public static PApplet applet;
  int pts = 0;
  public Synthesizer synth;

  public void setup() {
    Main.applet = this;
    size(screen.width, screen.height, P3D);
    synth = new Synthesizer();
  }

  public void draw() {
    background(RGB, 100, 120, 34);
    lights();
    synth.draw();  
  }

  public void keyPressed() {
    if (key == CODED) {
      if (keyCode == UP) {
        if (pts < 1000) {
          pts = pts + 10;
        }
      } else if (keyCode == DOWN) {
        if (pts > -1000) {
          pts = pts - 10;
        }
      }
    }
  }

  // main method to launch this Processing sketch from computer
  public static void main(String[] args) {
    PApplet.main(new String[]{"rtifinal.Main"});
  }
}