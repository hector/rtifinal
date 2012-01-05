package rtifinal.graphics;

import processing.core.*;
import rtifinal.graphics.Drawable;

public class Gradient extends Processing {

  // constants
  int Y_AXIS = 1;
  int X_AXIS = 2;
  int color, c1, c2;
  public static float mx, my, toggle;

  public Gradient() {}
  
  public void setGradient(int x, int y, float w, float h, int axis) {

//    mx = oscComm.xpos * 255;
    mx = 1 * 255;
//    my = (oscComm.ypos - 1) * -255;
    my = (1 - 1) * -255;
//    c1 = p5.color(my, 0, mx);
//    c2 = p5.color(mx, my, 0);
    c1 = 10;
    c2 = 100;
    // calculate differences between color components
    float deltaR = p5.red(c2) - p5.red(c1);
    float deltaG = p5.green(c2) - p5.green(c1);
    float deltaB = p5.blue(c2) - p5.blue(c1);

    // choose axis
    if (axis == Y_AXIS) {
    // column
      for (int i = x; i <= (x + w); i++) {
        // row
        for (int j = y; j <= (y + h); j++) {
          color = p5.color((p5.red(c1) + (j - y) * (deltaR / h)), (p5.green(c1) + (j - y) * (deltaG / h)), (p5.blue(c1) + (j - y) * (deltaB / h)));
          p5.set(i, j, color);
        }
      }
    } else if (axis == X_AXIS) {
      // column
      for (int i = y; i <= (y + h); i++) {
        // row
        for (int j = x; j <= (x + w); j++) {
          color = p5.color((p5.red(c1) + (j - x) * (deltaR / h)), (p5.green(c1) + (j - x) * (deltaG / h)), (p5.blue(c1) + (j - x) * (deltaB / h)));
          p5.set(j, i, color);
        }
      }
    }
  }


}
