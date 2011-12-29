package rtifinal;

import java.awt.Color;
import oscP5.*;
import netP5.NetAddress;
import processing.core.*;
import rtifinal.*;
import rtifinal.effects.*;
import rtifinal.instruments.*;
import rtifinal.graphics.*;
import rtifinal.OSC.OSCSendReceive;
import java.util.Iterator;

public class Main extends PApplet {

  public static Main applet;
  int time, startFrameMillis, pts;
  int Y_AXIS = 1;
  int X_AXIS = 2;
  Synthesizer synth1, synth2, synth3, synth4;
  OSCSendReceive oscComm = new OSCSendReceive();
  Gradient grad;
  int b1;
  int b2;
  float mx, my;

  // main method to launch this Processing sketch from computer
  public static void main(String[] args) {
    PApplet.main(new String[]{"rtifinal.Main"});

  }

  public void setup() {
    Main.applet = this;
    size(screen.width/2, screen.height/2, P3D);
    synth1 = new Synthesizer();
    synth2 = new Synthesizer();
    synth3 = new Synthesizer();
    synth4 = new Synthesizer();
    time = 0;
    grad = new Gradient();
  }

  public void draw() {
    background(0);
    startFrameMillis = millis();
    grad.setGradient(0, 0, width, height, Y_AXIS);
    lights();
    strokeWeight(3);
    if (oscComm.toggle1 == 1 && oscComm.hashMap.containsKey(1)) {
      synth1.draw();
      fill(134,255,23);
      noStroke();
      pushMatrix();
      int sl = height / 20;
      translate(sl * 2, sl * 5, 0);
      sphere(sl);
      popMatrix();
    }
    if (oscComm.toggle1 == 1 && oscComm.hashMap.containsKey(2)) {
      synth2.draw();
      fill(100,45,234);
      noStroke();
      pushMatrix();
      int sl = height / 20;
      translate(sl * 2, sl * 2, 0);
      sphere(sl);
      popMatrix();
    }
    if (oscComm.toggle1 == 1 && oscComm.hashMap.containsKey(3)) {
      synth3.draw();
    }
    if (oscComm.toggle1 == 1 && oscComm.hashMap.containsKey(4)) {
      synth4.draw();
    }
    oscComm.oscSend();
    time = millis();


  }

  public void oscEvent(OscMessage theOscMessage) {
    oscComm.oscEvent(theOscMessage);

  }

  // Returns the number of milliseconds since the last draw()
  // This value is fixed during the draw function (calculates time at start of frame)
  public int spentTime() {

    return startFrameMillis - time;
  }

  public void keyPressed() {
//    if (key == CODED) {
//      if (keyCode == UP) {
//        
//      } else if (keyCode == DOWN) {
//        if (pts > -1000) {
//          pts = pts - 10;
//        }
//      }
//    }
    if (key == '1') {
      synth1.addReverb(new Reverb());
    } else if (key == '2') {
      synth1.addDelay(new Delay());
    } else if (key == '3') {
      synth1.addDistortion(new Distortion());
    } else if (key == '4') {
      synth1.removeReverb();
    } else if (key == '5') {
      synth1.removeDelay();
    } else if (key == '6') {
      synth1.removeDistortion();
    }
  }
}
