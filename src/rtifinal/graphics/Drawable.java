package rtifinal.graphics;

import processing.core.PApplet;
import rtifinal.Main;

public abstract class Drawable {
  
  protected PApplet p5;
  
  public Drawable() {
    p5 = Main.applet;
  }
  
  protected static PApplet applet() {
    return Main.applet;
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
  
  public abstract void draw();
  
}
