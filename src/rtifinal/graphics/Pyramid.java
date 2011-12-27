package rtifinal.graphics;

import processing.core.*;

public class Pyramid extends Polyedre {

  PVector topPoint;
  float height;
  
  public Pyramid (Square3D base) {
    this.polygons = new Polygon3D[5];
    this.polygons[0] = new Square3D(base.getVertices(), base.getNormal());
    this.height = base.size()/2;
    this.topPoint = new PVector();
    calculateTriangles();
  }
  
  private void calculateTriangles() {
    calculateTopPoint();
    PVector[] vertices = base().getVertices();
    polygons[1] = new Triangle3D(vertices[0], vertices[1], topPoint);
    polygons[2] = new Triangle3D(vertices[1], vertices[2], topPoint);
    polygons[3] = new Triangle3D(vertices[2], vertices[3], topPoint);
    polygons[4] = new Triangle3D(vertices[3], vertices[0], topPoint);
  }
  
  private void calculateTopPoint() {
    PVector[] vertices = base().getVertices();
    topPoint.set(vertices[0]);
    topPoint.add(vertices[1]);
    topPoint.add(vertices[2]);
    topPoint.add(vertices[3]);
    topPoint.div(4);
    PVector normal = new PVector();
    normal.set(base().getNormal());
    normal.mult(height);
    topPoint.add(normal);
  }
  
  public Square3D base() {
    return (Square3D) polygons[0];
  }
  
}
