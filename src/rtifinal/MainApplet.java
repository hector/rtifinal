package rtifinal;

import oscP5.*;
import netP5.*;
import processing.core.*;
import rtifinal.instruments.*;
import rtifinal.graphics.*;
import java.util.*;

public class MainApplet extends PApplet {

  public static MainApplet applet;
  static int listeningPort = 9000;
  static int broadcastPort = 12000;
  int time, startFrameMillis;
  float tempo;
  Gradient grad;
  NetAddress pureData;
  OscP5 oscP5 = null;
  ArrayList<Instrument> instruments;
  HashMap<String, Instrument> devices;

  // main method to launch this Processing sketch from computer
  public static void main(String[] args) {
    PApplet.main(new String[]{"rtifinal.MainApplet"});
  }

  @Override
  public void setup() {
    MainApplet.applet = this;
    if(oscP5 == null) oscP5 = new OscP5(this, listeningPort, OscP5.UDP);
    pureData = new NetAddress("127.0.0.1", broadcastPort);
    devices = new HashMap<String, Instrument>();
    instruments = new ArrayList<Instrument>(10);
    frameRate(25);
    grad = new Gradient();
    size(screen.width, screen.height, P3D);
    tempo = 120;
    time = 0;
//    try {
//      Instrument synth = new Synthesizer();
//    } catch (Exception ex) {
//      ex.printStackTrace();
//    }
  }

  @Override
  public void draw() {
    startFrameMillis = millis();
    background(0);
    grad.setGradient(0, 0, width, height, 2);
    lights();
    strokeWeight(3);
    for (Instrument instrument : instruments) {
      instrument.draw();
    }
    time = millis();
  }

  public void oscEvent(OscMessage msg) throws Exception {
    try {
      // Forward all messages to pure data
      //oscP5.send(msg, pureData); 
      String ip = msg.netAddress().address();
      // Handle instrument creation/swaping
      if (msg.checkAddrPattern("/1/push12") && msg.get(0).floatValue() == 1.0) {
        createSynthesizer(ip);
      } else if (msg.checkAddrPattern("/1/push11") && msg.get(0).floatValue() == 1.0) {
        createDrumMachine(ip);
      } else if (msg.checkAddrPattern("/1/push10") && msg.get(0).floatValue() == 1.0) {
        changeInstrument(ip);
      }
      // Send message to the corresponding instrument
      Instrument instrument = devices.get(ip);
      if (instrument != null) {
        instrument.oscEvent(msg);
      }
    } catch(Exception e) {
      e.printStackTrace();
      throw e;
    }
  }

  private void createSynthesizer(String ip) throws Exception {
    Instrument synth = new Synthesizer();
    float w = width * (float)Math.random();
    float h = height * (float)Math.random();
    synth.setPosition(new PVector(w, h, 0));
    synth.setBPM(60);
    devices.put(ip, synth);
    instruments.add(synth);
    sendLayout(synth, ip);
  }

  private void createDrumMachine(String ip) {
    Instrument drums = new DrumMachine();
    float w = width * (float)Math.random();
    float h = height * (float)Math.random();
    drums.setPosition(new PVector(w, h, 0));
    devices.put(ip, drums);
    instruments.add(drums);
    sendLayout(drums, ip);
  }

  private void changeInstrument(String ip) {
    Instrument instrument = devices.get(ip);
    int size = instruments.size();
    int pos = (instrument==null) ? size : instruments.indexOf(instrument);
    int index = pos + 1;
    while (index != pos) {
      if (index >= size) index = 0;
      else {
        instrument = instruments.get(index);
        if (!devices.containsValue(instrument)) {
          devices.put(ip, instrument);
          sendLayout(instrument, ip);
          index = pos;
        } else index += 1;
      }
    }
  }

  private void sendLayout(Instrument instrument, String ip) {
    ArrayList<OscMessage> msgs = instrument.oscLayoutMessages();
    oscSend(msgs, ip);
  }

  public void oscSend(OscMessage msg, NetAddress dest) {
    oscP5.send(msg, dest);
  }

  public void oscSend(ArrayList<OscMessage> msgs, String ip) {
    NetAddress dest = new NetAddress(ip, broadcastPort);
    OscBundle bundle = new OscBundle();
    for (OscMessage msg : msgs) bundle.add(msg);
    oscP5.send(bundle, dest);
  }

  // Returns the number of milliseconds since the last draw()
  // This value is fixed during the draw function (calculates time at start of frame)
  public int spentTime() {
    return startFrameMillis - time;
  }

}
