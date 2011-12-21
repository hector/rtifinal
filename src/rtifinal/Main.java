package rtifinal;

import processing.core.*;
import rtifinal.instruments.*;
import rtifinal.graphics.*;

public class Main extends PApplet {

  // Rotation of a Custom Cube Around the X-, Y-, and Z-Axes
// custom Cube reference variable
  SpinnyCube c1;
// array to hold different face colors
  int[] quadBG = new int[6];
  public static PApplet applet;

  @Override
  public void setup() {
    Main.applet = this;
    size(screen.width, screen.height, P3D);
    quadBG[0] = color(175, 30, 30, 255);
    quadBG[1] = color(30, 175, 30, 255);
    quadBG[2] = color(30, 30, 175, 255);
    quadBG[3] = color(175, 175, 30, 255);
    quadBG[4] = color(175, 30, 175, 255);
    quadBG[5] = color(175, 87, 30, 255);
    //instantiate cube
    c1 = new SpinnyCube(200, 200, 200);
  }

  @Override
  public void draw() {
    background(100);
    translate(width / 2, height / 2);
    if (mousePressed) {
      //interactive rotation
      c1.spinnyRotateX(mouseY);
      c1.spinnyRotateY(mouseX);
    } else {
      if (mousePressed) {
        //interactive rotation
        c1.spinnyRotateX(mouseY);
        c1.spinnyRotateY(mouseX);
      } else {
        //automatic rotation
        c1.spinnyRotateX(frameCount * PI);
        c1.spinnyRotateY(frameCount * PI / 4);
        c1.spinnyRotateZ(frameCount * PI / 5);
      }
      //draw cube
      noStroke();
      c1.create(quadBG);
    }



  }

  // main method to launch this Processing sketch from computer
  public static void main(String[] args) {
    PApplet.main(new String[]{"rtifinal.Main"});
  }
}