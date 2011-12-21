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
  
  public abstract void draw();
  
}
