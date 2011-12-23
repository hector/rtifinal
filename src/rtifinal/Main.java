package rtifinal;

import processing.core.*;
import rtifinal.instruments.*;
import rtifinal.graphics.*;

public class Main extends PApplet {

  public static Main applet;
  int time, startFrameMillis, pts;
  Synthesizer synth;

  // main method to launch this Processing sketch from computer
  public static void main(String[] args) {
    PApplet.main(new String[]{"rtifinal.Main"});
  }

  @Override
  public void setup() {
    Main.applet = this;
    size(screen.width, screen.height, P3D);
    synth = new Synthesizer();
    time = 0;
  }

  @Override
  public void draw() {
    startFrameMillis = millis();
    background(RGB, 100, 120, 34);
    lights();
    strokeWeight(3);
    synth.draw();
    time = millis();
  }

  // Returns the number of milliseconds since the last draw()
  // This value is fixed during the draw function (calculates time at start of frame)
  public int spentTime() {
    return startFrameMillis - time;
  }

  @Override
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
}