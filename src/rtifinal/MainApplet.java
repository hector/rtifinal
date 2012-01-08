package rtifinal;

import oscP5.*;
import netP5.*;
import processing.core.*;
import rtifinal.instruments.*;
import rtifinal.graphics.*;
import java.util.*;
import java.util.logging.Level;

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
  ArrayList<Synthesizer> synthesizers;
  ArrayList<DrumMachine> drumMachines;
  HashMap<String, Instrument> devices;
  ArrayList<String> clients;
  int[] colors;

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
    clients = new ArrayList<String>(4);
    instruments = new ArrayList<Instrument>(8);
    synthesizers = new ArrayList<Synthesizer>(4);
    drumMachines = new ArrayList<DrumMachine>(4);
    frameRate(25);
    grad = new Gradient();
    size(screen.width, screen.height, P3D);
    tempo = 120;
    initColors();
    time = 0;
  }
  
  private void initColors() {
    colors = new int[4];
    int trans = 75;
    colors[0] = color(255,0,0,trans);
    colors[1] = color(0,255,0,trans);
    colors[2] = color(0,0,255,trans);
    colors[3] = color(255,255,0,trans);
  }

  @Override
  public void draw() {
    try {
      startFrameMillis = millis();
      background(0);
      //grad.setGradient(instruments,0, 0, 2);
      lights();
      // Draw instruments
      strokeWeight(3);
      for (Instrument instrument : instruments) {
        instrument.draw();
      }
      // Draw client coloured circles
      Instrument inst;
      strokeWeight(0);
      for (int i=0; i < clients.size(); i++) {
        inst = devices.get(clients.get(i));
        fill(colors[i]);
        ellipse(inst.getPosition().x, inst.getPosition().y, inst.getSize()*2, inst.getSize()*2);
      }
      time = millis();
    } catch(ConcurrentModificationException cme) {}
  }

  public void oscEvent(OscMessage msg) throws Exception {
    try {
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
        // Forward all messages to pure data
        msg.setAddrPattern(instrPattern(ip) + msg.addrPattern());
        oscP5.send(msg, pureData); 
      }
    } catch(ConcurrentModificationException cme) {
    } catch(Exception e) {
      e.printStackTrace();
      throw e;
    }
  }

  private void createSynthesizer(String ip) throws Exception {
    if(synthesizers.size() >= 4) return;
    Synthesizer synth = new Synthesizer();
    float w = width * (float)Math.random();
    float h = height * (float)Math.random();
    synth.setPosition(new PVector(w, h, 0));
    synth.setBPM(tempo);
    devices.put(ip, synth);
    addClient(ip);
    instruments.add(synth);
    synthesizers.add(synth);
    sendLayout(synth, ip);
  }

  private void createDrumMachine(String ip) throws Exception {
    if(drumMachines.size() >= 4) return;
    DrumMachine drums = new DrumMachine();
    float w = width * (float)Math.random();
    float h = height * (float)Math.random();
    drums.setPosition(new PVector(w, h, 0));
    drums.setBPM(tempo);
    devices.put(ip, drums);
    addClient(ip);
    instruments.add(drums);
    drumMachines.add(drums);
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
          addClient(ip);
          sendLayout(instrument, ip);
          index = pos;
        } else index += 1;
      }
    }
  }
  
  private void addClient(String ip) {
    if(!clients.contains(ip)) clients.add(ip);
  }
  
  private String instrPattern(String ip) {
    Instrument instr = devices.get(ip);
    int num = drumMachines.indexOf(instr) + 1;
    if (num == 0) num = synthesizers.indexOf(instr) + 4;
    return "/"+num;
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
//    OscBundle bundle = new OscBundle();
    try {
      for (OscMessage msg : msgs) {
        oscSend(msg, dest);
        Thread.sleep(1);
      }
    } catch (Exception e) {}
      //bundle.add(msg);
//    oscP5.send(bundle, dest);
  }

  // Returns the number of milliseconds since the last draw()
  // This value is fixed during the draw function (calculates time at start of frame)
  public int spentTime() {
    return startFrameMillis - time;
  }

}
