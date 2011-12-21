package rtifinal;

import processing.core.*;
import rtifinal.instruments.*;

public class Main extends PApplet {

  public static PApplet applet;

  @Override
  public void setup() {
    Main.applet = this;
    size(screen.width, screen.height, P3D);
  }

  @Override
  public void draw() {
    background(0);
    Synthesizer synth = new Synthesizer();
    synth.draw();
  }

  // main method to launch this Processing sketch from computer
  public static void main(String[] args) {
    PApplet.main(new String[]{"rtifinal.Main"});
  }
}