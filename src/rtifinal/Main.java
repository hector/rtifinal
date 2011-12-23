package rtifinal;

import oscP5.*;
import netP5.NetAddress;
import processing.core.*;
import rtifinal.instruments.*;
import rtifinal.graphics.*;
import rtifinal.OSC.OSCSendReceive;

public class Main extends PApplet {

  public static Main applet;
  int time, startFrameMillis, pts;
  Synthesizer synth;
  OSCSendReceive oscComm;

  // main method to launch this Processing sketch from computer
  public static void main(String[] args) {
    PApplet.main(new String[]{"rtifinal.Main"});
  }

  public void setup() {
    Main.applet = this;
    size(screen.width, screen.height, P3D);
    synth = new Synthesizer();
    oscComm = new OSCSendReceive();
    time = 0;
  }

  public void draw() {
    startFrameMillis = millis();
    background(RGB, 100, 120, 34);
    lights();
    strokeWeight(3);
    synth.draw();
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

}