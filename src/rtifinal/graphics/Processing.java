package rtifinal.graphics;

import processing.core.PApplet;
import rtifinal.Main;

// Class to access processing library
public abstract class Processing {

  static float PI = PApplet.PI;
  static float TWO_PI = PApplet.TWO_PI;
  public Main p5;

  public Processing() {
    p5 = Main.applet;
  }
}
