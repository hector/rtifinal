package rtifinal.graphics;

import processing.core.PApplet;
import rtifinal.Main;

public abstract class Drawable {
  
  protected static PApplet applet() {
    return Main.applet;
  }
  
  public abstract void draw();
  
}
