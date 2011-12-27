package rtifinal.graphics;

import processing.core.*;

public class Cube extends Polyedre {

  PVector[] vertices;
  float width, height, depth;

  public Cube(float size) {
    this(size, size, size);
  }

  public Cube(float width, float height, float depth) {
    this.width = width;
    this.height = height;
    this.depth = depth;
    vertices = new PVector[8];
    calculateVertices();
    polygons = new Square3D[6];
    calculatePolygons();
  }
  
  private void calculateVertices() {
    float x = -width/2f;
    float y = -height/2f;
    float z = -depth/2f;
    vertices[0] = new PVector(x,y,z);
    vertices[1] = new PVector(x,y,-z);
    vertices[2] = new PVector(x,-y,z);
    vertices[3] = new PVector(x,-y,-z);
    vertices[4] = new PVector(-x,y,z);
    vertices[5] = new PVector(-x,y,-z);
    vertices[6] = new PVector(-x,-y,z);
    vertices[7] = new PVector(-x,-y,-z);
  }
  
  private void calculatePolygons() {
    polygons[0] = new Square3D(vertices[0], vertices[4], vertices[6], vertices[2], 0,0,-1); //front
    polygons[1] = new Square3D(vertices[4], vertices[5], vertices[7], vertices[6], 1,0,0); //right
    polygons[2] = new Square3D(vertices[5], vertices[1], vertices[3], vertices[7], 0,0,1); //back
    polygons[3] = new Square3D(vertices[1], vertices[0], vertices[2], vertices[3], -1,0,0); //left
    polygons[4] = new Square3D(vertices[1], vertices[5], vertices[4], vertices[0], 0,-1,0); //top
    polygons[5] = new Square3D(vertices[2], vertices[6], vertices[7], vertices[3], 0,1,0); //bottom
  }  
  
}
