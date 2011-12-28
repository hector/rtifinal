package rtifinal.graphics;

import processing.core.*;
import rtifinal.Main;
import rtifinal.OSC.OSCSendReceive;
import rtifinal.graphics.Drawable;

public class Gradient  {

  // constants
  int Y_AXIS = 1;
  int X_AXIS = 2;
  int color, c1, c2;
  public static float mx, my, toggle;
  OSCSendReceive oscControl = new OSCSendReceive();

  public Gradient() {
    
  }
  public void getValues(){
    
  }
  public void setGradient(int x, int y, float w, float h, int axis) {

    mx = oscControl.xpos * 255;
    my = (oscControl.ypos - 1) * -255;
    c1 = Main.applet.color(my, 0, mx);
    c2 = Main.applet.color(mx, my, 0);
// calculate differences between color components
    float deltaR = Main.applet.red(c2) - Main.applet.red(c1);
    float deltaG = Main.applet.green(c2) - Main.applet.green(c1);
    float deltaB = Main.applet.blue(c2) - Main.applet.blue(c1);

// choose axis
    if (axis == Y_AXIS) {
// column
      for (int i = x; i <= (x + w); i++) {
// row
        for (int j = y; j <= (y + h); j++) {
          color = Main.applet.color((Main.applet.red(c1) + (j - y) * (deltaR / h)), (Main.applet.green(c1) + (j - y) * (deltaG / h)), (Main.applet.blue(c1) + (j - y) * (deltaB / h)));
          Main.applet.set(i, j, color);
        }
      }
    } else if (axis == X_AXIS) {
// column
      for (int i = y; i <= (y + h); i++) {
// row
        for (int j = x; j <= (x + w); j++) {
          color = Main.applet.color((Main.applet.red(c1) + (j - x) * (deltaR / h)), (Main.applet.green(c1) + (j - x) * (deltaG / h)), (Main.applet.blue(c1) + (j - x) * (deltaB / h)));
          Main.applet.set(j, i, color);
        }
      }
    }
  }


}
