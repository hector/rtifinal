package rtifinal.graphics;

import processing.core.*;

public class Square3D extends Drawable {
  protected PVector[] vertices;
  protected PVector normal;
  
  public Square3D (PVector point1, PVector point2, PVector point3, PVector point4, 
          float normal_x, float normal_y, float normal_z) {
    this.vertices = new PVector[4];
    vertices[0] = point1;
    vertices[1] = point2;
    vertices[2] = point3;
    vertices[3] = point4;
    normal = new PVector(normal_x, normal_y, normal_z);
  }
  
  @Override
  public void draw() {
    p5.beginShape(PApplet.QUADS);
    p5.normal(normal.x, normal.y, normal.z);
    for(PVector vertex : vertices) {
      p5.vertex(vertex.x, vertex.y, vertex.z);
    }
    p5.endShape();
  }

}
