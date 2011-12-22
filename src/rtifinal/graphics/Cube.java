package rtifinal.graphics;

import processing.core.*;

public class Cube extends Polyedre {

  Point3D[] vertices;
  Point3D[] transformedVertices;
  float width, height, depth;
  float angleX, angleY, angleZ;

  public Cube(float width, float height, float depth) {
    this.width = width;
    this.height = height;
    this.depth = depth;
    vertices = new Point3D[24];
    transformedVertices = new Point3D[24];
    initializeVertices();
  }

  public void spinnyRotateXYZ() {
    // temporary vertices arrays
    Point3D[] rotatedVertices_XAxis = new Point3D[24];
    Point3D[] rotatedVertices_YAxis = new Point3D[24];
    Point3D[] rotatedVertices_ZAxis = new Point3D[24];

    for (int i = 0; i < 24; i++) {
      // initialize temp vertices arrays
      rotatedVertices_XAxis[i] = new Point3D();
      rotatedVertices_YAxis[i] = new Point3D();
      rotatedVertices_ZAxis[i] = new Point3D();

      // rotation around x-axis
      rotatedVertices_XAxis[i].x = vertices[i].x;
      rotatedVertices_XAxis[i].y = cos(radians(angleX)) * vertices[i].y - sin(radians(angleX)) * vertices[i].z;
      rotatedVertices_XAxis[i].z = sin(radians(angleX)) * vertices[i].y + cos(radians(angleX)) * vertices[i].z;

      // rotation around y-axis
      rotatedVertices_YAxis[i].y = rotatedVertices_XAxis[i].y;
      rotatedVertices_YAxis[i].z = cos(radians(angleY)) * rotatedVertices_XAxis[i].z - sin(radians(angleY)) * rotatedVertices_XAxis[i].x;
      rotatedVertices_YAxis[i].x = sin(radians(angleY)) * rotatedVertices_XAxis[i].z + cos(radians(angleY)) * rotatedVertices_XAxis[i].x;

      // rotation around z-axis
      rotatedVertices_ZAxis[i].x = cos(radians(angleZ)) * rotatedVertices_YAxis[i].x - sin(radians(angleZ)) * rotatedVertices_YAxis[i].y;
      rotatedVertices_ZAxis[i].y = sin(radians(angleZ)) * rotatedVertices_YAxis[i].x + cos(radians(angleZ)) * rotatedVertices_YAxis[i].y;
      rotatedVertices_ZAxis[i].z = rotatedVertices_YAxis[i].z;
    }
    // update transformedVertices arrays
    transformedVertices = rotatedVertices_ZAxis;
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

  @Override
  public void draw() {
    // draw cube
    spinnyRotateXYZ();
    p5.fill(p5.color(134));
    p5.noStroke();
    //p5.stroke();
    for (int i = 0; i < 6; i++) {
      p5.beginShape(PApplet.QUADS);
      for (int j = 0; j < 4; j++) {
        p5.vertex(transformedVertices[j + 4 * i].x, transformedVertices[j + 4 * i].y, transformedVertices[j + 4 * i].z);
      }
      p5.endShape();
    }
  }

  private void initializeVertices() {
    // cube composed of 6 quads
    //front
    vertices[0] = new Point3D(-width / 2, -height / 2, depth / 2);
    vertices[1] = new Point3D(width / 2, -height / 2, depth / 2);
    vertices[2] = new Point3D(width / 2, height / 2, depth / 2);
    vertices[3] = new Point3D(-width / 2, height / 2, depth / 2);
    //left
    vertices[4] = new Point3D(-width / 2, -height / 2, depth / 2);
    vertices[5] = new Point3D(-width / 2, -height / 2, -depth / 2);
    vertices[6] = new Point3D(-width / 2, height / 2, -depth / 2);
    vertices[7] = new Point3D(-width / 2, height / 2, depth / 2);
    //right
    vertices[8] = new Point3D(width / 2, -height / 2, depth / 2);
    vertices[9] = new Point3D(width / 2, -height / 2, -depth / 2);
    vertices[10] = new Point3D(width / 2, height / 2, -depth / 2);
    vertices[11] = new Point3D(width / 2, height / 2, depth / 2);
    //back
    vertices[12] = new Point3D(-width / 2, -height / 2, -depth / 2);
    vertices[13] = new Point3D(width / 2, -height / 2, -depth / 2);
    vertices[14] = new Point3D(width / 2, height / 2, -depth / 2);
    vertices[15] = new Point3D(-width / 2, height / 2, -depth / 2);
    //top
    vertices[16] = new Point3D(-width / 2, -height / 2, depth / 2);
    vertices[17] = new Point3D(-width / 2, -height / 2, -depth / 2);
    vertices[18] = new Point3D(width / 2, -height / 2, -depth / 2);
    vertices[19] = new Point3D(width / 2, -height / 2, depth / 2);
    //bottom
    vertices[20] = new Point3D(-width / 2, height / 2, depth / 2);
    vertices[21] = new Point3D(-width / 2, height / 2, -depth / 2);
    vertices[22] = new Point3D(width / 2, height / 2, -depth / 2);
    vertices[23] = new Point3D(width / 2, height / 2, depth / 2);
  }
  
}
