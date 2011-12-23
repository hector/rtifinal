package rtifinal;

import oscP5.*;
import netP5.NetAddress;
import processing.core.*;
import rtifinal.instruments.*;
import rtifinal.graphics.*;
import rtifinal.OSC.OSCSendReceive;

public class Main extends PApplet {

  public static PApplet applet;
  int pts = 0;
  public Synthesizer synth;
  OSCSendReceive oscComm;
  

  public void setup() {
    Main.applet = this;
    size(screen.width, screen.height, P3D);
    synth = new Synthesizer();
    oscComm = new OSCSendReceive();
    

  }

  public void draw() {
    background(RGB, 100, 120, 34);
    lights();
    synth.draw();
    oscComm.oscSend();

  }

  public void oscEvent(OscMessage theOscMessage) {
    oscComm.oscEvent(theOscMessage);
  }

  // main method to launch this Processing sketch from computer
  public static void main(String[] args) {
    PApplet.main(new String[]{"rtifinal.Main"});
  }
}