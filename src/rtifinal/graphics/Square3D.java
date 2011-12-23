package rtifinal.graphics;

import processing.core.*;

public class Square3D extends Polygon3D {

  // Constructor
  public Square3D (PVector[] vertices, PVector normal) {
    super(vertices, PApplet.QUADS, normal);
  }

  public Square3D (PVector point1, PVector point2, PVector point3, PVector point4, float normal_x, float normal_y, float normal_z) {
    PVector[] v = {point1, point2, point3, point4};
    setVertices(v);
    setNormal(new PVector(normal_x, normal_y, normal_z));
    setShapeMode(PApplet.QUADS);
  }
  
  public float size() {
    return vertices[1].dist(vertices[2]);
  }

}
