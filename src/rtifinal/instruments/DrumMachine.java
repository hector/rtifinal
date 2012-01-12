package rtifinal.instruments;

import rtifinal.OSC.Control;

public class DrumMachine extends Instrument {
  
  public DrumMachine(int size) throws Exception {
    super(size);
    setColor(p5.color(254,249,157)); // yellow
    Control tempoFader = layout.getControl("/1/fader1");
    tempoFader.setValues((float)0.5);
    layout.getControl("/1/fader1").map(p5, "globalTempo", this);
  }  

}
