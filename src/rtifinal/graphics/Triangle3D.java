package rtifinal.graphics;

import processing.core.*;

public class Triangle3D extends Polygon3D {
  
  public Triangle3D(PVector point1, PVector point2, PVector point3) {
    super();
    PVector[] v = {point1, point2, point3};
    vertices = v;
    setShapeMode(PApplet.TRIANGLES);
  }

}
