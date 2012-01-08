package rtifinal.graphics;

import processing.core.*;

public abstract class Drawable extends Processing {

  int color;
  PVector position; // center of the drawable
  float alpha, angle, bpm, scale, tempScale;
  boolean sequencer, visible;

  public Drawable() {
    super();
    color = 255;
    alpha = 255;
    position = new PVector(0, 0, 0);
    angle = 0;
    bpm = 0;
    scale = 1;
    sequencer = false;
    visible = true;
  }

  public int getColor() {
    return color;
  }

  public void setColor(int color) {
    this.color = color;
  }

  public void setAlpha(float alpha) {
    this.alpha = alpha;
  }

  public PVector getPosition() {
    return position;
  }

  public void setPosition(PVector position) {
    this.position = position;
  }

  public void draw() {
    if(!visible) return;
    p5.pushMatrix();
    translate();
    rotate();
    scale();
    selfDraw();
    p5.popMatrix();
  }

  // Place here the specific code for subclasses drawing
  protected void selfDraw() {
    if(!visible) return;
    p5.fill(color, alpha);
    p5.stroke(color, alpha);
  }

  protected void translate() {
    p5.translate(position.x, position.y, position.z);
  }

  protected void rotate() {
    if (bpm != 0 && sequencer) {
      angle += (p5.spentTime() * bpm * PI ) / 120000 ;
    }
    p5.rotateX(angle);
  }
  
  protected void scale() {
    float s = scale;
    if(tempScale > scale) {
      s = tempScale;
      tempScale = (float) (tempScale*0.9);
    }
    p5.scale(s);
  }
  
  public void bump() {
    tempScale = (float) (scale * 1.25);
  }

  public void setBPM(float bpm) {
    this.bpm = bpm;
  }
  
  public void sequencer(boolean on) {
    this.sequencer = on;
  }
  
  public void visible(boolean visible) {
    this.visible = visible;
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
