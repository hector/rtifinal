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
  
  protected static float cos(float angle) {
    return PApplet.cos(angle);
  }

  protected static float sin(float angle) {
    return PApplet.sin(angle);
  }

  protected static float radians(float degrees) {
    return PApplet.radians(degrees);
  }

  protected static float radians(double degrees) {
    return PApplet.radians((float)degrees);
  }
  
}
