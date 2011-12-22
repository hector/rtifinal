package rtifinal.graphics;

import processing.core.*;

public class Cube extends Polyedre {

  PVector[] vertices;
  Square3D[] squares;
  PVector[] transformedVertices;
  float width, height, depth;
  float angleX, angleY, angleZ;

  public Cube(float width, float height, float depth) {
    this.width = width;
    this.height = height;
    this.depth = depth;
    vertices = new PVector[8];
    calculateVertices();
    squares = new Square3D[6];
    calculateSquares();
    transformedVertices = new PVector[24];
  }
  
  private void calculateVertices() {
    float x = -width/2f;
    float y = -height/2f;
    float z= -depth/2f;
    vertices[0] = new PVector(x,y,z);
    vertices[1] = new PVector(x,y,-z);
    vertices[2] = new PVector(x,-y,z);
    vertices[3] = new PVector(x,-y,-z);
    vertices[4] = new PVector(-x,y,z);
    vertices[5] = new PVector(-x,y,-z);
    vertices[6] = new PVector(-x,-y,z);
    vertices[7] = new PVector(-x,-y,-z);
  }
  
  private void calculateSquares() {
    squares[0] = new Square3D(vertices[0], vertices[4], vertices[6], vertices[2], 0,0,1); //front
    squares[1] = new Square3D(vertices[4], vertices[5], vertices[7], vertices[6], 1,0,0); //right
    squares[2] = new Square3D(vertices[5], vertices[1], vertices[3], vertices[7], 0,0,-1); //back
    squares[3] = new Square3D(vertices[1], vertices[0], vertices[2], vertices[3], -1,0,0); //left
    squares[4] = new Square3D(vertices[1], vertices[5], vertices[4], vertices[0], 0,1,0); //top
    squares[5] = new Square3D(vertices[2], vertices[6], vertices[7], vertices[3], 0,-1,0); //bottom    
  }  
  
  @Override
  public void draw() {
    // draw cube
    //spinnyRotateXYZ();
    p5.fill(p5.color(134));
    //p5.noStroke();
    p5.stroke(134);
    drawSquares();
  }
  
  protected void drawSquares() {
    for(Square3D square : squares) {
      square.draw();
    }
  }
  
  protected void drawTriangles() {
    PVector tri = new PVector();
    tri.add(transformedVertices[16]);
    tri.add(transformedVertices[17]);
    tri.add(transformedVertices[18]);
    tri.add(transformedVertices[19]);
    tri.div(4);
    PVector h = new PVector();
    h.add(transformedVertices[16]);
    h.sub(transformedVertices[3]);
    h.div(2);
    tri.add(h);
    
    p5.fill(p5.color(134,0,0));
    p5.beginShape(PApplet.TRIANGLES);
      p5.vertex(transformedVertices[16].x, transformedVertices[16].y, transformedVertices[16].z);
      p5.vertex(transformedVertices[17].x, transformedVertices[17].y, transformedVertices[17].z);
      p5.vertex(tri.x, tri.y, tri.z);
    p5.endShape();    
  }  

  public void spinnyRotateX(float angle) {
    angleX = angle;
  }

  public void spinnyRotateY(float angle) {
    angleY = angle;
  }

  public void spinnyRotateZ(float angle) {
    angleZ = angle;
  }
  
//  public void spinnyRotateXYZ() {
//    // temporary vertices arrays
//    PVector[] rotatedVertices_XAxis = new PVector[24];
//    PVector[] rotatedVertices_YAxis = new PVector[24];
//    PVector[] rotatedVertices_ZAxis = new PVector[24];
//
//    for (int i = 0; i < 24; i++) {
//      // initialize temp vertices arrays
//      rotatedVertices_XAxis[i] = new PVector();
//      rotatedVertices_YAxis[i] = new PVector();
//      rotatedVertices_ZAxis[i] = new PVector();
//
//      // rotation around x-axis
//      rotatedVertices_XAxis[i].x = vertices[i].x;
//      rotatedVertices_XAxis[i].y = cos(radians(angleX)) * vertices[i].y - sin(radians(angleX)) * vertices[i].z;
//      rotatedVertices_XAxis[i].z = sin(radians(angleX)) * vertices[i].y + cos(radians(angleX)) * vertices[i].z;
//
//      // rotation around y-axis
//      rotatedVertices_YAxis[i].y = rotatedVertices_XAxis[i].y;
//      rotatedVertices_YAxis[i].z = cos(radians(angleY)) * rotatedVertices_XAxis[i].z - sin(radians(angleY)) * rotatedVertices_XAxis[i].x;
//      rotatedVertices_YAxis[i].x = sin(radians(angleY)) * rotatedVertices_XAxis[i].z + cos(radians(angleY)) * rotatedVertices_XAxis[i].x;
//
//      // rotation around z-axis
//      rotatedVertices_ZAxis[i].x = cos(radians(angleZ)) * rotatedVertices_YAxis[i].x - sin(radians(angleZ)) * rotatedVertices_YAxis[i].y;
//      rotatedVertices_ZAxis[i].y = sin(radians(angleZ)) * rotatedVertices_YAxis[i].x + cos(radians(angleZ)) * rotatedVertices_YAxis[i].y;
//      rotatedVertices_ZAxis[i].z = rotatedVertices_YAxis[i].z;
//    }
//    // update transformedVertices arrays
//    transformedVertices = rotatedVertices_ZAxis;
//  }  
  
}
