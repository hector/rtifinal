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

public class Main extends PApplet {

  public static Main applet;
  int time, startFrameMillis, pts;
  int Y_AXIS = 1;
  int X_AXIS = 2;
  Synthesizer synth;
  OSCSendReceive oscComm;
  Gradient grad;
  int b1;
  int b2;
  float mx,my;

  

  // main method to launch this Processing sketch from computer
  public static void main(String[] args) {
    PApplet.main(new String[]{"rtifinal.Main"});
    
  }

  public void setup() {
    Main.applet = this;
    size(screen.width/2, screen.height/2, P3D);
    synth = new Synthesizer();
    oscComm = new OSCSendReceive();
    time = 0;
    grad = new Gradient();   
  }

  public void draw() {
    background(0);
    startFrameMillis = millis();
    grad.setGradient(0, 0, width, height,Y_AXIS);
    lights();
    strokeWeight(3);
    if(oscComm.toggle1 == 1) {
      synth.draw();
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
    if(key == '1') {
      synth.addReverb(new Reverb());
    } else if(key == '2') {
      synth.addDelay(new Delay());
    } else if(key == '3') {
      synth.addDistortion(new Distortion());
    } else if(key == '4') {
      synth.removeReverb();
    } else if(key == '5') {
      synth.removeDelay();
    } else if(key == '6') {
      synth.removeDistortion();
    }
  }
  
}
