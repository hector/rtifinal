package rtifinal.graphics;

import processing.core.*;

public class Polygon3D extends Drawable {
  PVector[] vertices;
  PVector normal;
  int shapeMode;
  static final int defaultShapeMode = PApplet.POLYGON;

  // Parameterless contructor
  public Polygon3D () {
    this(null, defaultShapeMode, null);
  }

  public Polygon3D (PVector[] vertices) {
    this(vertices, defaultShapeMode, null);
  }

  // Constructor
  public Polygon3D (PVector[] vertices, int shapeMode, PVector normal) {
    this.vertices = vertices;
    this.normal = normal;
    this.shapeMode = shapeMode;
  }
  
  public PVector[] getVertices() {
    return vertices;
  }

  public void setVertices(PVector[] vertices) {
    this.vertices = vertices;
  }

  public void setShapeMode (int shapeMode) {
    this.shapeMode = shapeMode;
  }
  
  public PVector getNormal() {
    return normal;
  }

  public void setNormal (PVector normal) {
    this.normal = normal;
  }

  @Override
  public void selfDraw() {
    super.selfDraw();
    p5.beginShape(shapeMode);
    if(normal != null) p5.normal(normal.x, normal.y, normal.z);
    for(PVector vertex : vertices) {
      p5.vertex(vertex.x, vertex.y, vertex.z);
    }
    p5.endShape();
  }

}
