package rtifinal.graphics;

import processing.core.*;
import rtifinal.Main;

public abstract class Drawable extends Processing {

  int color;
  PVector position; // center of the drawable
  float angle, bpm;
  float a = 0;

  public Drawable() {
    color = 255;
    position = new PVector(0, 0, 0);
    angle = bpm = 0;
  }

  public int getColor() {
    return color;
  }

  public void setColor(int color) {
    this.color = color;
  }

  public PVector getPosition() {
    return position;
  }

  public void setPosition(PVector position) {
    this.position = position;
  }

  protected static float cos(float angle) {
    return PApplet.cos(angle);
  }

  protected static float sin(float angle) {
    return PApplet.sin(angle);
  }

  protected static float radians(float degrees) {
    return PApplet.radians(degrees);
  }

  public void draw() {
    p5.pushMatrix();
    translateMouse();
    rotate();
    selfDraw();
    p5.popMatrix();
  }

  // Place here the specific code for subclasses drawing
  protected void selfDraw() {
    p5.fill(color);
    p5.stroke(color);    
  }

  protected void translate() {
    p5.translate(position.x, position.y, position.z);
  }

  protected void rotate() {
    if (bpm != 0) {
      angle += p5.spentTime() * (bpm * PI / 10) / 1000;
    }
    p5.rotateX(angle);
  }

  public void setBPM(int bpm) {
    this.bpm = bpm;
  }

  // FUNCTIONS FOR TESTING
  protected void rotateMouse() {
    p5.rotateX(radians(p5.mouseX) % (2 * PI));
    p5.rotateY(radians(p5.mouseY) % (2 * PI));
  }

  protected void translateMouse() {
    p5.translate(p5.mouseX, p5.mouseY, 0);
  }

  protected void translateCenter() {
    p5.translate(p5.width / 2f, p5.height / 2f, position.z);
  }
}