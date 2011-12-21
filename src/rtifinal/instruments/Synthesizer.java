package rtifinal.instruments;

import rtifinal.effects.*;

public class Synthesizer extends Instrument {

  protected Delay delay;
  protected Distortion distortion;
  protected Reverb reverb;

  public Synthesizer() {
    delay = null;
    distortion = null;
    reverb = null;
  }

  public void draw() {
    applet().box(150, 150, 150);
  }
}
