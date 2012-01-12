package rtifinal;

import java.awt.DisplayMode;
import java.awt.Frame;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import oscP5.*;
import netP5.*;
import processing.core.*;
import rtifinal.instruments.*;
import rtifinal.graphics.*;
import java.util.*;
import rtifinal.OSC.Fader;

public class MainApplet extends PApplet {

  public static MainApplet applet;
  static int listeningPort = 9000;
  static int broadcastPort = 12000;
  int time, startFrameMillis, instrumentSize;
  float tempo, alpha, instrumentScale;
  Gradient grad;
  NetAddress pureData;
  OscP5 oscP5 = null;
  ArrayList<Instrument> instruments;
  ArrayList<Synthesizer> synthesizers;
  ArrayList<DrumMachine> drumMachines;
  HashMap<String, Instrument> devices;
  List<String> clients;
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
    frameRate(60);
    grad = new Gradient();
    size(screen.width, screen.height, OPENGL);
    hint(ENABLE_OPENGL_4X_SMOOTH);
    noCursor();
    tempo = 120;
    initColors(); 
    alpha = 75;
    instrumentSize = height/3;
    instrumentScale = 1;
    time = 0;
  }  
  
  private void initColors() {
    colors = new int[4];
    colors[0] = color(100,100,255); // blue
    colors[1] = color(100,255,100); // green
    colors[2] = color(255,100,100); // red
    colors[3] = color(255,255,0); // yellow
  }

  @Override
  public void draw() {
    try {
      startFrameMillis = millis();
      background(50);
      grad.draw();
      lights();
      // Draw instruments
      strokeWeight(3);
      for (Instrument instrument : instruments) {
        instrument.draw();
      }
      // Draw client coloured circles
      Instrument inst;
      float size;
      stroke(255, alpha);
      for (int i=0; i < clients.size(); i++) {
        inst = devices.get(clients.get(i));
        fill(colors[i], alpha);
        size = inst.getSize()*inst.getScale()*2;
        ellipse(inst.getPosition().x, inst.getPosition().y, size, size);
      }
      time = millis();
    } catch(ConcurrentModificationException cme) {}
  }

  public void oscEvent(OscMessage msg) throws Exception {
    try {
      String ip = msg.netAddress().address();
      if("127.0.0.1".equals(ip)) {
        Instrument instr = instrumentFromPattern(msg);
        instr.bump();
      } else {
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
          msg.setAddrPattern(ip2Pattern(ip) + msg.addrPattern());
          oscSendPD(msg);
        }        
      }
    } catch(ConcurrentModificationException cme) {
    } catch(Exception e) {
      e.printStackTrace();
      throw e;
    }
  }

  private void createSynthesizer(String ip) throws Exception {
    if(synthesizers.size() >= 4) return;
    Synthesizer synth = new Synthesizer(instrumentSize);
    addInstrument(ip, synth);
    synthesizers.add(synth);
  }

  private void createDrumMachine(String ip) throws Exception {
    if(drumMachines.size() >= 4) return;
    DrumMachine drums = new DrumMachine(instrumentSize);
    addInstrument(ip, drums);
    drumMachines.add(drums);
  }

  private void addInstrument(String ip, Instrument instrument) {
    instrument.setInitialPosition(emptyPosition());
    instrument.setBPM(tempo);
    devices.put(ip, instrument);
    addClient(ip);
    instruments.add(instrument);
    scaleInstruments();
    sendLayout(instrument, ip);
  }

  private PVector emptyPosition() {
    PVector position = new PVector(width/2, height/2, 0);
    float margin = instrumentSize*instrumentScale;
    float mWidth = width - 2*margin;
    float mHeight = height - 2*margin;
    float distance;
    boolean found = true;
    int i = 0;
    while(i < 100) {
      position = new PVector(mWidth * (float)Math.random() + margin, 
                            mHeight * (float)Math.random() + margin, 0);
      for(Instrument instr : instruments) {
        distance = (float) (instr.getSize() * instr.getScale() * 1.5);
        if (position.dist(instr.getPosition()) < distance) {
          found = false;
          break;
        }
      }
      if (found) i = 100;
      else found = true;
      i++;
    }
    return position;
  }
  
  private void scaleInstruments() {
    instrumentScale *= (float)0.9;
    for(Instrument instr : instruments) instr.setScale(instrumentScale);
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
  
  private String ip2Pattern(String ip) {
    Instrument instr = devices.get(ip);
    int num = drumMachines.indexOf(instr) + 1;
    if (num == 0) num = synthesizers.indexOf(instr) + 5;
    return "/"+num;
  }
  
  private Instrument instrumentFromPattern(OscMessage msg) {
    String pattern = msg.addrPattern();
    int instrNum = new Integer(pattern.substring(1,2));
    if(instrNum > 4) return synthesizers.get(instrNum-5);
    else return drumMachines.get(instrNum-1);
  } 
  
  public void globalTempo(float tempo, DrumMachine drums) {
    float bpm = tempo * 240; // Tempo from touchOSC slider to BPM
    this.tempo = bpm; // Save to global tempo
    for(Instrument instr : instruments) {
      instr.setBPM(bpm); // Set bpm to all instruments
      if(instr.getClass().equals(drums.getClass()) && instr != drums) {
        instr.getLayout().getControl("/1/fader1").setValues(tempo); // Set tempo to drumMachine layouts
      }
    }
    OscMessage msg;
    for(String ip : clients) {
      Instrument instr = devices.get(ip);
      if(instr.getClass().equals(drums.getClass()) && instr != drums) {
        msg = instr.getLayout().getControl("/1/fader1").oscMessage();
        oscSend(msg, ip); // Send new tempo to clients with drumMachines
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
  
  public void oscSend(OscMessage msg, String ip) {
    NetAddress dest = new NetAddress(ip, broadcastPort);
    oscSend(msg, dest);
  }  
  
  public void oscSendPD(OscMessage msg) {
    oscP5.send(msg, pureData); 
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
