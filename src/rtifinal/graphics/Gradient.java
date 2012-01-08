package rtifinal.graphics;

import java.util.ArrayList;
import processing.core.PGraphics;
import processing.core.PVector;
import rtifinal.instruments.Instrument;

public class Gradient extends Processing {

  // constants
  int Y_AXIS = 1;
  int X_AXIS = 2;
  int color, c1, c2;
  public static float mx, my, toggle;
  int pixelSize = 2;
  PGraphics pg;

  public Gradient() {
    // Create buffered image for plasma effect
    pg = p5.createGraphics(160, 90, p5.P3D);
//    colorMode(p5.HSB);
//    p5.noSmooth();    
    calculate();
  }
  
  public void draw() {
    // display the results
    p5.image(pg, 0, 0, p5.width, p5.height);
  }

  public void calculate() {
    float xc = 25;

    // Enable this to control the speed of animation regardless of CPU power
    //float timeDisplacement = p5.millis()/30;

    // This runs plasma as fast as your computer can handle
    float timeDisplacement = p5.frameCount;

    // No need to do this math for every pixel
    float calculation1 = sin(radians(timeDisplacement * (float) 0.61655617));
    float calculation2 = sin(radians(timeDisplacement * (float) -3.6352262));

    // Output into a buffered image for reuse
    pg.beginDraw();
    pg.loadPixels();

    // Plasma algorithm
    for (int x = 0; x < pg.width; x++, xc += pixelSize) {
      float yc = 25;
      float s1 = 128 + 128 * sin(radians(xc) * calculation1);

      for (int y = 0; y < pg.height; y++, yc += pixelSize) {
        float s2 = 128 + 128 * sin(radians(yc) * calculation2);
        float s3 = 128 + 128 * sin(radians((xc + yc + timeDisplacement * 5) / 2));
        float s = (s1 + s2 + s3) / 3;
        pg.pixels[x + y * pg.width] = p5.color(s, (float) 255 - s / (float) 2.0, (float) 255);
      }
    }
    pg.updatePixels();
    pg.endDraw();
  }

  public void setGradient(ArrayList<Instrument> instruments, int x, int y, int axis) {
    int w = p5.width;
    int h = p5.height;

    PVector mean;
    if (instruments.isEmpty()) {
      mean = new PVector(w / 2, h / 2);
    } else {
      mean = new PVector(0, 0);
      for (Drawable instrument : instruments) {
        mean.add(instrument.getPosition());
      }
      mean.div(instruments.size());
    }

    mx = mean.x * 255;
    my = (mean.y - 1) * -255;
    my = (1 - 1) * -255;
    c1 = p5.color(my, 0, mx);
    c2 = p5.color(mx, my, 0);
//    c1 = 10;
//    c2 = 100;
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
