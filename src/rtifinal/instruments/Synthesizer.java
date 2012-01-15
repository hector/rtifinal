package rtifinal.instruments;

import rtifinal.OSC.Control;

public class Synthesizer extends Instrument {

  public Synthesizer(int size) throws Exception {
    super(size);
    setColor(p5.color(141,211,247)); // Sky blue
    Control octaveFader = layout.getControl("/1/fader1");
    octaveFader.setValues((float)0.5);
  }

}
