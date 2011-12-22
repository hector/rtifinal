/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rtifinal.graphics;

/**
 *
 * @author
 * rata
 */
public class SpinnyCube extends Cube {

  float angleX, angleY, angleZ;
  Point3D[] transformedVertices = new Point3D[24];
// default constructor
  public SpinnyCube() {
  }

// constructor
  public SpinnyCube(float w, float h, float d) {
// call superclass constructor
    super(w, h, d);
  }
// rotation method
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
      rotatedVertices_XAxis[i].y = applet().cos(applet().radians(angleX)) * vertices[i].y - applet().sin(applet().radians(angleX)) * vertices[i].z;
      rotatedVertices_XAxis[i].z = applet().sin(applet().radians(angleX)) * vertices[i].y + applet().cos(applet().radians(angleX)) * vertices[i].z;

// rotation around y-axis
      rotatedVertices_YAxis[i].y = rotatedVertices_XAxis[i].y;
      rotatedVertices_YAxis[i].z = applet().cos(applet().radians(angleY)) * rotatedVertices_XAxis[i].z - applet().sin(applet().radians(angleY)) * rotatedVertices_XAxis[i].x;
      rotatedVertices_YAxis[i].x = applet().sin(applet().radians(angleY)) * rotatedVertices_XAxis[i].z + applet().cos(applet().radians(angleY)) * rotatedVertices_XAxis[i].x;

// rotation around z-axis
      rotatedVertices_ZAxis[i].x = applet().cos(applet().radians(angleZ)) * rotatedVertices_YAxis[i].x - applet().sin(applet().radians(angleZ)) * rotatedVertices_YAxis[i].y;
      rotatedVertices_ZAxis[i].y = applet().sin(applet().radians(angleZ)) * rotatedVertices_YAxis[i].x + applet().cos(applet().radians(angleZ)) * rotatedVertices_YAxis[i].y;
      rotatedVertices_ZAxis[i].z = rotatedVertices_YAxis[i].z;
    }
// update transformedVertices arrays
    transformedVertices = rotatedVertices_ZAxis;
  }
// assign rotation angles for each axis
  public void spinnyRotateX(float angle) {
    angleX = angle;
  }

  public void spinnyRotateY(float angle) {
    angleY = angle;
  }

  public void spinnyRotateZ(float angle) {
    angleZ = angle;
  }

  public void draw() {
// draw cube
    spinnyRotateXYZ();
    applet().fill(p5.color(134));
    //applet().stroke();
    for (int i = 0; i < 6; i++) {
      applet().beginShape(applet().QUADS);
      for (int j = 0; j < 4; j++) {
        applet().vertex(transformedVertices[j + 4 * i].x, transformedVertices[j + 4 * i].y, transformedVertices[j + 4 * i].z);
      }
      applet().endShape();
    }
  }

  public void draw(int[]quadBG){
// draw cube
spinnyRotateXYZ();
    for (int i = 0; i < 6; i++) {
      applet().fill(quadBG[i]);
      applet().beginShape(applet().QUADS);
      for (int j = 0; j < 4; j++) {
        applet().vertex(transformedVertices[j + 4 * i].x, transformedVertices[j + 4 * i].y, transformedVertices[j + 4 * i].z);
      }
      applet().endShape();
    }
  }
}
