package rtifinal.graphics;

import processing.core.PApplet;
import rtifinal.MainApplet;

// Class to access processing library
public abstract class Processing {

  static float PI = PApplet.PI;
  static float TWO_PI = PApplet.TWO_PI;
  public MainApplet p5;

  public Processing() {
    p5 = MainApplet.applet;
  }
}
